/*
 * Copyright 2017 Pablo Barrientos (pablo.barrientos@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.funky.validation

import org.funky.collections.cons
import org.funky.either.Either
import org.funky.option.Option

typealias NonEmptyList<L> = List<L>

/**
 * Custom implementation of Validation<NonEmptyList>.
 * ValidationNel is similar to Either but it will accumulate all errors on the left.
 *
 * An instance of `ValidationNEL` is an instance of either Failure or Success.
 */
sealed class ValidationNEL<L, R> {

    companion object {
        /**
         * Constructor of Failure from a single value.
         */
        fun <L, R> failure(error: L): ValidationNEL<L, R> = Failure(listOf(error))

        /**
         * Constructor of Failure from a list of values. If the list is empty, it will throw an IllegalArgumentException
         */
        @Throws(IllegalArgumentException::class)
        fun <L, R> failure(nel: NonEmptyList<L>): ValidationNEL<L, R> =
                if (nel.isEmpty()) throw IllegalArgumentException("Empty list") else Failure(nel)

        /**
         * Constructor of Success from a value
         */
        fun <L, R> success(value: R): ValidationNEL<L, R> = Success(value)
    }

    /**
     * Kotlin projection of ValidationNEL's first component (failure). Returns null id the instance is Success.
     */
    operator abstract fun component1(): NonEmptyList<L>?

    /**
     * Kotlin projection of ValidationNEL's second component (success). Returns null id the instance is Failure.
     */
    operator abstract fun component2(): R?

    /**
     * Returns True if the given value is a Success, False otherwise.
     */
    abstract fun isSuccess(): Boolean

    /**
     * Returns True if the given value is a Failure, False otherwise.
     */
    abstract fun isFailure(): Boolean

    /**
     * Returns an instance of Eiher that is equivalent to the receiver (Right <-> Success, Left <-> Failure)
     */
    fun toEither(): Either<NonEmptyList<L>, R> = fold( { Either.left(it) }, { Either.right(it) })

    /**
     * Catamorphism for ValidationNEL.
     * Applies fl if this is a Failure or fr if this is Success.
     */
    inline fun <A> fold(fl: (NonEmptyList<L>) -> A, fr: (R) -> A): A = when (this) {
        is Failure -> fl(l)
        is Success -> fr(r)
    }

    /**
     * Returns the given function applied to the value if the receiver is Success or returns a Failure if it is Failure.
     */
    inline fun <A> flatMap(f: (R) -> ValidationNEL<L, A>): ValidationNEL<L, A> =
        fold( { failure<L, A>(it) }, { f(it) })

    /**
     * The given function is applied if this is a Success.
     */
    inline fun <A> map(f: (R) -> A): ValidationNEL<L, A> = flatMap { Success<L, A>(f(it)) }

    /**
      * Returns `false` if Failure or returns the result of the application of the given predicate to the Success value.
      */
    inline fun exists(p : (R) -> Boolean) : Boolean = fold( { false }, { r: R -> p(r) })

    /**
     * Executes the given side-effecting function if this is a Failure.
     */
    inline fun forEach(f: (R) -> Unit) {
        when (this) {
            is Success -> f(this.r)
        }
    }

    /**
     * Returns the value from this Failure or the given argument if this is Success.
     */
    infix fun getOrElse(default: () -> R): R = fold( { default() }, { it })

    /**
    * Returns the value from this Success or the given argument if this is Failure.
    */
    infix fun orElse(alternative: () -> ValidationNEL<L, R>): ValidationNEL<L, R> =
            fold( { _ -> alternative() }, { this })

    /**
     * Similar to orElse, but the argument is evaluated before.
     */
    infix fun or(value: ValidationNEL<L, R>): ValidationNEL<L, R> = fold( { value }, { this })

    class Failure<L, R> internal constructor(val l: NonEmptyList<L>) : ValidationNEL<L, R>() {
        override fun isSuccess() = false
        override fun isFailure() = true

        override fun component1(): NonEmptyList<L> = l
        override fun component2(): R? = null

        override fun equals(other: Any?): Boolean = when (other) {
            is Failure<*, *> -> l == other.l
            else -> false
        }

        override fun hashCode(): Int = l.hashCode()

        override fun toString(): String = "ValidationNEL.Failure($l)"
    }

    class Success<L, R>(val r: R) : ValidationNEL<L, R>() {
        override fun isSuccess() = true
        override fun isFailure() = false

        override fun component1(): NonEmptyList<L>? = null
        override fun component2(): R = r

        override fun equals(other: Any?): Boolean = when (other) {
            is Success<*, *> -> r == other.r
            else -> false
        }

        override fun hashCode(): Int = r!!.hashCode()

        override fun toString(): String = "ValidationNEL.Success($r)"
    }
}

//extension functions for other types

/**
 * Builder of Success from any type
 */
fun <L, R> R.toSuccessNel(): ValidationNEL<L, R> =
        ValidationNEL.success(this)

/**
 * Builder of Failure from any type
 */
fun <L, R> L.toFailureNel(): ValidationNEL<L, R> =
        ValidationNEL.failure(this)

/**
 * Builder of Failure from a pair. It takes the first component of the pair to build the value of Failure.
 */
fun <L, R> Pair<L, R>.toFailureNel(): ValidationNEL<L, R> = this.component1().toFailureNel()

/**
 * Builder of Success from a pair. It takes the second component of the pair to build the value of Success.
 */
fun <L, R> Pair<L, R>.toSuccessNel(): ValidationNEL<L, R> = this.component2().toSuccessNel()

/**
 * Wraps an action in a ValidationNEL. In case the action execution throws an exception it returns a Failure, otherwise
 * it returns Success with the result of action.
 */
inline fun <T> validationNelTry(body: () -> T): ValidationNEL<Throwable, T> = try {
    body().toSuccessNel()
} catch (t: Throwable) {
    t.toFailureNel()
}

/**
 * Builder of Success from Option type
 */
inline fun <L, R> Option<R>.toSuccess(failure: () -> L): ValidationNEL<L, R> =
        fold({ failure().toFailureNel() }, { get().toSuccessNel() })

/**
 * Builder of Failure from Option type. In case the receiver is non-empty, success will be its value.
 */
inline fun <L, R> Option<L>.toFailure(success: () -> R): ValidationNEL<L, R> =
        fold({ success().toSuccessNel() }, { get().toFailureNel() })

/**
 * Embeds pure values in ValidationNEL. Lift a value.
 */
fun <L, R> R.pure(): ValidationNEL<L, R> = this.toSuccessNel()

/**
 * Sequential application of ValidationNEL as applicative functor.
 */
infix fun <L, R, A> ValidationNEL<L, (A) -> R>.ap(valX: ValidationNEL<L, A>): ValidationNEL<L, R> =
    if (this.isFailure() && valX.isFailure()) ValidationNEL.Failure<L, R>(this.component1()!! + valX.component1()!!)
    else flatMap { valX.map(it) }

/**
 * Sequential application of ValidationNEL as applicative functor.
 */
fun <L, A, B, R> ValidationNEL<L, (A, B) -> R>.ap2(valX: ValidationNEL<L, A>, valY: ValidationNEL<L, B>): ValidationNEL<L, R> =
        if (this.isFailure() && valX.isFailure() && valY.isFailure())
            ValidationNEL.Failure<L, R>(this.component1()!! + valX.component1()!! + valY.component1()!!)
        else if (this.isFailure() && valX.isFailure())
            ValidationNEL.Failure<L, R>(this.component1()!! + valX.component1()!!)
        else if (this.isFailure() && valY.isFailure())
            ValidationNEL.Failure<L, R>(this.component1()!! + valY.component1()!!)
        else if (valX.isFailure() && valY.isFailure())
            ValidationNEL.Failure<L, R>(valX.component1()!! + valY.component1()!!)
        else
            this.flatMap { f ->
                valX.flatMap { a ->
                valY.map { b -> f (a, b )
            } } }

/**
 * Lifts a function to actions.
 */
fun <L, A, R> ((A) -> R).liftA(): (ValidationNEL<L, A>) -> ValidationNEL<L, R> = { it.map(this) }

/**
 * Lifts a binary function to actions.
 */
fun <L, A, B, C> ((A, B) -> C).liftA2(): (ValidationNEL<L, A>, ValidationNEL<L, B>) -> ValidationNEL<L, C> =
        { valX, valY -> toSuccessNel<L, ((A, B) -> C)>().ap2(valX, valY) }

/**
 * Transforms a nested ValidationNEL, ie, a ValidationNEL of type ValidationNEL<L, ValidationNEL<L, R>>, into an un-nested
 * ValidationNEL, ie, an ValidationNEL of type ValidationNEL<L, R>.
 */
fun <L, R> ValidationNEL<L, ValidationNEL<L, R>>.flatten(): ValidationNEL<L, R> =
        fold({ ValidationNEL.failure(it) }, { it })

/**
* Maps each element of the list of ValidationNEL to an action, evaluates these actions from left to right, and collects the
* results, accumulating errors on the left.
 */
fun <L, A, B> List<A>.traverseA(f: (A) -> ValidationNEL<L, B>): ValidationNEL<L, List<B>> =
        foldRight(ValidationNEL.success(emptyList()),
                { a: A, tail: ValidationNEL<L, List<B>> ->
                    val head = f(a)
                    val liftedCons: ValidationNEL<L, (B, List<B>) -> List<B>> =
                            { b: B, lb: List<B> -> b cons lb }.toSuccessNel()
                    liftedCons.ap2(head, tail)
                }
        )

/**
 * Evaluates each action in the structure from left to right, and collects the results. It also gathers together
 * applicative effects.
 * It "flips" List<ValidationNEL<L, A>> to ValidationNEL<L, List<A>>.
 *
 * This function is interesting to accumulate validation errors (or the list of results) from a list of ValidationNEL.
 *
 * E.g.: validate name and surname, lift Person's constructor to ValidationNEL and get a ValidationNel<String, Person>
 *
 * val validationNameNotEmpty: ValidationNEL<String, String> = ... some function validating the person's name and returning it
 * val validationSurnameNotEmpty: ValidationNEL<String, String> = ... some function validating the person's surname and returning it
 * val personOrErrors: ValidationNel<String, Person> =
  *    ::Person.liftA2(
 *           listOf(validationNameNotEmpty, validationSurnameNotEmpty).sequence)
 */
fun <L, A> List<ValidationNEL<L, A>>.sequence(): ValidationNEL<L, List<A>> = traverseA { it }

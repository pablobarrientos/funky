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
package org.funky.either

import org.funky.option.Option
import org.funky.util.identity
import org.funky.validation.ValidationNEL

/**
 * Represents a value of one of two possible types (a disjoint union.)
 * An instance of `Either` is an instance of either Left or Right.
 *
 * The Either type is sometimes used to represent a value which is either correct or an error.
 *  A common use of `Either` is as an alternative to Option for dealing with possibly missing values. In this usage,
  * None is replaced with a Left which can contain useful information.
 *  Right takes the place of Some. Convention dictates that `Left` is used for failure and `Right` is used for success.
 *
 *
 *  For example, you could use `Either<String, Int>` to indicate whether a received input is a `String` or an `Int`.
 *
 */
sealed class Either<L, R> {

    companion object {
        /**
         * Constructor of left.
         */
        fun <L, R> left(left: L): Either<L, R> = Either.Left(left)
        /**
         * Constructor of right.
         */
        fun <L, R> right(right: R): Either<L, R> = Either.Right(right)
    }

    /**
     * Kotlin projection of Either's first component (left). Returns null id the instance is Right.
     */
    operator abstract fun component1(): L?

    /**
     * Kotlin projection of Either's second component (right). Returns null id the instance is Left.
     */
    operator abstract fun component2(): R?

    /**
     * Returns True if the given value is a Left-value, False otherwise.
     */
    abstract fun isLeft(): Boolean

    /**
     * Returns True if the given value is a Right-value, False otherwise.
     */
    abstract fun isRight(): Boolean

    /**
     * Catamorphism for Either.
     * Applies isLeft if this is a Left or isRight if this is Right.
     */
    inline fun <A> fold(isLeft: (L) -> A, isRight: (R) -> A): A = when (this) {
        is Either.Left -> isLeft(l)
        is Either.Right -> isRight(r)
    }

    /**
     * Returns the given function applied to the value if the receiver is Right or returns a Left if it is Left.
     */
    inline fun <A> flatMap(f: (R) -> Either<L, A>): Either<L, A> =
            fold({ Either.Left<L, A>(it) }, { f(it) })

    /**
     * The given function is applied if this is a Right.
     */
    inline fun <A> map(f: (R) -> A): Either<L, A> = flatMap { Either.Right<L, A>(f(it)) }

    /**
     * If this is a `Left`, then return the left value in `Right` or vice versa.
     */
    fun swap(): Either<R, L> = fold(::Right, ::Left)

    /**
     * Returns an instance of ValidationNEL that is equivalent to the receiver (Right <-> Success, Left <-> Failure)
     */
    fun toValidationNEL(): ValidationNEL<L, R> = fold( { ValidationNEL.failure<L, R>(it) }, { ValidationNEL.success<L, R>(it) })

    /**
     * Returns `false` if Left or returns the result of the application of the given predicate to the Right value.
     */
    inline fun exists(p: (R) -> Boolean): Boolean = fold( { false }, { r: R -> p(r) })

    /**
     * Executes the given side-effecting function if this is a Left.
     */
    inline fun forEach(f: (R) -> Unit) {
        when (this) {
            is Either.Right -> f(this.r)
        }
    }

    /**
     * Returns the value from this Left or the given argument if this is a Right.
     */
    infix fun getOrElse(default: () -> R): R = fold( { default() }, { it })

    /**
     * Returns the value from this Right or the given argument if this is a Left.
     */
    infix fun orElse(alternative: () -> Either<L, R>): Either<L, R> = fold( { _ -> alternative() }, { this })

    /**
     * Similar to orElse, but the argument is evaluated before.
     */
    infix fun or(value: Either<L, R>): Either<L, R> = fold( { value }, { this })

    class Left<L, R>(val l: L) : Either<L, R>() {
        override fun isLeft(): Boolean = true
        override fun isRight(): Boolean = false

        override fun component1(): L = l
        override fun component2(): R? = null

        override fun equals(other: Any?): Boolean = when (other) {
            is Either.Left<*, *> -> l == other.l
            else -> false
        }

        override fun hashCode(): Int = l!!.hashCode()

        override fun toString(): String = "Either.Left($l)"
    }

    class Right<L, R>(val r: R) : Either<L, R>() {
        override fun isLeft(): Boolean = false
        override fun isRight(): Boolean = true

        override fun component1(): L? = null
        override fun component2(): R = r

        override fun equals(other: Any?): Boolean = when (other) {
            is Either.Right<*, *> -> r == other.r
            else -> false
        }

        override fun hashCode(): Int = r!!.hashCode()

        override fun toString(): String = "Either.Right($r)"
    }
}

//extension functions for other types
/**
 * In case both components of Either are equal, merge returns the A and removes the Either.
 */
fun <A> Either<A, A>.merge(): A = this.fold(identity(), identity())

/**
 * Builder of Left from any type
 */
fun <L, R> L.toLeft(): Either<L, R> = Either.left<L, R>(this)

/**
 * Builder of Right from any type
 */
fun <L, R> R.toRight(): Either<L, R> = Either.right<L, R>(this)

/**
 * Builder of Left from a pair. It takes the first component of the pair to build the value of Left.
 */
fun <L, R> Pair<L, R>.toLeft(): Either<L, R> = Either.Left(this.component1())

/**
 * Builder of Right from a pair. It takes the second component of the pair to build the value of Right.
 */
fun <L, R> Pair<L, R>.toRight(): Either<L, R> = Either.Right(this.component2())

/**
 * Wraps an action in an Either. In case the action execution throws an exception it returns a Left, otherwise it
 * returns a Right with the result of action.
 */
inline fun <T> eitherTry(action: () -> T): Either<Throwable, T> = try {
    Either.Right(action())
} catch (t: Throwable) {
    Either.Left(t)
}

/**
 * Builder of Right from Option type
 */
inline fun <L, R> Option<R>.toRight(left: () -> L): Either<L, R> =
        fold({ Either.Left(left()) }, { Either.Right(get()) })

/**
 * Builder of Left from Option type. In case the receiver is Right, right will be its value.
 */
inline fun <L, R> Option<L>.toLeft(right: () -> R): Either<L, R> =
        fold({ Either.Right(right()) }, { Either.Left(get()) })

/**
 * Embed pure values in Either. Lift a value.
 */
fun <L, R> R.pure(): Either<L, R> = Either.right(this)

/**
 * Sequential application of Either as applicative functor.
 */
infix fun <L, R, A> Either<L, (A) -> R>.ap(eitherR: Either<L, A>): Either<L, R> = flatMap { eitherR.map(it) }

/**
 * Sequential application of Either as applicative functor.
 */
fun <L, A, B, R> Either<L, (A, B) -> R>.ap2(eitherA: Either<L, A>, eitherB: Either<L, B>): Either<L, R> =
        this.flatMap { f ->
            eitherA.flatMap { a ->
            eitherB.map { b -> f(a, b )
        } } }

/**
 * Lifts a function to actions.
 */
fun <L, A, R> ((A) -> R).liftA(): (Either<L, A>) -> Either<L, R> = { toRight<L, ((A) -> R)>().ap(it) }

/**
 * Lifts a binary function to actions.
 */
fun <L, A, B, R> ((A, B) -> R).liftA2(): (Either<L, A>, Either<L, B>) -> Either<L, R> =
        { eitherA, eitherB -> this.toRight<L, ((A, B) -> R)>().ap2(eitherA, eitherB) }
/**
 * Transforms a nested Either, ie, a Either of type Either<L, Either<L, R>>, into an un-nested Either, ie, an Either of type Either<L, R>.
 */
fun <L, R> Either<L, Either<L, R>>.flatten(): Either<L, R> = fold({ Either.left(it) }, { it })

/**
* Maps each element of the list of Either to an action, evaluates these actions from left to right. It returns the first
* left in case there's at least one in the list. It is important to note that it evaluates all the elements of the list.
 */
inline fun <L, A, B> List<A>.traverseA(f: (A) -> Either<L, B>): Either<L, List<B>> =
        foldRight(Either.right(emptyList()),
                { a: A, eitherAcc: Either<L, List<B>> ->
                    f(a).flatMap { b ->
                        eitherAcc.map { accB -> accB + b
                        } }
                }
        )

/**
 * Evaluates each action in the structure from left to right, and collects the results. It also gathers together
 * applicative effects.
 * It "flips" List<Either<L, A>> to Either<L, List<A>>.
 */
fun <L, A> List<Either<L, A>>.sequence(): Either<L, List<A>> = traverseA { it }
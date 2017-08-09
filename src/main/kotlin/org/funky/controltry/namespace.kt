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
package org.funky.controltry

import org.funky.either.Either
import org.funky.either.toLeft
import org.funky.either.toRight
import org.funky.option.Option
import org.funky.option.toOption
import java.util.NoSuchElementException

/*
 * The Try type represents a computation that may either result in an exception, or return a successfully computed value.
 * It's similar to, but semantically different Either type.
 *
 * Instances of Try<T>, are either an instance of Success<T> or Failure<T>.
 *
 * For example, Try can be used to perform division on a user-defined input, without the need to do explicit exception-handling in all of the places that an exception might occur.
 * fun divide(): Try<Int> {
 *        println("Enter an Int that you'd like to divide:")
 *        val dividend = Try { readLine()?.toInt() }
 *        println("Enter an Int that you'd like to divide by:")
 *        val divisor = Try { readLine()?.toInt() }
 *        val problem = dividend.flatMap { x -> divisor.map { y -> x!!.div(y!!) } }
 *        when(problem) {
 *            is Try.Success<Int> -> {
 *                println("Result of ${dividend.get()} / ${divisor.get()} is: ${problem.get()}")
 *            }
 *            is Try.Failure<*> -> {
 *                println("You must've divided by zero or entered something that's not an Int. Try again!")
 *                println("Info from the exception: ${problem.throwable.message}")
 *            }
 *        }
 *        return problem
 *    }
 */
sealed class Try<T> {
    /*
     * Returns true if the Try is a Failure, false otherwise.
     */
    abstract fun isFailure(): Boolean
    /*
     * Returns true if the Try is a Success, false otherwise.
     */
    abstract fun isSuccess(): Boolean

    /*
     * Returns the value from this Success or throws the exception if this is a Failure.
     */
    @Throws(Throwable::class)
    abstract fun get(): T

    /*
     * Catamorphism for Try.
     * Applies f if this is a Failure or s if this is a Success. If s is initially applied and throws an exception, then f is applied with this exception.
     */
    fun <X> fold(s: (T) -> X, f: (Throwable) -> X): X = when (this) {
        is Try.Success -> try {
            s(get())
        } catch (t: Throwable) {
            f(t)
        }
        is Try.Failure -> f(throwable)
    }

    /*
     * Returns the value from this Success or the given default argument if this is a Failure.
     */
    fun getOrElse(alternative: () -> T): T = fold({ it }, { alternative() })

    /*
     * Returns this Try if it's a Success or the given default argument if this is a Failure.
     */
    infix fun orElse(alternative: () -> Try<T>): Try<T> = fold({ this }, { alternative() })

    /*
     * Applies the given function f if this is a Success, otherwise returns Unit if this is a Failure.
     */
    fun foreach(f: (T) -> Unit) {
        if (isSuccess()) f(get())
    }

    /*
     * Returns the given function applied to the value from this Success or returns this if this is a Failure.
     */
    fun <U> flatMap(f: (T) -> Try<U>): Try<U> = when (this) {
        is Try.Success -> try {
                        f(get())
                    } catch (t: Throwable) {
            Try.Failure<U>(t)
                    }
        is Try.Failure -> Try.Failure<U>(throwable)
    }

    /*
    * Maps the given function to the value from this Success or returns this if this is a Failure.
     */
    fun <U> map(f: (T) -> U): Try<U> = flatMap { Try.Success(f(it)) }

    /*
     * Indicates if the receiver is a Success and its value satisfies the predicate.
     */
    fun exists(predicate: (T) -> Boolean): Boolean = fold({ predicate(it) }, { false })

    /*
     * Converts this to a Failure if the predicate is not satisfied.
     */
    fun filter(predicate: (T) -> Boolean): Try<T> = when (this) {
        is Try.Success -> try {
                        val value = get()
                        if (predicate(value)) this
                        else Try.Failure<T>(NoSuchElementException("Predicate does not hold for $value"))
                    } catch (t: Throwable) {
            Try.Failure<T>(t)
                    }
        is Try.Failure -> this
    }

    /*
     * Applies the given function f if this is a Failure, otherwise returns this if this is a Success.
     */
    fun recover(f: (Throwable) -> Try<T>): Try<T> = when (this) {
        is Try.Success -> this
        is Try.Failure -> try {
            f(throwable)
        } catch (t: Throwable) {
            Try.Failure<T>(t)
        }
    }

    /*
     * Executes body in case the receiver is Success. Returns the receiver.
     */
    fun onSuccess(body: (T) -> Unit): Try<T> {
        foreach(body)
        return this
    }

    /*
     * Executes body in case the receiver is Failure. Returns the receiver.
     */
    fun onFailure(body: (Throwable) -> Unit): Try<T> = when (this) {
        is Try.Success -> this
        is Try.Failure -> {
            body(throwable)
            this
        }
    }

    /*
     * Returns None if this is a Failure or a Some containing the value if this is a Success.
     */
    fun toOption(): Option<T> = fold({ it.toOption() }, { Option.empty() })

    /*
     * Returns Either.Left if this is a Failure orn Either,Right containing the value if this is a Success.
     */
    fun toEither(): Either<Throwable, T> = fold({ it.toRight() }, { it.toLeft() })

    /*
    * Completes this Try with an exception wrapped in a Success.
     */
    fun failed(): Try<Throwable> = fold({ Try.Failure(UnsupportedOperationException("Success")) }, { Try.Success(it) })

    /*
     * Completes this Try by applying the function f to this if this is of type Failure, or conversely, by applying s if this is a Success.
     */
    fun <U> transform(s: (T) -> Try<U>, f: (Throwable) -> Try<U>): Try<U> = when (this) {
        is Try.Success -> flatMap(s)
        is Try.Failure -> try {
            f(throwable)
        } catch (t: Throwable) {
            Try.Failure<U>(t)
        }
    }

    /*
     * Executes runnable in case the receiver is Success, wrapping the result in a new Try.
     */
    fun andThen(runnable: () -> Unit): Try<Unit> =
            flatMap { _ -> Try { runnable() } }

    class Success<T>(private val t: T) : Try<T>() {
        override fun get(): T = t

        override fun isFailure() = false

        override fun isSuccess() = true

        override fun toString(): String = "Success($t)"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Try.Success<*>) return false
            if (t != other.t) return false
            return true
        }

        override fun hashCode(): Int = t?.hashCode() ?: 0
    }

    class Failure<T>(val throwable: Throwable) : Try<T>() {
        override fun get(): T = throw throwable

        override fun isFailure() = true

        override fun isSuccess() = false

        override fun toString(): String = "Failure($throwable)"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Try.Failure<*>) return false
            if (throwable != other.throwable) return false
            return true
        }

        override fun hashCode(): Int = throwable.hashCode()
    }
}

/*
 * Utility function to wrap any code as a Try instance.
 */
fun <T> Try(body: () -> T): Try<T> = try {
    Try.Success(body())
} catch (t: Throwable) {
    Try.Failure(t)
}

/*
 * Transforms a nested Try, ie, a Try of type Try<Try<T>>, into an un-nested Try, ie, a Try of type Try<T>.
 */
fun <T> Try<Try<T>>.flatten(): Try<T> = fold ({ it }, { Try.Failure<T>(it) })
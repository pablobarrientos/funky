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
package org.funky.option

import org.funky.util.compose
import java.util.NoSuchElementException

/*
 * Represents optional values. Instances of Option are either an instance of scala.Some or the object None.
 * The most idiomatic way to use an scala.Option instance is to treat it as a collection of zero or one element and use
  * map,flatMap, filter, or foreach.
 */
sealed class Option<out A> {

    companion object {
        /*
         * Build a non-empty Option in case the receiver is not null
         */
        fun <A> of(t: A?) : Option<A> = if (t == null) Option.empty() else Some(t)
        /*
         * Returns an empty Option of type A.
         */
        fun <A> empty() : Option<A> = None

        object None : Option<Nothing>() {
            override fun isEmpty() = true
            override fun get() = throw NoSuchElementException("None.get")
            override fun equals(other: Any?): Boolean = when (other) {
                is None -> true
                else -> false
            }
            override fun hashCode(): Int = Integer.MAX_VALUE
        }

        class Some<out A> internal constructor(val t: A) : Option<A>() {
            override fun isEmpty() = false
            override fun get() = t
            override fun equals(other: Any?) : Boolean = when (other) {
                is Some<*> -> t == other.get()
                else -> false
            }
            override fun hashCode(): Int = t?.hashCode() ?: None.hashCode()
            override fun toString(): String = "Some<$t>"
        }
    }

    /*
     *Returns true if the option is None, false otherwise
     */
    abstract fun isEmpty(): Boolean

    /*
     * Returns the option's value. Throw NoSuchElementException if the Option is empty
     */
    @Throws(NoSuchElementException::class)
    abstract fun get(): A

    /*
     * Returns the result of applying ifSome to this Option's value if the Option is nonEmpty. Otherwise, evaluates
     * expression ifNone.
     */
    inline fun <B> fold(ifNone: () -> B, ifSome: (A) -> B): B =
        when (this) {
            is None -> ifNone()
            is Some -> ifSome(this.get())
        }

    /*
     * Indicates it the receiver is a non-empty Option
     */
    fun nonEmpty(): Boolean = isDefined()

    /*
     * Indicates it the receiver is a non-empty Option
     */
    fun isDefined(): Boolean = !isEmpty()

    /*
     * Converts this Option to a nullable value.
     */
    fun orNull(): A? = fold({ null }, { this.get() })

    /*
     * Returns the result of applying f to this Option's value if this Option is nonEmpty.
     */
    inline fun <B> flatMap(f: (A) -> Option<B>): Option<B> = fold({ None }, { f(this.get()) })

    /*
     * The given function is applied if this is a non-empty Option.
     */
    fun <B> map(f: (A) -> B): Option<B> = flatMap({ b : B -> Some(b) }.compose(f))

    /*
     * Returns this Option if it is nonEmpty and applying the predicate p to this Option's value returns true.
     */
    inline fun filter(p: (A) -> Boolean): Option<A> = flatMap { t: A -> if (p(t)) Option.of(t) else Option.empty() }

    /*
     * Returns this Option if it is non-empty and applying the predicate p to this Option's value returns false
     */
    inline fun filterNot(p: (A) -> Boolean): Option<A> = filter({ t: A -> !(p(t)) })

    /*
     * Returns true if this option is non-empty and the predicate p returns true when applied to this Option's value.
     */
    inline fun exists(p: (A) -> Boolean): Boolean = fold( { false }, { t: A -> p(t) })

    /*
     * Apply the given procedure f to the option's value, if it is non-empty
     */
    inline fun forEach(f: (A) -> Unit) {
        if (this.isDefined()) f(this.get())
    }

    /*
     * Returns a singleton list containing the Option's value if it is non-empty, or the empty list if the Option is empty.
     */
    fun toList(): List<A> = fold( { kotlin.collections.emptyList() }, { e: A -> listOf(e) })

}

/*
 * Returns the option's value if the option is non-empty, otherwise return the result of evaluating default.
 */
infix fun <A> Option<A>.getOrElse(default: () -> A): A = fold( default, { it })

/*
 * Returns this Option if it is non-empty, otherwise return the result of evaluating alternative.
 */
infix fun <A> Option<A>.orElse(alternative: () -> Option<A>): Option<A> = fold( alternative, { this })

/*
 * Returns this Option if it is non-empty, otherwise return the (pre-evaluated) alternative.
 */
infix fun <A> Option<A>.or(value: Option<A>): Option<A> = fold( { value }, { this })

//extensions for other types

/*
 * Convert any value to Option.
 */
fun <A> A?.toOption(): Option<A> = Option.of(this)

/*
 * Wraps an action in an Option. In case the action execution throws an exception it returns None, otherwise it
 * returns a non-empty Option with the result of action.
 */
inline fun <A> optionTry(action: () -> A): Option<A> =
    try {
        Option.of(action())
    } catch (e: Exception) {
        Option.empty()
    }

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun <A> Array<out A>.firstOption(): Option<A> = firstOrNull().toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun BooleanArray.firstOption(): Option<Boolean> = firstOrNull().toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun ByteArray.firstOption(): Option<Byte> = firstOrNull().toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun CharArray.firstOption(): Option<Char> = firstOrNull().toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun DoubleArray.firstOption(): Option<Double> = firstOrNull().toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun FloatArray.firstOption(): Option<Float> = firstOrNull().toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun IntArray.firstOption(): Option<Int> = firstOrNull().toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun LongArray.firstOption(): Option<Long> = firstOrNull().toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun ShortArray.firstOption(): Option<Short> = firstOrNull().toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun <A> Iterable<A?>.firstOption(): Option<A> = firstOrNull().toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun <A> Sequence<A?>.firstOption(): Option<A> = firstOrNull().toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun String.firstOption(): Option<Char> = firstOrNull().toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun <A> Array<out A>.firstOption(p: (A) -> Boolean): Option<A> = firstOrNull(p).toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
inline fun BooleanArray.firstOption(p: (Boolean) -> Boolean): Option<Boolean> = firstOrNull(p).toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
inline fun ByteArray.firstOption(p: (Byte) -> Boolean): Option<Byte> = firstOrNull(p).toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
inline fun CharArray.firstOption(p: (Char) -> Boolean): Option<Char> = firstOrNull(p).toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
inline fun DoubleArray.firstOption(p: (Double) -> Boolean): Option<Double> = firstOrNull(p).toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
inline fun FloatArray.firstOption(p: (Float) -> Boolean): Option<Float> = firstOrNull(p).toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
inline fun IntArray.firstOption(p: (Int) -> Boolean): Option<Int> = firstOrNull(p).toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
inline fun LongArray.firstOption(p: (Long) -> Boolean): Option<Long> = firstOrNull(p).toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
inline fun ShortArray.firstOption(p: (Short) -> Boolean): Option<Short> = firstOrNull(p).toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun <A> Iterable<A?>.firstOption(p: (A?) -> Boolean): Option<A> = firstOrNull(p).toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
fun <A> Sequence<A?>.firstOption(p: (A?) -> Boolean): Option<A> = firstOrNull(p).toOption()

/*
 * Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.
 */
inline fun String.firstOption(p: (Char) -> Boolean): Option<Char> = firstOrNull(p).toOption()

/*
 * Embed pure values in Option. Lift a value.
 */
fun <A> A.pure(): Option<A> = Option.of(this)

/*
 * Sequential application of Option as applicative functor.
 */
infix fun <A, B> Option<(A) -> B>.ap(optA: Option<A>): Option<B> = flatMap { optA.map(it) }

/*
 * Sequential application of Option as applicative functor.
 */
fun <A, B, C> Option<(A, B) -> C>.ap2(optA: Option<A>, optB: Option<B>): Option<C> =
        this.flatMap { f ->
        optA.flatMap { a ->
            optB.map { b -> f (a, b )
        } } }

/*
 * Lift a function to actions.
 */
fun <A, B> ((A) -> B).liftA(): (Option<A>) -> Option<B> = { toOption() ap it }

/*
 * Lift a binary function to actions.
 */
fun <A, B, C> ((A, B) -> C).liftA2(): (Option<A>, Option<B>) -> Option<C> =
        { optA, optB -> toOption().ap2(optA, optB) }

/*
 * Transforms a nested Option, ie, a Option of type Option<Option<A>>, into an un-nested Option, ie, an Option of type Option< R>.
 */
fun <A> Option<Option<A>>.flatten(): Option<A> = fold({ Option.empty() }, { it })

/*
* Map each element of the list of Option to an action, evaluate these actions from left to right, and collect the results.
 */
fun <A, B> List<A>.traverseA(f: (A) -> Option<B>): Option<List<B>> =
    foldRight(Option.of(emptyList()),
            { a: A, optAcc: Option<List<B>> ->
                f(a).flatMap { b ->
                optAcc.map { accB -> accB + b } }
            }
    )

/*
 * Evaluate each action in the structure from left to right, and collect the results. It also gathers together
 * applicative effects.
 * It "flips" List<Option<A>> to Option<List<A>>.
 */
fun <A> List<Option<A>>.sequenceA(): Option<List<A>> = traverseA { it }

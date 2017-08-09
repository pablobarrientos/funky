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
package org.funky.collections

import org.funky.option.Option
import org.funky.option.firstOption
import org.funky.util.identity
import java.lang.IllegalArgumentException
import kotlin.sequences.generateSequence
/*
 * Extract the first element of a list and wraps it in an Option.
 */
fun <A> Iterable<A>.head(): Option<A> = firstOption()

/*
 * Extract the elements after the head of a list, which must be non-empty.
 */
fun <A> Iterable<A>.tail(): Iterable<A> = this.drop(1)

/*
 * The partition function takes a predicate a list and returns the pair of lists of elements which do and do not satisfy the predicate, respectively.
 */
fun <A> Iterable<A>.partition(p: (A) -> Boolean): Pair<Iterable<A>, Iterable<A>> =
        Pair(this.filter(p), filterNot(p))

/*
 * Return all the elements of a list except the last one. The list must be non-empty.
 */
fun <A> Iterable<A>.init(): List<A> =
        this.take(this.count() - 1)

/*
 * A variant of fold that has no base case, and thus may only be applied to non-empty structures.
 * Throws IllegalArgumentException if the iterable is empty.
 */
@Throws(IllegalArgumentException::class)
fun <A> Iterable<A>.fold1(f: (A, A) -> A) =
        if (this.none()) throw IllegalArgumentException("Receiver is empty")
        else this.drop(1).fold(this.first(), f)

/*
 * A variant of foldRight that has no base case, and thus may only be applied to non-empty structures.
* Throws IllegalArgumentException if the iterable is empty.
 */
@Throws(IllegalArgumentException::class)
fun <A> Iterable<A>.foldRight1(f: (A, A) -> A): A =
        if (this.none()) throw IllegalArgumentException("Receiver is empty")
        else this.drop(1).foldRight(this.first(), f)
/*
 * x.iterate(f) returns an infinite sequence of repeated applications of f to x:
 *  x.iterate(f) == listOf(x, f x, f (f x), ...)
 */
fun <A: Any> A.iterate(f: (A) -> A): Sequence<A> = generateSequence( this, f )

/*
 * x.repeat() is an infinite sequence, with x the value of every element.
 */
fun <A: Any> A.repeat(): Sequence<A> = iterate(identity())

/*
 * cycle ties a finite into a circular sequence, or equivalently, the infinite repetition of the original list.
 */
fun <A> Iterable<A>.cycle(): Sequence<A> =
        generateSequence(
                Pair(this.first(), this.drop(1)),
                { remaining -> if (remaining.second.count() == 0) Pair(this.first(), this.drop(1))
                                else Pair(remaining.second.first(), remaining.second.drop(1))
                }
        ).map { it.first }
/*
 * a.replicate(n) is a list of length n with x the value of every element
 */
fun <A> A.replicate(n: Int): List<A> = (1..n).map { this }

/*
 * The inits function returns all initial segments of the argument, shortest first. For example,
 * listOf(1,2,3).inits() == listOf(emptyList(), listOf(1), listOf(1,2), listOf(1,2,3))
 */
fun <A> List<A>.inits(): List<List<A>> = (0.. this.size).map { this.take(it) }

/*
 * zipWith generalises zip by zipping with the function given as the first argument, instead of a tupling function.
 */
fun <A, B, C> Iterable<A>.zipWith(f: (A , B) -> C, other: Iterable<B>): Iterable<C> =
        this.zip(other, f)

/*
 * unzip transforms a list of pairs into a list of first components and a list of second components
 */
fun <A, B> Iterable<Pair<A, B>>.unzip(): Pair<Iterable<A>, Iterable<B>> =
        Pair(this.map { it.first }, this.map { it.second })

/*
 * filterMap traverses the list once filtering elements by the predicate p and then applying the transformation f.
 */
fun <A, B> List<A>.filterMap(p: (A) -> Boolean, f: (A) -> B): List<B> =
        this.foldRight( emptyList(), { e, acc -> if (p(e)) acc + f(e) else acc })

/*
 * mapFilter traverses the list once applying the transformation f and then filtering elements by the predicate p.
 */
fun <A, B> List<A>.mapFilter(f: (A) -> B, p: (B) -> Boolean): List<B> =
        this.foldRight( emptyList(),
                { e, acc ->
                    val x = f(e)
                    if (p(x)) acc + x else acc })

/*
 * creates a list containing the receiver as its only element.
 */
fun <A> A.singleton(): List<A> = listOf(this)

/*
 * Constructs a list with the receiver  and the tail, using the receiver as the head of the list
 */
infix fun <A> A.cons(tail: List<A>) = singleton() + tail
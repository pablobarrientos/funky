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
package org.funky.reader

import org.funky.collections.cons
import org.funky.util.compose
import org.funky.util.const
import org.funky.curry.ext.invoke

/**
 * Extension functions to represent the applicative functor for the type "(A) ->".
 * It is known as the "reader" or "environment" applicative functor. It allows a parameter to be passed across functions.
 * From a Functor point of view this "container" stores the value A. Given a particular environment, you can retrieve
 * the corresponding value by simply applying the function.
*/

// unfortunately, we cannot represent the type constructor (A) -> ... yet!
typealias F<X, Y> = (X) -> Y
typealias F2<X, Y, Z> = (X, Y) -> Z

/**
 * Mapping a function over a function is just like composing the receiver and the argument.
 */
fun <A, B, C> F<B, C>.map(f: F<A, B>): F<A, C> = this.compose(f)

/**
 * It "extracts" the value from the receiver and passes it to f to produce the result.
 */
fun <A, B, C> F<A, B>.flatMap(f: (B) -> F<A, C>): F<A, C> = { a: A -> f(this(a))(a) }

/**
 * Embeds pure values. Lifts a value to a function. Naturally, it is the const function.
 */
fun <A, B> A.pure(): F<B, A> = const(this)

/**
 * Sequential application of Function as applicative functor. It passes the "environment" across all functions involved
 * in the operation.
 */
infix fun <A, B, C> F<C, (A) -> B>.ap(f: F<C, A>): F<C, B> = { c: C -> this(c)(f(c)) }

/**
 * Sequential application of Function as applicative functor. It passes the "environment" across all functions involved
 * in the operation.
 */
fun <A, B, C, D> F<D, (A, B) -> C>.ap2(fa: F<D, A>, fb: F<D, B>): F<D, C> =
        { d -> (this(d))(fa(d))(fb(d)) }

/**
 * Lifts a function to actions.
 */
fun <A, B, C> ((A) -> B).liftA(): (F<C, A>) -> F<C, B> = this::map

/**
 * Lifts a binary function to actions.
 */
fun <A, B, C, D> ((A, B) -> C).liftA2(): (F<D, A>, F<D, B>) -> F<D, C> =
        { fa, fb -> { d -> this(fa(d), fb(d)) } }

/**
 * Maps each element from the list to an action, evaluates these actions from left to right, and collect the results.
 */
fun <A, B, C> List<A>.traverseA(f: (A) -> F<C, B>): F<C, List<B>> =
        foldRight(emptyList<B>().pure(),
            { a: A, optAcc: F<C, List<B>> -> { c -> f(a)(c) cons optAcc(c) } })

/**
 * Evaluates each action in the structure from left to right, and collects the results. It also gathers together
 * applicative effects.
 * It "flips" List<F<B, A>> to F<B, <List<A>>.
 */
fun <A, B> List<F<B, A>>.sequenceA(): F<B, List<A>> = traverseA { it }
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
package org.funky.util

/**
 * Function composition. I.e. (f compose g)(x) <=> f(g(x))
 */
infix fun <A, B, C> ((B) -> C).compose(f: (A) -> B): (A) -> C = { this(f(it)) }

/**
 * Composition of function in reverse order: (f forwardCompose g)(x) <=> g(f(x)).
 * This function is useful if we chain functions and provide the "initial input" at the end of the chain.
 */
infix fun <A, B, C> ((A) -> B).forwardCompose(f: (B) -> C): (A) -> C = { f(this(it)) }

/**
 * Alias for forwardCompose
 */
infix fun <A, B, C> ((A) -> B).andThen(f: (B) -> C): (A) -> C = forwardCompose(f)

/**
 * Identify function.
 */
fun <A> identity(): (A) -> A = { it }

/**
 * Converts any value A to a function (B) -> A
 */
fun <A, B> const(a: A): (B) -> A = { _ -> a }

/**
 * flip takes its two arguments in the reverse order of the receiver.
 */
fun <A, B, C> ((A, B) -> C).flip(): (B, A) -> C = { a, b -> this(b, a) }
fun <A, B, C, D> ((A, B, C) -> D).flip(): (C, B, A) -> D = { a, b, c -> this(c, b, a ) }
fun <A, B, C, D, E> ((A, B, C, D) -> E).flip(): (D, C, B, A) -> E = { a, b, c, d -> this(d, c, b, a ) }
fun <A, B, C, D, E, F> ((A, B, C, D, E) -> F).flip(): (E, D, C, B, A) -> F = { a, b, c, d, e -> this(e, d, c, b, a ) }
fun <A, B, C, D, E, F, G> ((A, B, C, D, E, F) -> G).flip(): (F, E, D, C, B, A) -> G = { a, b, c, d, e, f -> this(f, e, d, c, b, a ) }
fun <A, B, C, D, E, F, G, H> ((A, B, C, D, E, F, G) -> H).flip(): (G, F, E, D, C, B, A) -> H = { a, b, c, d, e, f, g -> this(g, f, e, d, c, b, a ) }

/**
 * Until yields the result of applying the function until condition holds.
 *
 * var count = 0
 * { x:Int ->
 *   count = count + 1
 *   x + 2
 * }.until( {it > 0}, -10)
 *
 */
tailrec fun <T> ((T) -> T).until(condition: (T) -> Boolean, initialValue: T): T =
        if (condition(initialValue)) initialValue else this.until(condition, this(initialValue))

/**
 * Given the condition, it will execute the function if the condition is false.
 *
 * unless (x == 0) { y = 100 / x }
 */
fun unless(condition: Boolean): (() -> Unit) -> Unit = { body -> if (!condition) body() }

/**
 * Infix application operator.
 */
infix fun <A, B> ((A) -> B).`$`(parameter: A) = this(parameter)
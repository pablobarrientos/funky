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
package org.funky.curry.ext

/**
 * Syntax extension to partially apply uncurried functions.
 *
 * E.g.:
 * val sum2: (Int, Int) -> Int = { x, y -> x + y }
 *
 * val inc: (Int) -> Int = (sum2)(1)
 */
operator fun <A, B, C> ((A, B) -> C).invoke(a: A): (B) -> C =
        { b -> this(a, b) }

operator fun <A, B, C, D> ((A, B, C) -> D).invoke(a: A): (B, C) -> D =
        { b, c -> this(a, b, c) }

operator fun <A, B, C, D, E> ((A, B, C, D) -> E).invoke(a: A): (B, C, D) -> E =
        { b, c, d -> this(a, b, c, d) }

operator fun <A, B, C, D, E, F> ((A, B, C, D, E) -> F).invoke(a: A): (B, C, D, E) -> F =
        { b, c, d, e -> this(a, b, c, d, e) }

operator fun <A, B, C, D, E, F, G> ((A, B, C, D, E, F) -> G).invoke(a: A): (B, C, D, E, F) -> G =
        { b, c, d, e, f -> this(a, b, c, d, e, f) }

operator fun <A, B, C, D, E, F, G, H> ((A, B, C, D, E, F, G) -> H).invoke(a: A): (B, C, D, E, F, G) -> H =
        { b, c, d, e, f, g -> this(a, b, c, d, e, f, g) }

operator fun <A, B, C, D, E, F, G, H, I> ((A, B, C, D, E, F, G, H) -> I).invoke(a: A): (B, C, D, E, F, G, H) -> I =
        { b, c, d, e, f, g, h -> this(a, b, c, d, e, f, g, h) }

operator fun <A, B, C, D, E, F, G, H, I, J> ((A, B, C, D, E, F, G, H, I) -> J).invoke(a: A): (B, C, D, E, F, G, H, I) -> J =
        { b, c, d, e, f, g, h, i -> this(a, b, c, d, e, f, g, h, i) }

operator fun <A, B, C, D, E, F, G, H, I, J, K> ((A, B, C, D, E, F, G, H, I, J) -> K).invoke(a: A): (B, C, D, E, F, G, H, I, J) -> K =
        { b, c, d, e, f, g, h, i, j -> this(a, b, c, d, e, f, g, h, i, j) }
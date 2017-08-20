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

import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import io.kotlintest.properties.Gen

class UtilTest : StringSpec() {

    init {
        "compose" {
            forAll(Gen.choose(0, 10000)) { x: Int ->
                val plus1 = { a: Int -> a + 1 }
                val mult2 = { b: Int -> b * 2 }
                (mult2 compose plus1)(x) == (x + 1) * 2
            }
        }

        "forwardCompose" {
            forAll(Gen.choose(0, 10000)) { x: Int ->
                val plus1 = { a: Int -> a + 1 }
                val mult2 = { b: Int -> b * 2 }
                (mult2 forwardCompose plus1)(x) == (x * 2) + 1
            }
        }

        "andThen" {
            forAll(Gen.choose(0, 10000)) { x: Int ->
                val plus1 = { a: Int -> a + 1 }
                val mult2 = { b: Int -> b * 2 }
                (mult2 andThen plus1)(x) == (x * 2) + 1
            }
        }

        "identity" {
            forAll { x: Int ->
                (org.funky.util.identity<Int>())(x) == x
            }
        }

        "const" {
            forAll { x: Int, y: Int ->
                x == const(x)(y)
            }
        }

        "flip2" {
            forAll { x: Int, y: Int ->
                val minus = { a: Int, b: Int -> a - b }
                val minus2 = minus.flip()
                minus2(x, y) == y - x
            }
        }

        "flip3" {
            forAll { x: Int, y: Int, z: Int ->
                val minus = { a: Int, b: Int, c: Int -> a - b - c }
                val minus3 = minus.flip()
                minus3(x, y, z) == z - y - x
            }
        }

        "until" {
            var count = 0
            { x: Int ->
                count += 1
                x + 2
            }.until( { it > 0 }, -10)
            count shouldBe 6
        }

        "unless" {
            forAll { x: Int ->
                var greater = false
                unless(x < 0)({
                    greater = true
                })
                greater == x >= 0
            }
        }

        "infix apply" {
            val plus1 = { a: Int -> a + 1 }
            val result = plus1 `$` 1 + 2 + 3
            result shouldBe 7
        }
    }
}
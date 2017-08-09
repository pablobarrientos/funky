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
package org.funky.curry

import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.funky.curry.ext.invoke

class CurryTest : StringSpec() {

    init {
        "curry2" {
            forAll { x: Int, y: Int ->
                val sum = { a: Int, b: Int -> a + b }
                val curried = sum.curried()
                curried(x)(y) shouldBe sum(x, y)
                val addX = curried(x)
                addX(y) == sum(x, y)
            }
        }

        "curry3" {
            forAll { x: Int, y: Int, z: Int ->
                val sum = { a: Int, b: Int, c: Int -> a + b + c }
                val curried = sum.curried()
                curried(x)(y)(z) shouldBe sum(x, y, z)
                val addXY = curried(x)(y)
                addXY(z) == sum(x, y, z)
            }
        }

        "curryUncurry" {
            forAll { x: Int, y: Int ->
                val sum = { a: Int, b: Int -> a + b }
                sum.curried().uncurried()(x, y) == sum(x, y)
            }
        }

        "uncurryCurry" {
            forAll { x: Int, y: Int ->
                val sum = { a: Int -> { b: Int -> a + b } }
                sum.uncurried().curried()(x)(y) == sum(x)(y)
            }
        }

        "ext-invoke" {
            forAll { x: Int, y: Int ->
                fun sum(a: Int, b: Int) = a + b
                (::sum)(x)(y) == sum(x, y)
            }
        }
    }
}
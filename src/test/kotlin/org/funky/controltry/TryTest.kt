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

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import io.kotlintest.properties.Gen

class TryTest : StringSpec() {

    init {
        "get" {
            shouldThrow<Exception> { Try.Failure<Int>(Exception()).get() }
            Try.Success(1).get() shouldBe 1
        }

        "fold" {
            forAll(TryGen) { t: Try<Int> ->
                val result = t.fold( { it }, { _ -> 0 })
                (t.isFailure() && result == 0) || (t.isSuccess() && result == t.get())
            }
        }

        "getOrElse" {
            forAll(TryGen) { t: Try<Int> ->
                val result = t.getOrElse { 0 }
                (t.isFailure() && result == 0) || (t.isSuccess() && result == t.get())
            }
        }

        "orElse" {
            forAll(TryGen) { t: Try<Int> ->
                val result = t.orElse { Try.Failure(Exception()) }
                (t.isFailure() && result.isFailure()) || (t == result)
            }
        }

        "foreach" {
            forAll(TryGen) { t: Try<Int> ->
                var x: Int? = null
                t.foreach { x = it }
                (t.isFailure() && x == null) || (t.isSuccess() && x != null)
            }
        }

        "flatMap" {
            val tryy = Try.Success(1)
            val failed = tryy.flatMap<Int> { _ -> throw Exception() }
            failed.isFailure() shouldBe true
            forAll(TryGen) { t: Try<Int> ->
                val result = t.flatMap { Try.Success(it + 1) }
                (t.isFailure() && result == t) || (t.isSuccess() && (result.get() - 1 == t.get()))
            }
        }

        "map" {
            forAll(TryGen) { t: Try<Int> ->
                val result = t.map { it + 1 }
                (t.isFailure() && result == t) || (t.isSuccess() && (result.get() - 1 == t.get()))
            }
        }

        "exists" {
            forAll(TryGen) { t: Try<Int> ->
                val result = t.exists { it == 1 }
                (t.isFailure() && !result) || (t.isSuccess() && (result == (t.get() == 1)))
            }
        }

        "filter" {
            forAll(TryGen) { t: Try<Int> ->
                val result = t.filter { it % 2 == 0 }
                (result.isFailure() && (t.isFailure() || t.get() % 2 != 0)) ||
                (result.isSuccess() && result == t)
            }
        }

        "recover" {
            forAll(TryGen) { t: Try<Int> ->
                val result = t.recover { _ -> Try.Success(0) }
                result.isSuccess() && (t.isSuccess() || result.get() == 0)
            }
        }

        "onSuccess" {
            forAll(TryGen) { t: Try<Int> ->
                var x: Int? = null
                t.onSuccess { x = it }
                (t.isFailure() && x == null) || (t.isSuccess() && x != null)
            }
        }

        "onFailure" {
            forAll(TryGen) { t: Try<Int> ->
                var x: Int? = null
                t.onFailure { _ -> x = 1 }
                (t.isFailure() && x == 1) || (t.isSuccess() && x == null)
            }
        }

        "toOption" {
            forAll(TryGen) { t: Try<Int> ->
                val result = t.toOption()
                (result.isEmpty() && t.isFailure()) || (result.isDefined() && t.isSuccess() && result.get() == t.get())
            }
        }

        "toEither" {
            forAll(TryGen) { t: Try<Int> ->
                val result = t.toEither()
                (result.isLeft() && t.isFailure()) || (result.isRight() && t.isSuccess() && result.component2()!! == t.get())
            }
        }

        "failed" {
            forAll(TryGen) { t: Try<Int> ->
                val result = t.failed()
                (t.isSuccess() && result.isFailure()) || (t.isFailure() && result.isSuccess())
            }
        }

        "transform" {
            forAll(TryGen) { t: Try<Int> ->
                val result = t.transform({ i -> Try.Success(i + 1) }, { _ -> Try.Failure(IllegalArgumentException()) })
                (t.isSuccess() && result.isSuccess() && result.get() == t.get() + 1) ||
                (t.isFailure() && result.isFailure())
            }
        }

        "andThen" {
            forAll(TryGen) { t: Try<Int> ->
                var x: Int? = null
                val result = t.andThen({ x = 1 })
                (t.isSuccess() && result.isSuccess() && x == 1) ||
                        (t.isFailure() && result.isFailure())
            }
        }

        "Try" {
            forAll { success: Boolean, n: Int ->
                val tryy = Try { if (success) n else throw Exception() }
                tryy.isSuccess() == success
            }
        }

        "flatten" {
            Try<Try<Int>> { throw RuntimeException("") }.flatten().isFailure() shouldBe true
            forAll(TryGen) { t: Try<Int> ->
                val result = Try { t }.flatten()
                result.isFailure() == t.isFailure()
            }
        }
    }
}

private typealias TestTry = Try<Int>

private object TryGen : Gen<TestTry> {
    override fun generate(): TestTry =
        if (Gen.bool().generate()) Try.Failure(Exception(Gen.string().generate()))
        else Try.Success(Gen.int().generate())
}
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
import org.funky.util.identity
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import io.kotlintest.properties.Gen
import java.util.NoSuchElementException

class OptionTest : StringSpec() {

    init {
        "Option.of" {
            Option.of(null) shouldBe Option.empty<Int>()
            val opt = Option.of(0)
            opt.isDefined() shouldBe true
            opt.exists { it == 0 }
        }

        "get" {
            shouldThrow<NoSuchElementException> {
                Option.empty<Int>().get()
            }
            Option.of(0).get() shouldBe 0
        }

        "isEmpty" {
            Option.empty<Int>().isEmpty() shouldBe true
            Option.of(0).isEmpty() shouldBe false
        }

        "isDefined" {
            Option.empty<Int>().isDefined() shouldBe false
            Option.of(0).isDefined() shouldBe true
        }

        "nonEmpty" {
            Option.empty<Int>().nonEmpty() shouldBe false
            Option.of(0).nonEmpty() shouldBe true
        }

        "fold" {
            forAll(OptionGen) { option: Option<Int> ->
                option.fold( { option.isEmpty() }, { option.isDefined() })
            }
        }

        "orNull" {
            forAll(OptionGen) { option: Option<Int> ->
                val result = option.orNull()
                (option.isEmpty() && result == null) || (option.isDefined() && result != null)
            }
        }

        "flatMap" {
            forAll(OptionGen) { option: Option<Int> ->
                val expected = if (option.isDefined()) option.get() + 1 else 0
                val result = option.flatMap { r -> (r + 1).toOption() }
                (option.isEmpty() && result.isEmpty()) || (option.isDefined() && result.get() == expected)
            }
        }

        "map" {
            forAll(OptionGen) { option: Option<Int> ->
                val expected = if (option.isDefined()) option.get() + 1 else 0
                val result = option.map { r -> r + 1 }
                (option.isEmpty() && result.isEmpty()) || (option.isDefined() && result.get() == expected)
            }
        }

        "filter" {
            forAll(OptionGen) { option: Option<Int> ->
                val result = option.filter { r -> r / 2 == 0 }
                (option.isEmpty() && result.isEmpty()) ||
                (option.isDefined() && ((result.isDefined() == (option.get() / 2 == 0))))
            }
        }

        "filterNot" {
            forAll(OptionGen) { option: Option<Int> ->
                val result = option.filterNot { r -> r / 2 == 0 }
                (option.isEmpty() && result.isEmpty()) ||
                        (option.isDefined() && ((result.isDefined() == (option.get() / 2 != 0))))
            }
        }

        "forEach" {
            forAll(OptionGen) { option: Option<Int> ->
                var x: Int? = null
                option.forEach { x = it }
                (option.isEmpty() && x == null) || (x != null && option.isDefined())
            }
        }

        "toList" {
            forAll(OptionGen) { option: Option<Int> ->
                val result = option.toList()
                (option.isEmpty() && result.isEmpty()) || (option.isDefined() && result.size == 1 && result[0] == option.get())
            }
        }

        "getOrElse" {
            forAll(OptionGen) { option: Option<Int> ->
                val result = option.getOrElse { 0 }
                (option.isEmpty() && result == 0) || (option.isDefined() && result == option.get())
            }
        }

        "orElse" {
            forAll(OptionGen) { option: Option<Int> ->
                val alt = 0.toOption()
                val result = option.orElse { alt }
                (option.isEmpty() && result == alt) || (option.isDefined() && result == option)
            }
        }

        "or" {
            forAll(OptionGen) { option: Option<Int> ->
                val alt = 0.toOption()
                val result = option.or(alt)
                (option.isEmpty() && result == alt) || (option.isDefined() && result == option)
            }
        }

        "toOption" {
            null.toOption() shouldBe Option.empty<Int>()
            forAll { n: Int ->
                val result = n.toOption()
                result.isDefined() && result.get() == n
            }
        }

        "optionTry" {
            forAll { n: Int ->
                val actionOK = { n }
                val actionException = { throw Exception() }
                val resultOK = optionTry(actionOK)
                val resultException = optionTry(actionException)
                resultOK.isDefined() && resultException.isEmpty()
            }
        }

        "pure" {
            forAll { n: Int ->
                val result = n.pure()
                result.isDefined() && result.get() == n
            }
        }

        "ap" {
            forAll(OptionGen, Gen.bool()) { option: Option<Int>, createSome: Boolean ->
                val op = { x: Int -> x + 1 }
                val optOp: Option<(Int) -> Int> =
                        if (createSome) op.toOption() else Option.empty()
                val result = optOp ap option
                (result.isEmpty() && (option.isEmpty() || optOp.isEmpty())) ||
                (optOp.isDefined() && option.isDefined() && result.isDefined() && result.get() == option.get() + 1)
            }
        }

        "ap2" {
            forAll(OptionGen, OptionGen, Gen.bool()) { option1: Option<Int>, option2: Option<Int>, createSome: Boolean ->
                val op = { x: Int, y: Int -> x + y }
                val optOp: Option<(Int, Int) -> Int> =
                        if (createSome) op.toOption() else Option.empty()
                val result = optOp.ap2(option1, option2)
                (result.isEmpty() && (option1.isEmpty() || option2.isEmpty() || optOp.isEmpty())) ||
                (optOp.isDefined() && option1.isDefined() && option2.isDefined() && result.isDefined() &&
                    result.get() == option1.get() + option2.get())
            }
        }

        "liftA" {
            forAll(OptionGen) { option: Option<Int> ->
                val op = { x: Int -> x + 1 }
                val result = op.liftA()(option)
                (result.isEmpty() && option.isEmpty()) ||
                        (option.isDefined() && result.isDefined() && result.get() == option.get() + 1)
            }
        }

        "liftA2" {
            forAll(OptionGen, OptionGen) { option1: Option<Int>, option2: Option<Int> ->
                val op = { x: Int, y: Int -> x + y }
                val result = op.liftA2()(option1, option2)
                (result.isEmpty() && (option1.isEmpty() || option2.isEmpty())) ||
                (option1.isDefined() && option2.isDefined() && result.isDefined() &&
                        result.get() == option1.get() + option2.get())
            }
        }

        "flatten" {
            val option = 1.toOption()
            val wrapped = option.toOption()
            wrapped.flatten() shouldBe option
        }

        "traverseA" {
            forAll { list: List<Int> ->
                var generatedNone = false
                val result = list.traverseA { _ ->
                    val option = OptionGen.generate()
                    generatedNone = generatedNone || option.isEmpty()
                    option
                }
                (generatedNone && result.isEmpty()) || (!generatedNone && result.isDefined())
            }
        }

        "sequence" {
            forAll(Gen.list(OptionGen)) { list: List<Option<Int>> ->
                val result = list.sequenceA()
                (list.any { it.isEmpty() } && result.isEmpty()) || (list.all { it.isDefined() } && result.isDefined())
            }
        }

        //FUNCTOR LAWS
        /**
        fmap id = id                   -- 1st functor law
        fmap (g . f) = fmap g . fmap f -- 2nd functor law
        */
        "functor - 1st law" {
            forAll(OptionGen) { option: Option<Int> ->
                option.map { it } == option
            }
        }

        "functor - 2nd law" {
            forAll(OptionGen) { option: Option<Int> ->
                val f = { a: Int -> a + 1 }
                val g = { b: Int -> b.toString() }
                option.map { g.compose(f)(it) } == option.map(f).map(g)
            }
        }

        //APPLICATIVE FUNCTOR LAWS
        /**
        pure id <*> v = v                            -- Identity
        pure f <*> pure x = pure (f x)               -- Homomorphism
        u <*> pure y = pure ($ y) <*> u              -- Interchange
        pure (.) <*> u <*> v <*> w = u <*> (v <*> w) -- Composition
        */
        "applicative functor - identity" {
            forAll(OptionGen) { v: Option<Int> ->
                val pureId: Option<(Int) -> Int> = identity<Int>().pure()
                pureId ap v == v
            }
        }

        "applicative functor - homomorphism" {
            forAll { n: Int ->
                val sum : (Int) -> Int = { it + 1 }
                val pureSum = sum.pure()
                val pureN = n.pure()
                pureSum ap pureN == sum(n).pure()
            }
        }

        "applicative functor - interchange" {
            forAll { y: Int ->
                val f : (Int) -> Int = { x -> x + 1 }
                val u = f.pure()
                val pureY = y.pure()
                val appTo: (((Int) -> Int) -> Int) = { it(y) }
                u ap pureY == appTo.pure() ap u
            }
        }

        "applicative functor - composition" {
            forAll { y: Int ->
                val f : (Int) -> Int = { it
                    + 1 }
                val v = f.pure()
                val g : (Int) -> String = { it.toString() }
                val u = g.pure()
                val w = y.pure()
                val compose2: ((Int) -> String) -> ((Int) -> Int) -> (Int) -> String =
                        { fu -> { fv -> fu compose fv } }
                compose2.pure().ap(u).ap(v).ap(w) == u.ap(v.ap(w))
            }
        }
    }
}

private typealias TestOption = Option<Int>

private object OptionGen : Gen<TestOption> {
    override fun generate(): TestOption =
            if (!Gen.bool().generate() && Gen.choose(1, 20).generate() == 1) Option.empty()
            else Option.of(Gen.int().generate())
}
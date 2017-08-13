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
package org.funky.validation

import org.funky.option.Option
import org.funky.option.toOption
import org.funky.util.compose
import org.funky.util.identity
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import io.kotlintest.properties.Gen

class ValidationNELTest : StringSpec() {

    init {
        "toEither" {
            forAll(ValidationNELGen) { validationNEL: ValidationNEL<String, Int> ->
                val either = validationNEL.toEither()
                (either.isRight() && validationNEL.isSuccess() && validationNEL.component2() == either.component2()) ||
                        (either.isLeft() && validationNEL.isFailure())
            }
        }

        "fold" {
            forAll(ValidationNELGen) { validationNEL: ValidationNEL<String, Int> ->
                val expected = when (validationNEL) {
                    is ValidationNEL.Failure -> 0
                    is ValidationNEL.Success -> validationNEL.r
                }
                validationNEL.fold( { 0 }, identity() ) == expected
            }
        }

        "flatMap" {
            forAll(ValidationNELGen) { validationNEL: ValidationNEL<String, Int> ->
                val expected = when (validationNEL) {
                    is ValidationNEL.Success -> validationNEL.r + 1
                    is ValidationNEL.Failure -> 0
                }
                val result = validationNEL.flatMap { r -> (r + 1).toSuccessNel<String, Int>() }
                result.map { n -> n == expected }.getOrElse { result.isFailure() }
            }
        }

        "map" {
            forAll(ValidationNELGen) { validationNEL: ValidationNEL<String, Int> ->
                val expected = when (validationNEL) {
                    is ValidationNEL.Success -> validationNEL.r + 1
                    is ValidationNEL.Failure -> 0
                }
                val result = validationNEL.map { r -> (r + 1) }
                result.map { n -> n == expected }.getOrElse { result.isFailure() }
            }
        }

        "exists" {
            forAll(ValidationNELGen) { validationNEL: ValidationNEL<String, Int> ->
                validationNEL.exists { it == 2 } == validationNEL.map { it == 2 }.getOrElse { !validationNEL.isFailure() }
            }
        }

        "forEach" {
            forAll(ValidationNELGen) { validationNEL: ValidationNEL<String, Int> ->
                var x: Option<Int> = Option.empty()
                validationNEL.forEach { x = it.toOption() }
                (validationNEL.isFailure() && x.isEmpty()) || (x.isDefined() && x.exists { it == validationNEL.component2()!! })
            }
        }

        "getOrElse" {
            forAll(ValidationNELGen) { validationNEL: ValidationNEL<String, Int> ->
                val result = validationNEL.getOrElse { 0 }
                (validationNEL.isFailure() && result == 0) || (validationNEL.isSuccess() && result == validationNEL.component2()!!)
            }
        }

        "orElse" {
            forAll(ValidationNELGen) { validationNEL: ValidationNEL<String, Int> ->
                val alt = "".toFailureNel<String, Int>()
                val result = validationNEL.orElse { alt }
                (validationNEL.isFailure() && result == alt) || (validationNEL.isSuccess() && result == validationNEL)
            }
        }

        "or" {
            forAll(ValidationNELGen) { validationNEL: ValidationNEL<String, Int> ->
                val alt = "".toFailureNel<String, Int>()
                val result = validationNEL or alt
                (validationNEL.isFailure() && result == alt) || (validationNEL.isSuccess() && result == validationNEL)
            }
        }

        "toFailureNel" {
            forAll { n: Int ->
                val result = n.toFailureNel<Int, Int>()
                result.isFailure() && result.component1()!! == listOf(n)
            }
        }

        "toSuccessNel" {
            forAll { n: Int ->
                val result = n.toSuccessNel<Int, Int>()
                result.isSuccess() && result.component2()!! == n
            }
        }

        "pair toSuccessNel" {
            forAll { n: Int, m: Int ->
                val result = Pair(n, m).toSuccessNel()
                result.isSuccess() && result.component2()!! == m
            }
        }

        "pair toFailureNel" {
            forAll { n: Int, m: Int ->
                val result = Pair(n, m).toFailureNel()
                result.isFailure() && result.component1()!! == listOf(n)
            }
        }

        "validationNelTry" {
            forAll { n: Int ->
                val actionOK = { n }
                val actionException = { throw Exception() }
                val resultOK = validationNelTry(actionOK)
                val resultException = validationNelTry(actionException)
                resultOK.isSuccess() && resultException.isFailure()
            }
        }

        "toFailureNel" {
            forAll { n: Int ->
                val result = n.toFailureNel<Int, String>()
                result.isFailure() && result.component1()!! == listOf(n)
            }
        }

        "toSuccessNel" {
            forAll { n: Int ->
                val result = n.toSuccessNel<String, Int>()
                result.isSuccess() && result.component2()!! == n
            }
        }

        "pure" {
            forAll { n: Int ->
                val result = n.pure<String, Int>()
                result.isSuccess() && result.component2()!! == n
            }
        }

        "ap" {
            forAll(ValidationNELGen, Gen.bool()) { validationNEL: ValidationNEL<String, Int>, createRight: Boolean ->
                val op = { x: Int -> x + 1 }
                val eitherOp: ValidationNEL<String, (Int) -> Int> =
                        if (createRight) op.toSuccessNel() else "".toFailureNel()
                val result = eitherOp ap validationNEL
                (result.isFailure() && (validationNEL.isFailure() || eitherOp.isFailure())) ||
                (eitherOp.isSuccess() && validationNEL.isSuccess() && result.isSuccess() &&
                        result.component2()!! == validationNEL.component2()!! + 1)
            }
        }

        "ap2" {
            forAll(ValidationNELGen, ValidationNELGen, Gen.bool()) { validationNEL1: ValidationNEL<String, Int>, validationNEL2: ValidationNEL<String, Int>, createRight: Boolean ->
                val op = { x: Int, y: Int -> x + y }
                val validationNELOp: ValidationNEL<String, (Int, Int) -> Int> =
                        if (createRight) op.toSuccessNel() else "".toFailureNel()
                val result = validationNELOp.ap2(validationNEL1, validationNEL2)
                (result.isFailure() && (validationNEL1.isFailure() || validationNEL2.isFailure() || validationNELOp.isFailure())) ||
                (validationNELOp.isSuccess() && validationNEL1.isSuccess() && validationNEL2.isSuccess() && result.isSuccess() &&
                    result.component2()!! == validationNEL1.component2()!! + validationNEL2.component2()!!)
            }
        }

        "liftA" {
            forAll(ValidationNELGen) { validationNEL: ValidationNEL<String, Int> ->
                val op = { x: Int -> x + 1 }
                val result = op.liftA<String, Int, Int>()(validationNEL)
                (result.isFailure() && validationNEL.isFailure()) ||
                        (validationNEL.isSuccess() && result.isSuccess() &&
                                result.component2()!! == validationNEL.component2()!! + 1)
            }
        }

        "liftA2" {
            forAll(ValidationNELGen, ValidationNELGen) { validationNEL1: ValidationNEL<String, Int>, validationNEL2: ValidationNEL<String, Int> ->
                val op = { x: Int, y: Int -> x + y }
                val result = op.liftA2<String, Int, Int, Int>()(validationNEL1, validationNEL2)
                (result.isFailure() && (validationNEL1.isFailure() || validationNEL2.isFailure())) ||
                (validationNEL1.isSuccess() && validationNEL2.isSuccess() && result.isSuccess() &&
                        result.component2()!! == validationNEL1.component2()!! + validationNEL2.component2()!!)
            }
        }

        "flatten" {
            val validationNEL = (1.toSuccessNel<String, Int>())
            val wrapped = validationNEL.toSuccessNel<String, ValidationNEL<String, Int>>()
            wrapped.flatten() == validationNEL
        }

        "traverseA" {
            forAll { list: List<Int> ->
                var failures = 0
                val result = list.traverseA { _ ->
                    val validationNEL = ValidationNELGen.generate()
                    failures += if (validationNEL.isFailure()) 1 else 0
                    validationNEL
                }
                (result.isFailure() && failures == result.component1()!!.count()) || (failures == 0 && result.isSuccess())
            }
        }

        "sequence" {
            forAll(Gen.list(ValidationNELGen)) { list: List<ValidationNEL<String, Int>> ->
                val result = list.sequence()
                (list.any { it.isFailure() } && result.isFailure()) || (list.all { it.isSuccess() } && result.isSuccess())
            }
        }

        //FUNCTOR LAWS
        /**
        fmap id = id                   -- 1st functor law
        fmap (g . f) = fmap g . fmap f -- 2nd functor law
        */
        "functor - 1st law" {
            forAll(ValidationNELGen) { either: ValidationNEL<String, Int> ->
                either.map { it } == either
            }
        }

        "functor - 2nd law" {
            forAll(ValidationNELGen) { either: ValidationNEL<String, Int> ->
                val f = { a: Int -> a + 1 }
                val g = { b: Int -> b.toString() }
                either.map { g.compose(f)(it) } == either.map(f).map(g)
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
            forAll(ValidationNELGen) { v: ValidationNEL<String, Int> ->
                val pureId: ValidationNEL<String, (Int) -> Int> = identity<Int>().pure()
                pureId ap v == v
            }
        }

        "applicative functor - homomorphism" {
            forAll { n: Int ->
                val sum : (Int) -> Int = { it + 1 }
                val pureSum: ValidationNEL<String, (Int) -> Int> = sum.pure()
                val pureN: ValidationNEL<String, Int> = n.pure()
                pureSum ap pureN == sum(n).pure<String, Int>()
            }
        }

        "applicative functor - interchange" {
            forAll { y: Int ->
                val f : (Int) -> Int = { x -> x + 1 }
                val u: ValidationNEL<String, (Int) -> Int> = f.pure()
                val pureY: ValidationNEL<String, Int> = y.pure()
                val appTo: (((Int) -> Int) -> Int) = { it(y) }
                u ap pureY == appTo.pure<String, ((Int) -> Int) -> Int>() ap u
            }
        }

        "applicative functor - composition" {
            forAll { y: Int ->
                val f : (Int) -> Int = { x -> x + 1 }
                val v: ValidationNEL<String, (Int) -> Int> = f.pure()
                val g : (Int) -> String = { it.toString() }
                val u: ValidationNEL<String, (Int) -> String> = g.pure()
                val w: ValidationNEL<String, Int> = y.pure()
                val compose2: ((Int) -> String) -> ((Int) -> Int) -> (Int) -> String =
                        { fu -> { fv -> fu compose fv } }
                val composePure: ValidationNEL<String, ((Int) -> String) -> ((Int) -> Int) -> (Int) -> String> = compose2.pure()
                composePure.ap(u).ap(v).ap(w) == u.ap(v.ap(w))
            }
        }
    }
}

private typealias TestValidationNEL = ValidationNEL<String, Int>

private object ValidationNELGen : Gen<TestValidationNEL> {
    override fun generate(): TestValidationNEL =
            if (!Gen.bool().generate() && Gen.choose(1, 20).generate() == 1)
                ValidationNEL.failure(Gen.string().generate())
            else ValidationNEL.success(Gen.int().generate())
}
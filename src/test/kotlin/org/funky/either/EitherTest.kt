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
package org.funky.either

import org.funky.option.Option
import org.funky.option.toOption
import org.funky.util.compose
import org.funky.util.identity
import org.funky.validation.ValidationNEL
import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import io.kotlintest.properties.Gen

class EitherTest : StringSpec() {

    init {
        "fold" {
            forAll(EitherGen) { either: Either<String, Int> ->
                val expected = when (either) {
                    is Either.Left -> 0
                    is Either.Right -> either.r
                }
                either.fold( { 0 }, identity()) == expected
            }
        }

        "flatMap" {
            forAll(EitherGen) { either: Either<String, Int> ->
                val expected = when (either) {
                    is Either.Right -> either.r + 1
                    is Either.Left -> 0
                }
                val result = either.flatMap { r -> (r + 1).toRight<String, Int>() }
                result.map { n -> n == expected }.getOrElse { result.isLeft() }
            }
        }

        "map" {
            forAll(EitherGen) { either: Either<String, Int> ->
                val expected = when (either) {
                    is Either.Right -> either.r + 1
                    is Either.Left -> 0
                }
                val result = either.map { r -> (r + 1) }
                result.map { n -> n == expected }.getOrElse { result.isLeft() }
            }
        }

        "swap" {
            forAll(EitherGen) { either: Either<String, Int> ->
                val expected = either.fold( { it.toRight<Int, String>() }, { it.toLeft<Int, String>() })
                either.swap() == expected
            }
        }

        "toValidationNEL" {
            forAll(EitherGen) { either: Either<String, Int> ->
                val expected = when (either) {
                    is Either.Right -> ValidationNEL.success<String, Int>(either.r)
                    is Either.Left -> ValidationNEL.failure<String, Int>(either.l)
                }
                expected == either.toValidationNEL()
            }
        }

        "exists" {
            forAll(EitherGen) { either: Either<String, Int> ->
                either.exists { it == 2 } == either.map { it == 2 }.getOrElse { !either.isLeft() }
            }
        }

        "forEach" {
            forAll(EitherGen) { either: Either<String, Int> ->
                var x: Option<Int> = Option.empty()
                either.forEach { x = it.toOption() }
                (either.isLeft() && x.isEmpty()) || (x.isDefined() && x.exists { it == either.component2()!! })
            }
        }

        "getOrElse" {
            forAll(EitherGen) { either: Either<String, Int> ->
                val result = either.getOrElse { 0 }
                (either.isLeft() && result == 0) || (either.isRight() && result == either.component2()!!)
            }
        }

        "orElse" {
            forAll(EitherGen) { either: Either<String, Int> ->
                val alt = "".toLeft<String, Int>()
                val result = either.orElse { alt }
                (either.isLeft() && result == alt) || (either.isRight() && result == either)
            }
        }

        "or" {
            forAll(EitherGen) { either: Either<String, Int> ->
                val alt = "".toLeft<String, Int>()
                val result = either or alt
                (either.isLeft() && result == alt) || (either.isRight() && result == either)
            }
        }

        "merge" {
            forAll(EitherGen) { either: Either<String, Int> ->
                val e = either.map { _ -> "was right" }
                (either.isRight() && e.merge() == "was right") || (either.isLeft() && e.merge() == either.component1()!!)
            }
        }

        "toLeft" {
            forAll { n: Int ->
                val result = n.toLeft<Int, Int>()
                result.isLeft() && result.component1()!! == n
            }
        }

        "toRight" {
            forAll { n: Int ->
                val result = n.toRight<Int, Int>()
                result.isRight() && result.component2()!! == n
            }
        }

        "Pair toRight" {
            forAll { n: Int, m: Int ->
                val result = Pair(n, m).toRight()
                result.isRight() && result.component2()!! == m
            }
        }

        "Pair toLeft" {
            forAll { n: Int, m: Int ->
                val result = Pair(n, m).toLeft()
                result.isLeft() && result.component1()!! == n
            }
        }

        "Option toLeft" {
            val result = Option.of(0).toRight { "" }
            result.isRight() shouldBe true
            result.component2()!! shouldBe 0
        }

        "Option toLeft" {
            val result = Option.of(0).toLeft { "" }
            result.isLeft() shouldBe true
            result.component1()!! shouldBe 0
        }

        "eitherTry" {
            forAll { n: Int ->
                val actionOK = { n }
                val actionException = { throw Exception() }
                val resultOK = eitherTry(actionOK)
                val resultException = eitherTry(actionException)
                resultOK.isRight() && resultException.isLeft()
            }
        }

        "toLeft" {
            forAll { n: Int ->
                val result = n.toLeft<Int, String>()
                result.isLeft() && result.component1()!! == n
            }
        }

        "toRight" {
            forAll { n: Int ->
                val result = n.toRight<String, Int>()
                result.isRight() && result.component2()!! == n
            }
        }

        "pure" {
            forAll { n: Int ->
                val result = n.pure<String, Int>()
                result.isRight() && result.component2()!! == n
            }
        }

        "ap" {
            forAll(EitherGen, Gen.bool()) { either: Either<String, Int>, createRight: Boolean ->
                val op = { x: Int -> x + 1 }
                val eitherOp: Either<String, (Int) -> Int> =
                        if (createRight) op.toRight() else "".toLeft()
                val result = eitherOp ap either
                (result.isLeft() && (either.isLeft() || eitherOp.isLeft())) ||
                (eitherOp.isRight() && either.isRight() && result.isRight() &&
                        result.component2()!! == either.component2()!! + 1)
            }
        }

        "ap2" {
            forAll(EitherGen, EitherGen, Gen.bool()) { either1: Either<String, Int>, either2: Either<String, Int>, createRight: Boolean ->
                val op = { x: Int, y: Int -> x + y }
                val eitherOp: Either<String, (Int, Int) -> Int> =
                        if (createRight) op.toRight() else "".toLeft()
                val result = eitherOp.ap2(either1, either2)
                (result.isLeft() && (either1.isLeft() || either2.isLeft() || eitherOp.isLeft())) ||
                (eitherOp.isRight() && either1.isRight() && either2.isRight() && result.isRight() &&
                    result.component2()!! == either1.component2()!! + either2.component2()!!)
            }
        }

        "liftA" {
            forAll(EitherGen) { either: Either<String, Int> ->
                val op = { x: Int -> x + 1 }
                val result = op.liftA<String, Int, Int>()(either)
                (result.isLeft() && either.isLeft()) ||
                        (either.isRight() && result.isRight() &&
                                result.component2()!! == either.component2()!! + 1)
            }
        }

        "liftA2" {
            forAll(EitherGen, EitherGen) { either1: Either<String, Int>, either2: Either<String, Int> ->
                val op = { x: Int, y: Int -> x + y }
                val result = op.liftA2<String, Int, Int, Int>()(either1, either2)
                (result.isLeft() && (either1.isLeft() || either2.isLeft())) ||
                (either1.isRight() && either2.isRight() && result.isRight() &&
                        result.component2()!! == either1.component2()!! + either2.component2()!!)
            }
        }

        "flatten" {
            val either = (1.toRight<String, Int>())
            val wrapped = either.toRight<String, Either<String, Int>>()
            wrapped.flatten() shouldBe either
        }

        "traverseA" {
            forAll { list: List<Int> ->
                var generatedLeft = false
                val result = list.traverseA { _ ->
                    val either = EitherGen.generate()
                    generatedLeft = generatedLeft || either.isLeft()
                    either
                }
                (generatedLeft && result.isLeft()) || (!generatedLeft && result.isRight())
            }
        }

        "sequence" {
            forAll(Gen.list(EitherGen)) { list: List<Either<String, Int>> ->
                val result = list.sequence()
                (list.any { it.isLeft() } && result.isLeft()) || (list.all { it.isRight() } && result.isRight())
            }
        }

        //FUNCTOR LAWS
        /*
        fmap id = id                   -- 1st functor law
        fmap (g . f) = fmap g . fmap f -- 2nd functor law
        */
        "functor - 1st law" {
            forAll(EitherGen) { either: Either<String, Int> ->
                either.map { it } == either
            }
        }

        "functor - 2nd law" {
            forAll(EitherGen) { either: Either<String, Int> ->
                val f = { a: Int -> a + 1 }
                val g = { b: Int -> b.toString() }
                either.map { g.compose(f)(it) } == either.map(f).map(g)
            }
        }

        //APPLICATIVE FUNCTOR LAWS
        /*
        pure id <*> v = v                            -- Identity
        pure f <*> pure x = pure (f x)               -- Homomorphism
        u <*> pure y = pure ($ y) <*> u              -- Interchange
        pure (.) <*> u <*> v <*> w = u <*> (v <*> w) -- Composition
        */
        "applicative functor - identity" {
            forAll(EitherGen) { v: Either<String, Int> ->
                val pureId: Either<String, (Int) -> Int> = identity<Int>().pure()
                pureId ap v == v
            }
        }

        "applicative functor - homomorphism" {
            forAll { n: Int ->
                val sum : (Int) -> Int = { it + 1 }
                val pureSum: Either<String, (Int) -> Int> = sum.pure()
                val pureN: Either<String, Int> = n.pure()
                pureSum ap pureN == sum(n).pure<String, Int>()
            }
        }

        "applicative functor - interchange" {
            forAll { y: Int ->
                val f : (Int) -> Int = { x -> x + 1 }
                val u: Either<String, (Int) -> Int> = f.pure()
                val pureY: Either<String, Int> = y.pure()
                val appTo: (((Int) -> Int) -> Int) = { it(y) }
                u ap pureY == appTo.pure<String, ((Int) -> Int) -> Int>().ap(u)
            }
        }

        "applicative functor - composition" {
            forAll { y: Int ->
                val f : (Int) -> Int = { it
                    + 1 }
                val v: Either<String, (Int) -> Int> = f.pure()
                val g : (Int) -> String = { it.toString() }
                val u: Either<String, (Int) -> String> = g.pure()
                val w: Either<String, Int> = y.pure()
                val compose2: ((Int) -> String) -> ((Int) -> Int) -> (Int) -> String =
                        { fu -> { fv -> fu compose fv } }
                val composePure: Either<String, ((Int) -> String) -> ((Int) -> Int) -> (Int) -> String> = compose2.pure()
                composePure.ap(u).ap(v).ap(w) == u.ap(v.ap(w))
            }
        }
    }
}

private typealias TestEither = Either<String, Int>

private object EitherGen : Gen<TestEither> {
    override fun generate(): TestEither =
        if (Gen.bool().generate()) Either.left(Gen.string().generate()) else Either.right(Gen.int().generate())

}
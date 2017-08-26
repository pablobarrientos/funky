package org.funky.reader

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.funky.util.compose
import org.funky.util.const
import org.funky.util.identity

class ReaderTest : StringSpec() {

    init {
        "map" {
            forAll(FGen, Gen.int()) { f: FIntInt, n: Int ->
                val expected = f(n)
                val result = f.map(identity())(n)
                result == expected
            }
        }

        "flatMap" {
            forAll(FGen, Gen.int()) { f: FIntInt, n: Int ->
                val expected = f(n) + 1
                val result = f.flatMap { r -> (r + 1).pure<Int, Int>() } (n)
                result == expected
            }
        }

        "pure" {
            forAll { n: Int, m: Int ->
                val result = n.pure<Int, Int>() (m)
                result == n
            }
        }

        "ap" {
            forAll(FGen, Gen.int()) { f: FIntInt, n: Int ->
                val expected = f(n)
                val fOp: F<Int, (Int) -> Int> = f.pure()
                val result = (fOp ap identity()) (n)
                result == expected
            }
        }

        "ap2" {
            forAll(Gen.choose(1, 1000)) { n: Int ->
                val op = { x: Int, y: Int -> x + y }
                val expected = op(n, n)
                val optOp = op.pure<(Int, Int) -> Int, Int>()
                val result = optOp.ap2(identity(), identity())(n)
                result == expected
            }
        }

        "liftA" {
            forAll(Gen.int()) { n: Int ->
                val op = { x: Int -> x + 1 }
                val result = op.liftA<Int, Int, Int>()(const(n))
                result(n) == op(n)
            }
        }

        "liftA2" {
            forAll(FGen, FGen, Gen.choose(-1000, 1000)) { f1: FIntInt, f2: FIntInt, n: Int ->
                val op = { x: Int, y: Int -> x + y }
                val result = op.liftA2<Int, Int, Int, Int>()(f1, f2)(n)
                result == op(f1(n), f2(n))
            }
        }

        "traverseA" {
            forAll(Gen.list(Gen.choose(-10, 10))) { list: List<Int> ->
                val result = list.traverseA { const<Int, Int>(it) }
                runReader(result) == list
            }
        }

        "sequence" {
            forAll(Gen.list(FGen)) { list: List<FIntInt> ->
                val result = list.sequenceA()
                runReader(result) == list.map { it(1) }
            }
        }

        //FUNCTOR LAWS
        /**
        fmap id = id                   -- 1st functor law
        fmap (g . f) = fmap g . fmap f -- 2nd functor law
        */
        "functor - 1st law" {
            forAll(FGen) { f: FIntInt ->
                runReader(f.map<Int, Int, Int> { it }) == runReader(f)
            }
        }

        "functor - 2nd law" {
            forAll(FGen, FGen) { f: FIntInt, g: FIntInt ->
                runReader(f.map { g.compose(f)(it) }) == runReader(f.map(f).map(g))
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
            forAll(FGen) { f: FIntInt ->
                val pureId: F<Int, (Int) -> Int> = identity<Int>().pure()
                runReader(pureId ap f) == runReader(f)
            }
        }

        "applicative functor - homomorphism" {
            forAll { n: Int ->
                val sum: (Int) -> Int = { it + 1 }
                val pureSum = sum.pure<(Int) -> Int, Int>()
                val pureN = n.pure<Int, Int>()
                runReader(pureSum ap pureN) == runReader(sum(n).pure<Int, Int>())
            }
        }

        "applicative functor - interchange" {
            forAll { y: Int ->
                val f: (Int) -> Int = { x -> x + 1 }
                val u = f.pure<FIntInt, Int>()
                val pureY = y.pure<Int, Int>()
                val appTo: (((Int) -> Int) -> Int) = { it(y) }
                runReader(u ap pureY) == runReader(appTo.pure<(((Int) -> Int) -> Int), Int>() ap u)
            }
        }

        "applicative functor - composition" {
            forAll { y: Int ->
                val f: (Int) -> Int = { it +1 }
                val v = f.pure<(Int) -> Int, Int>()
                val g: (Int) -> String = { it.toString() }
                val u = g.pure<(Int) -> String, Int>()
                val w = y.pure<Int, Int>()
                val compose2: ((Int) -> String) -> ((Int) -> Int) -> (Int) -> String =
                        { fu -> { fv -> fu compose fv } }
                runReader(compose2.pure<((Int) -> String) -> ((Int) -> Int) -> (Int) -> String, Int>().ap(u).ap(v).ap(w)) == runReader(u.ap(v.ap(w)))
            }
        }
    }
}

private typealias FIntInt = F<Int, Int>
private object FGen : Gen<FIntInt> {
    // not really interesting
    override fun generate(): FIntInt = const(Gen.choose(-1000, 1000).generate())
}

private fun <B> runReader(f: F<Int, B>) = f(1)
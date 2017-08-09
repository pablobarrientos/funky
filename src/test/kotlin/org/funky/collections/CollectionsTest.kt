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
package org.funky.collections

import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec

class CollectionsTest : StringSpec() {

    init {
        "head" {
            forAll { iterable: List<Int> ->
                iterable.head().isDefined() == iterable.count() > 0
            }
            forAll { iterable: Set<Int> ->
                iterable.head().isDefined() == iterable.count() > 0
            }
        }

        "tail" {
            forAll { iterable: List<Int> ->
                iterable.tail().count() shouldBe iterable.count() - 1
                iterable.tail() == iterable.drop(1)
            }
            forAll { iterable: Set<Int> ->
                iterable.tail().count() shouldBe iterable.count() - 1
                iterable.tail() == iterable.drop(1)
            }
        }

        "partition" {
            val predicate = { i: Int -> i.div(2) == 0 }
            forAll { iterable: List<Int> ->
                val (p1, p2) = iterable.partition(predicate)
                (p1 + p2).count() shouldBe iterable.count()
                iterable.all { elem -> if (elem in p1) elem !in p2 else elem in p2 }
            }
        }

        "init" {
            forAll { iterable: List<Int> ->
                val last = iterable.last()
                iterable.init().count() shouldBe iterable.count() - 1
                iterable.init().indexOfLast { it == last } != iterable.lastIndex
            }
        }

        "fold1" {
            forAll { iterable: List<Int> ->
                iterable.isEmpty() ||
                        iterable.fold1({ x, y -> x + y }) == iterable.sum()
            }
        }

        "foldRight1" {
            forAll { iterable: List<Int> ->
                iterable.isEmpty() ||
                        iterable.foldRight1({ x, y -> x + y }) == iterable.sum()
            }
        }

        "iterate" {
            forAll(Gen.choose(1, 1000), Gen.choose(1, 20)) { i: Int, n: Int ->
                i.iterate { it + 1 }.take(n).sum() == (i * n) + (0..n - 1).sum()
            }
        }

        "repeat" {
            forAll(Gen.choose(1, 1000), Gen.choose(1, 20)) { i: Int, n: Int ->
                i.repeat().take(n).sum() shouldBe i * n
                i.repeat().take(n).all { it == i }
            }
        }

        "cycle" {
            forAll(Gen.list(Gen.int()), Gen.choose(1, 5)) { list: List<Int>, n: Int ->
                list.cycle().take(n * list.count()).toList() == (1..n).flatMap { list }
            }
        }

        "repeat" {
            forAll(Gen.choose(1, 1000), Gen.choose(1, 5)) { i: Int, n: Int ->
                i.replicate(n).size shouldBe n
                i.replicate(n).all { it == i }
            }
        }

        "inits" {
            forAll { list: List<Int> ->
                val result = list.inits()
                result.forEachIndexed { index, init ->
                    if (index == 0) init.isEmpty() shouldBe true else init shouldBe list.subList(0, index)
                }
                result.size == list.size + 1
            }
        }

        "zipWith" {
            forAll { list1: List<Int>, list2: List<Int> ->
                list1.zipWith(::Pair, list2) shouldBe list1.zip(list2)
                list1.zipWith(::maxOf, list2) == list1.zip(list2).map { p -> maxOf(p.first, p.second) }
            }
        }

        "unzip" {
            forAll { list1: List<Int>, list2: List<Int> ->
                val result = list1.zip(list2).unzip()
                val n = minOf(list1.size, list2.size)
                result.first shouldBe list1.take(n)
                result.second == list2.take(n)
            }
        }

        "filterMap" {
            forAll { list1: List<Int> ->
                val p: (Int) -> Boolean = { it.div(2) == 0 }
                val f: (Int) -> Int = { it + 1 }
                list1.filterMap(p, f) == list1.filter(p).map(f)
            }
        }

        "mapFilter" {
            forAll { list1: List<Int> ->
                val p: (Int) -> Boolean = { it.div(2) == 0 }
                val f: (Int) -> Int = { it + 1 }
                list1.mapFilter(f, p) == list1.map(f).filter(p)
            }
        }

        "singleton" {
            forAll { n: Int ->
                n.singleton() == listOf(n)
            }
        }

        "cons" {
            forAll { head: Int, tail: List<Int> ->
                head cons tail == listOf(head) + tail
            }
        }
    }
}
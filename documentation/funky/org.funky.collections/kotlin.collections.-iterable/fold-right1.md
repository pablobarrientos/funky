[funky](../../index.md) / [org.funky.collections](../index.md) / [kotlin.collections.Iterable](index.md) / [foldRight1](.)

# foldRight1

`fun <A> `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<A>.foldRight1(f: (A, A) -> A): A`

A variant of foldRight that has no base case, and thus may only be applied to non-empty structures.
Throws IllegalArgumentException if the iterable is empty.


[funky](../index.md) / [org.funky.collections](index.md) / [iterate](.)

# iterate

`fun <A : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> A.iterate(f: (A) -> A): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<A>`

x.iterate(f) returns an infinite sequence of repeated applications of f to x:
x.iterate(f) == listOf(x, f x, f (f x), ...)


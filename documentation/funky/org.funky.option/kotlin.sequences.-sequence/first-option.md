[funky](../../index.md) / [org.funky.option](../index.md) / [kotlin.sequences.Sequence](index.md) / [firstOption](.)

# firstOption

`fun <A> `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<A?>.firstOption(): `[`Option`](../-option/index.md)`<A>`
`fun <A> `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<A?>.firstOption(p: (A?) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Option`](../-option/index.md)`<A>`

Extract the first element and wrapps it in an Option. Returns None is the receiver has no elements.


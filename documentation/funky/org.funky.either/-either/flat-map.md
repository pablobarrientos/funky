[funky](../../index.md) / [org.funky.either](../index.md) / [Either](index.md) / [flatMap](.)

# flatMap

`inline fun <A> flatMap(f: (R) -> `[`Either`](index.md)`<L, A>): `[`Either`](index.md)`<L, A>`

Returns the given function applied to the value if the receiver is Right or returns a Left if it is Left.


[funky](../../index.md) / [org.funky.validation](../index.md) / [ValidationNEL](index.md) / [flatMap](.)

# flatMap

`inline fun <A> flatMap(f: (R) -> `[`ValidationNEL`](index.md)`<L, A>): `[`ValidationNEL`](index.md)`<L, A>`

Returns the given function applied to the value if the receiver is Success or returns a Failure if it is Failure.


[funky](../../index.md) / [org.funky.controltry](../index.md) / [Try](index.md) / [orElse](.)

# orElse

`infix fun orElse(alternative: () -> `[`Try`](index.md)`<T>): `[`Try`](index.md)`<T>`

Returns this Try if it's a Success or the given default argument if this is a Failure.


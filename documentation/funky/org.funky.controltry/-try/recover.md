[funky](../../index.md) / [org.funky.controltry](../index.md) / [Try](index.md) / [recover](.)

# recover

`fun recover(f: (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Try`](index.md)`<T>): `[`Try`](index.md)`<T>`

Applies the given function f if this is a Failure, otherwise returns this if this is a Success.


[funky](../../index.md) / [org.funky.controltry](../index.md) / [Try](index.md) / [fold](.)

# fold

`fun <X> fold(s: (T) -> X, f: (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> X): X`

Catamorphism for Try.
Applies f if this is a Failure or s if this is a Success. If s is initially applied and throws an exception, then f is applied with this exception.


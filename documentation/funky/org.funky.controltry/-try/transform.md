[funky](../../index.md) / [org.funky.controltry](../index.md) / [Try](index.md) / [transform](.)

# transform

`fun <U> transform(s: (T) -> `[`Try`](index.md)`<U>, f: (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Try`](index.md)`<U>): `[`Try`](index.md)`<U>`

Completes this Try by applying the function f to this if this is of type Failure, or conversely, by applying s if this is a Success.


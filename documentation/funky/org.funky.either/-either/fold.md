[funky](../../index.md) / [org.funky.either](../index.md) / [Either](index.md) / [fold](.)

# fold

`inline fun <A> fold(isLeft: (L) -> A, isRight: (R) -> A): A`

Catamorphism for Either.
Applies isLeft if this is a Left or isRight if this is Right.


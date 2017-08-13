[funky](../../index.md) / [org.funky.validation](../index.md) / [ValidationNEL](index.md) / [fold](.)

# fold

`inline fun <A> fold(fl: (`[`NonEmptyList`](../-non-empty-list.md)`<L>) -> A, fr: (R) -> A): A`

Catamorphism for ValidationNEL.
Applies fl if this is a Failure or fr if this is Success.


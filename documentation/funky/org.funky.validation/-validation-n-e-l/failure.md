[funky](../../index.md) / [org.funky.validation](../index.md) / [ValidationNEL](index.md) / [failure](.)

# failure

`fun <L, R> failure(left: L): `[`ValidationNEL`](index.md)`<L, R>`

Constructor of Failure from a single value.

`fun <L, R> failure(nel: `[`NonEmptyList`](../-non-empty-list.md)`<L>): `[`ValidationNEL`](index.md)`<L, R>`

Constructor of Failure from a list of values. If the list is empty, it will throw an IllegalArgumentException


[funky](../../index.md) / [org.funky.option](../index.md) / [kotlin.collections.List](index.md) / [traverseA](.)

# traverseA

`fun <A, B> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>.traverseA(f: (A) -> `[`Option`](../-option/index.md)`<B>): `[`Option`](../-option/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<B>>`

Maps each element of the list of Option to an action, evaluates these actions from left to right, and collect the results.


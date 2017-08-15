[funky](../../index.md) / [org.funky.validation](../index.md) / [kotlin.collections.List](index.md) / [traverseA](.)

# traverseA

`fun <L, A, B> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>.traverseA(f: (A) -> `[`ValidationNEL`](../-validation-n-e-l/index.md)`<L, B>): `[`ValidationNEL`](../-validation-n-e-l/index.md)`<L, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<B>>`

Maps each element of the list of ValidationNEL to an action, evaluates these actions from left to right, and collects the
results, accumulating errors on the left.


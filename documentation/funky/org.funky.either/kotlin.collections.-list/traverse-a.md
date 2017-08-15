[funky](../../index.md) / [org.funky.either](../index.md) / [kotlin.collections.List](index.md) / [traverseA](.)

# traverseA

`inline fun <L, A, B> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>.traverseA(f: (A) -> `[`Either`](../-either/index.md)`<L, B>): `[`Either`](../-either/index.md)`<L, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<B>>`

Maps each element of the list of Either to an action, evaluates these actions from left to right. It returns the first
left in case there's at least one in the list. It is important to note that it evaluates all the elements of the list.


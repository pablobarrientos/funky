[funky](../../index.md) / [org.funky.collections](../index.md) / [kotlin.collections.List](index.md) / [inits](.)

# inits

`fun <A> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>.inits(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>>`

The inits function returns all initial segments of the argument, shortest first. For example,
listOf(1,2,3).inits() == listOf(emptyList(), listOf(1), listOf(1,2), listOf(1,2,3))


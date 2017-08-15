[funky](../../index.md) / [org.funky.collections](../index.md) / [kotlin.collections.List](.)

### Extensions for kotlin.collections.List

| Name | Summary |
|---|---|
| [filterMap](filter-map.md) | `fun <A, B> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>.filterMap(p: (A) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, f: (A) -> B): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<B>`<br>filterMap traverses the list just once, filtering elements by the predicate p and then applying the transformation f. |
| [inits](inits.md) | `fun <A> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>.inits(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>>`<br>Returns all initial segments of the argument, shortest first. For example, listOf(1,2,3).inits() == listOf(emptyList(), listOf(1), listOf(1,2), listOf(1,2,3)) |
| [mapFilter](map-filter.md) | `fun <A, B> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>.mapFilter(f: (A) -> B, p: (B) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<B>`<br>mapFilter traverses the list just once, applying the transformation f and then filtering elements by the predicate p. |

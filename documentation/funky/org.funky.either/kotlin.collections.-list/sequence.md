[funky](../../index.md) / [org.funky.either](../index.md) / [kotlin.collections.List](index.md) / [sequence](.)

# sequence

`fun <L, A> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Either`](../-either/index.md)`<L, A>>.sequence(): `[`Either`](../-either/index.md)`<L, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>>`

Evaluate each action in the structure from left to right, and collect the results. It also gathers together
applicative effects.
It "flips" List&lt;Either&lt;L, A&gt;&gt; to Either&lt;L, List&gt;.


[funky](../../index.md) / [org.funky.reader](../index.md) / [kotlin.collections.List](index.md) / [sequenceA](.)

# sequenceA

`fun <A, B> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`F`](../-f.md)`<B, A>>.sequenceA(): `[`F`](../-f.md)`<B, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>>`

Evaluates each action in the structure from left to right, and collects the results. It also gathers together
applicative effects.
It "flips" List&lt;F&lt;B, A&gt;&gt; to F&lt;B, &lt;List&gt;.


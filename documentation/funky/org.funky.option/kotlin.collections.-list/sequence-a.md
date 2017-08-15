[funky](../../index.md) / [org.funky.option](../index.md) / [kotlin.collections.List](index.md) / [sequenceA](.)

# sequenceA

`fun <A> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Option`](../-option/index.md)`<A>>.sequenceA(): `[`Option`](../-option/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>>`

Evaluates each action in the structure from left to right, and collects the results. It also gathers together
applicative effects.
It "flips" List&lt;Option&gt; to Option&lt;List&gt;.


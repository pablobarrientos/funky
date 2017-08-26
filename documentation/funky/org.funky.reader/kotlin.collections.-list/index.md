[funky](../../index.md) / [org.funky.reader](../index.md) / [kotlin.collections.List](.)

### Extensions for kotlin.collections.List

| Name | Summary |
|---|---|
| [sequenceA](sequence-a.md) | `fun <A, B> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`F`](../-f.md)`<B, A>>.sequenceA(): `[`F`](../-f.md)`<B, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>>`<br>Evaluates each action in the structure from left to right, and collects the results. It also gathers together applicative effects. It "flips" List&lt;F&lt;B, A&gt;&gt; to F&lt;B, &lt;List&gt;. |
| [traverseA](traverse-a.md) | `fun <A, B, C> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>.traverseA(f: (A) -> `[`F`](../-f.md)`<C, B>): `[`F`](../-f.md)`<C, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<B>>`<br>Maps each element from the list to an action, evaluates these actions from left to right, and collect the results. |

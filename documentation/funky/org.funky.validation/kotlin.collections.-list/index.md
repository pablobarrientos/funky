[funky](../../index.md) / [org.funky.validation](../index.md) / [kotlin.collections.List](.)

### Extensions for kotlin.collections.List

| Name | Summary |
|---|---|
| [sequence](sequence.md) | `fun <L, A> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ValidationNEL`](../-validation-n-e-l/index.md)`<L, A>>.sequence(): `[`ValidationNEL`](../-validation-n-e-l/index.md)`<L, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>>`<br>Evaluates each action in the structure from left to right, and collects the results. It also gathers together applicative effects. It "flips" List&lt;ValidationNEL&lt;L, A&gt;&gt; to ValidationNEL&lt;L, List&gt;. |
| [traverseA](traverse-a.md) | `fun <L, A, B> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>.traverseA(f: (A) -> `[`ValidationNEL`](../-validation-n-e-l/index.md)`<L, B>): `[`ValidationNEL`](../-validation-n-e-l/index.md)`<L, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<B>>`<br>Maps each element of the list of ValidationNEL to an action, evaluates these actions from left to right, and collects the results, accumulating errors on the left. |

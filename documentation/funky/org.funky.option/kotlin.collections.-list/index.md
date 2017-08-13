[funky](../../index.md) / [org.funky.option](../index.md) / [kotlin.collections.List](.)

### Extensions for kotlin.collections.List

| Name | Summary |
|---|---|
| [sequenceA](sequence-a.md) | `fun <A> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Option`](../-option/index.md)`<A>>.sequenceA(): `[`Option`](../-option/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>>`<br>Evaluate each action in the structure from left to right, and collect the results. It also gathers together applicative effects. It "flips" List&lt;Option&gt; to Option&lt;List&gt;. |
| [traverseA](traverse-a.md) | `fun <A, B> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>.traverseA(f: (A) -> `[`Option`](../-option/index.md)`<B>): `[`Option`](../-option/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<B>>`<br>Map each element of the list of Option to an action, evaluate these actions from left to right, and collect the results. |

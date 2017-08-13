[funky](../../index.md) / [org.funky.either](../index.md) / [kotlin.collections.List](.)

### Extensions for kotlin.collections.List

| [sequence](sequence.md) | `fun <L, A> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Either`](../-either/index.md)`<L, A>>.sequence(): `[`Either`](../-either/index.md)`<L, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>>`<br>Evaluate each action in the structure from left to right, and collect the results. It also gathers together applicative effects. It "flips" List&lt;Either&lt;L, A&gt;&gt; to Either&lt;L, List&gt;. |
| [traverseA](traverse-a.md) | `fun <L, A, B> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>.traverseA(f: (A) -> `[`Either`](../-either/index.md)`<L, B>): `[`Either`](../-either/index.md)`<L, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<B>>`<br>Map each element of the list of Either to an action, evaluate these actions from left to right. It returns the first left in case there's at least one in the list. It is important to note that it evaluates all the elements of the list. |


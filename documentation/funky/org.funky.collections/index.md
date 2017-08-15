[funky](../index.md) / [org.funky.collections](.)

## Package org.funky.collections

### Extensions for External Classes

| Name | Summary |
|---|---|
| [kotlin.collections.Iterable](kotlin.collections.-iterable/index.md) |  |
| [kotlin.collections.List](kotlin.collections.-list/index.md) |  |

### Functions

| Name | Summary |
|---|---|
| [cons](cons.md) | `infix fun <A> A.cons(tail: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>`<br>Constructs a list with the receiver  and the tail, using the receiver as the head of the list |
| [iterate](iterate.md) | `fun <A : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> A.iterate(f: (A) -> A): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<A>`<br>x.iterate(f) returns an infinite sequence of repeated applications of f to x: x.iterate(f) == listOf(x, f x, f (f x), ...) |
| [repeat](repeat.md) | `fun <A : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> A.repeat(): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<A>`<br>x.repeat() is an infinite sequence, with x the value of every element. |
| [replicate](replicate.md) | `fun <A> A.replicate(n: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>`<br>a.replicate(n) is a list of length n with x the value of every element |
| [singleton](singleton.md) | `fun <A> A.singleton(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>`<br>Creates a list containing the receiver as its only element. |

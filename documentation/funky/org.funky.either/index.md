[funky](../index.md) / [org.funky.either](.)

## Package org.funky.either

### Types

| Name | Summary |
|---|---|
| [Either](-either/index.md) | `sealed class Either<L, R>`<br>Represents a value of one of two possible types (a disjoint union.) An instance of `Either` is an instance of either Left or Right. |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [kotlin.Function1](kotlin.-function1/index.md) |  |
| [kotlin.Function2](kotlin.-function2/index.md) |  |
| [kotlin.Pair](kotlin.-pair/index.md) |  |
| [kotlin.collections.List](kotlin.collections.-list/index.md) |  |

### Functions

| Name | Summary |
|---|---|
| [ap](ap.md) | `infix fun <L, R, A> `[`Either`](-either/index.md)`<L, (A) -> R>.ap(eitherR: `[`Either`](-either/index.md)`<L, A>): `[`Either`](-either/index.md)`<L, R>`<br>Sequential application of Either as applicative functor. |
| [ap2](ap2.md) | `fun <L, A, B, R> `[`Either`](-either/index.md)`<L, (A, B) -> R>.ap2(eitherA: `[`Either`](-either/index.md)`<L, A>, eitherB: `[`Either`](-either/index.md)`<L, B>): `[`Either`](-either/index.md)`<L, R>`<br>Sequential application of Either as applicative functor. |
| [eitherTry](either-try.md) | `fun <T> eitherTry(action: () -> T): `[`Either`](-either/index.md)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, T>`<br>Wraps an action in an Either. In case the action execution throws an exception it returns a Left, otherwise it returns a Right with the result of action. |
| [flatten](flatten.md) | `fun <L, R> `[`Either`](-either/index.md)`<L, `[`Either`](-either/index.md)`<L, R>>.flatten(): `[`Either`](-either/index.md)`<L, R>`<br>Transforms a nested Either, ie, a Either of type Either&lt;L, Either&lt;L, R&gt;&gt;, into an un-nested Either, ie, an Either of type Either&lt;L, R&gt;. |
| [merge](merge.md) | `fun <A> `[`Either`](-either/index.md)`<A, A>.merge(): A`<br>In case both components of Either are equal, merge returns the A and removes the Either. |
| [pure](pure.md) | `fun <L, R> R.pure(): `[`Either`](-either/index.md)`<L, R>`<br>Embed pure values in Either. Lift a value. |
| [toLeft](to-left.md) | `fun <L, R> L.toLeft(): `[`Either`](-either/index.md)`<L, R>`<br>Builder of Left from any type`fun <L, R> `[`Option`](../org.funky.option/-option/index.md)`<L>.toLeft(right: () -> R): `[`Either`](-either/index.md)`<L, R>`<br>Builder of Left from Option type. In case the receiver is Right, right will be its value. |
| [toRight](to-right.md) | `fun <L, R> R.toRight(): `[`Either`](-either/index.md)`<L, R>`<br>Builder of Right from any type`fun <L, R> `[`Option`](../org.funky.option/-option/index.md)`<R>.toRight(left: () -> L): `[`Either`](-either/index.md)`<L, R>`<br>Builder of Right from Option type |

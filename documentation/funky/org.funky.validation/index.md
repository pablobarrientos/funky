[funky](../index.md) / [org.funky.validation](.)

## Package org.funky.validation

### Types

| Name | Summary |
|---|---|
| [ValidationNEL](-validation-n-e-l/index.md) | `sealed class ValidationNEL<L, R>`<br>Custom implementation of Validation. ValidationNel is similar to Either but it will accumulate all errors on the left. |

### Type Aliases

| Name | Summary |
|---|---|
| [NonEmptyList](-non-empty-list.md) | `typealias NonEmptyList<L> = `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<L>` |

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
| [ap](ap.md) | `infix fun <L, R, A> `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, (A) -> R>.ap(valX: `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, A>): `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, R>`<br>Sequential application of ValidationNEL as applicative functor. |
| [ap2](ap2.md) | `fun <L, A, B, R> `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, (A, B) -> R>.ap2(valX: `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, A>, valY: `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, B>): `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, R>`<br>Sequential application of ValidationNEL as applicative functor. |
| [flatten](flatten.md) | `fun <L, R> `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, R>>.flatten(): `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, R>`<br>Transforms a nested ValidationNEL, ie, a ValidationNEL of type ValidationNEL&lt;L, ValidationNEL&lt;L, R&gt;&gt;, into an un-nested ValidationNEL, ie, an ValidationNEL of type ValidationNEL&lt;L, R&gt;. |
| [pure](pure.md) | `fun <L, R> R.pure(): `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, R>`<br>Embeds pure values in ValidationNEL. Lift a value. |
| [toFailure](to-failure.md) | `fun <L, R> `[`Option`](../org.funky.option/-option/index.md)`<L>.toFailure(success: () -> R): `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, R>`<br>Builder of Failure from Option type. In case the receiver is non-empty, success will be its value. |
| [toFailureNel](to-failure-nel.md) | `fun <L, R> L.toFailureNel(): `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, R>`<br>Builder of Failure from any type |
| [toSuccess](to-success.md) | `fun <L, R> `[`Option`](../org.funky.option/-option/index.md)`<R>.toSuccess(failure: () -> L): `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, R>`<br>Builder of Success from Option type |
| [toSuccessNel](to-success-nel.md) | `fun <L, R> R.toSuccessNel(): `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, R>`<br>Builder of Success from any type |
| [validationNelTry](validation-nel-try.md) | `fun <T> validationNelTry(body: () -> T): `[`ValidationNEL`](-validation-n-e-l/index.md)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, T>`<br>Wraps an action in a ValidationNEL. In case the action execution throws an exception it returns a Failure, otherwise it returns Success with the result of action. |

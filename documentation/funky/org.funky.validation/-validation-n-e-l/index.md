[funky](../../index.md) / [org.funky.validation](../index.md) / [ValidationNEL](.)

# ValidationNEL

`sealed class ValidationNEL<L, R>`

Custom implementation of Validation.
ValidationNel is similar to Either but it will accumulate all errors on the left.

An instance of `Either` is an instance of either Failure or Success.

### Types

| Name | Summary |
|---|---|
| [Failure](-failure/index.md) | `class Failure<L, R> : ValidationNEL<L, R>` |
| [Success](-success/index.md) | `class Success<L, R> : ValidationNEL<L, R>` |

### Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | `abstract operator fun component1(): `[`NonEmptyList`](../-non-empty-list.md)`<L>?`<br>Kotlin projection of ValidationNEL's first component (failure). Returns null id the instance is Success. |
| [component2](component2.md) | `abstract operator fun component2(): R?`<br>Kotlin projection of ValidationNEL's second component (success). Returns null id the instance is Failure. |
| [exists](exists.md) | `fun exists(p: (R) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns `false` if Failure or returns the result of the application of the given predicate to the Success value. |
| [flatMap](flat-map.md) | `fun <A> flatMap(f: (R) -> ValidationNEL<L, A>): ValidationNEL<L, A>`<br>Returns the given function applied to the value if the receiver is Success or returns a Failure if it is Failure. |
| [fold](fold.md) | `fun <A> fold(fl: (`[`NonEmptyList`](../-non-empty-list.md)`<L>) -> A, fr: (R) -> A): A`<br>Catamorphism for ValidationNEL. Applies fl if this is a Failure or fr if this is Success. |
| [forEach](for-each.md) | `fun forEach(f: (R) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Executes the given side-effecting function if this is a Failure. |
| [getOrElse](get-or-else.md) | `infix fun getOrElse(default: () -> R): R`<br>Returns the value from this Failure or the given argument if this is Success. |
| [isFailure](is-failure.md) | `abstract fun isFailure(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Return True if the given value is a Failure, False otherwise. |
| [isSuccess](is-success.md) | `abstract fun isSuccess(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Return True if the given value is a Success, False otherwise. |
| [map](map.md) | `fun <A> map(f: (R) -> A): ValidationNEL<L, A>`<br>The given function is applied if this is a Success. |
| [or](or.md) | `infix fun or(value: ValidationNEL<L, R>): ValidationNEL<L, R>`<br>Similar to orElse, but the argument is evaluated before. |
| [orElse](or-else.md) | `infix fun orElse(alternative: () -> ValidationNEL<L, R>): ValidationNEL<L, R>`<br>Returns the value from this Success or the given argument if this is Failure. |
| [toEither](to-either.md) | `fun toEither(): `[`Either`](../../org.funky.either/-either/index.md)`<`[`NonEmptyList`](../-non-empty-list.md)`<L>, R>`<br>Returns an instance of Eiher that is equivalent to the receiver (Right &lt;-&gt; Success, Left &lt;-&gt; Failure) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [failure](failure.md) | `fun <L, R> failure(left: L): ValidationNEL<L, R>`<br>Constructor of Failure from a single value.`fun <L, R> failure(nel: `[`NonEmptyList`](../-non-empty-list.md)`<L>): ValidationNEL<L, R>`<br>Constructor of Failure from a list of values. If the list is empty, it will throw an IllegalArgumentException |
| [success](success.md) | `fun <L, R> success(right: R): ValidationNEL<L, R>`<br>Constructor of Success from a value |

### Extension Functions

| Name | Summary |
|---|---|
| [ap](../ap.md) | `infix fun <L, R, A> ValidationNEL<L, (A) -> R>.ap(valX: ValidationNEL<L, A>): ValidationNEL<L, R>`<br>Sequential application of ValidationNEL as applicative functor. |
| [ap2](../ap2.md) | `fun <L, A, B, R> ValidationNEL<L, (A, B) -> R>.ap2(valX: ValidationNEL<L, A>, valY: ValidationNEL<L, B>): ValidationNEL<L, R>`<br>Sequential application of ValidationNEL as applicative functor. |
| [flatten](../flatten.md) | `fun <L, R> ValidationNEL<L, ValidationNEL<L, R>>.flatten(): ValidationNEL<L, R>`<br>Transforms a nested Either, ie, a ValidationNEL of type ValidationNEL&lt;L, ValidationNEL&lt;L, R&gt;&gt;, into an un-nested ValidationNEL, ie, an ValidationNEL of type ValidationNEL&lt;L, R&gt;. |

### Inheritors

| Name | Summary |
|---|---|
| [Failure](-failure/index.md) | `class Failure<L, R> : ValidationNEL<L, R>` |
| [Success](-success/index.md) | `class Success<L, R> : ValidationNEL<L, R>` |

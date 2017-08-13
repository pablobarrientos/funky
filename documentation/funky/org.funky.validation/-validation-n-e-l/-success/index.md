[funky](../../../index.md) / [org.funky.validation](../../index.md) / [ValidationNEL](../index.md) / [Success](.)

# Success

`class Success<L, R> : `[`ValidationNEL`](../index.md)`<L, R>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Success(r: R)` |

### Properties

| Name | Summary |
|---|---|
| [r](r.md) | `val r: R` |

### Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | `fun component1(): `[`NonEmptyList`](../../-non-empty-list.md)`<L>?`<br>Kotlin projection of ValidationNEL's first component (failure). Returns null id the instance is Success. |
| [component2](component2.md) | `fun component2(): R`<br>Kotlin projection of ValidationNEL's second component (success). Returns null id the instance is Failure. |
| [equals](equals.md) | `fun equals(other: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | `fun hashCode(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isFailure](is-failure.md) | `fun isFailure(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Return True if the given value is a Failure, False otherwise. |
| [isSuccess](is-success.md) | `fun isSuccess(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Return True if the given value is a Success, False otherwise. |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [toEither](../to-either.md) | `fun toEither(): `[`Either`](../../../org.funky.either/-either/index.md)`<`[`NonEmptyList`](../../-non-empty-list.md)`<L>, R>`<br>Returns an instance of Eiher that is equivalent to the receiver (Right &lt;-&gt; Success, Left &lt;-&gt; Failure) |

[funky](../../../index.md) / [org.funky.controltry](../../index.md) / [Try](../index.md) / [Success](.)

# Success

`class Success<T> : `[`Try`](../index.md)`<T>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Success(t: T)` |

### Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | `fun equals(other: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [get](get.md) | `fun get(): T`<br>Returns the value from this Success or throws the exception if this is a Failure. |
| [hashCode](hash-code.md) | `fun hashCode(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isFailure](is-failure.md) | `fun isFailure(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true if the Try is a Failure, false otherwise. |
| [isSuccess](is-success.md) | `fun isSuccess(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true if the Try is a Success, false otherwise. |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [andThen](../and-then.md) | `fun andThen(runnable: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Try`](../index.md)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>`<br>Executes runnable in case the receiver is Success, wrapping the result in a new Try. |
| [failed](../failed.md) | `fun failed(): `[`Try`](../index.md)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>`<br>Completes this Try with an exception wrapped in a Success. |
| [onFailure](../on-failure.md) | `fun onFailure(body: (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Try`](../index.md)`<T>`<br>Executes body in case the receiver is Failure. Returns the receiver. |
| [toEither](../to-either.md) | `fun toEither(): `[`Either`](../../../org.funky.either/-either/index.md)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, T>`<br>Returns Either.Left if this is a Failure orn Either,Right containing the value if this is a Success. |
| [toOption](../to-option.md) | `fun toOption(): `[`Option`](../../../org.funky.option/-option/index.md)`<T>`<br>Returns None if this is a Failure or a Some containing the value if this is a Success. |

[funky](../../../index.md) / [org.funky.either](../../index.md) / [Either](../index.md) / [Right](.)

# Right

`class Right<L, R> : `[`Either`](../index.md)`<L, R>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Right(r: R)` |

### Properties

| Name | Summary |
|---|---|
| [r](r.md) | `val r: R` |

### Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | `fun component1(): L?`<br>Kotlin projection of Either's first component (left). Returns null id the instance is Right. |
| [component2](component2.md) | `fun component2(): R`<br>Kotlin projection of Either's second component (right). Returns null id the instance is Left. |
| [equals](equals.md) | `fun equals(other: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | `fun hashCode(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isLeft](is-left.md) | `fun isLeft(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Return True if the given value is a Left-value, False otherwise. |
| [isRight](is-right.md) | `fun isRight(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Return True if the given value is a Right-value, False otherwise. |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [swap](../swap.md) | `fun swap(): `[`Either`](../index.md)`<R, L>`<br>If this is a `Left`, then return the left value in `Right` or vice versa. |
| [toValidationNEL](../to-validation-n-e-l.md) | `fun toValidationNEL(): `[`ValidationNEL`](../../../org.funky.validation/-validation-n-e-l/index.md)`<L, R>`<br>Returns an instance of ValidationNEL that is equivalent to the receiver (Right &lt;-&gt; Success, Left &lt;-&gt; Failure) |

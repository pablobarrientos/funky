[funky](../../../index.md) / [org.funky.option](../../index.md) / [Option](../index.md) / [None](.)

# None

`object None : `[`Option`](../index.md)`<`[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html)`>`

### Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | `fun equals(other: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [get](get.md) | `fun get(): `[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html)<br>Returns the option's value. Throw NoSuchElementException if the Option is empty |
| [hashCode](hash-code.md) | `fun hashCode(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isEmpty](is-empty.md) | `fun isEmpty(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true if the option is None, false otherwise |

### Inherited Functions

| Name | Summary |
|---|---|
| [isDefined](../is-defined.md) | `fun isDefined(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates if the receiver is a non-empty Option |
| [nonEmpty](../non-empty.md) | `fun nonEmpty(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates if the receiver is a non-empty Option |
| [orNull](../or-null.md) | `fun orNull(): A?`<br>Converts this Option to a nullable value. |
| [toList](../to-list.md) | `fun toList(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>`<br>Returns a singleton list containing the Option's value if it is non-empty, or the empty list if the Option is empty. |

[funky](../../index.md) / [org.funky.either](../index.md) / [Either](.)

# Either

`sealed class Either<L, R>`

Represents a value of one of two possible types (a disjoint union.)
An instance of `Either` is an instance of either Left or Right.

The Either type is sometimes used to represent a value which is either correct or an error.
A common use of `Either` is as an alternative to Option for dealing with possibly missing values. In this usage,
None is replaced with a Left which can contain useful information.
Right takes the place of Some. Convention dictates that `Left` is used for failure and `Right` is used for success.

For example, you could use `Either<String, Int>` to indicate whether a received input is a `String` or an `Int`.

### Types

| Name | Summary |
|---|---|
| [Left](-left/index.md) | `class Left<L, R> : Either<L, R>` |
| [Right](-right/index.md) | `class Right<L, R> : Either<L, R>` |

### Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | `abstract operator fun component1(): L?`<br>Kotlin projection of Either's first component (left). Returns null id the instance is Right. |
| [component2](component2.md) | `abstract operator fun component2(): R?`<br>Kotlin projection of Either's second component (right). Returns null id the instance is Left. |
| [exists](exists.md) | `fun exists(p: (R) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns `false` if Left or returns the result of the application of the given predicate to the Right value. |
| [flatMap](flat-map.md) | `fun <A> flatMap(f: (R) -> Either<L, A>): Either<L, A>`<br>Returns the given function applied to the value if the receiver is Right or returns a Left if it is Left. |
| [fold](fold.md) | `fun <A> fold(isLeft: (L) -> A, isRight: (R) -> A): A`<br>Catamorphism for Either. Applies isLeft if this is a Left or isRight if this is Right. |
| [forEach](for-each.md) | `fun forEach(f: (R) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Executes the given side-effecting function if this is a Left. |
| [getOrElse](get-or-else.md) | `infix fun getOrElse(default: () -> R): R`<br>Returns the value from this Left or the given argument if this is a Right. |
| [isLeft](is-left.md) | `abstract fun isLeft(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns True if the given value is a Left-value, False otherwise. |
| [isRight](is-right.md) | `abstract fun isRight(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns True if the given value is a Right-value, False otherwise. |
| [map](map.md) | `fun <A> map(f: (R) -> A): Either<L, A>`<br>The given function is applied if this is a Right. |
| [or](or.md) | `infix fun or(value: Either<L, R>): Either<L, R>`<br>Similar to orElse, but the argument is evaluated before. |
| [orElse](or-else.md) | `infix fun orElse(alternative: () -> Either<L, R>): Either<L, R>`<br>Returns the value from this Right or the given argument if this is a Left. |
| [swap](swap.md) | `fun swap(): Either<R, L>`<br>If this is a `Left`, then return the left value in `Right` or vice versa. |
| [toValidationNEL](to-validation-n-e-l.md) | `fun toValidationNEL(): `[`ValidationNEL`](../../org.funky.validation/-validation-n-e-l/index.md)`<L, R>`<br>Returns an instance of ValidationNEL that is equivalent to the receiver (Right &lt;-&gt; Success, Left &lt;-&gt; Failure) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [left](left.md) | `fun <L, R> left(left: L): Either<L, R>`<br>Constructor of left. |
| [right](right.md) | `fun <L, R> right(right: R): Either<L, R>`<br>Constructor of right. |

### Extension Functions

| Name | Summary |
|---|---|
| [ap](../ap.md) | `infix fun <L, R, A> Either<L, (A) -> R>.ap(eitherR: Either<L, A>): Either<L, R>`<br>Sequential application of Either as applicative functor. |
| [ap2](../ap2.md) | `fun <L, A, B, R> Either<L, (A, B) -> R>.ap2(eitherA: Either<L, A>, eitherB: Either<L, B>): Either<L, R>`<br>Sequential application of Either as applicative functor. |
| [flatten](../flatten.md) | `fun <L, R> Either<L, Either<L, R>>.flatten(): Either<L, R>`<br>Transforms a nested Either, ie, a Either of type Either&lt;L, Either&lt;L, R&gt;&gt;, into an un-nested Either, ie, an Either of type Either&lt;L, R&gt;. |
| [merge](../merge.md) | `fun <A> Either<A, A>.merge(): A`<br>In case both components of Either are equal, merge returns the A and removes the Either. |

### Inheritors

| Name | Summary |
|---|---|
| [Left](-left/index.md) | `class Left<L, R> : Either<L, R>` |
| [Right](-right/index.md) | `class Right<L, R> : Either<L, R>` |

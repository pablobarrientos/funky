[funky](../../index.md) / [org.funky.option](../index.md) / [Option](.)

# Option

`sealed class Option<out A>`

Represents optional values. Instances of Option are either an instance of Some or the object None.
The most idiomatic way to use an Option instance is to treat it as a collection of zero or one element and use
map,flatMap, filter, or foreach.

### Types

| [None](-none/index.md) | `object None : Option<`[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html)`>` |
| [Some](-some/index.md) | `class Some<out A> : Option<A>` |

### Functions

| [exists](exists.md) | `fun exists(p: (A) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true if this option is non-empty and the predicate p returns true when applied to this Option's value. |
| [filter](filter.md) | `fun filter(p: (A) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): Option<A>`<br>Returns this Option if it is nonEmpty and applying the predicate p to this Option's value returns true. |
| [filterNot](filter-not.md) | `fun filterNot(p: (A) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): Option<A>`<br>Returns this Option if it is non-empty and applying the predicate p to this Option's value returns false |
| [flatMap](flat-map.md) | `fun <B> flatMap(f: (A) -> Option<B>): Option<B>`<br>Returns the result of applying f to this Option's value if this Option is nonEmpty. |
| [fold](fold.md) | `fun <B> fold(ifNone: () -> B, ifSome: (A) -> B): B`<br>Returns the result of applying ifSome to this Option's value if the Option is nonEmpty. Otherwise, evaluates expression ifNone. |
| [forEach](for-each.md) | `fun forEach(f: (A) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Apply the given procedure f to the option's value, if it is non-empty |
| [get](get.md) | `abstract fun get(): A`<br>Returns the option's value. Throw NoSuchElementException if the Option is empty |
| [isDefined](is-defined.md) | `fun isDefined(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates it the receiver is a non-empty Option |
| [isEmpty](is-empty.md) | `abstract fun isEmpty(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true if the option is None, false otherwise |
| [map](map.md) | `fun <B> map(f: (A) -> B): Option<B>`<br>The given function is applied if this is a non-empty Option. |
| [nonEmpty](non-empty.md) | `fun nonEmpty(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates it the receiver is a non-empty Option |
| [orNull](or-null.md) | `fun orNull(): A?`<br>Converts this Option to a nullable value. |
| [toList](to-list.md) | `fun toList(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>`<br>Returns a singleton list containing the Option's value if it is non-empty, or the empty list if the Option is empty. |

### Companion Object Functions

| [empty](empty.md) | `fun <A> empty(): Option<A>`<br>Returns an empty Option of type A. |
| [of](of.md) | `fun <A> of(t: A?): Option<A>`<br>Build a non-empty Option in case the receiver is not null |

### Extension Functions

| [ap](../ap.md) | `infix fun <A, B> Option<(A) -> B>.ap(optA: Option<A>): Option<B>`<br>Sequential application of Option as applicative functor. |
| [ap2](../ap2.md) | `fun <A, B, C> Option<(A, B) -> C>.ap2(optA: Option<A>, optB: Option<B>): Option<C>`<br>Sequential application of Option as applicative functor. |
| [flatten](../flatten.md) | `fun <A> Option<Option<A>>.flatten(): Option<A>`<br>Transforms a nested Option, ie, a Option of type Option&lt;Option&gt;, into an un-nested Option, ie, an Option of type Option&lt; R&gt;. |
| [getOrElse](../get-or-else.md) | `infix fun <A> Option<A>.getOrElse(default: () -> A): A`<br>Returns the option's value if the option is non-empty, otherwise return the result of evaluating default. |
| [or](../or.md) | `infix fun <A> Option<A>.or(value: Option<A>): Option<A>`<br>Returns this Option if it is non-empty, otherwise return the (pre-evaluated) alternative. |
| [orElse](../or-else.md) | `infix fun <A> Option<A>.orElse(alternative: () -> Option<A>): Option<A>`<br>Returns this Option if it is non-empty, otherwise return the result of evaluating alternative. |
| [toFailure](../../org.funky.validation/to-failure.md) | `fun <L, R> Option<L>.toFailure(right: () -> R): `[`ValidationNEL`](../../org.funky.validation/-validation-n-e-l/index.md)`<L, R>`<br>Builder of Failure from Option type. In case the receiver is non-empty, Success will be its value. |
| [toLeft](../../org.funky.either/to-left.md) | `fun <L, R> Option<L>.toLeft(right: () -> R): `[`Either`](../../org.funky.either/-either/index.md)`<L, R>`<br>Builder of Left from Option type. In case the receiver is Right, right will be its value. |
| [toRight](../../org.funky.either/to-right.md) | `fun <L, R> Option<R>.toRight(left: () -> L): `[`Either`](../../org.funky.either/-either/index.md)`<L, R>`<br>Builder of Right from Option type |
| [toSuccess](../../org.funky.validation/to-success.md) | `fun <L, R> Option<R>.toSuccess(left: () -> L): `[`ValidationNEL`](../../org.funky.validation/-validation-n-e-l/index.md)`<L, R>`<br>Builder of Success from Option type |

### Inheritors

| [None](-none/index.md) | `object None : Option<`[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html)`>` |
| [Some](-some/index.md) | `class Some<out A> : Option<A>` |


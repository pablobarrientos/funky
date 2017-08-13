[funky](../index.md) / [org.funky.option](.)

## Package org.funky.option

### Types

| [Option](-option/index.md) | `sealed class Option<out A>`<br>Represents optional values. Instances of Option are either an instance of Some or the object None. The most idiomatic way to use an Option instance is to treat it as a collection of zero or one element and use map,flatMap, filter, or foreach. |

### Extensions for External Classes

| [kotlin.Array](kotlin.-array/index.md) |  |
| [kotlin.BooleanArray](kotlin.-boolean-array/index.md) |  |
| [kotlin.ByteArray](kotlin.-byte-array/index.md) |  |
| [kotlin.CharArray](kotlin.-char-array/index.md) |  |
| [kotlin.DoubleArray](kotlin.-double-array/index.md) |  |
| [kotlin.FloatArray](kotlin.-float-array/index.md) |  |
| [kotlin.Function1](kotlin.-function1/index.md) |  |
| [kotlin.Function2](kotlin.-function2/index.md) |  |
| [kotlin.IntArray](kotlin.-int-array/index.md) |  |
| [kotlin.LongArray](kotlin.-long-array/index.md) |  |
| [kotlin.ShortArray](kotlin.-short-array/index.md) |  |
| [kotlin.String](kotlin.-string/index.md) |  |
| [kotlin.collections.Iterable](kotlin.collections.-iterable/index.md) |  |
| [kotlin.collections.List](kotlin.collections.-list/index.md) |  |
| [kotlin.sequences.Sequence](kotlin.sequences.-sequence/index.md) |  |

### Functions

| [ap](ap.md) | `infix fun <A, B> `[`Option`](-option/index.md)`<(A) -> B>.ap(optA: `[`Option`](-option/index.md)`<A>): `[`Option`](-option/index.md)`<B>`<br>Sequential application of Option as applicative functor. |
| [ap2](ap2.md) | `fun <A, B, C> `[`Option`](-option/index.md)`<(A, B) -> C>.ap2(optA: `[`Option`](-option/index.md)`<A>, optB: `[`Option`](-option/index.md)`<B>): `[`Option`](-option/index.md)`<C>`<br>Sequential application of Option as applicative functor. |
| [flatten](flatten.md) | `fun <A> `[`Option`](-option/index.md)`<`[`Option`](-option/index.md)`<A>>.flatten(): `[`Option`](-option/index.md)`<A>`<br>Transforms a nested Option, ie, a Option of type Option&lt;Option&gt;, into an un-nested Option, ie, an Option of type Option&lt; R&gt;. |
| [getOrElse](get-or-else.md) | `infix fun <A> `[`Option`](-option/index.md)`<A>.getOrElse(default: () -> A): A`<br>Returns the option's value if the option is non-empty, otherwise return the result of evaluating default. |
| [optionTry](option-try.md) | `fun <A> optionTry(action: () -> A): `[`Option`](-option/index.md)`<A>`<br>Wraps an action in an Option. In case the action execution throws an exception it returns None, otherwise it returns a non-empty Option with the result of action. |
| [or](or.md) | `infix fun <A> `[`Option`](-option/index.md)`<A>.or(value: `[`Option`](-option/index.md)`<A>): `[`Option`](-option/index.md)`<A>`<br>Returns this Option if it is non-empty, otherwise return the (pre-evaluated) alternative. |
| [orElse](or-else.md) | `infix fun <A> `[`Option`](-option/index.md)`<A>.orElse(alternative: () -> `[`Option`](-option/index.md)`<A>): `[`Option`](-option/index.md)`<A>`<br>Returns this Option if it is non-empty, otherwise return the result of evaluating alternative. |
| [pure](pure.md) | `fun <A> A.pure(): `[`Option`](-option/index.md)`<A>`<br>Embed pure values in Option. Lift a value. |
| [toOption](to-option.md) | `fun <A> A?.toOption(): `[`Option`](-option/index.md)`<A>`<br>Convert any value to Option. |


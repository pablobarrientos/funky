[funky](../index.md) / [org.funky.reader](.)

## Package org.funky.reader

### Type Aliases

| Name | Summary |
|---|---|
| [F](-f.md) | `typealias F<X, Y> = (X) -> Y`<br>Extension functions to represent the applicative functor for the type "(A) -&gt;". It is known as the "reader" or "environment" applicative functor. It allows a parameter to be passed across functions. From a Functor point of view this "container" stores the value A. Given a particular environment, you can retrieve the corresponding value by simply applying the function. |
| [F2](-f2.md) | `typealias F2<X, Y, Z> = (X, Y) -> Z` |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [kotlin.Function1](kotlin.-function1/index.md) |  |
| [kotlin.Function2](kotlin.-function2/index.md) |  |
| [kotlin.collections.List](kotlin.collections.-list/index.md) |  |

### Functions

| Name | Summary |
|---|---|
| [pure](pure.md) | `fun <A, B> A.pure(): `[`F`](-f.md)`<B, A>`<br>Embeds pure values. Lifts a value to a function. Naturally, it is the const function. |

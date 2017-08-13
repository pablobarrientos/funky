[funky](../index.md) / [org.funky.controltry](.)

## Package org.funky.controltry

### Types

| Name | Summary |
|---|---|
| [Try](-try/index.md) | `sealed class Try<T>`<br>The Try type represents a computation that may either result in an exception, or return a successfully computed value. It's similar to, but semantically different Either type. |

### Functions

| Name | Summary |
|---|---|
| [Try](-try.md) | `fun <T> Try(body: () -> T): `[`Try`](-try/index.md)`<T>`<br>Utility function to wrap any code as a Try instance. |
| [flatten](flatten.md) | `fun <T> `[`Try`](-try/index.md)`<`[`Try`](-try/index.md)`<T>>.flatten(): `[`Try`](-try/index.md)`<T>`<br>Transforms a nested Try, ie, a Try of type Try&lt;Try&gt;, into an un-nested Try, ie, a Try of type Try. |

[funky](../index.md) / [org.funky.validation](index.md) / [toFailure](.)

# toFailure

`inline fun <L, R> `[`Option`](../org.funky.option/-option/index.md)`<L>.toFailure(success: () -> R): `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, R>`

Builder of Failure from Option type. In case the receiver is non-empty, success will be its value.


[funky](../index.md) / [org.funky.validation](index.md) / [validationNelTry](.)

# validationNelTry

`inline fun <T> validationNelTry(body: () -> T): `[`ValidationNEL`](-validation-n-e-l/index.md)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, T>`

Wraps an action in a ValidationNEL. In case the action execution throws an exception it returns a Failure, otherwise
it returns Success with the result of action.


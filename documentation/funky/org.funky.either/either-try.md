[funky](../index.md) / [org.funky.either](index.md) / [eitherTry](.)

# eitherTry

`inline fun <T> eitherTry(action: () -> T): `[`Either`](-either/index.md)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, T>`

Wraps an action in an Either. In case the action execution throws an exception it returns a Left, otherwise it
returns a Right with the result of action.


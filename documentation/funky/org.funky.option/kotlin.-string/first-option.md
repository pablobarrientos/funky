[funky](../../index.md) / [org.funky.option](../index.md) / [kotlin.String](index.md) / [firstOption](.)

# firstOption

`fun `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.firstOption(): `[`Option`](../-option/index.md)`<`[`Char`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html)`>`
`inline fun `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.firstOption(p: (`[`Char`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html)`) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Option`](../-option/index.md)`<`[`Char`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html)`>`

Extracts the first element and wraps it in an Option. Returns None is the receiver has no elements.


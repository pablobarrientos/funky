[funky](../index.md) / [org.funky.util](index.md) / [unless](.)

# unless

`fun unless(condition: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): (() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Given the condition, it will execute the function if the condition is false.

unless (x == 0) { y = 100 / x }


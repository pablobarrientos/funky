[funky](../../index.md) / [org.funky.util](../index.md) / [kotlin.Function1](index.md) / [until](.)

# until

`tailrec fun <T> ((T) -> T).until(condition: (T) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, initialValue: T): T`

Until yields the result of applying the function until condition holds.

var count = 0
{ x:Int -&gt;
count = count + 1
x + 2
}.until( {it &gt; 0}, -10)


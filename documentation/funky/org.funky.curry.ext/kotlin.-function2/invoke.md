[funky](../../index.md) / [org.funky.curry.ext](../index.md) / [kotlin.Function2](index.md) / [invoke](.)

# invoke

`operator fun <A, B, C> ((A, B) -> C).invoke(a: A): (B) -> C`

Syntax extension to partially apply uncurried functions.

E.g.:
val sum2: (Int, Int) -&gt; Int = { x, y -&gt; x + y }

val inc: (Int) -&gt; Int = (sum2)(1)


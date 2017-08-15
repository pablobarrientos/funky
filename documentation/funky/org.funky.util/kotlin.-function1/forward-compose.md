[funky](../../index.md) / [org.funky.util](../index.md) / [kotlin.Function1](index.md) / [forwardCompose](.)

# forwardCompose

`infix fun <A, B, C> ((A) -> B).forwardCompose(f: (B) -> C): (A) -> C`

Composition of function in reverse order: (f forwardCompose g)(x) &lt;=&gt; g(f(x)).
This function is useful if we chain functions and provide the "initial input" at the end of the chain.


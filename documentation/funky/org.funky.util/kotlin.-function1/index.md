[funky](../../index.md) / [org.funky.util](../index.md) / [kotlin.Function1](.)

### Extensions for kotlin.Function1

| Name | Summary |
|---|---|
| [andThen](and-then.md) | `infix fun <A, B, C> ((A) -> B).andThen(f: (B) -> C): (A) -> C`<br>Alias for forwardCompose |
| [compose](compose.md) | `infix fun <A, B, C> ((B) -> C).compose(f: (A) -> B): (A) -> C`<br>Function composition. I.e. (f compose g)(x) &lt;=&gt; f(g(x)) |
| [forwardCompose](forward-compose.md) | `infix fun <A, B, C> ((A) -> B).forwardCompose(f: (B) -> C): (A) -> C`<br>Composition of function in reverse order: (f forwardCompose g)(x) &lt;=&gt; g(f(x)). This function is useful if we chain functions and provide the "initial input" at the end of the chain. |
| [until](until.md) | `tailrec fun <T> ((T) -> T).until(condition: (T) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, initialValue: T): T`<br>Until yields the result of applying the function until condition holds. |

[funky](../../index.md) / [org.funky.validation](../index.md) / [kotlin.collections.List](index.md) / [sequence](.)

# sequence

`fun <L, A> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ValidationNEL`](../-validation-n-e-l/index.md)`<L, A>>.sequence(): `[`ValidationNEL`](../-validation-n-e-l/index.md)`<L, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<A>>`

Evaluates each action in the structure from left to right, and collects the results. It also gathers together
applicative effects.
It "flips" List&lt;ValidationNEL&lt;L, A&gt;&gt; to ValidationNEL&lt;L, List&gt;.

This function is interesting to accumulate validation errors (or the list of results) from a list of ValidationNEL.

E.g.: validate name and surname, lift Person's constructor to ValidationNEL and get a ValidationNel&lt;String, Person&gt;

val validationNameNotEmpty: ValidationNEL&lt;String, String&gt; = ... some function validating the person's name and returning it
val validationSurnameNotEmpty: ValidationNEL&lt;String, String&gt; = ... some function validating the person's surname and returning it
val personOrErrors: ValidationNel&lt;String, Person&gt; =
    ::Person.liftA2(
           listOf(validationNameNotEmpty, validationSurnameNotEmpty).sequence)


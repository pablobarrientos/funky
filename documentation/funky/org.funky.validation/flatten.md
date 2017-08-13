[funky](../index.md) / [org.funky.validation](index.md) / [flatten](.)

# flatten

`fun <L, R> `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, R>>.flatten(): `[`ValidationNEL`](-validation-n-e-l/index.md)`<L, R>`

Transforms a nested Either, ie, a ValidationNEL of type ValidationNEL&lt;L, ValidationNEL&lt;L, R&gt;&gt;, into an un-nested
ValidationNEL, ie, an ValidationNEL of type ValidationNEL&lt;L, R&gt;.


[funky](../index.md) / [org.funky.either](index.md) / [flatten](.)

# flatten

`fun <L, R> `[`Either`](-either/index.md)`<L, `[`Either`](-either/index.md)`<L, R>>.flatten(): `[`Either`](-either/index.md)`<L, R>`

Transforms a nested Either, ie, a Either of type Either&lt;L, Either&lt;L, R&gt;&gt;, into an un-nested Either, ie, an Either of type Either&lt;L, R&gt;.


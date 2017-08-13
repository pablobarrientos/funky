[funky](../index.md) / [org.funky.either](index.md) / [toLeft](.)

# toLeft

`fun <L, R> L.toLeft(): `[`Either`](-either/index.md)`<L, R>`

Builder of Left from any type

`inline fun <L, R> `[`Option`](../org.funky.option/-option/index.md)`<L>.toLeft(right: () -> R): `[`Either`](-either/index.md)`<L, R>`

Builder of Left from Option type. In case the receiver is Right, right will be its value.


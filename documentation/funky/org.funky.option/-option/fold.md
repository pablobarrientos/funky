[funky](../../index.md) / [org.funky.option](../index.md) / [Option](index.md) / [fold](.)

# fold

`inline fun <B> fold(ifNone: () -> B, ifSome: (A) -> B): B`

Returns the result of applying ifSome to this Option's value if the Option is nonEmpty. Otherwise, evaluates
expression ifNone.


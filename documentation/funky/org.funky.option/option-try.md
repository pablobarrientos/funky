[funky](../index.md) / [org.funky.option](index.md) / [optionTry](.)

# optionTry

`inline fun <A> optionTry(action: () -> A): `[`Option`](-option/index.md)`<A>`

Wraps an action in an Option. In case the action execution throws an exception it returns None, otherwise it
returns a non-empty Option with the result of action.


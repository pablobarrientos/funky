[funky](../../index.md) / [org.funky.controltry](../index.md) / [Try](.)

# Try

`sealed class Try<T>`

The Try type represents a computation that may either result in an exception, or return a successfully computed value.
It's similar to, but semantically different Either type.

Instances of Try, are either an instance of Success or Failure.

For example, Try can be used to perform division on a user-defined input, without the need to do explicit exception-handling in all of the places that an exception might occur.
fun divide(): Try {
        println("Enter an Int that you'd like to divide:")
        val dividend = Try { readLine()?.toInt() }
        println("Enter an Int that you'd like to divide by:")
        val divisor = Try { readLine()?.toInt() }
        val problem = dividend.flatMap { x -&gt; divisor.map { y -&gt; x!!.div(y!!) } }
        when(problem) {
            is Try.Success -&gt; {
                println("Result of ${dividend.get()} / ${divisor.get()} is: ${problem.get()}")
            }
            is Try.Failure&lt;*&gt; -&gt; {
                println("You must've divided by zero or entered something that's not an Int. Try again!")
                println("Info from the exception: ${problem.throwable.message}")
            }
        }
        return problem
    }

### Types

| Name | Summary |
|---|---|
| [Failure](-failure/index.md) | `class Failure<T> : Try<T>` |
| [Success](-success/index.md) | `class Success<T> : Try<T>` |

### Functions

| Name | Summary |
|---|---|
| [andThen](and-then.md) | `fun andThen(runnable: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): Try<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>`<br>Executes runnable in case the receiver is Success, wrapping the result in a new Try. |
| [exists](exists.md) | `fun exists(predicate: (T) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates if the receiver is a Success and its value satisfies the predicate. |
| [failed](failed.md) | `fun failed(): Try<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>`<br>Completes this Try with an exception wrapped in a Success. |
| [filter](filter.md) | `fun filter(predicate: (T) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): Try<T>`<br>Converts this to a Failure if the predicate is not satisfied. |
| [flatMap](flat-map.md) | `fun <U> flatMap(f: (T) -> Try<U>): Try<U>`<br>Returns the given function applied to the value from this Success or returns this if this is a Failure. |
| [fold](fold.md) | `fun <X> fold(s: (T) -> X, f: (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> X): X`<br>Catamorphism for Try. Applies f if this is a Failure or s if this is a Success. If s is initially applied and throws an exception, then f is applied with this exception. |
| [foreach](foreach.md) | `fun foreach(f: (T) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Applies the given function f if this is a Success, otherwise returns Unit if this is a Failure. |
| [get](get.md) | `abstract fun get(): T`<br>Returns the value from this Success or throws the exception if this is a Failure. |
| [getOrElse](get-or-else.md) | `fun getOrElse(alternative: () -> T): T`<br>Returns the value from this Success or the given default argument if this is a Failure. |
| [isFailure](is-failure.md) | `abstract fun isFailure(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true if the Try is a Failure, false otherwise. |
| [isSuccess](is-success.md) | `abstract fun isSuccess(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true if the Try is a Success, false otherwise. |
| [map](map.md) | `fun <U> map(f: (T) -> U): Try<U>`<br>Maps the given function to the value from this Success or returns this if this is a Failure. |
| [onFailure](on-failure.md) | `fun onFailure(body: (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): Try<T>`<br>Executes body in case the receiver is Failure. Returns the receiver. |
| [onSuccess](on-success.md) | `fun onSuccess(body: (T) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): Try<T>`<br>Executes body in case the receiver is Success. Returns the receiver. |
| [orElse](or-else.md) | `infix fun orElse(alternative: () -> Try<T>): Try<T>`<br>Returns this Try if it's a Success or the given default argument if this is a Failure. |
| [recover](recover.md) | `fun recover(f: (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> Try<T>): Try<T>`<br>Applies the given function f if this is a Failure, otherwise returns this if this is a Success. |
| [toEither](to-either.md) | `fun toEither(): `[`Either`](../../org.funky.either/-either/index.md)`<`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, T>`<br>Returns Either.Left if this is a Failure orn Either,Right containing the value if this is a Success. |
| [toOption](to-option.md) | `fun toOption(): `[`Option`](../../org.funky.option/-option/index.md)`<T>`<br>Returns None if this is a Failure or a Some containing the value if this is a Success. |
| [transform](transform.md) | `fun <U> transform(s: (T) -> Try<U>, f: (`[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`) -> Try<U>): Try<U>`<br>Completes this Try by applying the function f to this if this is of type Failure, or conversely, by applying s if this is a Success. |

### Extension Functions

| Name | Summary |
|---|---|
| [flatten](../flatten.md) | `fun <T> Try<Try<T>>.flatten(): Try<T>`<br>Transforms a nested Try, ie, a Try of type Try&lt;Try&gt;, into an un-nested Try, ie, a Try of type Try. |

### Inheritors

| Name | Summary |
|---|---|
| [Failure](-failure/index.md) | `class Failure<T> : Try<T>` |
| [Success](-success/index.md) | `class Success<T> : Try<T>` |

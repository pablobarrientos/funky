[funky](../../index.md) / [org.funky.reader](../index.md) / [kotlin.Function1](.)

### Extensions for kotlin.Function1

| Name | Summary |
|---|---|
| [ap](ap.md) | `infix fun <A, B, C> `[`F`](../-f.md)`<C, (A) -> B>.ap(f: `[`F`](../-f.md)`<C, A>): `[`F`](../-f.md)`<C, B>`<br>Sequential application of Function as applicative functor. It passes the "environment" across all functions involved in the operation. |
| [ap2](ap2.md) | `fun <A, B, C, D> `[`F`](../-f.md)`<D, (A, B) -> C>.ap2(fa: `[`F`](../-f.md)`<D, A>, fb: `[`F`](../-f.md)`<D, B>): `[`F`](../-f.md)`<D, C>`<br>Sequential application of Function as applicative functor. It passes the "environment" across all functions involved in the operation. |
| [flatMap](flat-map.md) | `fun <A, B, C> `[`F`](../-f.md)`<A, B>.flatMap(f: (B) -> `[`F`](../-f.md)`<A, C>): `[`F`](../-f.md)`<A, C>`<br>It "extracts" the value from the receiver and passes it to f to produce the result. |
| [liftA](lift-a.md) | `fun <A, B, C> ((A) -> B).liftA(): (`[`F`](../-f.md)`<C, A>) -> `[`F`](../-f.md)`<C, B>`<br>Lifts a function to actions. |
| [map](map.md) | `fun <A, B, C> `[`F`](../-f.md)`<B, C>.map(f: `[`F`](../-f.md)`<A, B>): `[`F`](../-f.md)`<A, C>`<br>Mapping a function over a function is just like composing the receiver and the argument. |

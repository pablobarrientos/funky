[funky](../index.md) / [org.funky.reader](index.md) / [F](.)

# F

`typealias F<X, Y> = (X) -> Y`

Extension functions to represent the applicative functor for the type "(A) -&gt;".
It is known as the "reader" or "environment" applicative functor. It allows a parameter to be passed across functions.
From a Functor point of view this "container" stores the value A. Given a particular environment, you can retrieve
the corresponding value by simply applying the function.


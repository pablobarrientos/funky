[funky](../../index.md) / [org.funky.curry](../index.md) / [kotlin.Function1](index.md) / [uncurried](.)

# uncurried

`fun <A, B, Z> ((A) -> (B) -> Z).uncurried(): (A, B) -> Z`

Extensions to convert a curried function to an uncurried function.

`fun <A, B, C, Z> ((A) -> (B) -> (C) -> Z).uncurried(): (A, B, C) -> Z`
`fun <A, B, C, D, Z> ((A) -> (B) -> (C) -> (D) -> Z).uncurried(): (A, B, C, D) -> Z`
`fun <A, B, C, D, E, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> Z).uncurried(): (A, B, C, D, E) -> Z`
`fun <A, B, C, D, E, F, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> Z).uncurried(): (A, B, C, D, E, F) -> Z`
`fun <A, B, C, D, E, F, G, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> Z).uncurried(): (A, B, C, D, E, F, G) -> Z`
`fun <A, B, C, D, E, F, G, H, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> Z).uncurried(): (A, B, C, D, E, F, G, H) -> Z`
`fun <A, B, C, D, E, F, G, H, I, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, K, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J, K) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, K, L, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J, K, L) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, K, L, M, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J, K, L, M) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J, K, L, M, N) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> (O) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> (O) -> (P) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> (O) -> (P) -> (Q) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> (O) -> (P) -> (Q) -> (R) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> (O) -> (P) -> (Q) -> (R) -> (S) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> (O) -> (P) -> (Q) -> (R) -> (S) -> (T) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> (O) -> (P) -> (Q) -> (R) -> (S) -> (T) -> (U) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U) -> Z`
`fun <A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, Z> ((A) -> (B) -> (C) -> (D) -> (E) -> (F) -> (G) -> (H) -> (I) -> (J) -> (K) -> (L) -> (M) -> (N) -> (O) -> (P) -> (Q) -> (R) -> (S) -> (T) -> (U) -> (V) -> Z).uncurried(): (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V) -> Z`
package lavahack.kevin

fun <T> combine(
    lambda1 : (T) -> Unit,
    lambda2 : (T) -> Unit
) = { t : T ->
    lambda1(t)
    lambda2(t)
}
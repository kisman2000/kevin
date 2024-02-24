package lavahack.kevin

class Listener<T>(
    val priority : Int = 0,
    val invoker : (T) -> Unit
)
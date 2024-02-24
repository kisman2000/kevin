package lavahack.kevin

import java.util.*

open class EventBus<T> {
    private val listeners = TreeMap<Int, MutableSet<Listener<T>>>()
    private var builtListener : (T) -> Unit = { }

    open fun post(
        event : T
    ) {
        builtListener(event)
    }

    open fun subscribe(
        listener : Listener<T>
    ) {
        val priority = -listener.priority

        if(listeners.contains(priority)) {
            listeners[priority]!!.add(listener)
        } else {
            listeners[priority] = hashSetOf(listener)
        }

        rebuildListeners()
    }

    open fun unsubscribe(
        listener : Listener<T>
    ) {
        val priority = -listener.priority

        if(listeners.contains(priority)) {
            listeners[priority]!!.remove(listener)
        }

        rebuildListeners()
    }

    protected open fun rebuildListeners() {
        builtListener = { }

        for(listeners0 in listeners.values) {
            for(listener in listeners0) {
                builtListener = combine(builtListener, listener.invoker)
            }
        }
    }
}
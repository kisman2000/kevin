package lavahack.kevin;

import java.util.*;

public class EventBus<T> {
    private final TreeMap<Integer, ArrayList<Listener<T>>> listeners = new TreeMap<>();
    private Invokable<T> invoker = event -> { };

    public boolean skipRebuilding = false;

    public void post(T event) {
        invoker.invoke(event);
    }

    public void subscribe(Listener<T> listener) {
        if(listeners.containsKey(-listener.priority())) {
            listeners.get(-listener.priority()).add(listener);
        } else {
            listeners.put(-listener.priority(), new ArrayList<>(List.of(listener)));
        }

        if(!skipRebuilding) {
            rebuildInvoker();
        }
    }

    public void unsubscribe(Listener<T> listener) {
        if(listeners.containsKey(-listener.priority()) && listeners.get(-listener.priority()).remove(listener) && !skipRebuilding) {
            rebuildInvoker();
        }
    }

    protected void rebuildInvoker() {
        invoker = event -> { };

        for(ArrayList<Listener<T>> listeners2 : listeners.values()) {
            for(Listener<T> listener : listeners2) {
                invoker = new Invoker<>(listener.invoker(), invoker);
            }
        }
    }
}

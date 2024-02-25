package lavahack.kevin;

import java.util.function.Consumer;

public record Listener<T>(int priority, Consumer<T> invoker) {
    public Listener(Consumer<T> invoker) {
        this(0, invoker);
    }
}

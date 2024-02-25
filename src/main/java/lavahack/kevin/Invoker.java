package lavahack.kevin;

import java.util.function.Consumer;

public record Invoker<T>(Consumer<T> lambda, Invokable<T> invoker) implements Invokable<T> {
    @Override
    public void invoke(T t) {
        lambda.accept(t);
        invoker.invoke(t);
    }
}

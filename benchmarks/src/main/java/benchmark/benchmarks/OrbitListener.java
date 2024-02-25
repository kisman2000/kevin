package benchmark.benchmarks;

import meteordevelopment.orbit.listeners.IListener;

import java.util.Random;

public class OrbitListener implements IListener {
    @Override
    public void call(Object event) {
        if(event instanceof OrbitEvent) {
            ((OrbitEvent) event).blackhole().consume(Integer.bitCount(Integer.parseInt("123")));
        }
    }

    @Override
    public Class<?> getTarget() {
        return OrbitEvent.class;
    }

    @Override
    public int getPriority() {
        return new Random().nextInt();
    }

    @Override
    public boolean isStatic() {
        return false;
    }
}

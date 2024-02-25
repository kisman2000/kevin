package benchmark.benchmarks;

import benchmark.Constants;
import benchmark.IBenchmark;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import meteordevelopment.orbit.listeners.IListener;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrbitBenchmark implements IBenchmark {
    private final IEventBus BUS = new EventBus();

    public void prepare() {
        try {
            Map<Class<?>, List<IListener>> newListenerMap = new ConcurrentHashMap<>();
            List<IListener> listeners = new ArrayList<>();

            for(int i = 0; i < Constants.LISTENERS; i++) {
                listeners.add(new OrbitListener());
            }

            newListenerMap.put(OrbitEvent.class, listeners);

            Field listenerMapField = EventBus.class.getDeclaredField("listenerMap");

            listenerMapField.setAccessible(true);
            listenerMapField.set(BUS, newListenerMap);
        } catch(Exception ignored) { }
    }

    public void benchmark(Blackhole blackhole) {
        BUS.post(new OrbitEvent(blackhole));
    }
}

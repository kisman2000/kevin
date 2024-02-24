package benchmarks;

import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import meteordevelopment.orbit.listeners.IListener;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
public class OrbitBenchmark {
    private static final IEventBus BUS = new EventBus();

    public static void prepare() throws NoSuchFieldException, IllegalAccessException {
        Map<Class<?>, List<IListener>> newListenerMap = new ConcurrentHashMap<>();
        List<IListener> listeners = new ArrayList<>();

        for(int i = 0; i < Constants.LISTENERS; i++) {
            listeners.add(new OrbitListener());
        }

        newListenerMap.put(OrbitEvent.class, listeners);

        Field listenerMapField = EventBus.class.getDeclaredField("listenerMap");

        listenerMapField.setAccessible(true);
        listenerMapField.set(BUS, newListenerMap);
    }

    @Benchmark
    public static void benchmark(Blackhole blackhole) {
        BUS.post(new OrbitEvent(blackhole));
    }
}

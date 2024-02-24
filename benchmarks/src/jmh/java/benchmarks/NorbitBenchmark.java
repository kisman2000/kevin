package benchmarks;

import io.github.racoondog.norbit.EventBus;
import meteordevelopment.orbit.listeners.IListener;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
public class NorbitBenchmark {
    private static EventBus BUS = null;

    static {
        Map<Class<?>, List<IListener>> listenerMap = new HashMap<>();
        List<IListener> listeners = new ArrayList<>();

        for(int i = 0; i < Constants.LISTENERS; i++) {
            listeners.add(new OrbitListener());
        }

        listenerMap.put(OrbitEvent.class, listeners);

        BUS = new EventBus(new IdentityHashMap<>(), listenerMap, new HashMap<>(), ArrayList::new);
    }

    /*public static void prepare() {

    }*/

    @Benchmark
    public static void benchmark(Blackhole blackhole) {
        BUS.post(new OrbitEvent(blackhole));
    }
}

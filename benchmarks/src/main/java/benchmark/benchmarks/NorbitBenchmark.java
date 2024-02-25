package benchmark.benchmarks;

import benchmark.Constants;
import benchmark.IBenchmark;
import io.github.racoondog.norbit.EventBus;
import meteordevelopment.orbit.listeners.IListener;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;

public class NorbitBenchmark implements IBenchmark {
    private EventBus bus;

    public void prepare() {
        Map<Class<?>, List<IListener>> listenerMap = new HashMap<>();
        List<IListener> listeners = new ArrayList<>();

        for(int i = 0; i < Constants.LISTENERS; i++) {
            listeners.add(new OrbitListener());
        }

        listenerMap.put(OrbitEvent.class, listeners);

        bus = new EventBus(new IdentityHashMap<>(), new HashMap<>(), listenerMap, ArrayList::new);
    }

    public void benchmark(Blackhole blackhole) {
        bus.post(new OrbitEvent(blackhole));
    }
}

package lavahack.kevin.benchmark.benchmarks;

import io.github.racoondog.norbit.EventBus;
import lavahack.kevin.benchmark.BenchmarkEvent;
import lavahack.kevin.benchmark.Constants;
import meteordevelopment.orbit.listeners.ConsumerListener;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.invoke.MethodHandles;
import java.util.function.Consumer;

@State(Scope.Benchmark)
public class NorbitBenchmark {
    private EventBus bus;

    @Setup
    public void setup() {
        bus = Constants.NORBIT_THREADSAFE ? EventBus.threadSafe() : EventBus.threadUnsafe();
        bus.registerLambdaFactory("lavahack.kevin.benchmark.benchmarks", ((lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup())));

        Consumer<EventBus> listenerFactory = Constants.ORBIT_CONSUMER_MODE ?
                bus -> bus.subscribe(new ConsumerListener<>(BenchmarkEvent.class, event -> event.blackhole().consume(Integer.bitCount(Integer.parseInt("123"))))) :
                bus -> bus.subscribe(new OrbitListener());

        for (int i = 0; i < Constants.LISTENERS; i++) {
            listenerFactory.accept(bus);
        }
    }

    @Benchmark
    public void benchmark(Blackhole blackhole) {
        bus.post(new BenchmarkEvent(blackhole));
    }
}

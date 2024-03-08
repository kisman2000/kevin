package lavahack.kevin.benchmark.benchmarks;

import lavahack.kevin.EventBus;
import lavahack.kevin.Listener;
import lavahack.kevin.benchmark.BenchmarkEvent;
import lavahack.kevin.benchmark.Constants;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class KevinBenchmark {
    private EventBus<BenchmarkEvent> bus;

    @Setup
    public void setup() {
        bus = new EventBus<>();

        for (int i = 0; i < Constants.LISTENERS; i++) {
            bus.subscribe(new Listener<>(event -> {
                event.blackhole().consume(Integer.bitCount(Integer.parseInt("123")));
            }));
        }
    }

    @Benchmark
    public void benchmark(Blackhole blackhole) {
        bus.post(new BenchmarkEvent(blackhole));
    }
}

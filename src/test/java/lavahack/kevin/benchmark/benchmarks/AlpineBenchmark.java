package lavahack.kevin.benchmark.benchmarks;

import lavahack.kevin.benchmark.BenchmarkEvent;
import lavahack.kevin.benchmark.Constants;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import me.zero.alpine.listener.Listener;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class AlpineBenchmark {
    private EventBus bus;

    @Setup
    public void prepare() {
        bus = EventManager.builder().setName("meow").build();

        for (int i = 0; i < Constants.LISTENERS; i++) {
            bus.subscribe(new Listener<BenchmarkEvent>(event -> {
                event.blackhole().consume(Integer.bitCount(Integer.parseInt("123")));
            }));
        }
    }

    @Benchmark
    public void benchmark(Blackhole blackhole) {
        bus.post(new BenchmarkEvent(blackhole));
    }
}

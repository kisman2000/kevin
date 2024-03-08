package lavahack.kevin.benchmark.benchmarks;

import lavahack.kevin.benchmark.BenchmarkEvent;
import meteordevelopment.orbit.EventHandler;

public class OrbitListener {
    @EventHandler
    private void onBenchmark(BenchmarkEvent event) {
        event.blackhole().consume(Integer.bitCount(Integer.parseInt("123")));
    }
}

package benchmark.benchmarks;

import benchmark.Constants;
import benchmark.IBenchmark;
import lavahack.kevin.EventBus;
import lavahack.kevin.Listener;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;

public class KevinBenchmark implements IBenchmark {
    public void prepare() {
        KevinEvent.BUS = new EventBus<>();

        for(int i = 0; i < Constants.LISTENERS; i++) {
            KevinEvent.BUS.subscribe(new Listener<>(new Random().nextInt(), event -> event.blackhole().consume(Integer.bitCount(Integer.parseInt("123")))));
        }
    }

    public void benchmark(Blackhole blackhole) {
        KevinEvent.BUS.post(new KevinEvent(blackhole));
    }
}

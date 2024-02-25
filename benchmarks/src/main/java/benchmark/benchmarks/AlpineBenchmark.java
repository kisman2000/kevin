package benchmark.benchmarks;

import benchmark.Constants;
import benchmark.IBenchmark;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import me.zero.alpine.listener.Listener;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;

public class AlpineBenchmark implements IBenchmark {
    private EventBus bus;

    public void prepare() {
        bus = EventManager.builder().setName("meow").build();

        for(int i = 0; i < Constants.LISTENERS; i++) {
            bus.subscribe(new Listener<OrbitEvent>(event -> event.blackhole().consume(Integer.bitCount(Integer.parseInt("123"))), new Random().nextInt()));
        }
    }

    public void benchmark(Blackhole blackhole) {
        bus.post(new OrbitEvent(blackhole));
    }
}

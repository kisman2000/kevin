package benchmark.benchmarks;

import lavahack.kevin.EventBus;
import org.openjdk.jmh.infra.Blackhole;

public record KevinEvent(Blackhole blackhole) {
    //

    public static EventBus<KevinEvent> BUS;
}

package benchmarks;

import org.openjdk.jmh.infra.Blackhole;

public class OrbitEvent {
    public final Blackhole blackhole;

    public OrbitEvent(Blackhole blackhole) {
        this.blackhole = blackhole;
    }
}

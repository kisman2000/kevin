package benchmark;

import org.openjdk.jmh.infra.Blackhole;

public interface IBenchmark {
    void prepare();
    void benchmark(Blackhole blackhole);
}

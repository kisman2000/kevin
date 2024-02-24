package benchmarks;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

public class Benchmarks {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, RunnerException {
        OrbitBenchmark.prepare();
        KevinBenchmark.prepare();

        Options options = new OptionsBuilder()
                .include(OrbitBenchmark.class.getSimpleName())
                .include(KevinBenchmark.class.getSimpleName())
                .include(NorbitBenchmark.class.getSimpleName())
                .verbosity(VerboseMode.EXTRA)
                .build();

        new Runner(options).run();
    }
}

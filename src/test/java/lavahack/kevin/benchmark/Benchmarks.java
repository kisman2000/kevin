package lavahack.kevin.benchmark;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.util.concurrent.TimeUnit;

public class Benchmarks {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include("lavahack\\.kevin\\.benchmark\\.benchmarks\\..*Benchmark\\.benchmark")
                .warmupTime(TimeValue.seconds(5))
                .warmupIterations(5)
                .measurementTime(TimeValue.seconds(5))
                .measurementIterations(5)
                .forks(2)
                .timeUnit(TimeUnit.NANOSECONDS)
                .mode(Mode.AverageTime)
                .verbosity(VerboseMode.EXTRA)
                .build();

        new Runner(options).run();
    }
}

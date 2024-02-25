package benchmark;

import benchmark.benchmarks.AlpineBenchmark;
import benchmark.benchmarks.KevinBenchmark;
import benchmark.benchmarks.NorbitBenchmark;
import benchmark.benchmarks.OrbitBenchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.List;

public class Benchmarks {
    public static void main(String[] args) {
        List<IBenchmark> benchmarks = List.of(new AlpineBenchmark(), new KevinBenchmark(), new NorbitBenchmark(), new OrbitBenchmark());
        Blackhole blackhole = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");

        for(IBenchmark benchmark : benchmarks) {
            long[] timings = new long[Constants.ITERATIONS];
            long minTime = Long.MAX_VALUE;
            long maxTime = Long.MIN_VALUE;

            benchmark.prepare();
            for(int i = 0; i < Constants.WARMUP_ITERATIONS + Constants.ITERATIONS; i++) {

                long nanos = System.nanoTime();

                benchmark.benchmark(blackhole);

                long delta = System.nanoTime() - nanos;

                if(i >= Constants.WARMUP_ITERATIONS) {
                    timings[i - Constants.WARMUP_ITERATIONS] = delta;
                    minTime = Math.min(minTime, delta);
                    maxTime = Math.max(maxTime, delta);
                }
            }

            long median = median(timings);

            System.out.println(benchmark.getClass().getSimpleName() + " " + median + " ± " + ((maxTime - minTime) / 2L));
        }
    }

    private static long median(long... nums) {
        Arrays.sort(nums);

        int length = nums.length;

        if(length % 2 == 0) {
            return (nums[length / 2 - 1] + nums[length / 2]) / 2L;
        } else {
            return nums[length / 2];
        }
    }
}

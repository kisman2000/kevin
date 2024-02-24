package benchmarks

import lavahack.kevin.Listener
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
open class KevinBenchmark {
    companion object {
        @JvmStatic
        fun prepare() {
            for(i in 0..<Constants.LISTENERS) {
                val listener = Listener<KevinEvent>(Random().nextInt()) {
                    it.blackhole.consume(Integer.bitCount(Integer.getInteger("123")))
                }

                KevinEvent.BUS.subscribe(listener)
            }
        }

    }

    @Benchmark
    fun benchmark(
        blackhole : Blackhole
    ) {
        val event = KevinEvent(blackhole)

        KevinEvent.BUS.post(event)
    }
}
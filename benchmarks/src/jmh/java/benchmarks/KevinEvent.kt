package benchmarks

import lavahack.kevin.EventBus
import org.openjdk.jmh.infra.Blackhole

class KevinEvent(
    val blackhole : Blackhole
) {
    companion object {
        val BUS = EventBus<KevinEvent>()
    }
}
package lavahack.kevin.benchmark;

import meteordevelopment.orbit.listeners.*;

public class Constants {
    public static final int LISTENERS = 200;

    /**
     * Use thread-safe data types for the Norbit event bus.
     */
    public static final boolean NORBIT_THREADSAFE = false;

    /**
     * Use {@link ConsumerListener} instead of {@link LambdaListener} for Orbit & Norbit.
     */
    public static final boolean ORBIT_CONSUMER_MODE = true;
}

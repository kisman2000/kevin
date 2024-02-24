<div align="center">

# Kevin

Extremely fast kotlin event system

Originally made for `LavaHack`

# Notes

Event posting is very fast, but subscribing/unsubscribing maybe slow(idk i didnt test)

I recommend using it only if you dont care about subscribing/unsubscribing timing

# How it works?

The main problem with any standard event system is timing of event posting.

There are 2 reasons of this problem.

The first reason is time for finding collection of listeners for some posted event.

The second reason is time for iterating and invoking every listener from this collection.

Solution to the first problem is binding collection as static thing directly to class of event.

Solution to the second problem is combining lambdas of listeners from the collection into one, but it may cause long timing for subscribing/unsubscribing listeners, but we dont case okay?

# Examples

### Creating class of event

</div>

```kotlin
import lavahack.kevin.EventBus

class TestEvent {
    //
    
    companion object {
        val BUS = EventBus<TestEvent>()
    }
}
```

<div align="center">

### Creating listeners

</div>

```kotlin
import lavahack.kevin.Listener

//...

val listenerWithDefaultPriority = Listener<TestEvent> { event -> /*action or idk*/ }
val listenerWithCustomPriority = Listener<TestEvent>(/*integer that means priority*/) { event -> /*action or idk*/ }
```

<div align="center">

### Actions with event

</div>

```kotlin
//Subscribing listener
TestEvent.BUS.subscribe(listener)

//Unsubscribing listener
TestEvent.BUS.unsubscribe(listener)

//Posting event
TestEvent.BUS.post(event)
```

<div align="center">



Download deobfuscator4000 or build them yourself, run it, select paths in the UI, press `Decompile` button and enjoy src of mod without mappings!

# Benchmarks

This benchmark shows average time of posting test event when 16 listeners are subscribed

| Event system  | Average time(ns/op) |
|---------------|---------------------|
| Kevin         | 0.674 ± 0.082       |
| Norbit        | 1.221 ± 0.223       |
| Orbit         | 1.627 ± 0.108       |

This benchmark shows average time of posting test event when 200 listeners are subscribed

| Event system  | Average time(ns/op) |
|---------------|---------------------|
| Kevin         | 0.687 ± 0.237       |
| Norbit        | 1.122 ± 0.122       |
| Orbit         | 1.823 ± 0.099       |

</div>
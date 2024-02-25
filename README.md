<div align="center">

# Kevin

Extremely fast event system

Originally made for `LavaHack`

# Notes

Event posting is very fast, but subscribing/unsubscribing maybe slow(idk i didnt test)

I recommend using it only if you dont care about subscribing/unsubscribing timing

# How it works?

The main problem with any standard event system is timing of event posting

There are 2 reasons of this problem

The first reason is timing for finding collection of listeners for some posted event

The second reason is timing for iterating and invoking every listener from this collection

Solution to the first problem is binding collection as static thing directly to class of event

Solution to the second problem is combining lambdas of listeners from the collection into one, but it may cause long timing for subscribing/unsubscribing listeners, but we dont care okay?

# Examples

### Creating class of event
#### Kotlin

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

#### Java

</div>

```java
import lavahack.kevin.EventBus;

public record TestEvent(/*...*/) {
    //
    
    public static final EventBus<TestEvent> BUS = new EventBus<>();
}
```

<div align="center">

### Creating listeners
#### Kotlin

</div>

```kotlin
import lavahack.kevin.Listener

val listenerWithDefaultPriority = Listener<TestEvent> { event -> /*action or idk*/ }
val listenerWithCustomPriority = Listener<TestEvent>(/*integer that means priority*/) { event -> /*action or idk*/ }
```

<div align="center">

#### Java

</div>

```java
import lavahack.kevin.Listener;

Listener<TestEvent> listenerWithDefaultPriority = new Listener<>(event -> /*action or idk*/);
Listener<TestEvent> listenerWithCustomPriority = new Listener<>(/*integer that means priority*/, event -> /*action or idk*/);
```

<div align="center">

### Actions with event
#### Kotlin

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

#### Java

</div>

```java
//Subscribing listener
TestEvent.BUS.subscribe(listener);

//Unsubscribing listener
TestEvent.BUS.unsubscribe(listener);

//Posting event
TestEvent.BUS.post(event);
```

<div align="center">

# Benchmark

This benchmark shows average time of posting test event when 200 listeners are subscribed

| Event system | Average time(ns/op) |
|--------------|---------------------|
| Kevin        | 7387 ± 317          |
| Norbit       | 19455 ± 17022       |
| Orbit        | 17685 ± 8369        |
| Alpine       | 57519 ± 330174      |

</div>

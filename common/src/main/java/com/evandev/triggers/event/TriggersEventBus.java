package com.evandev.triggers.event;

import net.jodah.typetools.TypeResolver;
import com.evandev.triggers.Constants;
import com.evandev.triggers.event.events.TriggerEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Simple event bus for Triggers events.
 * <p>
 * This class is basically a stripped down reimplementation of Forge's event bus.
 */
public class TriggersEventBus {
    private final Map<Class<?>, List<Consumer<? extends TriggerEvent>>> listeners = new HashMap<>();

    public <T extends TriggerEvent> void addListener(Consumer<T> listener) {
        //noinspection unchecked
        Class<T> eventClass = (Class<T>) TypeResolver.resolveRawArgument(Consumer.class, listener.getClass());
        if (eventClass != null && ((Class<?>) eventClass) != TypeResolver.Unknown.class) {
            addListener(eventClass, listener);
        } else {
            throw new IllegalArgumentException("Could not resolve event class for listener " + listener);
        }
    }

    public <T extends TriggerEvent> void addListener(Class<T> eventClass, Consumer<T> listener) {
        if (!TriggerEvent.class.isAssignableFrom(eventClass)) {
            Constants.LOG.warn("Registering an event of class {} which is not a subclass of TriggerEvent", eventClass);
        }

        List<Consumer<? extends TriggerEvent>> listeners = this.listeners.computeIfAbsent(eventClass, k -> new ArrayList<>(1));
        listeners.add(listener);
    }

    public <T extends TriggerEvent> void post(T event) {
        List<Consumer<? extends TriggerEvent>> listeners = this.listeners.get(event.getClass());
        if (listeners != null) {
            for (Consumer<? extends TriggerEvent> listener : listeners) {
                //noinspection unchecked
                ((Consumer<T>) listener).accept(event);
            }
        }
    }

    public void removeAllListeners() {
        this.listeners.clear();
    }
}

package com.app;

public class Mutable<T> {
    private T value;

    public Mutable(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public static <T> Mutable<T> of(T value) {
        return new Mutable<>(value);
    }
}

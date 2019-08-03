package com.ucsdextandroid2.android2final.Data;

import android.os.Handler;

public class Debouncer<T> {
    private long delay;

    private Handler handler;
    private ChangeListener<T> changeListener;

    private T latestValue;

    public static <T> Debouncer<T> create(long delay, ChangeListener<T> changeListener) {
        return new Debouncer<>(delay, changeListener);
    }

    private Debouncer(long delay, ChangeListener<T> changeListener) {
        this.delay = delay;
        this.changeListener = changeListener;
        this.handler = new Handler();
    }

    public void onChange(T item) {
        handler.removeCallbacks(runnable);
        latestValue = item;
        handler.postDelayed(runnable, delay);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            changeListener.onChange(latestValue);
        }
    };

    public interface ChangeListener<T> {
        void onChange(T item);
    }
}

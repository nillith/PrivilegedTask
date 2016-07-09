package com.nillith.android.permissions;

import java.util.concurrent.atomic.AtomicInteger;

class Sequencer {
    private static AtomicInteger counter = new AtomicInteger();
    public static int next() {
        return counter.getAndIncrement();
    }

    private Sequencer() {
    }
}

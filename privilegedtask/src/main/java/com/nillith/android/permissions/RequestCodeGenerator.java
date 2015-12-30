package com.nillith.android.permissions;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generate requestCode through this class to avoid potential requestCode clash.
 */
public class RequestCodeGenerator {
    private static AtomicInteger counter = new AtomicInteger();
    public static int next() {
        return counter.addAndGet(1);
    }

    private RequestCodeGenerator() {
    }
}

package com.nillith.android.permissions;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Nil on 2015/12/27.
 */
public class PermissionRequestCodeGenerator {
    private static AtomicInteger counter = new AtomicInteger();
    public static int next() {
        return counter.addAndGet(1);
    }

    private PermissionRequestCodeGenerator() {
    }
}

package com.nillith.android.permissions;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class sequencerTest {
    @Test
    public void sequencerShouldReturnIntegerIncrementallyFrom0To255(){
        int pre = Sequencer.next();
        int cur;
        for (int i = 0; i < 10000; ++i){
            cur = Sequencer.next();
            Assert.assertTrue(cur >= 0);
            Assert.assertTrue(cur <= 255);
            if(pre == 255){
                pre = 0;
                Assert.assertEquals(0, cur);
            }else {
                Assert.assertEquals(1, cur - pre);
                pre = cur;
            }
        }
    }
}
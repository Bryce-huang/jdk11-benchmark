package com.webank.benchmarks.concurrent;

import com.webank.benchmarks.concurrent.NotifyBenchmark.CountDownLatchState;
import com.webank.benchmarks.concurrent.NotifyBenchmark.WaitNotifyState;
import org.junit.Test;

public class RoundTripNotificationTest {

    @Test
    public void countDownLatch() {
        verify(new CountDownLatchState());
    }

    @Test
    public void waitNotify() {
        verify(new WaitNotifyState());
    }

    @Test
    public void phaser() {
        verify(new NotifyBenchmark.PhaserState());
    }

    @Test
    public void cyclicBarrier() {
        verify(new NotifyBenchmark.CyclicBarrierState());
    }

    private void verify(NotifyBenchmark.AbstractNotifyState state) {
        try {
            state.doSetup();
            for (int i = 0; i < 2; i++)
                state.notification.perform(10);
        } finally {
            state.doTeardown();
        }
    }
}
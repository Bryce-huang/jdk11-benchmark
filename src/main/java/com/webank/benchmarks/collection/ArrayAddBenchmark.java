package com.webank.benchmarks.collection;

import com.webank.benchmarks.Constants;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import static java.util.concurrent.TimeUnit.*;

@Warmup(iterations = 10, time = 1000, timeUnit = MILLISECONDS)
@Measurement(iterations = 10, time = 1000, timeUnit = MILLISECONDS)
public class ArrayAddBenchmark extends AbstractCollectionBenchmark {

    @Benchmark
    public int[] intArrayClone(MyState s) {
        return s.ints.clone();
    }


    @Benchmark
    public int[] intArrayCopyOf(MyState s) {
        return Arrays.copyOf(s.ints, Constants.OPERATIONS_PER_PER_INVOCATION);
    }

    @Benchmark
    public int[] intArrayAdd(MyState s) {
        int[] ints = new int[Constants.OPERATIONS_PER_PER_INVOCATION];
        System.arraycopy(s.ints, 0, ints, 0, Constants.OPERATIONS_PER_PER_INVOCATION);
        return ints;
    }

    @Benchmark
    public Integer[] intWrapperArrayClone(MyState s) {
        return s.intWrappers.clone();
    }

    @Benchmark
    public Integer[] intWrapperArrayCopyOf(MyState s) {
        return Arrays.copyOf(s.intWrappers, Constants.OPERATIONS_PER_PER_INVOCATION);
    }

    @Benchmark
    public Integer[] intWrapperArrayAdd(MyState s) {
        Integer[] ints = new Integer[Constants.OPERATIONS_PER_PER_INVOCATION];
        System.arraycopy(s.intWrappers, 0, ints, 0, Constants.OPERATIONS_PER_PER_INVOCATION);
        return ints;
    }

    @Benchmark
    public long[] longArrayClone(MyState s) {
        return s.longs.clone();
    }


    @Benchmark
    public long[] longArrayCopyOf(MyState s) {
        return Arrays.copyOf(s.longs, Constants.OPERATIONS_PER_PER_INVOCATION);
    }

    @Benchmark
    public long[] longArrayAdd(MyState s) {
        long[] longs = new long[Constants.OPERATIONS_PER_PER_INVOCATION];
        System.arraycopy(s.longs, 0, longs, 0, Constants.OPERATIONS_PER_PER_INVOCATION);
        return longs;
    }

    @Benchmark
    public Long[] longWrapperArrayClone(MyState s) {
        return s.longWrappers.clone();
    }

    @Benchmark
    public Long[] longWrapperArrayCopyOf(MyState s) {
        return Arrays.copyOf(s.longWrappers, Constants.OPERATIONS_PER_PER_INVOCATION);
    }

    @Benchmark
    public Long[] longWrapperArrayAdd(MyState s) {
        Long[] longs = new Long[Constants.OPERATIONS_PER_PER_INVOCATION];
        System.arraycopy(s.longWrappers, 0, longs, 0, Constants.OPERATIONS_PER_PER_INVOCATION);
        return longs;
    }

    @State(Scope.Thread)
    public static class MyState {
        Integer[] intWrappers;
        int[] ints;
        Long[] longWrappers;
        long[] longs;

        @Setup
        public void doSetup() {
            intWrappers = IntStream.range(0, Constants.OPERATIONS_PER_PER_INVOCATION).boxed().toArray(Integer[]::new);
            ints = IntStream.range(0, Constants.OPERATIONS_PER_PER_INVOCATION).toArray();

            longWrappers = LongStream.range(0, Constants.OPERATIONS_PER_PER_INVOCATION).boxed().toArray(Long[]::new);
            longs = LongStream.range(0, Constants.OPERATIONS_PER_PER_INVOCATION).toArray();
        }
    }
}

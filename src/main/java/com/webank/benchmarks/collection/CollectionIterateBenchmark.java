package com.webank.benchmarks.collection;

import com.webank.benchmarks.Constants;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.concurrent.TimeUnit.*;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 10, time = 1000, timeUnit = MILLISECONDS)
@Measurement(iterations = 10, time = 1000, timeUnit = MILLISECONDS)
@Fork(value = 1)
public class CollectionIterateBenchmark extends AbstractCollectionBenchmark {

    @Benchmark
    public void ArrayBlockingQueue(MyState s, Blackhole bh) {
        iterate(bh, s.abq);
    }

    private void iterate(Blackhole bh, Collection<Integer> target) {
        Iterator<Integer> iterator = target.iterator();
        int sum = 0;
        while (iterator.hasNext())
            sum += iterator.next();
        bh.consume(sum);
    }

    @Benchmark
    public void ArrayDeque(MyState s, Blackhole bh) {
        iterate(bh, s.ad);
    }

    @Benchmark
    public void ArrayList(MyState s, Blackhole bh) {
        iterate(bh, s.al);
    }

    @Benchmark
    public void ArrayListNoResize(MyState s, Blackhole bh) {
        iterate(bh, s.alnr);
    }

    @Benchmark
    public void ConcurrentHashMap(MyState s, Blackhole bh) {
        iterate(bh, s.chm);
    }

    private void iterate(Blackhole bh, Map<Integer, Integer> target) {
        for (Map.Entry<Integer, Integer> integerIntegerEntry : target.entrySet()) bh.consume(integerIntegerEntry);
    }

    @Benchmark
    public void ConcurrentLinkedDeque(MyState s, Blackhole bh) {
        iterate(bh, s.cld);
    }

    @Benchmark
    public void ConcurrentLinkedQueue(MyState s, Blackhole bh) {
        iterate(bh, s.clq);
    }

    @Benchmark
    public void ConcurrentSkipListMap(MyState s, Blackhole bh) {
        iterate(bh, s.cslm);
    }

    @Benchmark
    public void ConcurrentSkipListSet(MyState s, Blackhole bh) {
        iterate(bh, s.csls);
    }

    @Benchmark
    public void CopyOnWriteArrayList(MyState s, Blackhole bh) {
        iterate(bh, s.cowal);
    }

    @Benchmark
    public void CopyOnWriteArraySet(MyState s, Blackhole bh) {
        iterate(bh, s.cowas);
    }

    @Benchmark
    public void HashMap(MyState s, Blackhole bh) {
        iterate(bh, s.hm);
    }

    @Benchmark
    public void HashSet(MyState s, Blackhole bh) {
        iterate(bh, s.hs);
    }

    @Benchmark
    public void LinkedBlockingDeque(MyState s, Blackhole bh) {
        iterate(bh, s.lbd);
    }

    @Benchmark
    public void LinkedBlockingQueue(MyState s, Blackhole bh) {
        iterate(bh, s.lbq);
    }

    @Benchmark
    public void LinkedHashSet(MyState s, Blackhole bh) {
        iterate(bh, s.lhs);
    }

    @Benchmark
    public void LinkedHashMap(MyState s, Blackhole bh) {
        iterate(bh, s.lhm);
    }

    @Benchmark
    public void LinkedList(MyState s, Blackhole bh) {
        iterate(bh, s.ll);
    }

    @Benchmark
    public void LinkedTransferQueue(MyState s, Blackhole bh) {
        iterate(bh, s.ltq);
    }

    @Benchmark
    public void PriorityBlockingQueue(MyState s, Blackhole bh) {
        iterate(bh, s.pbq);
    }

    @Benchmark
    public void PriorityQueue(MyState s, Blackhole bh) {
        iterate(bh, s.pq);
    }

    @Benchmark
    public void Stack(MyState s, Blackhole bh) {
        iterate(bh, s.s);
    }

    @Benchmark
    public void TreeMap(MyState s, Blackhole bh) {
        iterate(bh, s.tm);
    }

    @Benchmark
    public void TreeSet(MyState s, Blackhole bh) {
        iterate(bh, s.ts);
    }

    @Benchmark
    public void Vector(MyState s, Blackhole bh) {
        iterate(bh, s.v);
    }

    @Benchmark
    public void VectorNoResize(MyState s, Blackhole bh) {
        iterate(bh, s.vnr);
    }

    public static class MyState extends AbstractCollectionBenchmarkState {

        private Collection<Integer> integers;
        private Map<Integer, Integer> intMap;

        @Setup
        public void doSetup() {
            super.doSetup();
            integers = IntStream.range(0, Constants.OPERATIONS_PER_PER_INVOCATION).boxed().collect(Collectors.toList());
            intMap = IntStream.range(0, Constants.OPERATIONS_PER_PER_INVOCATION).boxed().collect(Collectors.toMap(identity(), identity()));
            initialize(abq);
            initialize(ad);
            initialize(al);
            initialize(alnr);
            initialize(chm);
            initialize(cld);
            initialize(clq);
            initialize(cslm);
            initialize(csls);
            initialize(cowal);
            initialize(cowas);
            initialize(hm);
            initialize(hs);
            initialize(lbd);
            initialize(lbq);
            initialize(lhs);
            initialize(lhm);
            initialize(ll);
            initialize(ltq);
            initialize(pbq);
            initialize(pq);
            initialize(s);
            initialize(tm);
            initialize(ts);
            initialize(v);
            initialize(vnr);
        }

        @TearDown
        public void doTearDown() {
            super.doTearDown();
            integers = null;
            intMap = null;
        }

        private void initialize(Collection<Integer> c) {
            c.addAll(integers);
        }

        private void initialize(Map<Integer, Integer> c) {
            c.putAll(intMap);
        }
    }
}

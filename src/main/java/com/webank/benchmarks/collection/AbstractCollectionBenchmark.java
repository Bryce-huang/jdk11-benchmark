package com.webank.benchmarks.collection;

import com.webank.benchmarks.AbstractBenchmark;
import com.webank.benchmarks.Constants;
import org.openjdk.jmh.annotations.*;

@OperationsPerInvocation(Constants.OPERATIONS_PER_PER_INVOCATION)
public abstract class AbstractCollectionBenchmark extends AbstractBenchmark {
}

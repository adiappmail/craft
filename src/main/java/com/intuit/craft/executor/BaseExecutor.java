package com.intuit.craft.executor;

import java.util.concurrent.Executor;

public interface BaseExecutor {

    public Executor taskExecutor(int poolSize);
}

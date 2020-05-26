package com.enjoy.concurrent.comprehensive.queryProgressOfTask;

public interface ITask<V,R> {

    R calculate(V value) throws Throwable;
}



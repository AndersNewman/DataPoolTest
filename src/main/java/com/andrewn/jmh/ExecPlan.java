package com.andrewn.jmh;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ExecPlan {

    @Param({ "1", "5", "10" })
    public int numberOfClients;
}

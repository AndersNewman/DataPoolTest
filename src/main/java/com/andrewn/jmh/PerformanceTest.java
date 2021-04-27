package com.andrewn.jmh;

import com.andrewn.DataHandler;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;


public class PerformanceTest {

    public static final String FILE_PATH = "src/main/resources/data.json";

    public static final int OPERATIONS_NUM = 50000;

    public static final DataHandler handler = new DataHandler(FILE_PATH);

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 1)
    public void performBenchmark(ExecPlan plan) {
        try{
            int opsPerClient = OPERATIONS_NUM / plan.numberOfClients;
            List<Client> clients = new ArrayList<Client>();
            for(int i = 0; i < plan.numberOfClients; i++){
                Client client = new Client(handler, opsPerClient);
                client.start();
                clients.add(client);
            }
            for(Client client : clients){
                client.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

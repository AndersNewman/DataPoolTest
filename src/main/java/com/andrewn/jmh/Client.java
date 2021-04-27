package com.andrewn.jmh;

import com.andrewn.DataHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client extends Thread {

    private DataHandler dataHandler;
    private int numberOfOperations;

    public Client(DataHandler dataHandler, int numberOfOperations) {
        this.dataHandler = dataHandler;
        this.numberOfOperations = numberOfOperations;
    }

    @Override
    public void run() {
        List<Long> listOfId = new ArrayList<Long>();
        listOfId.add(dataHandler.write(this.toString()));
        Random rand = new Random();
        for (int i = 0; i < numberOfOperations; i++) {
            if (Math.round(Math.random()) == 0) {
                dataHandler.read(listOfId.get(rand.nextInt(listOfId.size())));
            } else {
                listOfId.add(dataHandler.write(this.toString()));
            }
        }

    }
}

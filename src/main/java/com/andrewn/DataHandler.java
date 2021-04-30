package com.andrewn;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DataHandler {

    private ConcurrentHashMap<Long, String> dataPool;
    
    private String filePath;

    public DataHandler(String filePath) {
        this.filePath = filePath;
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (new File(filePath).createNewFile()) {
                dataPool = new ConcurrentHashMap<Long, String>();
            } else {
                dataPool = mapper.readValue(new File(filePath), new TypeReference<ConcurrentHashMap<Long, String>>() {
                });
            }
        } catch (IOException e) {
            System.out.println("Exception message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Long write(String data) {
        Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        dataPool.put(id, data);
        saveData(filePath);
        return id;
    }

    public String read(Long id) {
        return dataPool.get(id);
    }

    public synchronized void saveData(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(filePath), dataPool);
        } catch (IOException e) {
            System.out.println("Exception message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ConcurrentHashMap<Long, String> getDataPool() {
        return dataPool;
    }

}

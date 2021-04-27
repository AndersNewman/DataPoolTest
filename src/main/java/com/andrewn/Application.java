package com.andrewn;

import java.util.Date;

public class Application {
    public static final String FILE_PATH = "src/main/resources/data.json";

    public static final DataHandler handler = new DataHandler(FILE_PATH);

    public static void main(String[] args) {
        try {
            Long id = handler.write(new Date().toString());
            System.out.println("ID   : " + id);
            System.out.println("Data : " + handler.read(id));
        } finally {
            handler.saveData(FILE_PATH);
        }
    }

}

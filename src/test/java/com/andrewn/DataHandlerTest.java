package com.andrewn;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class DataHandlerTest {

    private static final String TEST_FILE_PATH = "src/main/resources/data_test.json";

    private static final DataHandler handler = new DataHandler(TEST_FILE_PATH);

    @Test
    public void saveDataTest() throws IOException {
        String fooData = "FooData";
        Long id = handler.write(fooData);
        String predicted = "{\"" + id.toString() + "\":\"" + fooData + "\"}";

        handler.saveData(TEST_FILE_PATH);

        File file = new File(TEST_FILE_PATH);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String result = br.readLine();
        fr.close();
        br.close();
        file.delete();
        System.out.println("Predicted : " + predicted);
        System.out.println("Result    : " + result);
        assertEquals(predicted, result);

    }

}
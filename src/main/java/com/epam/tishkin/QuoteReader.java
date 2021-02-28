package com.epam.tishkin;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;

public class QuoteReader {
    public static void main(String[] args) throws IOException {
        DocumentReader reader = new DocumentReader();
        String address = "18";
        reader.readDocument(address);

        //try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Программирование\\test.txt"))) {
            //String line = "";
           // while ((line = reader.readLine()) != null) {
            //    parser.findSubstringWithPattern(line);
           // }
       // }
    }
}

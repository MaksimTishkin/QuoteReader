package com.epam.tishkin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class QuoteReader {
    public static void main(String[] args) {
        Parser parser = new Parser();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String quoteNumber;
            while (true) {
                quoteNumber = reader.readLine();
                if ("exit".equals(quoteNumber)) {
                    break;
                }
                String[] quote = parser.parseDocument(quoteNumber);
                printQuote(quote);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void printQuote(String[] quote) {
        if (quote == null) {
            System.out.println("There is no quote with this number");
        } else {
            for (String current : quote) {
                System.out.println(current.trim());
            }
        }
    }
}

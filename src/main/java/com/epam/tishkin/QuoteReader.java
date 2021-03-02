package com.epam.tishkin;

import com.epam.tishkin.exception.NonExistentQuoteException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class QuoteReader {
    final static Logger logger = LogManager.getLogger(QuoteReader.class);

    public static void main(String[] args) {
        Parser parser = new Parser();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String quoteNumber;
            while (true) {
                quoteNumber = reader.readLine();
                if ("exit".equals(quoteNumber)) {
                    break;
                }
                try {
                    String[] quote = parser.parseDocument(quoteNumber);
                    printQuote(quote);
                } catch (NonExistentQuoteException e) {
                    logger.warn(e.getMessage());
                }
            }
        } catch (IOException e) {
            logger.error("Sorry, Input incorrect", e);
        }
    }

    private static void printQuote(String[] quote) {
        for (String current : quote) {
            System.out.println(current.trim());
        }
    }
}


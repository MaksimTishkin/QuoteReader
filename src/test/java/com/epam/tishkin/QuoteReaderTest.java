package com.epam.tishkin;

import com.epam.tishkin.exception.NonExistentQuoteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

public class QuoteReaderTest {
    private static Parser parser;
    private static String existingQuoteNumber;
    private static String nonExistentQuoteNumber;

    @BeforeAll
    static void initAll() {
        parser = new Parser();
        existingQuoteNumber = "18";
        nonExistentQuoteNumber = "225";
    }

    @Test
    public void testParseDocument() throws MalformedURLException, NonExistentQuoteException {
        String[] expectedQuote = {"&lt;Faye&gt; так у куно же очко разбито.. ",
                "&lt;Kuno[ex-driver]&gt; я туда стекло новое вставил и все ЖЗ ",
                "&lt;Ayashi&gt; КАКОЕ СТЕКЛО??????????????? ",
                "&lt;Kuno[ex-driver]&gt; ну... в разбитое очко..."
        };
        Assertions.assertArrayEquals(expectedQuote, parser.parseDocument(existingQuoteNumber));
        Assertions.assertThrows(NonExistentQuoteException.class, () -> parser.parseDocument(nonExistentQuoteNumber));
    }
}

package com.epam.tishkin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.MalformedURLException;
import java.net.URL;

public class QuoteReaderTest {
    private static Parser parser;
    private static String existingQuoteNumber;
    private static String nonExistentQuoteNumber;
    private static URL urlExistingQuote;
    private static URL urlNonExistentQuote;

    @BeforeAll
    static void initAll() throws MalformedURLException {
        parser = new Parser();
        Parser parcerMock = Mockito.mock(Parser.class);
        String prefixURL = "https://bash.im/quote/";
        existingQuoteNumber = "18";
        nonExistentQuoteNumber = "225";
        urlExistingQuote = new URL(prefixURL + existingQuoteNumber);
        urlNonExistentQuote = new URL(prefixURL + nonExistentQuoteNumber);
        Mockito.when(parcerMock.isQuoteExists(urlNonExistentQuote, nonExistentQuoteNumber))
                .thenReturn(false);
        Mockito.when(parcerMock.isQuoteExists(urlExistingQuote, existingQuoteNumber))
                .thenReturn(true);
    }

    @Test
    public void testIsQuoteExists() {
        Assertions.assertFalse(parser.isQuoteExists(urlNonExistentQuote, nonExistentQuoteNumber));
        Assertions.assertTrue(parser.isQuoteExists(urlExistingQuote, existingQuoteNumber));
    }

    @Test
    public void testParseDocument() throws MalformedURLException {
        String[] expectedQuote = {"&lt;Faye&gt; так у куно же очко разбито.. ",
                "&lt;Kuno[ex-driver]&gt; я туда стекло новое вставил и все ЖЗ ",
                "&lt;Ayashi&gt; КАКОЕ СТЕКЛО??????????????? ",
                "&lt;Kuno[ex-driver]&gt; ну... в разбитое очко..."
        };
        Assertions.assertArrayEquals(expectedQuote, parser.parseDocument(existingQuoteNumber));
        Assertions.assertNull(parser.parseDocument(nonExistentQuoteNumber));
    }
}

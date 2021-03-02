package com.epam.tishkin;

import com.epam.tishkin.exception.NonExistentQuoteException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    final static Logger logger = LogManager.getLogger(Parser.class);

    String[] parseDocument(String quoteNumber) throws MalformedURLException, NonExistentQuoteException {
        URL url = new URL("https://bash.im/quote/" + quoteNumber);
        Pattern pattern = Pattern.compile("<title>(.+)</title>");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            Matcher matcher = pattern.matcher(reader.readLine());
            while (!matcher.find()) {
                matcher = pattern.matcher(reader.readLine());
            }
            if (matcher.group().contains(quoteNumber)) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.contains("quote__body")) {
                        currentLine = reader.readLine();
                        return currentLine.trim().split("<br\\s?/?>");
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Incorrect URL: " + url.getHost() + url.getPath());
        }
        throw new NonExistentQuoteException("There is no quote with this number " + quoteNumber);
    }
}

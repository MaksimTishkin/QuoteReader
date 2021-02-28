package com.epam.tishkin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    URL url;

    String[] parseDocument(String quoteNumber) throws MalformedURLException {
        url = new URL("https://bash.im/quote/" + quoteNumber);
        if (isQuoteExists(url, quoteNumber)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.contains("quote__body")) {
                        currentLine = reader.readLine();
                        break;
                    }
                }
                if (currentLine != null) {
                    return currentLine.trim().split("<br\\s?/?>");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    boolean isQuoteExists(URL url, String quoteNumber) {
        Pattern pattern = Pattern.compile("<title>(.+)</title>");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            Matcher matcher = pattern.matcher(reader.readLine());
            while (!matcher.find()) {
                matcher = pattern.matcher(reader.readLine());
            }
            return matcher.group().contains(quoteNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
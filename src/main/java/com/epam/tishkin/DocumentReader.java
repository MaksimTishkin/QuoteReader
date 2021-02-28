package com.epam.tishkin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class DocumentReader {
    Parser parser = new Parser();
    static URL url;

    void readDocument(String quoteNumber) throws IOException {
        url = new URL("https://bash.im/quote/" + quoteNumber);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.contains("quote__body")) {
                    line = reader.readLine();
                    parser.findSubstringWithPattern(line);
                }
            }
        }
    }

}

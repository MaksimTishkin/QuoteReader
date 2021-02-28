package com.epam.tishkin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    Pattern pattern = Pattern.compile("([a-яА-Я]+\\s)");
    Matcher matcher;

    void findSubstringWithPattern(String inputString) {
        matcher = pattern.matcher(inputString);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}

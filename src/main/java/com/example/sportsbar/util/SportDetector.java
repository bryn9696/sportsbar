package com.example.sportsbar.util;

import java.util.Arrays;
import java.util.List;

public class SportDetector {
    private static final List<String> FOOTBALL_KEYWORDS = Arrays.asList("football", "soccer", "goal");
    private static final List<String> BASKETBALL_KEYWORDS = Arrays.asList("basketball", "hoops");

    public static String detectSport(String content) {
        if (FOOTBALL_KEYWORDS.stream().anyMatch(content::contains)) return "football";
        if (BASKETBALL_KEYWORDS.stream().anyMatch(content::contains)) return "basketball";
        return "unknown";
    }
}

package util;

import java.util.*;

public class SmartSummarizer {

    private static final Set<String> STOP_WORDS = Set.of(
        "ve", "veya", "ama", "ile", "için", "bu", "şu", "bir",
        "da", "de", "ki", "olarak", "çok", "daha"
    );

    public static String summarize(String text, int sentenceCount) {

        if (text == null || text.isBlank()) {
            return "";
        }

        // 1. Cümlelere böl
        String[] sentences = text.split("\\.");

        if (sentences.length <= sentenceCount) {
            return text;
        }

        // 2. Anahtar kelime frekansı
        Map<String, Integer> wordFreq = new HashMap<>();

        for (String s : sentences) {
            for (String w : s.toLowerCase().split("\\s+")) {
                if (!STOP_WORDS.contains(w) && w.length() > 3) {
                    wordFreq.put(w, wordFreq.getOrDefault(w, 0) + 1);
                }
            }
        }

        // 3. Cümleleri puanla
        Map<String, Integer> sentenceScore = new LinkedHashMap<>();

        for (int i = 0; i < sentences.length; i++) {
            String s = sentences[i];
            int score = 0;

            for (String w : s.toLowerCase().split("\\s+")) {
                score += wordFreq.getOrDefault(w, 0);
            }

            // İlk cümleye bonus
            if (i == 0) {
                score += 2;
            }

            sentenceScore.put(s.trim(), score);
        }

        // 4. En yüksek puanlı cümleleri seç
        return sentenceScore.entrySet().stream()
            .sorted((a, b) -> b.getValue() - a.getValue())
            .limit(sentenceCount)
            .map(Map.Entry::getKey)
            .reduce((a, b) -> a + ". " + b)
            .orElse("");
    }
}

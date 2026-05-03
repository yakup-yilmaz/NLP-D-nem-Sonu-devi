package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class AiService {
    private static final String API_URL = "http://127.0.0.1:8000/summarize";

    public static String getSummary(String content) {
        try {
            // JSON hazırlığı: Metindeki tırnakları ve satır başlarını temizliyoruz
            String cleanContent = content.replace("\"", "\\\"").replace("\n", " ").replace("\r", " ");
            String jsonInput = "{\"text\": \"" + cleanContent + "\"}";

            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInput))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Basit bir yöntemle JSON içindeki özet kısmını ayıklıyoruz
                String body = response.body();
                int start = body.indexOf("\"summary\":\"") + 11;
                int end = body.indexOf("\",\"is_safe\"");
                return body.substring(start, end);
            }
        } catch (Exception e) {
            System.out.println("AI Baglanti Hatasi: " + e.getMessage());
        }
        return "Ozet olusturulamadi (AI Servisi kapali olabilir).";
    }
}
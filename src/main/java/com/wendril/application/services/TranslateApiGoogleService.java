package com.wendril.application.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class TranslateApiGoogleService {

    public TranslateApiGoogleService() {
    }

    public String translateToEnglish(String texto) {
        try {
            String urlStr = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=pt&tl=en&dt=t&q="
                    + URLEncoder.encode(texto, StandardCharsets.UTF_8);
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String response = reader.readLine();
                return response.split("\"")[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
            return texto;
        }
    }
}

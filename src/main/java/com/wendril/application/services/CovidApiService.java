package com.wendril.application.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wendril.application.data.CovidApiProperties;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CovidApiService {

    private final CovidApiProperties properties;
    private URL url;

    public CovidApiService(CovidApiProperties properties) {
        this.properties = properties;
    }

    public JsonNode getCovidData(String country, String cases) {
        try {
            if (cases.equals("cases")) {
                url = new URL(properties.getApiUrl() + country);
            } else {
                url = new URL(properties.getApiUrl() + country + "&type=deaths");
            }

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("X-Api-Key", properties.getApiKey());

            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(responseStream);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}

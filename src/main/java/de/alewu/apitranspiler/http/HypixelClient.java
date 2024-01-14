package de.alewu.apitranspiler.http;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.InputStreamReader;

public class HypixelClient implements AutoCloseable {

    private static final String URL_BASE = "https://api.hypixel.net/v2";
    private final CloseableHttpClient client;

    public HypixelClient(String apiKey) {
        this.client = HttpClientBuilder.create().addInterceptorFirst((HttpRequestInterceptor) (httpRequest, httpContext)
                -> httpRequest.addHeader("API-Key", apiKey)).build();
    }



    public JsonObject collections() throws Exception {
        HttpGet get = new HttpGet(URL_BASE + "/resources/skyblock/collections");
        try (var response = client.execute(get);
             var reader = new InputStreamReader(response.getEntity().getContent())) {
            JsonElement element = JsonParser.parseReader(reader);
            if (!element.isJsonObject()) {
                throw new ResponseParsingException("Expected response to be of type JsonObject");
            }
            return element.getAsJsonObject();
        }
    }

    @Override
    public void close() throws Exception {
        client.close();
    }
}

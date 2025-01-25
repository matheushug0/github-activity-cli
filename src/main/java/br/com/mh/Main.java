package br.com.mh;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.OffsetDateTime;

public class Main {
    public static void main(String[] args) {

    }

    static void fetchActivity(String username){
        String GITHUB_API_URL = "https://api.github.com/users/"+ username.strip() + "/events";
        HttpClient client = HttpClient.newHttpClient(); // HttpClient to send and receive requests

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(GITHUB_API_URL)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println(response.statusCode());
            }
            displayActivity(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    static void displayActivity(HttpResponse<String> response){
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(response.body()).getAsJsonArray();
    }
    static long parseDate(String jsonDate, OffsetDateTime currentDateTime){
        return 0;
    }
}

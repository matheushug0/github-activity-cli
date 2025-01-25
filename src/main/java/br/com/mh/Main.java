package br.com.mh;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("USAGE: java -jar main.jar <url>");
        }
        fetchActivity(args[0].strip());
    }

    static void fetchActivity(String username){
        final String GITHUB_API_URL_TEMPLATE = "https://api.github.com/users/%s/events";
        final String DAYS_AGO_FORMAT = "%d days ago";
        HttpClient client = HttpClient.newHttpClient(); // HttpClient to send and receive requests

        try {
            String GITHUB_API_URL = String.format(GITHUB_API_URL_TEMPLATE, username);
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(GITHUB_API_URL)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Error: HTTP " + response.statusCode());
            }
            displayActivity(response);
        } catch (IOException e) {
            System.err.println("Network Error: Unable to connect to GitHub");
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Request was interrupted");
            e.printStackTrace();
        }
    }
    static void displayActivity(HttpResponse<String> response){
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(response.body()).getAsJsonArray();

        for (JsonElement jsonElement : jsonArray) {
            JsonObject object = jsonElement.getAsJsonObject();
            System.out.println(object.get("type").getAsString());
        }

    }
    static long parseDate(String jsonDate, OffsetDateTime currentDateTime){
        return 0;
    }
}

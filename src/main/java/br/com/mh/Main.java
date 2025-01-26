package br.com.mh;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Correct Usage: java -jar github-activity-cli-1.0.jar <username>");
        }
        fetchActivity(args[0].strip());
    }

    static void fetchActivity(String username) {
        final String GITHUB_API_URL_TEMPLATE = "https://api.github.com/users/%s/events";
        final String DAYS_AGO_FORMAT = "%d days ago";
        HttpClient client = HttpClient.newHttpClient(); // HttpClient to send and receive requests

        try {
            String GITHUB_API_URL = String.format(GITHUB_API_URL_TEMPLATE, username);
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(GITHUB_API_URL)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
                String responseBody = String.format("Status code: %s | User %s", jsonObject.get("status").getAsString(), jsonObject.get("message").getAsString());
                System.err.println(responseBody);
            } else {
                displayActivity(response);
            }
        } catch (IOException e) {
            System.err.println("Network Error: Unable to connect to GitHub");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Request was interrupted");
        }
    }

    static void displayActivity(HttpResponse<String> response) {
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(response.body(), JsonArray.class);

        for (JsonElement jsonElement : jsonArray) {
            JsonObject object = jsonElement.getAsJsonObject();
            String type = object.get("type").getAsString();
            String action = "";
            String repoName = getRepoName(object);
            Long parseDate = parseDate(object.get("created_at").getAsString(), now);
            String daysAgo = parseDate + (parseDate == 1 ? " day ago" : " days ago");

            switch (type) {
                case "PushEvent":
                    int commitCount = object.get("payload").getAsJsonObject().get("commits").getAsJsonArray().size();
                    action = String.format("Pushed %d %s to %s %s", commitCount, commitCount == 1 ? "commit" : "commits", repoName, daysAgo);
                    break;
                case "IssuesEvent":
                    action = formatEvent(object.get("payload").getAsJsonObject().get("action").getAsString() + " a new Issue", repoName, daysAgo);
                    break;
                case "WatchEvent":
                    action = formatEvent("starred", repoName, daysAgo);
                    break;
                case "CreateEvent":
                    action = formatEvent("created", repoName, daysAgo);
                    break;
                case "PublicEvent":
                    action = formatEvent("set to Public", repoName, daysAgo);
                    break;
                default:
                    action = formatEvent(type.replace("Event", ""), repoName, daysAgo);
                    break;
            }
            System.out.println(action);
        }

    }

    static long parseDate(String jsonDate, OffsetDateTime currentDateTime) {
        return ChronoUnit.DAYS.between(OffsetDateTime.parse(jsonDate), currentDateTime);
    }

    static String getRepoName(JsonObject object) {
        return object.get("repo").getAsJsonObject().get("name").getAsString();
    }

    static String formatEvent(String type, String repoName, String daysAgo) {
        type = type.replace(type.charAt(0), type.toUpperCase().charAt(0));
        return String.format("%s in %s %s", type, repoName, daysAgo);
    }
}

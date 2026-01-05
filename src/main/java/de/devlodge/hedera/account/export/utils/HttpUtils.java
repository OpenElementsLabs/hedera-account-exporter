package de.devlodge.hedera.account.export.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class HttpUtils {

    public static final String APPLICATION_JSON_MIMETYPE = "application/json";

    private HttpUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static HttpRequest createGetRequestForJson(final String uri) {
        Objects.requireNonNull(uri, "uri must not be null");
        return createGetRequestForJson(URI.create(uri));
    }

    public static HttpRequest createGetRequestForJson(final URI uri) {
        Objects.requireNonNull(uri, "uri must not be null");
        return HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .header("accept", APPLICATION_JSON_MIMETYPE)
                .build();
    }

    public static String getJsonForGetRequest(final HttpClient client, final URI uri)
            throws IOException, InterruptedException {
        final HttpRequest request = createGetRequestForJson(uri);
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static String getJsonForGetRequest(final HttpClient client, final String uri)
            throws IOException, InterruptedException {
        return getJsonForGetRequest(client, URI.create(uri));
    }

    public static JsonElement getJsonElementForGetRequest(final HttpClient client, final URI uri)
            throws IOException, InterruptedException {
        final String jsonString = getJsonForGetRequest(client, uri);
        try {
            return JsonParser.parseString(jsonString);
        } catch (JsonSyntaxException e) {
            throw new IllegalStateException("Could not parse json string: " + jsonString, e);
        }
    }

    public static JsonElement getJsonElementForGetRequest(final HttpClient client, final String uri)
            throws IOException, InterruptedException {
        return getJsonElementForGetRequest(client, URI.create(uri));
    }

}

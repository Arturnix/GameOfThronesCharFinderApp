package pl.arturzgodka.connectivity;

import pl.arturzgodka.datamodel.BookCharacter;
import pl.arturzgodka.mappers.BookCharacterMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CharactersAPIHandler {
    private final String CHARACTERS_URL = "https://www.anapioficeandfire.com/api/characters";
    private final HttpClient client = HttpClient.newBuilder().build();

    public BookCharacter getBookCharacter(int index) { //wydobywa postac na podsawie zadanego indexu jako parametr
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CHARACTERS_URL + "/" + index))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return BookCharacterMapper.mapJSONToCharacter(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public BookCharacter getBookCharacter(String name) { //wydobywa postac na podsawie zadanego imienia postaci jako parametr
        try {
            name = name.replaceAll(" ", "%20");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CHARACTERS_URL + "?name=" + name))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.body().length() == 2) {
                return null;
            }
            return BookCharacterMapper.mapJSONToCharacter(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}

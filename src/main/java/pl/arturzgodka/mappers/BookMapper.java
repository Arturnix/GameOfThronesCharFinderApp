package pl.arturzgodka.mappers;

import pl.arturzgodka.datamodel.Book;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BookMapper { //mapuje JSON na porzadany obiekt w Javie
    public static List<Book> mapBookList(String bookListJSON) {
        List<Book> books = new ArrayList<>();
        JSONTokener tokener = new JSONTokener(bookListJSON);
        JSONArray array = new JSONArray(tokener);
        for (int i = 0; i < array.length(); i++) {
            books.add(mapSingleBook(array.getJSONObject(i).toString()));
        }

        return books;
    }

    public static Book mapSingleBook(String bookJSON) {
        JSONTokener tokener = new JSONTokener(bookJSON);
        JSONObject object = new JSONObject(tokener);
        String name = object.getString("name");
        String isbn = object.getString("isbn");
        String authors = (String) object.getJSONArray("authors").get(0);
        Integer numberOfPages = (Integer) object.getNumber("numberOfPages");
        String publisher = object.getString("publisher");
        String countryOfOrigin = object.getString("country");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDate dateOfRelease = LocalDate.parse(object.getString("released"), formatter);

        return new Book(name, isbn, authors, numberOfPages, publisher, countryOfOrigin, dateOfRelease, Collections.emptyList());
    }
}

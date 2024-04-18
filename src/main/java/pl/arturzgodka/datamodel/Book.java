package pl.arturzgodka.datamodel;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Book {
    //te pola to nie sa wszsytkie dane dostepne w RESTowym API, ale to pola, które mnie najbardziej interesuja w tej aplikacji
    private String name;
    private String isbn;
    private String author;
    private Integer numberOfPages;
    private String publisher;
    private String countryOfOrigin;
    private LocalDate releaseDate;
    private List<BookCharacter> characterList;

    public Book(String name, String isbn, String author, Integer numberOfPages, String publisher,
                String countryOfOrigin, LocalDate releaseDate, List<BookCharacter> characterList) {
        this.name = name;
        this.isbn = isbn;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
        this.countryOfOrigin = countryOfOrigin;
        this.releaseDate = releaseDate;
        this.characterList = characterList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<BookCharacter> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<BookCharacter> characterList) {
        this.characterList = characterList;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors=" + author +
                ", numberOfPages=" + numberOfPages +
                ", publisher='" + publisher + '\'' +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", releaseDate=" + releaseDate +
                ", characterList=" + characterList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(name, book.name) && Objects.equals(isbn, book.isbn)
                && Objects.equals(author, book.author) && Objects.equals(numberOfPages,
                book.numberOfPages) && Objects.equals(publisher, book.publisher)
                && Objects.equals(countryOfOrigin, book.countryOfOrigin)
                && Objects.equals(releaseDate, book.releaseDate) && Objects.equals(
                characterList, book.characterList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, isbn, numberOfPages, publisher, countryOfOrigin, releaseDate,
                characterList, author);
        result = 31 * result;
        return result;
    }
}

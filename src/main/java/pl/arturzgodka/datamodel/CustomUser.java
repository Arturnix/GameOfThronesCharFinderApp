package pl.arturzgodka.datamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity //jesli jakas klasa ma zostac zapisywana w bazie danych to trzeba to dopisac
public class CustomUser { //klasa bedzie zapisywana w bazie danych

    @Id //kazdy @Entity czyli klasa zapisywana w bazie danych musi miec w ten sposob oznaczony klucz glowny aby sie dopasowalo w bazie danych.
    @GeneratedValue //okresla wartosc generowana automatycznie dla tego pola
    private long id;

    private String email;
    private String password;

    //obiekty oblusgiwane przez hibernate potrzebuja domyslnego konstruktora pustgo wstawionego tutaj
    public CustomUser() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomUser that = (CustomUser) o;

        if (id != that.id) return false;
        if (!Objects.equals(email, that.email)) return false;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package pl.arturzgodka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //daje @Controller i od razu oznacza mi ta klase jako bean. Ta zielona ikonka z boku nazwy klasy.
public class MainController {
    //przekierowanie uzytkownika na zasob index kiedy wchodzi na domylsna strone
    @RequestMapping("/") //Obsluguje kazdy rodzaj zapytania na podym adresie - tutaj to domylny adres strony internetowej mojej. Wskazuje jaka sciezka ma byc obslugiwana.
    public String getMainPage() {
        return "index"; //co jest zwracane na zapytanie okreslone powyzej (deklaracji nazwy metody). To jest zwracany widok index.html z resources bo to jest domylsny katalog dla widokow wiec nie trzeba podawac calej sciezki.
    }
}

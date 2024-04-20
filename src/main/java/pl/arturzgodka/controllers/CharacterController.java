package pl.arturzgodka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.arturzgodka.connectivity.CharactersAPIHandler;
import pl.arturzgodka.datamodel.CharacterSearchObject;

import java.util.concurrent.ThreadLocalRandom;

@Controller
public class CharacterController {

    private CharactersAPIHandler charactersAPIHandler = new CharactersAPIHandler();

    //Get dostaje formularz, Post aby przeslac dane z formularza do API

    @GetMapping("/characterSearch") //jesli user wejdzie na taki adres GET to zwroc mu widok z return.
    public String getCharacterSearchView(Model model) { //ta metoda ma 2 przypadki: obsluga zapytan GET i POST.
        model.addAttribute("characterSearchObject", new CharacterSearchObject());
        return "characterSearch";
    }

    @PostMapping("/characterSearch") //w ramach dzialanai POST zostaje wyslany na ten adres z wartosciami jakie zostaly podane w formularzu.
    public String getSpecificCharacter(Model model, CharacterSearchObject characterSearchObject) { //tu jest przekazywany efekt dzialania post, to co jest w ciele.
        //wykorzystuje jego wartosc, tego parametru aby dzieki niemu wyszukac zadana postac
        model.addAttribute("character", charactersAPIHandler.getBookCharacter(characterSearchObject.getName())); //wykorzystuje do wyszukania w RESTowym API bohatera o zadanym imieniu podanym w formularzu, to w tym obiekcie jest ono przechowywane.
        return "character";
    }

    //wyszukaj losowa postac
    @GetMapping("/character")
    public String getRandomCharacter(Model model) {
        model.addAttribute("character", charactersAPIHandler.getBookCharacter(ThreadLocalRandom.current().nextInt(1, 900 + 1))); //losowanie indexu dla postaci po wejscu w podany link
        return "character";
    }
}

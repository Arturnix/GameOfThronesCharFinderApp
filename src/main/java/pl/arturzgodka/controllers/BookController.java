package pl.arturzgodka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.arturzgodka.connectivity.BooksAPIHandler;

@Controller
public class BookController {

    public final BooksAPIHandler apiHandler = new BooksAPIHandler(); //fetchuje dane z REST API

    @RequestMapping("/books") //requestMapping bo nie chce rozrozniac typow zapytan, które lecą na podany adres.
    public String getBookList(Model model) {
        //przekazuje dane do widoku
        //aby moc przekazac dane z backendu do frontendu potrzebuje modelu. Przez model przekazuje te dane.
        model.addAttribute("booksList", apiHandler.listAllBooks()); //podaje najpier jak ma sie dana nazywac, a pozniej skad wziac jej wartosc.
        return "books";
    }

    @RequestMapping("/book") //przekauzje w widoku tam w linku argument id ksiazki. Zeby to polaczyc musze otagowac @RequestParam w parametrach metody.
    public String getSingleBook(Model model, @RequestParam Integer id) { //model jako parametr bo bede przekazywal pojedyncza ksiazke.
        //nazwa tego parametru jest taka sama jak podany w linku w widoku gdzie jest generowany ten link do tego konkretnego zasobu.
        model.addAttribute("book", apiHandler.getSingleBook(id)); //id jest wybrane przez uzytkownika przez klikniecie w link przy danej ksiazce.
        return "book";
    }
}

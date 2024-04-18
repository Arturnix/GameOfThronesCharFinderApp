package pl.arturzgodka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

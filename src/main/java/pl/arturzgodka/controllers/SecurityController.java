package pl.arturzgodka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.arturzgodka.databaseutils.CustomUserDao;
import pl.arturzgodka.datamodel.CustomUser;

@Controller
public class SecurityController { //odpowiada za obsluge uzytkownikow. Formularze logowania i rejestracji.

    @GetMapping("/login")
    public String getLoginPage() {
        return "security/login"; //wszystkie dane zwiazane z uzytkownikami zawarte sa w katalogu security.
    }
    //tu juz bez post bo jest to juz wczesniej obsluzone - logowanie, formularza logowania.
    //do rejestracji robie juz obsluge sam bo spring framework juz tak domylsnie jak logowania tego nie obsluguje.

    @GetMapping("/register") //otrzymuje formularz do wypelnienia.
    public String getRegistrationPage(Model model) { //w formularzach (rejestracji) uzywam modelu aby wrzucic tam uzytkownika, ktorego chce zapisac. Do tego jeszcze potrzebuje Dao.
        model.addAttribute("customUser", new CustomUser());
        return "security/register";
    }

    @PostMapping("/register") //przesylam wypelniony formularz.
    public String registerUser(CustomUser user) { //tu przekazuje tego usera, ktorego wlasnie stworzylem w metodzie powyzej. To wynik mojego formularza.
        CustomUserDao dao = new CustomUserDao();
        //tutaj jedna operacja jaka wykonuje to zapis mojego nowego uzykownika z formularza rejestracji.
        dao.saveUser(user);
        return "books"; //zwracam glowna strone.

    }


}

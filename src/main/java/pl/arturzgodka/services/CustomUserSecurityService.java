package pl.arturzgodka.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.arturzgodka.databaseutils.CustomUserDao;
import pl.arturzgodka.datamodel.CustomUser;

@Service //tak oznaczam serwisy dla springa
public class CustomUserSecurityService implements UserDetailsService { //wskazac w jaki sposob spring ma ladowac mojego uzytkownika.
//ten impekemnts pochodzi ze springa. Wskazuje w odopwiedni sposob wyszukiwanie uzytkownikow i parowanie tego co uzytkownik wpisuje.
    private final CustomUserDao dao = new CustomUserDao();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //tutaj jako username spring rozumie login, ktory bedzie wykorzystany przy logowaniu.
        CustomUser customUser = dao.findUserByEmail(username);

        return User.withUsername(customUser.getEmail())
                .password(customUser.getPassword())
                .authorities("USER").build(); //authorities nadaje role, USER, ADMIN i co sobie jeszcze zarzycze. To jest builder, ktory dostarcza spring framework.
    }
}

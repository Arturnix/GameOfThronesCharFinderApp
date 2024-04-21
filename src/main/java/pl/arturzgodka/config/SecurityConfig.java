package pl.arturzgodka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.arturzgodka.services.CustomUserSecurityService;

@Configuration
@EnableWebSecurity //wlacza zabezpieczenia i sprawia aby caly czas dzialaly
public class SecurityConfig {
    private final CustomUserSecurityService securityService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //enkoder hasel. Do weryfikacji, hash password podany przy logowaniu w formularzu musi byc taki sam jak hash tego hasla przechowywany w bazie danych.
    //tutaj robie wstrzykiwanie, bo chce aby CustomerUserService byl wstrzykiwany automatycznie, a nie deklarowany. Dlatego tam nie daje w nim = new. Czyli dodaje tylko konstruktor, który zawiera to pole.
    public SecurityConfig(CustomUserSecurityService securityService) {
        this.securityService = securityService;
    }

    //filter chain - filtrowanie requestow, ktore przychodza na strone. Czyli co ma byc dla kazdego widoczne, a co dla zalogowanych userow. itp.
    @Bean //aby spring mogl znalezc ta metode
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/", "/register", "/login") //lambda - uzywanie interfejsu funkcji.
                        .permitAll()//Na te podane linki moze wejsc kazdy, sa ustawione jako ogolnodostepne. Czyli na te adresy jak bede wchodzil to nie trzeba podawac juz username i password. A kazdy inny adres niech wymaga autheticated.
                        .anyRequest()
                        .authenticated())
                //wsazac, ze parametr email bedzie uzywany jako parametr do logowania.
                .formLogin((form) -> form //tutaj daje kolejna lambde
                        .loginPage("/login")
                        .usernameParameter("email") //nazwa parametru, ktory bedzie dostepny w widoku, input ktory bedzie wykorzystywal pozniej te moje bedzie musial ta sama nazwe co parametr tutaj aby spring mogl to obsluzyc. Jesli beda inne dane w formularzy to nie wyjdzie ok.
                        .defaultSuccessUrl("/books")
                        .permitAll());
//tu jest wskazane jakie requesty maja byc autentykowane, a jakie nie. Jaka strona jest odpwiedzialna za logowanie  i jaki parametr bedzie tam uzywany do logowania i gdzie ma przeniesc po udanym logowaniu.
        return security.build();
    }

    //authentication provider czy moj user to rzeczywiscie ten user, ktory zostal wrzucony do bazy
    @Bean
    public DaoAuthenticationProvider authProvider() { //Dao wykorzytsuje w momencie komunikowania sie z baza danych.
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(securityService); //ten serwis, ktory wstrzykuje.
        dao.setPasswordEncoder(encoder);

        return dao;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider());
        return authenticationManagerBuilder.build();
    }
}

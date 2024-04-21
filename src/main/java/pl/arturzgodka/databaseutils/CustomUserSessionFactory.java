package pl.arturzgodka.databaseutils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustomUserSessionFactory { //klasa zawiera narzedzia do komunikacji z baza danych

    public static SessionFactory getCustomUserSessionFactory() {
        Configuration config = new Configuration();
        config.configure("static/hibernate.cfg.xml"); //plik w ktorym konfiguruje hibernate
        return config.buildSessionFactory();
    }
}

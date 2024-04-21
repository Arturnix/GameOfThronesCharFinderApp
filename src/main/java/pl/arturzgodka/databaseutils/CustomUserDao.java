package pl.arturzgodka.databaseutils;

import pl.arturzgodka.datamodel.CustomUser;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomUserDao {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //lib spring zawarta w security
    private final SessionFactory sessionFactory = CustomUserSessionFactory.getCustomUserSessionFactory(); //zasob statyczny

    public void saveUser(CustomUser customUser) {
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        customUser.setPassword(passwordEncoder.encode(customUser.getPassword())); //aby nie trzymac hasel jako plain text w bazie danych.
        s.merge(customUser);
        t.commit();
        s.close();
    }

    private boolean userExists(CustomUser user) {
        return findUserByEmail(user.getEmail()) != null;
    }

    public CustomUser findUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder(); //klasa ktora rpzechwuje kryteria do query
        CriteriaQuery<CustomUser> userQuery = cb.createQuery(CustomUser.class);
        Root<CustomUser> root = userQuery.from(CustomUser.class);
        userQuery.select(root).where(cb.equal(root.get("email"), email)); //tworze query. Tlumaczenie sql na jazyk javowy
        CustomUser user = session.createQuery(userQuery).getSingleResultOrNull(); //tworze tymczasowego usera aby moc go w razie potrzeby degubowac.
        return user;
    }
}


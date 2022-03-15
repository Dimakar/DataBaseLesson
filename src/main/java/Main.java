import jdbc.User;
import model.UsersEntity;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
//        UsersEntity usersEntity = new UsersEntity();
//        usersEntity.setName("User is inserted by hibernate");
//        usersEntity.setUserId(3);
//
//        try (Session session = getSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.save(usersEntity);
//            transaction.commit();
//        }
        try (Session session = getSession()) {
            System.out.println(session.get(UsersEntity.class, 1).getSalesByUserId().stream()
                    .findAny().get().getSaleId());
        }
    }
}
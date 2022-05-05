import entities.Town;
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Scanner sc = new Scanner(System.in);

        String password = sc.nextLine();
        MyConnector.createConnection("root", password, "mini-orm");
        EntityManager<User> entityManager = new EntityManager<>(MyConnector.getConnection());

        Iterable<User> users = entityManager.find(User.class);
        for (User user : users) {
            System.out.println(user);
        }

        MyConnector.close();
    }
}

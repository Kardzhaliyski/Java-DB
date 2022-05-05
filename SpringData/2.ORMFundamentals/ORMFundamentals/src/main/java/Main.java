import entities.Admin;
import entities.Town;
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Scanner sc = new Scanner(System.in);

        String password = sc.nextLine();
        MyConnector.createConnection("root", password, "mini-orm");
        EntityManager<Admin> entityManager = new EntityManager<>(MyConnector.getConnection());

        entityManager.doDelete(Admin.class, "id < 3");

        MyConnector.close();
    }
}

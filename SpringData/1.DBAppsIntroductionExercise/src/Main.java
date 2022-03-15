import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Username: root");
        System.out.print("Enter password: ");
        String dbPassword = sc.nextLine().trim();
        System.out.println();

        Properties dbProps = new Properties();
        dbProps.setProperty("user", "root");
        dbProps.setProperty("password", dbPassword);

        Connection dbConnection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", dbProps);

        System.out.println(DbMethods.getMinionsInfo(dbConnection, 3));


        dbConnection.close();
    }
}

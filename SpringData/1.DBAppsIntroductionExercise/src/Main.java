import java.sql.*;
import java.util.*;

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
        DbMethods dbMethods = new DbMethods();
        dbMethods.printMinionNames(dbConnection);


        dbConnection.close();
    }




}

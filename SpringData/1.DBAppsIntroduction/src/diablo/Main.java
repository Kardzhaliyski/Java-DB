package diablo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username (default: root): ");
        String user = sc.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();
        System.out.print("Enter password (default: empty): ");
        String password = sc.nextLine().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT user_name, u.first_name, u.last_name, COUNT(ug.user_id) AS game_count " +
                        "FROM users AS u " +
                        "JOIN users_games AS ug " +
                        "ON u.id = ug.user_id " +
                        "WHERE user_name = ?  " +
                        "GROUP BY u.id");

        String username = sc.nextLine();
        preparedStatement.setString(1, username);

//        PreparedStatement preparedStatement = connection.prepareStatement(
//                "SELECT user_name,u.first_name, u.last_name, COUNT(ug.user_id) AS game_count FROM users AS u JOIN users_games AS ug ON u.id = ug.user_id  GROUP BY u.id");

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String currentUsername = rs.getString("user_name");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String gamesPlayed = rs.getString("game_count");
            System.out.printf("User: %s%n", currentUsername);
            System.out.printf("%s %s has played %s games%n", firstName, lastName, gamesPlayed);
        }

        connection.close();

    }
}

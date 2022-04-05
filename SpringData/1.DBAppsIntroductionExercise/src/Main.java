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

        String[] minionData = sc.nextLine().split(" ");
        String minionName = minionData[1];
        int minionAge = Integer.parseInt(minionData[2]);
        String minionTown = minionData[3];
        String villainName = sc.nextLine().split(" ")[1];

        int villainId = -1;
        if (!DbMethods.villainExist(dbConnection, villainName)) {
            villainId = DbMethods.addVillain(dbConnection, villainName);
            System.out.printf("Villain %s was added to the database.%n", villainName);
        } else {
            villainId = DbMethods.getVillainId(dbConnection, villainName);
        }

        int townId = -1;
        if (!DbMethods.townExist(dbConnection, minionTown)) {
            townId = DbMethods.addTown(dbConnection, minionTown);
            System.out.printf("Town %s was added to the database.%n", minionTown);
        } else {
            townId = DbMethods.getTownId(dbConnection, minionTown);
        }

        int minionId = DbMethods.addMinion(dbConnection, minionName, minionAge, townId);
        boolean minionAdded = DbMethods.connectMinionWithVillain(dbConnection, minionId, villainId);

        if (minionAdded) {
            System.out.printf("Successfully added %s to be minion of %s", minionName, villainName);
        } else {
            System.out.println("Something went wrong. Adding a minion failed!");
        }

        dbConnection.close();
    }
}

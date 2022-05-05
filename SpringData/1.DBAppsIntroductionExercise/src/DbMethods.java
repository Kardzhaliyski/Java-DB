import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbMethods {

    public static String getVillainsInfo(Connection connection, int numberOfMinions) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT" + " v.name villain_name," + " COUNT(DISTINCT m.id) minion_count" + " FROM" + " villains v" + " JOIN minions_villains mv on v.id = mv.villain_id" + " JOIN minions m on m.id = mv.minion_id" + " GROUP BY" + " v.id" + " HAVING" + " minion_count > ?" + " ORDER BY minion_count;");

        statement.setInt(1, numberOfMinions);

        ResultSet rs = statement.executeQuery();

        StringBuilder sb = new StringBuilder();
        while (rs.next()) {
            sb.append(rs.getString("villain_name")).append(" ").append(rs.getString("minion_count")).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public static String getMinionsInfo(Connection connection, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT" + " v.name villain_name," + " m.name minion_name," + " m.age minion_age" + " FROM" + " villains v" + " JOIN minions_villains mv on v.id = mv.villain_id" + " JOIN minions m on m.id = mv.minion_id" + " WHERE v.id = ?;");

        statement.setInt(1, villainId);

        ResultSet rs = statement.executeQuery();

        StringBuilder sb = new StringBuilder();
        int counter = 1;

        while (rs.next()) {
            if (counter == 1) {
                sb.append("Villein: ").append(rs.getString("villain_name")).append(System.lineSeparator());
            }

//          string format "x. name age"
            sb.append(counter).append(". ").append(rs.getString("minion_name")).append(" ").append(rs.getString("minion_age")).append(System.lineSeparator());

            counter++;
        }

        if (counter == 1) {
            return String.format("No villain with ID %s exists in the database.", villainId);
        }

        return sb.toString().trim();
    }


    private static boolean villainExist(Connection connection, String villainName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM villains WHERE name = ?;");
        statement.setString(1, villainName);
        return statement.executeQuery().next();
    }

    private static int addVillainToDb(Connection connection, String villainName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO villains (name, evilness_factor) VALUES ( ?, 'Evil');",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, villainName);
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();

        return generatedKeys.getInt(1);
//        return String.format("Villain %s was added to the database.", villainName);
    }

    private static boolean townExist(Connection connection, String townName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM towns WHERE name = ?;");
        statement.setString(1, townName);
        return statement.executeQuery().next();
    }

    private static int addTownToDb(Connection connection, String townName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO towns (name) VALUES (?);",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, townName);
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();

        return generatedKeys.getInt(1);
    }

    private static int addMinionToDb(Connection connection, String minionName, int minionAge, int townId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO minions (name, age, town_id) VALUES (?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, minionName);
        statement.setInt(2, minionAge);
        statement.setInt(3, townId);
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();

        return generatedKeys.getInt(1);

    }

    private static int getVillainId(Connection connection, String villainName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM villains WHERE name = ?");
        statement.setString(1, villainName);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    private static int getTownId(Connection connection, String townName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM towns WHERE name = ?");
        statement.setString(1, townName);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    private static boolean connectMinionWithVillain (Connection connection, int minionId, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?);");
        statement.setInt(1, minionId);
        statement.setInt(2, villainId);
        return statement.executeUpdate() > 0;
    }


    public static String addMinion(Scanner sc, Connection connection) throws SQLException {
        String[] minionData = sc.nextLine().split(" ");
        String minionName = minionData[1];
        int minionAge = Integer.parseInt(minionData[2]);
        String minionTown = minionData[3];
        String villainName = sc.nextLine().split(" ")[1];

        StringBuilder sb = new StringBuilder();
        int villainId = -1;
        if (!villainExist(connection, villainName)) {
            villainId = addVillainToDb(connection, villainName);
            sb.append("Villain ").append(villainName).append(" was added to the database.")
                    .append(System.lineSeparator());
        } else {
            villainId = getVillainId(connection, villainName);
        }

        int townId = -1;
        if (!townExist(connection, minionTown)) {
            townId = addTownToDb(connection, minionTown);
            sb.append("Town ").append(minionTown).append(" was added to the database.")
                    .append(System.lineSeparator());
        } else {
            townId = getTownId(connection, minionTown);
        }

        int minionId = addMinionToDb(connection, minionName, minionAge, townId);
        boolean minionAdded = connectMinionWithVillain(connection, minionId, villainId);

        if (minionAdded) {
            sb.append("Successfully added ").append(minionName)
                    .append(" to be minion of ").append(villainName);
        }

        return sb.toString();
    }

    public static String changeTownNamesToUppercase(String countryName, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE towns SET name = upper(name) WHERE country = ?");
        statement.setString(1, countryName);

        statement = connection.prepareStatement(
                "SELECT name FROM towns WHERE country = ?");
        statement.setString(1, countryName);
        ResultSet townsRS = statement.executeQuery();
        List<String> towns = new ArrayList<>();
        while (townsRS.next()) {
            towns.add(townsRS.getString("name"));
        }

        StringBuilder sb = new StringBuilder();
        if (towns.size() > 0) {
            sb.append(towns.size())
                    .append("town names were affected.")
                    .append(System.lineSeparator());
            sb.append(towns);
        } else {
            sb.append("No town names were affected.");
        }

        return sb.toString();
    }

    public static void deleteVillain(int villainId, Connection dbConnection) throws SQLException {
        PreparedStatement getVillainName = dbConnection.prepareStatement("SELECT name FROM villains WHERE id = ?");
        getVillainName.setInt(1,villainId);
        ResultSet villainNameSet = getVillainName.executeQuery();
        if(!villainNameSet.next())
        {
            System.out.println("No such villain was found");
            return;
        }

        String villainName = villainNameSet.getString(1);

        PreparedStatement getMinionCount = dbConnection.prepareStatement(
                "SELECT count(DISTINCT minion_id) as minion_count FROM minions_villains WHERE villain_id = ?");
        getMinionCount.setInt(1, villainId);
        ResultSet minionCountSet = getMinionCount.executeQuery();
        minionCountSet.next();
        int minionCount = minionCountSet.getInt("minion_count");

        dbConnection.setAutoCommit(false);
        try {

            PreparedStatement deleteMinions = dbConnection.prepareStatement(
                    "DELETE FROM minions_villains WHERE villain_id = ?");
            deleteMinions.setInt(1, villainId);
            deleteMinions.executeUpdate();

            PreparedStatement deleteVillain = dbConnection.prepareStatement(
                    "DELETE FROM villains WHERE id = ?");
            deleteVillain.setInt(1, villainId);
            deleteVillain.executeUpdate();

            dbConnection.commit();

            System.out.println(villainName + " was deleted");
            System.out.println(minionCount + " minions released");
        } catch (SQLException e) {
            dbConnection.rollback();
            e.printStackTrace();
        }

        dbConnection.setAutoCommit(true);
    }

    public static void printMinionNames(Connection dbConnection) throws SQLException {
        ResultSet minionSet = dbConnection.prepareStatement("SELECT name FROM minions").executeQuery();

        List<String> minionNames = new ArrayList<>();
        while (minionSet.next()) {
            minionNames.add(minionSet.getString("name"));
        }

        for (int i = 0; i < minionNames.size() / 2; i++) {
            System.out.println(minionNames.get(i));
            System.out.println(minionNames.get(minionNames.size() -1 -i));

            if((minionNames.size() % 2 == 1) && (i + 1 == minionNames.size() / 2)) {
                System.out.println(minionNames.get(i+1));
            }
        }
    }

    public static void increaseMinionAge(int[] minionIds, Connection dbConnection) throws SQLException {

        String statementString = "UPDATE minions SET  age = age + 1 , name = lower(name) WHERE id IN (?);";
        statementString = statementString.replace("?",("?" + ", ?".repeat(minionIds.length - 1)));
        PreparedStatement statement = dbConnection.prepareStatement(statementString);
        for (int i = 0; i < minionIds.length; i++) {
            statement.setInt(i +1, minionIds[i]);
        }
        statement.executeUpdate();

        ResultSet minions = dbConnection.prepareStatement("SELECT name, age FROM minions;").executeQuery();
        while (minions.next()) {
            System.out.println(minions.getString("name") + " " + minions.getString("age"));
        }
    }
}

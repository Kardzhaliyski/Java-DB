import java.sql.*;

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


    public static boolean villainExist(Connection connection, String villainName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM villains WHERE name = ?;");
        statement.setString(1, villainName);
        return statement.executeQuery().next();
    }

    public static int addVillain(Connection connection, String villainName) throws SQLException {
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

    public static boolean townExist(Connection connection, String townName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM towns WHERE name = ?;");
        statement.setString(1, townName);
        return statement.executeQuery().next();
    }

    public static int addTown(Connection connection, String townName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO towns (name) VALUES (?);",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, townName);
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();

        return generatedKeys.getInt(1);
    }

    public static int addMinion(Connection connection, String minionName, int minionAge, int townId) throws SQLException {
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

    public static int getVillainId(Connection connection, String villainName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM villains WHERE name = ?");
        statement.setString(1, villainName);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public static int getTownId(Connection connection, String townName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM towns WHERE name = ?");
        statement.setString(1, townName);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public static boolean connectMinionWithVillain (Connection connection, int minionId, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?);");
        statement.setInt(1, minionId);
        statement.setInt(2, villainId);
        return statement.executeUpdate() > 0;
    }

//    public static String addMinionToVillain(Connection connection, String )

}

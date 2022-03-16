import java.sql.*;

public class DbMethods {

    public static String getVillainsInfo(Connection connection, int numberOfMinions) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT" +
                " v.name villain_name," +
                " COUNT(m.id) minion_count" +
                " FROM" +
                " villains v" +
                " JOIN minions_villains mv on v.id = mv.villain_id" +
                " JOIN minions m on m.id = mv.minion_id" +
                " GROUP BY" +
                " v.id" +
                " HAVING" +
                " minion_count > ?" +
                " ORDER BY minion_count;");

        statement.setInt(1, numberOfMinions);

        ResultSet rs = statement.executeQuery();

        StringBuilder sb = new StringBuilder();
        while (rs.next()) {
            sb.append(rs.getString("villain_name"))
                    .append(" ")
                    .append(rs.getString("minion_count"))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public static String getMinionsInfo(Connection connection, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT DISTINCT" +
                " v.name villain_name," +
                " m.name minion_name," +
                " m.age minion_age" +
                " FROM" +
                " villains v" +
                " JOIN minions_villains mv on v.id = mv.villain_id" +
                " JOIN minions m on m.id = mv.minion_id" +
                " WHERE v.id = ?;");

                statement.setInt(1, villainId);

        ResultSet rs = statement.executeQuery();

        StringBuilder sb = new StringBuilder();
        int counter = 1;

        while (rs.next()) {
            if(counter == 1) {
                sb.append("Villein: ").append(rs.getString("villain_name")).append(System.lineSeparator());
            }

//          string format "x. name age"
            sb.append(counter)
                    .append(". ")
                    .append(rs.getString("minion_name"))
                    .append(" ")
                    .append(rs.getString("minion_age"))
                    .append(System.lineSeparator());

            counter++;
        }

        if(counter == 1) {
            return String.format("No villain with ID %s exists in the database.", villainId);
        }

        return sb.toString().trim();
    }
    }




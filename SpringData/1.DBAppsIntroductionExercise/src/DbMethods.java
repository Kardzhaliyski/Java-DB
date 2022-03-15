import java.sql.*;

public class DbMethods {

    public static String getVillainsInfo(Connection connection, int numberOfMinions) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT\n" +
                "    v.name villain_name,\n" +
                "    COUNT(m.id) minion_count\n" +
                "FROM\n" +
                "    villains v\n" +
                "        JOIN minions_villains mv on v.id = mv.villain_id\n" +
                "        JOIN minions m on m.id = mv.minion_id\n" +
                "GROUP BY\n" +
                "    v.id\n" +
                "HAVING\n" +
                "    minion_count > ? " +
                "ORDER BY minion_count;");

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
        PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT\n" +
                "    v.name villain_name,\n" +
                "    m.name minion_name,\n" +
                "    m.age minion_age\n" +
                "FROM\n" +
                "     villains v\n" +
                "        JOIN minions_villains mv on v.id = mv.villain_id\n" +
                "        JOIN minions m on m.id = mv.minion_id\n" +
                "WHERE v.id = ?;");

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




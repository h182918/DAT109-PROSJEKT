package db;

import Entities.Stand;

import javax.ejb.Stateless;
import java.sql.*;

@Stateless
public class DbHandler {

    private static String url = "jdbc:postgresql://data1.hib.no:5432/h571525";
    private static String user = "h571525";
    private static String password = "pass";

    public DbHandler() {
    }

    //TODO - all methods to access db

    /**
     * Inserts new stand into database - tested:OK
     *
     * @param stand
     */
    public synchronized void newStand(Stand stand) {

        {
            try {
                Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO expo.stand (name) values (?)");
                pstmt.setString(1, stand.getName());
                pstmt.execute();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Stand getStand(int id) {
        Stand stand = null;

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM expo.stand WHERE stand.id = ?");
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            stand = new Stand(resultSet.getString("name"));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return stand;
        }
    }
}

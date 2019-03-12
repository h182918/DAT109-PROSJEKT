package db;

import Entities.Stand;
import Entities.Vote;

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

            while (resultSet.next()) {
                stand = new Stand();
                stand.setId((Integer) resultSet.getObject(1));
                stand.setName((String) resultSet.getObject(2));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return stand;
        }
    }

    public synchronized Vote getVoteByUserForStand(String user, int standId) {
        Vote vote = null;

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM expo.vote WHERE vote.username = ? AND vote.stand = ?");
            pstmt.setString(1, user);
            pstmt.setInt(2, standId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                vote = new Vote();
                vote.setUserName((String) resultSet.getObject(1));
                vote.setStand((Stand) resultSet.getObject(2));
                vote.setScore((Integer) resultSet.getObject(3));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return vote;
        }
    }

}

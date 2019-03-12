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
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO expo.stand (name,imageurl) values (?,?)");
                pstmt.setString(1, stand.getName());
                pstmt.setString(2, stand.getImageurl());
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
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM expo.stand WHERE stand.standid = ?");
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                stand = new Stand();
                stand.setId((Integer) resultSet.getObject(1));
                stand.setName((String) resultSet.getObject(2));
                stand.setImageurl((String) resultSet.getObject(3));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            return stand;
        }
    }

    public synchronized Vote getVoteByUserForStand(String expouser, int standId) {
        Vote vote = null;

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM expo.vote WHERE vote.username = ? AND vote.stand = ?");
            pstmt.setString(1, expouser);
            pstmt.setInt(2, standId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                vote = new Vote();
                vote.setId((Integer) resultSet.getObject(1));
                vote.setUserName((String) resultSet.getObject(2));
                vote.setStand((Integer) resultSet.getObject(3));
                vote.setScore((Integer) resultSet.getObject(4));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            return vote;
        }
    }
    public synchronized void newVote(String expouser, int standId, int vote) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO expo.vote(username, stand, score)  VALUES (?,?,?)");
            pstmt.setString(1, expouser);
            pstmt.setInt(2, standId);
            pstmt.setInt(3,vote);
            pstmt.execute();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateVote(String expouser, int standId, int newVote) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = connection.prepareStatement("UPDATE expo.vote SET score = ? WHERE username = ? AND stand = ?");
            pstmt.setInt(1, newVote);
            pstmt.setString(2,expouser);
            pstmt.setInt(3, standId);
            pstmt.execute();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

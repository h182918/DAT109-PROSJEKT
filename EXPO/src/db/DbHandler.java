package db;

import entities.Stand;
import entities.Vote;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DbHandler {

    private static String url = "jdbc:postgresql://data1.hib.no:5432/h571525";
    private static String user = "h571525";
    private static String password = "pass";

    public DbHandler() {
    }

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

    /**
     * Finds stand in database based on the stands id
     * @param id
     * @return
     */
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

    /**
     * Finds a users vote for a stand.
     * @param expouser
     * @param standId
     * @return
     */
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

    /**
     * Inserts new vote into database
     * @param expouser
     * @param standId
     * @param vote
     */
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

    /**
     * Updates vote in database.
     * @param expouser
     * @param standId
     * @param newVote
     */
    public synchronized void updateVote(String expouser, int standId, int newVote) {
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

    /**
     * Returns the average score of the spesified stand in format '0.0' fx '4.7'
     * @param standId
     * @return double
     */
    public synchronized double findAverageVote(int standId) {
        double avg = 0.0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = connection.prepareStatement("SELECT AVG(score) FROM expo.vote WHERE stand=?");
            pstmt.setInt(1, standId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
               avg = resultSet.getDouble(1);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
           DecimalFormat df = new DecimalFormat("0.0");
           String avgFormattedStr = df.format(avg).replaceAll(",",".");
           double formattedAvg = Double.parseDouble(avgFormattedStr);
           return formattedAvg;
        }
    }
}

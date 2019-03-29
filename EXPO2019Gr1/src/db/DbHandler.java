package db;

import entities.Stand;
import entities.Vote;
import entities.standOverview;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The point of this class is to handle all transaction logic to/from the
 * database. All imports and exports to/from database has to go through this
 * class.
 *
 */
public class DbHandler {

	private static String url = "jdbc:postgresql://data1.hib.no:5432/h571525";
	private static String user = "h571525";
	private static String password = "pass";

	public DbHandler() {
	}

	// TODO - For admin: Get all the stands and their total score

	/**
	 * Inserts new stand into database
	 *
	 * @param stand
	 */
	public static synchronized void newStand(Stand stand) {
		{
			try {
				Class.forName("org.postgresql.Driver");
				Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = connection
						.prepareStatement("INSERT INTO expo.stand (name,imageurl,epostadmin,pin) values (?,?,?,?)");
				pstmt.setString(1, stand.getName());
				pstmt.setString(2, stand.getImageurl());
				pstmt.setString(3, stand.getEpostadmin());
				pstmt.setString(4, stand.getPin());
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
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("finally")
	public static synchronized Stand getStand(int id) {
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
				stand.setEpostadmin((String) resultSet.getObject(4));
				stand.setPin((String) resultSet.getObject(5));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return stand;
		}
	}

	/**
	 * Finds a users vote for a stand.
	 * 
	 * @param expouser
	 * @param standId  - id (int) of the stand
	 * @return
	 */
	@SuppressWarnings("finally")
	public static synchronized Vote getVoteByUserForStand(String expouser, int standId) {
		Vote vote = null;

		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM expo.vote WHERE vote.username = ? AND vote.stand = ?");
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
		} finally {
			return vote;
		}
	}

	/**
	 * Inserts new vote into database
	 * 
	 * @param expouser
	 * @param standId
	 * @param vote
	 */
	public static synchronized void newVote(String expouser, int standId, int vote) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection
					.prepareStatement("INSERT INTO expo.vote(username, stand, score)  VALUES (?,?,?)");
			pstmt.setString(1, expouser);
			pstmt.setInt(2, standId);
			pstmt.setInt(3, vote);
			pstmt.execute();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates vote in database.
	 * 
	 * @param expouser
	 * @param standId
	 * @param newVote
	 */
	public static synchronized void updateVote(String expouser, int standId, int newVote) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection
					.prepareStatement("UPDATE expo.vote SET score = ? WHERE username = ? AND stand = ?");
			pstmt.setInt(1, newVote);
			pstmt.setString(2, expouser);
			pstmt.setInt(3, standId);
			pstmt.execute();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the average score of the spesified stand in format '0.0' fx '4.7'
	 * 
	 * @param standId
	 * @return double
	 */
	@SuppressWarnings("finally")
	public static synchronized double findAverageVote(Stand stand) {
		double avg = 0.0;
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement("SELECT avg FROM standoverview WHERE name=?");
			pstmt.setString(1, stand.getName());
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				avg = resultSet.getDouble(1);
			}
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			DecimalFormat df = new DecimalFormat("0.0");
			String avgFormattedStr = df.format(avg).replaceAll(",", ".");
			double formattedAvg = Double.parseDouble(avgFormattedStr);
			return formattedAvg;
		}
	}

	/**
	 * Returns all the votes as a list of standOverview objects
	 * 
	 * @return
	 */
	@SuppressWarnings("finally")
	public static synchronized List<standOverview> getAllVotes() {
		standOverview stand;
		List<standOverview> overview = new ArrayList<standOverview>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM standoverview");
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				stand = new standOverview();
				stand.setStand(findStandByName((String) resultSet.getObject(1)));
				stand.setAverage(resultSet.getDouble(2));
				stand.setTotalScore((Long) resultSet.getObject(3));
				overview.add(stand);

			}
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			return overview;
		}
	}

	/**
	 * Finds the stand in the database which is connected to the email address
	 * specified as a param.
	 * 
	 * @param epostIn
	 * @return
	 */
	@SuppressWarnings("finally")
	public static Stand findStand(String epostIn) {
		Stand stand = null;
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM expo.stand where epostadmin = ?");
			pstmt.setString(1, epostIn);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				stand = new Stand();
				stand.setId((int) resultSet.getObject(1));
				stand.setName((String) resultSet.getObject(2));
				stand.setImageurl((String) resultSet.getObject(3));
				stand.setEpostadmin((String) resultSet.getObject(4));
				stand.setPin((String) resultSet.getObject(5));
			}
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			return stand;
		}
	}

	/**
	 * Finds the stand in the database which is connected to the email address
	 * specified as a param.
	 * 
	 * @param epostIn
	 * @return
	 */
	@SuppressWarnings("finally")
	public static Stand findStandByName(String name) {
		Stand stand = null;
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM expo.stand where name = ?");
			pstmt.setString(1, name);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				stand = new Stand();
				stand.setId((int) resultSet.getObject(1));
				stand.setName((String) resultSet.getObject(2));
				stand.setImageurl((String) resultSet.getObject(3));
				stand.setEpostadmin((String) resultSet.getObject(4));
				stand.setPin((String) resultSet.getObject(5));
			}
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			return stand;
		}
	}

	public static void updateStand(Stand stand) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection
					.prepareStatement("UPDATE expo.stand SET name=?,imageurl=?,epostadmin=?,pin=? WHERE standid = ?");
			pstmt.setString(1, stand.getName());
			pstmt.setString(2, stand.getImageurl());
			pstmt.setString(3, stand.getEpostadmin());
			pstmt.setString(4, stand.getPin());
			pstmt.setInt(5, stand.getId());
			pstmt.execute();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static boolean newUser(String email) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection
					.prepareStatement("INSERT INTO expo.expouser(epost)  VALUES (?)");
			pstmt.setString(1, email);
			pstmt.execute();
			connection.close();
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			return false;
		}
	}

}

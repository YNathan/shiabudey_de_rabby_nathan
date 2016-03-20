package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Entity.Gelt;
import Entity.User;
import Entity.UserLogin;

/**
 * 
 * @author Yaacov
 *
 */
public class getterDB {
	private final Lock lock = new ReentrantLock();
	private static Connection connect;
	private static PreparedStatement preparedStatement;
	private static Statement statement;
	private static ResultSet resultSet;
	private static String TABLE_BANK_NAME = "yankalee.bank";
	private static String TABLE_TEMP_DEBTS_NAME = "yankalee.temp_debts";
	private static String TABLE_USERS_NAME = "yankalee.users";
	private static String DATA_BASE_USER_NAME = "root";
	private static String DATA_BASE_PASSWORD_NAME = "Ny7516399";

	public ArrayList<String> getUserNames() {
		lock.lock();
		ArrayList<String> userNames = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection("jdbc:mysql://localhost/yankalee?user=" + DATA_BASE_USER_NAME
					+ "&password=" + DATA_BASE_PASSWORD_NAME);
			// Statements allow to issue SQL queries to the database
			preparedStatement = connect.prepareStatement("select * from " + TABLE_USERS_NAME);
			// Result set get the result of the SQL query
			resultSet = preparedStatement.executeQuery();
			// INFO
			play.Logger.info("<DATA_BASE> Get User-Names");

			// Looping over the result user-names
			while ((resultSet.next()) && (!resultSet.isClosed())) {
				userNames.add(resultSet.getString("user_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			play.Logger.error(e.getMessage());
			;
		} finally {
			// Closing the resultSet
			close();
		}
		lock.unlock();
		return userNames;
	}

	public ArrayList<String> getEmails() {
		lock.lock();
		ArrayList<String> emails = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection("jdbc:mysql://localhost/yankalee?user=" + DATA_BASE_USER_NAME
					+ "&password=" + DATA_BASE_PASSWORD_NAME);

			// Statements allow to issue SQL queries to the database
			preparedStatement = connect.prepareStatement("select * from " + TABLE_USERS_NAME);
			// Result set get the result of the SQL query
			resultSet = preparedStatement.executeQuery();

			// INFO
			play.Logger.info("<DATA_BASE> Get Emails");

			// Looping over the result user-names
			while (resultSet.next()) {
				emails.add(resultSet.getString("email"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			play.Logger.error(e.getMessage());
			;
		} finally {
			// Closing the resultSet
			close();
		}
		lock.unlock();
		return emails;
	}

	public ArrayList<UserLogin> getUserLogins() {
		lock.lock();
		ArrayList<UserLogin> userLogins = new ArrayList<UserLogin>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection("jdbc:mysql://localhost/yankalee?user=" + DATA_BASE_USER_NAME
					+ "&password=" + DATA_BASE_PASSWORD_NAME);
			// Statements allow to issue SQL queries to the database
			preparedStatement = connect.prepareStatement("select * from " + TABLE_USERS_NAME);
			// Result set get the result of the SQL query
			resultSet = preparedStatement.executeQuery();
			// INFO
			play.Logger.info("<DATA_BASE> Get User-Login");

			// Looping over the result user-names
			while (resultSet.next()) {
				userLogins.add(new UserLogin(resultSet.getString("user_name"), resultSet.getString("password")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			play.Logger.error(e.getMessage());
			;
		} finally {
			// Closing the resultSet
			close();
		}
		lock.unlock();
		return userLogins;
	}

	public ArrayList<User> getUsers() {

		lock.lock();
		ArrayList<User> usersLst = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection("jdbc:mysql://localhost/yankalee?user=" + DATA_BASE_USER_NAME
					+ "&password=" + DATA_BASE_PASSWORD_NAME);
			// Statements allow to issue SQL queries to the database
			preparedStatement = connect.prepareStatement("select * from " + TABLE_USERS_NAME);
			// Result set get the result of the SQL query
			resultSet = preparedStatement.executeQuery();
			// INFO
			play.Logger.info("<DATA_BASE> Get Users");

			// Looping over the result user-names
			while (resultSet.next()) {
				usersLst.add(new User(resultSet.getString("user_name"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("email"),
						resultSet.getString("telephone"), resultSet.getString("password"),
						resultSet.getString("birthdate"), resultSet.getString("user_id")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			play.Logger.error(e.getMessage());
			;
		} finally {
			// Closing the resultSet
			close();
		}
		lock.unlock();
		return usersLst;
	}

	public ArrayList<Gelt> getGelts() {
		lock.lock();
		ArrayList<Gelt> gelts = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection("jdbc:mysql://localhost/yankalee?user=" + DATA_BASE_USER_NAME
					+ "&password=" + DATA_BASE_PASSWORD_NAME);
			// Statements allow to issue SQL queries to the database
			preparedStatement = connect.prepareStatement("select * from " + TABLE_BANK_NAME);
			// Result set get the result of the SQL query
			resultSet = preparedStatement.executeQuery();
			// INFO
			play.Logger.info("<DATA_BASE> Get Gelts");

			// Looping over the result user-names
			while (resultSet.next()) {
				gelts.add(new Gelt(resultSet.getInt("debter_id"), resultSet.getInt("amount"),
						resultSet.getInt("entitled_id")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			play.Logger.error(e.getMessage());
			;
		} finally {
			// Closing the resultSet
			close();
		}
		lock.unlock();
		return gelts;
	}

	/**
	 * Getting the temp gelts who weiting for confirming from the debter
	 * 
	 * @return an array witch gelts who weiting for confirming from the debter
	 */
	public ArrayList<Gelt> getTempGelts() {
		lock.lock();
		ArrayList<Gelt> gelts = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection("jdbc:mysql://localhost/yankalee?user=" + DATA_BASE_USER_NAME
					+ "&password=" + DATA_BASE_PASSWORD_NAME);
			// Statements allow to issue SQL queries to the database
			preparedStatement = connect.prepareStatement("select * from " + TABLE_TEMP_DEBTS_NAME);
			// Result set get the result of the SQL query
			resultSet = preparedStatement.executeQuery();
			// INFO
			play.Logger.info("<DATA_BASE> Get Temp Gelts");

			// Looping over the result user-names
			while (resultSet.next()) {
				gelts.add(new Gelt(resultSet.getInt("debter_id"), resultSet.getInt("amount"),
						resultSet.getInt("entitled_id")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			play.Logger.error(e.getMessage());
		} finally {
			// Closing the resultSet
			close();
		}
		lock.unlock();
		return gelts;
	}

	// Closing the resultSet
	private static void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			play.Logger.error(e.getMessage());
			;
		}
	}

}

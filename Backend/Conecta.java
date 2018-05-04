
// Use the JDBC driver  
import java.sql.*;

public class Conecta {
	public static void main(String[] args) {
		String connectionString = "jdbc:sqlserver://localhost:1433;" 
				+ "database=master;" 
				+ "user=andre;"
				+ "password=andre133;" 
				+ "encrypt=false;" 
				+ "trustServerCertificate=false;"
				+ "hostNameInCertificate=*.database.windows.net;" 
				+ "loginTimeout=30;";

		// Declare the JDBC objects.
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(connectionString);

			// Create and execute a SELECT SQL statement.
			String selectSql = "SELECT * from [Usuarios Telegram]";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectSql);

			// Print results from select statement
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the connections after the data has been handled.
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (Exception e) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (Exception e) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (Exception e) {
				}
		}
	}

	public void seleciona() {
		
	}
}
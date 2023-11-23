package dataaccesslayer;
/**
 * Student Name: Mohammadhassan Yeganeshenas
 * Student Number: 041086643
 * Course & Section #: 22S_CST8288_031
 * Declaration: Assignment 01
 * This is my own original work and is free from Plagiarism.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DataSource class for managing database connections.
 */
public class DataSource {

    private Connection connection = null;

    // Initialize the url string variable properly. No need to load the
    // JDBC URL, username, and password from a properties file.
    private final String url = "jdbc:mysql://localhost:3306/peertutor?useSSL=false&allowPublicKeyRetrieval=true";
    private String username = "root";
    private String password = "root";

    /**
     * Default constructor for DataSource.
     */
    public DataSource() {}

    /**
     * Creates a database connection.
     *
     * @return The database connection.
     * @throws ClassNotFoundException If there's an issue with class loading.
     */
    public Connection createConnection() throws ClassNotFoundException {
        // Add your code here. Make sure to use try-catch statement.
        // Make sure there is only one connection for this application
        // to prevent memory leaks.

        try {
            if (connection != null) {
                System.out.println("Cannot create a new connection, one exists already.");
            } else {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}

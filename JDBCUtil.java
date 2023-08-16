package Utils;
import java.sql.*;

public class JDBCUtil implements iDBConnector {
    
    private Connection connection;
    private static JDBCUtil instance;

    private JDBCUtil() throws SQLException {
    
        final String username = System.getenv("DB_USERNAME"); // Use your DB credentials
        final String password = System.getenv("DB_PASSWORD");
        final String serverIPPort = System.getenv("DB_SERVER"); // Use your DB server's IP:PORT
        final String serverEndpoint = "jdbc:oracle:thin:" + username + "/" + password + serverIPPort;
        
        connection = DriverManager.getConnection(serverEndpoint);
        
    }
        
    public static JDBCUtil getInstance() throws SQLException {
        
        if (instance == null) {
            instance = new JDBCUtil();
        }
        return instance;
        
    }  
    
    @Override
    public ResultSet executeRead (String query) throws SQLException {
        
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    
    }
    
    @Override
    public void executeUpdate (String query) throws SQLException { // An update is simply something that writes to database
        
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        
    }

}

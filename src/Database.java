import java.sql.*;

public class Database {



    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/School_Database", "root", "password");

    Statement statement = connection.createStatement();

    ResultSet resultSet = statement.executeQuery("SELECT * FROM schoolDatabase");


    public Database() throws SQLException {
    }
}

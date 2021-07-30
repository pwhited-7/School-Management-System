import javax.xml.crypto.Data;
import java.sql.*;

public class Database {

    private Connection connection = null;
    private Statement statement = null;


    public void insertIntoTable(String table, String id, String name, String gradeLevel, String feesPaid, String feesTotal ){

        try{

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/School_Database", "root", "rootpassword");

            statement = connection.createStatement();

            String dataToInsert = String.format("INSERT INTO %s VALUES(%s, \"%s\", %s, %s, %s)", table, id, name, gradeLevel, feesPaid, feesTotal);

            System.out.println(dataToInsert);
            statement.executeUpdate(dataToInsert);

        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (statement != null)
                    connection.close();
            }catch (SQLException se){

            }
            try{
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        Database data = new Database();

        data.insertIntoTable("students", "3", "Pacen", "6", "0" ,"5000");
    }
}

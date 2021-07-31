import javax.xml.crypto.Data;
import java.sql.*;


public class Database {

    private Connection connection = null;
    private Statement statement = null;
    //DBTablePrinter tablePrinter = new DBTablePrinter();

    public void establishConnection() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/School_Database", "root", "rootpassword");

            statement = connection.createStatement();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void insertIntoTable(String table, String name, int gradeLevel, int feesPaid, int feesTotal ) {

        try{

            establishConnection();

            String dataToInsert = String.format("INSERT INTO %s(name, gradeLevel, feesPaid, feesTotal) VALUES(\"%s\", %d, %d, %d)", table, name, gradeLevel, feesPaid, feesTotal);

            statement.executeUpdate(dataToInsert);
            System.out.printf("%s has been added to the database.\n", name);

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

    public int getId(String studentName) {

        try {
            establishConnection();
            //String sql = "SELECT FROM * students"
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            int studentId = 0;
            while (resultSet.next()) {
                if (resultSet.getString("name").equalsIgnoreCase(studentName)) {
                    studentId = resultSet.getInt("studentId");
                }
            }

            return studentId;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }

    public void createStudentTable(){

        String table = "CREATE TABLE students(\n" +
                "    studentId INT NOT NULL AUTO_INCREMENT,\n" +
                "    name VARCHAR(40),\n" +
                "    gradeLevel INT,\n" +
                "    feesPaid INT,\n" +
                "    feesTotal INT,\n" +
                "    PRIMARY KEY(studentId)\n" +
                ")";
        try {
            establishConnection();
            statement.executeUpdate(table);

        }catch (SQLException se){
            se.printStackTrace();
        }

    }

    public void updateStudentFeesPaid(int feesPaid, String student){
        try {
            String updateFees = String.format("UPDATE students \n" +
                    "SET feesPaid = %d\n" +
                    "WHERE name = \"%s\"", feesPaid, student);
            establishConnection();
            statement.executeUpdate(updateFees);
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void displayTable(String str){
        DBTablePrinter tablePrinter = new DBTablePrinter();
        establishConnection();
        if(str.equalsIgnoreCase("students")){
            tablePrinter.printTable(connection, "students");
        }
        else if (str.equalsIgnoreCase("teachers")){
            tablePrinter.printTable(connection, "teachers");
        }

    }


    public static void main(String[] args) {

        Database data = new Database();
        DBTablePrinter tablePrinter = new DBTablePrinter();
       // data.insertIntoTable("students", "John", 7, 0 ,5000);

        data.establishConnection();
        try {
            ResultSet resultSet = data.statement.executeQuery("SELECT * FROM students");
            data.displayTable("teachers");
            //tablePrinter.printTable(data.connection, "students");
//            int id;
//            while (resultSet.next()){
//                id = resultSet.getInt("studentId");
//                System.out.printf("%d", id);
//
//               // System.out.println(resultSet.getString("name"));
//            }

//            System.out.println(resultSet);
        }catch (SQLException se){
            se.printStackTrace();
        }
        //System.out.println(data.getId("Katlyn"));

        //data.createTable();
    }
}

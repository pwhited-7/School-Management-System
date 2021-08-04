import javax.xml.crypto.Data;
import java.sql.*;


public class Database {

    public Connection connection = null;
    public Statement statement = null;
    //DBTablePrinter tablePrinter = new DBTablePrinter();

    public Database(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://34.106.53.84:3306/school_database", "root", "N43FbN0gADAyIa6k");

            statement = connection.createStatement();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void establishConnection() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://34.106.53.84:3306/school_database", "root", "N43FbN0gADAyIa6k");

            statement = connection.createStatement();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void insertIntoStudentsTable(String table, String name, int gradeLevel, int feesPaid, int feesTotal ) {

        try{

            String dataToInsert = String.format("INSERT INTO %s(name, gradeLevel, feesPaid, feesTotal) VALUES(\"%s\", %d, %d, %d)", table, name, gradeLevel, feesPaid, feesTotal);
            statement.executeUpdate(dataToInsert);
            System.out.printf("%s has been added to the database.\n", name);

        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertIntoTeachersTable(String table, String name, int yearsExperience, int salaryEarned, int salary ) {

        try{

            String dataToInsert = String.format("INSERT INTO %s(name, yearsExperience, salaryEarned, salary) VALUES(\"%s\", %d, %d, %d)",
                    table,
                    name,
                    yearsExperience,
                    salaryEarned,
                    salary);

            statement.executeUpdate(dataToInsert);
            System.out.printf("%s has been added to the database.\n", name);

        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public int getId(String name, String table) {

        try {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+table);
            int id = 0;
            while (resultSet.next()) {
                if (resultSet.getString("name").equalsIgnoreCase(name)) {
                    if(table.equals("students"))
                        id = resultSet.getInt("studentId");
                    else if(table.equals("teachers"))
                        id = resultSet.getInt("teacherId");
                }
            }

            return id;
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

            statement.executeUpdate(updateFees);
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void displayTable(String str){
        DBTablePrinter tablePrinter = new DBTablePrinter();

        if(str.equalsIgnoreCase("students")){
            tablePrinter.printTable(connection, "students");
        }
        else if (str.equalsIgnoreCase("teachers")){
            tablePrinter.printTable(connection, "teachers");
        }

    }

    public ResultSet getResultSet(String table){
        Database data = new Database();
        String sql = String.format("SELECT * FROM %s", table);
        ResultSet resultSet = null;
        try {
            resultSet = data.statement.executeQuery(sql);

        }catch (SQLException se){
            se.printStackTrace();
        }
        return resultSet;
    }


    public static void main(String[] args) {

        Database data = new Database();
        DBTablePrinter tablePrinter = new DBTablePrinter();
       // data.insertIntoTable("students", "John", 7, 0 ,5000);
        //data.insertIntoTeachersTable("teachers", "John", 4, 0, 50000);
//        data.establishConnection();
        try {
            ResultSet resultSet = data.statement.executeQuery("SELECT name FROM students WHERE studentId = 1");

            //System.out.println(name);
//            data.displayTable("students");
//            //tablePrinter.printTable(data.connection, "students");
////            int id;
            //while (resultSet.next()){
                resultSet.next();
                System.out.println(resultSet.getString("name"));
           // }
//
////            System.out.println(resultSet);
        }catch (SQLException se){
            se.printStackTrace();
        }
        //System.out.println(data.getId("Katlyn"));

        //data.createTable();
    }
}

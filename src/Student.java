import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Student implements ListInterface{
    private int id;
    private String name;
    private int gradeLevel;
    private int feesPaid;
    private int feesTotal;

    /**
     * Create a new student by initializing,
     * Fees for every student is $5,000.00
     * Fees paid initially is $0.00
     *
     //* @param id id for the student
     * @param name name of the student
     * @param gradeLevel grade level of the student (1-12)
     */
    public Student(int id, String name, int gradeLevel){
        feesPaid = 0;
        feesTotal = 5000;
        this.id = id;
        this.name = name;
        this.gradeLevel = gradeLevel;
    }
    public Student(){ }

    /**
     * Checks if a student has paid more than they owe
     * */
    public void checkIfFeesOverPaid(String name){
        ResultSet resultSet = database.getResultSet("students");
        try{
            while (resultSet.next()){
                if(resultSet.getString("name").equalsIgnoreCase(name)){
                    if(resultSet.getInt("feesPaid") == 10000)
                        System.out.println("This student has completely paid their fees");
                    else if(resultSet.getInt("feesPaid") > 10000)
                        System.out.println("This student has completely paid there fees and also more then they are required to pay.");
                }
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }


    public boolean verifyStudentIsRegistered(String studentName){

        try{

            ResultSet resultSet = database.statement.executeQuery("SELECT * FROM students");

            while(resultSet.next()){
                if(studentName.equalsIgnoreCase(resultSet.getString("name"))){
                    return true;
                }
            }

            resultSet.close();
        }catch (SQLException se){
            se.printStackTrace();
        }
        return false;
    }

    public int getFeesToPay(){
        Scanner scnr = new Scanner(System.in);
        int n=0;
        boolean flag;

        do {
            try {

                System.out.print("How much do you want to pay? ");
                n=scnr.nextInt();
                flag=false;
            }
            catch(Exception e) {
                // accept integer only.
                System.out.println("This is not an accepted value.");
                flag=true;
            }
        }
        while(flag);
        return n;
    }

    @Override
    public String toString(){
        return "\nStudent's name: " +name+ " total fees paid so far $" +feesPaid;
    }

    public static void main(String[] args) {
       // Student S = new Student(4, "Pacen", 5);
       // Student S1 = new Student(5, "John", 6);
        School sch = new School();
//        sch.addStudent(S);
//        sch.addStudent(S1);
//        sch.getStudents();


        //System.out.println(S);
//        S.students.add(S);
//        System.out.println(S.students.get(0).getId());
    }
}

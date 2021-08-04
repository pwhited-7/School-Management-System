import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This class is responsible for keeping
 * track of students id, fees, name & grades
 *
 * */
public class Student implements ListInterface{
    private int id;
    private String name;
    private int gradeLevel;
    private int feesPaid;
    private int feesTotal;
    //public List<Student> students = new
    //ArrayList<Student>(100);
   // School school = new School();


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

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public void setId(int id){
        this.id = id;
    }

    public void payFees(int fees){
        feesPaid += fees;
        database.updateStudentFeesPaid(feesPaid, this.name);
        School.updateTotalMoneyEarned(feesPaid);
    }



    public void checkIfFeesOverPaid(String name){
        ResultSet resultSet = database.getResultSet("students");
        try{
            while (resultSet.next()){
                if(resultSet.getString("name").equalsIgnoreCase(name)){
                    if(resultSet.getInt("feesPaid") == 5000)
                        System.out.println("This student has completely paid their fees");
                    else if(resultSet.getInt("feesPaid") > 5000)
                        System.out.println("This student has completely paid there fees and also more then they are required to pay.");
                }
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public int getFeesPaid(){
        return feesPaid;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getFeesTotal(){
        return feesTotal;
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

    public Student getStudentObject(String studentName){

        for(Student student : studentsList){
            if(student.getName().equalsIgnoreCase(studentName)){

                return student;
            }
            else
                System.out.println("This student is not registered.");
        }
        return null;
    }

    public Student getStudentById(int id){
        Student S = null;
        for(Student student: studentsList){
            if(id == student.getId()){
                S = student;
            }
        }
        return S;
    }

    /**
     * @return the remaining fees.
     */
    public int getRemainingTotal(){
        return feesTotal - feesPaid;
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

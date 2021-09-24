import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Teacher implements ListInterface{
    private int id;
    private String name;
    private int salary;
    private int salaryEarned;
    private int yearsExperience;

    public Teacher(int id, String name, int yearsExperience) {
        this.id = id;
        this.name = name;
        this.yearsExperience = yearsExperience;
        salaryNegotiation(yearsExperience);
        this.salaryEarned = 0;
    }
    public Teacher(){
    }


    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setSalary(int salary){
        this.salary=salary;
    }


    /**
     * Adds  to salaryEarned.
     * Removes from the total money earned by the school.
     * @param salary
     */
    public void receiveSalary(int salary){
        salaryEarned+=salary;
        School.updateTotalMoneySpent(salary);
    }

    /**
    *  Decides the salary of the teacher based on how many years of experience they have.
    *  @param experience
    * */

    public int salaryNegotiation(int experience){

        if(experience == 0 || experience == 1){
            setSalary(40000);
        }
        else if(experience == 2 || experience == 3) {
            setSalary(45000);
        }
        else if(experience == 4 || experience == 5){
            setSalary(50000);
        }
        else if(experience == 6 || experience == 7){
            setSalary(55000);
        }
        else if(experience == 8 || experience == 9){
            setSalary(60000);
        }
        else if(experience > 9){
            setSalary(70000);
        }
        return this.salary;
    }

    public boolean verifyTeacherIsRegistered(String teacherToVerify){

        try{
            ResultSet resultSet = database.statement.executeQuery("SELECT * FROM teachers");
            while(resultSet.next()){
                if(teacherToVerify.equalsIgnoreCase(resultSet.getString("name"))){
                    return true;
                }
            }
            resultSet.close();
        }catch (SQLException se){
            se.printStackTrace();
        }
        return false;
    }


    public int getTeacherSalary(String name){
        int salary = 0;

        try{
            ResultSet resultSet = database.getResultSet("teachers");
            while (resultSet.next()){
                if(resultSet.getString("name").equalsIgnoreCase(name)){
                    salary = resultSet.getInt("salary");
                }
            }
        }catch (SQLException se){
            se.printStackTrace();
        }

        return salary;
    }

    @Override
    public String toString() {
        return "\nName of the Teacher: " + name +" total salary earned so far $" + salaryEarned;
    }

    public static void main(String[] args) {
//        Teacher T = new Teacher(4, "Pacen", 4);
//        Teacher T1 = new Teacher(5, "John", 4);
        School sch = new School();
        Teacher T = new Teacher();
        T.receiveSalary(400);
        //sch.getTeachers();
        System.out.println(T.getTeacherSalary("Wil"));
    }


}


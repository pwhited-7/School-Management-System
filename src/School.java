import java.sql.*;
import java.lang.*;
import java.util.Random;
import java.util.Scanner;

public class School implements ListInterface{

    Scanner scnr = new Scanner(System.in);
    Teacher teach = new Teacher();
    Student stud = new Student();

    public School(){

    }

    /**
     * Adds a student to the school
     * @param student the student to be added.
     */
    public void addStudent(Student student) {
        studentsList.add(student);
    }

    /**
     *
     * @return the total money earned by the school.
     */
    public int getTotalMoneyEarned() {
        int moneyEarned = 0;
        ResultSet resultSet = database.getResultSet("school");

        try{
            resultSet.next();
            moneyEarned = resultSet.getInt("totalMoneyEarned");

        }catch (SQLException se){
            se.printStackTrace();
        }

        return moneyEarned;
    }

    /**
     * Adds the total money earned by the school.
     * @param moneyEarned money that is supposed to be  added.
     */
    public static void updateTotalMoneyEarned(int moneyEarned) {
        String updateMoney = String.format("UPDATE school set totalMoneyEarned = totalMoneyEarned + %d", moneyEarned);
        try{
            database.statement.executeUpdate(updateMoney);
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    /**
     * update the money that is spent by the school which
     * is the salary given by the school to its teachers.
     * @param moneySpent the money spent by school.
     */
    public static void updateTotalMoneySpent(int moneySpent) {
        //totalMoneyEarned-=moneySpent;
    }

    /**
     * Creates a student and adds them to the database.
     * Asks user for name and grade level.
     * Student ID is chosen by database
     * */
    public void createStudent()  {

        //Getting Name of Student
        System.out.print("Student Full Name: ");
        String studentName = scnr.nextLine();

        while(stringVerification(studentName)){
            System.out.print("Student Full Name: ");
            studentName = scnr.nextLine();
        }

        //Getting Grade Level
        int gradeLevel = correctGradeValue();
        if(gradeLevel > 12 || gradeLevel < 1){
            while(gradeLevel > 12 || gradeLevel < 1) {
                System.out.println("This is not an accepted grade level.");
                gradeLevel = correctGradeValue();
            }
        }
        database.insertIntoStudentsTable("students", studentName, gradeLevel, 0, 10000);

        //Assign student ID which is created automatically by database
        int id = assignStudentId(studentName);

        Student S = new Student(id, studentName, gradeLevel);
        addStudent(S);

    }

    public int assignStudentId(String studentName){

        int id = database.getId(studentName, "students");

        System.out.println("The student has been giving the ID: " + id);
        return id;
    }

    public int assignTeacherId(String teacherName){
        int id = database.getId(teacherName, "teachers");
        System.out.println("The teacher has been given the ID: "+id);
        return id;
    }

    public int correctGradeValue(){
        int n=0;
        boolean flag;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Grade (1-12): ");
                n=sc.nextInt();
                flag=false;
            }
            catch(Exception e) {
                System.out.println("This is not an accepted value.");
                flag=true;
            }
        }
        while(flag);
        return n;
    }


    /**
     * Creates a teacher and add them to the database.
     * Asks user for name, years of experience.
     * Uses salaryNegotiation() method to decide salary
     * ID assigned by database
     * */
    public void createTeacher(){
        System.out.print("Teacher Name: ");
        String teacherName = scnr.nextLine();

        while(stringVerification(teacherName)){
            System.out.print("Teacher Name: ");
            teacherName = scnr.nextLine();
        }
        //Getting years experience
        int yearsExperience = getYearsExperience();
        int salary = teach.salaryNegotiation(yearsExperience);
        System.out.println("Yearly salary is $" + salary);

        database.insertIntoTeachersTable("teachers", teacherName, yearsExperience, 0, salary);

        //Assign id to Teacher
        int id = assignTeacherId(teacherName);
        System.out.println();

        Teacher T = new Teacher(id, teacherName, yearsExperience);

        teachersList.add(T);

    }

    public boolean stringVerification(String str){
        boolean flag;
        for (int i = 0; i < str.length(); i++){
            flag = Character.isDigit(str.charAt(i));
            if(flag){
                System.out.println("Can't contain a number.");
                return true;
            }

        }

        return false;
    }


    public int getYearsExperience(){

        int experience = 0;
        boolean flag;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Years of experience: ");
                experience = sc.nextInt();
                flag=false;
            }
            catch(Exception e) {
                // accept integer only.
                System.out.println("This is not an accepted grade level.");
                flag=true;
            }
        }
        while(flag);
        return experience;
    }



    public int getCurrentFeesPaid(String studentOrTeacherName, String table, String query ){
        int feesOrSalaryPaid = 0;
        try{
            String sql = String.format("SELECT * FROM %s", table);
            ResultSet resultSet = database.statement.executeQuery(sql);
            while (resultSet.next()){
                if(resultSet.getString("name").equalsIgnoreCase(studentOrTeacherName)){
                    feesOrSalaryPaid = resultSet.getInt(query);
                }
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
        return feesOrSalaryPaid;

    }

    public void removeStudent(){

        String studentToRemove;
        do {
            System.out.print("Name of the student you would like to remove from the school? (Full Name) ");
            studentToRemove = scnr.nextLine();

        }while (stringVerification(studentToRemove));

        boolean flag = stud.verifyStudentIsRegistered(studentToRemove);

        if(flag)
            studentTeacherRemoverHelper("students", studentToRemove);

        else
            System.out.println("That student has already been removed or was never registered at this school.");
    }

    public void removeTeacher() {

        String teacherToRemove;
        do {
            System.out.print("Name of the teacher you would like to remove from the school? (Full Name) ");
            teacherToRemove = scnr.nextLine();
        } while (stringVerification(teacherToRemove));

        boolean flag = teach.verifyTeacherIsRegistered(teacherToRemove);

        if (flag)
            studentTeacherRemoverHelper("teachers", teacherToRemove);
        else
            System.out.println("That teacher has already been removed or was never registered at this school. ");
    }


    public void studentTeacherRemoverHelper(String table, String name){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://34.106.53.84:3306/school_database", "root", "N43FbN0gADAyIa6k");
            Statement status = conn.createStatement();
            String query = String.format("SELECT * FROM %s", table);
            ResultSet resultSet = status.executeQuery(query);
            while(resultSet.next()){
                if(name.equalsIgnoreCase(resultSet.getString("name"))){
                    String sql = String.format("DELETE FROM %s WHERE name = \"%s\"", table, name);
                    database.statement.executeUpdate(sql);
                    System.out.println(name +" has been removed from the database");
                }
            }
            resultSet.close();

        }catch (SQLException se ){
            se.printStackTrace();
        }
    }


    public void financialsSection(){

        int numberDecision = selectFinancialOption();

        if(numberDecision == 1){
            payStudentFees();
        }
        else if(numberDecision == 2){
            payTeacherSalary();
        }
        else if(numberDecision == 3){
            displaySchoolEarnings();
        }

    }

    public int selectFinancialOption(){
        int decision = 0;
        boolean flag;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("What would you like to do? (Enter a number) ");
                decision = sc.nextInt();
                flag=false;
            }
            catch(Exception e) {
                System.out.println("This is not an accepted value.");
                flag=true;
            }
        }
        while(flag);
        return decision;
    }

    public void payStudentFees(){
        String studentName;

        do {
            System.out.print("Who would you like to pay fees for? (Full Name) ");
            studentName = scnr.nextLine();

        }while (stringVerification(studentName));

        boolean flag = stud.verifyStudentIsRegistered(studentName);

        payFeesHelper("students", studentName, "Student", flag);

    }

    public void payTeacherSalary(){
        String teacherName;

        do {
            System.out.print("Who's salary would you like to pay? (Full Name) ");
            teacherName = scnr.nextLine();

        }while (stringVerification(teacherName));

        boolean flag = teach.verifyTeacherIsRegistered(teacherName);

        payFeesHelper("teachers", teacherName, "teacher", flag);

    }

    public void payFeesHelper(String table, String name, String studentOrTeacher, boolean flag){
        String feesPaid = "feesPaid";
        String sqlUpdate = "";
        String earningsUpdate = "";
        int totalFeesPaid = 0;

        if(!flag){
            System.out.println( studentOrTeacher + " is not in the database");
        }
        else {
            if(studentOrTeacher.equalsIgnoreCase("Student")) {
                int currentFeesPaid = getCurrentFeesPaid(name, table, feesPaid);
                System.out.println(name + " has currently paid $" + currentFeesPaid + " out of the $5000 owed.");
                int feesToPay = stud.getFeesToPay();
                sqlUpdate = String.format("UPDATE students SET feesPaid = feesPaid + %d WHERE name = \"%s\"", feesToPay, name);
                earningsUpdate = String.format("UPDATE school set totalMoneyEarned = totalMoneyEarned + %d", feesToPay);
                try {
                    ResultSet resultSet = database.getResultSet("students");
                    while (resultSet.next()) {
                        if (resultSet.getString("name").equalsIgnoreCase(name)) {
                            totalFeesPaid = feesToPay + resultSet.getInt(feesPaid);
                            database.statement.executeUpdate(sqlUpdate);
                            database.statement.executeUpdate(earningsUpdate);
                        }
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }
                stud.checkIfFeesOverPaid(name);

                System.out.println(name + " now has paid: $" + totalFeesPaid + " out of the $5000 owed.");
            }
            else if(studentOrTeacher.equalsIgnoreCase("teacher")){
                int salaryPaid = getCurrentFeesPaid(name, "teachers", "salaryEarned");
                int salaryTotal = teach.getTeacherSalary(name);
                System.out.printf("%s has been paid $%d of their $%d salary.%n", name, salaryPaid, salaryTotal);
                int salaryToPay = stud.getFeesToPay();
                sqlUpdate = String.format("UPDATE teachers SET salaryEarned = salaryEarned + %d WHERE name = \"%s\"", salaryToPay, name);
                earningsUpdate = String.format("UPDATE school set totalMoneyEarned = totalMoneyEarned - %d", salaryToPay);
                try{
                    ResultSet resultSet = database.getResultSet("teachers");
                    while (resultSet.next()){
                        if(resultSet.getString("name").equalsIgnoreCase(name)){
                            totalFeesPaid = salaryToPay + resultSet.getInt("salaryEarned");
                            database.statement.executeUpdate(sqlUpdate);
                            database.statement.executeUpdate(earningsUpdate);
                        }
                    }
                }catch (SQLException se){
                    se.printStackTrace();
                }
                System.out.printf("%s has now been paid $%d of their $%d salary%n", name, totalFeesPaid, salaryTotal);
            }
        }

    }

    public void displaySchoolEarnings(){
        System.out.printf("%nThe school has earned $%d\n", getTotalMoneyEarned());
    }

    public void displayStudentInfo(){

        String studentName = "";
        do {
            System.out.print("What is the name of the Student (Full Name) ");
            studentName = scnr.nextLine();
        }while (stringVerification(studentName));

        if(stud.verifyStudentIsRegistered(studentName))
            displayInfoHelper("students", studentName);
        else
            System.out.printf("%s is not registered in this database.%n", studentName);

    }

    public void displayTeacherInfo(){
        String teacherName = "";
        do {
            System.out.print("What is the name of the Teacher? (Full Name) ");
            teacherName = scnr.nextLine();
        }while (stringVerification(teacherName));

        if(teach.verifyTeacherIsRegistered(teacherName))
            displayInfoHelper("teachers", teacherName);
        else
            System.out.printf("%s is not registered in this database.%n", teacherName);

    }

    public void displayInfoHelper(String table, String name){

        try {
            String querySql = String.format("SELECT * FROM %s", table);
            ResultSet resultSet = database.statement.executeQuery(querySql);

            //String name;
            int experienceOrGradeLevel = 0;
            int id = 0;
            int salaryOrFeesTotal = 0;
            int salaryEarnedOrFeesPaid = 0;

            while (resultSet.next()){
                if(name.equalsIgnoreCase(resultSet.getString("name"))){
                    //name = resultSet.getString("name");
                    if(table.equals("teachers")) {
                        experienceOrGradeLevel = resultSet.getInt("yearsExperience");
                        id = resultSet.getInt("teacherId");
                        salaryEarnedOrFeesPaid = resultSet.getInt("salaryEarned");
                        salaryOrFeesTotal = resultSet.getInt("salary");

                        System.out.printf("""
                                          %nTeacher's Information:
                                          Name: %s
                                          Year's Experience: %d
                                          ID: %d
                                          Salary: $%d of $%d has been paid to %s.
                                          """, name, experienceOrGradeLevel, id, salaryEarnedOrFeesPaid, salaryOrFeesTotal, name);
                    }
                    else if(table.equals("students")) {
                        experienceOrGradeLevel = resultSet.getInt("gradeLevel");
                        id = resultSet.getInt("studentId");
                        salaryEarnedOrFeesPaid = resultSet.getInt("feesPaid");
                        salaryOrFeesTotal = resultSet.getInt("feesTotal");

                        System.out.printf("""
                                            %nStudent's Information:
                                            Name: %s
                                            Grade: %d
                                            ID: %d
                                            Fees Paid: %s has paid $%d of their $%d balance.
                                            """, name, experienceOrGradeLevel, id, name, salaryEarnedOrFeesPaid, salaryOrFeesTotal);
                    }
                }
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public int verifyIdIsInteger(){
        int n=0;
        boolean flag;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("What is the ID number? ");
                n=sc.nextInt();
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

    public void displayDatabase(){
        System.out.print("Which database would you like to see? (students or teachers) ");
        String studentOrTeacher = scnr.nextLine();
        System.out.println();
        if(!stringVerification(studentOrTeacher)) {
            database.displayTable(studentOrTeacher);
        }
    }

    public static void main(String[] args) {
        School sc = new School();
//        updateTotalMoneyEarned(100);
//        updateTotalMoneySpent(200);
//        System.out.println(totalMoneyEarned);
//        System.out.println(totalMoneySpent);

        //System.out.println(sc.getCurrentFeesPaid("Pacen Whited"));
        //System.out.println(sc.getCurrentFeesPaid("Pacen Whited", "students", "feesPaid"));
        sc.updateTotalMoneyEarned(1);
        System.out.println(sc.getTotalMoneyEarned());


        // sc.payStudentFees();
    }

}
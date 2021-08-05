import java.sql.*;
import java.lang.*;
import java.util.Random;
import java.util.Scanner;

public class School implements ListInterface{
    private static int  totalMoneyEarned = 0;
    private static int totalMoneySpent = 0;
    private static int studentIdCounter = 0;
    private static int teacherIdCounter = 0;

    Random rand = new Random();
    Scanner scnr = new Scanner(System.in);
    Teacher teach = new Teacher();
    Student stud = new Student();
    //Database database = new Database();

//    List<Student> studentsList = new ArrayList<>();
//    List<Teacher> teachersList = new ArrayList<>();


    public void getTeachers() {
        for(Teacher teacher : teachersList){
            System.out.println(teacher);
        }

        if(teachersList.size() == 0){
            System.out.println("There are no teachers registered.");
        }
        else if(teachersList.size() == 1) {
            System.out.printf("There is %d teacher registered\n", teachersList.size());
        }
        else{
            System.out.printf("There are %d teachers registered\n", teachersList.size());
        }
    }

    /**
     * Adds a teacher to the school.
     * @param teacher the teacher to be added.
     */
    public void addTeacher(Teacher teacher) {
        teachersList.add(teacher);
    }

    public void getStudents() {
        for(Student student : studentsList){
            System.out.println(student);
        }
        if(studentsList.size() == 0){
            System.out.println("There are no students registered.");
        }
        else if(studentsList.size() == 1) {
            System.out.printf("There is %d student registered\n", studentsList.size());
        }
        else{
            System.out.printf("There are %d students registered\n", studentsList.size());
        }
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
        return totalMoneyEarned;
    }

    /**
     * Adds the total money earned by the school.
     * @param MoneyEarned money that is supposed to be  added.
     */
    public static void updateTotalMoneyEarned(int MoneyEarned) {
        totalMoneyEarned += MoneyEarned;
    }

    /**
     *
     * @return the total money spent by the school.
     */
    public int getTotalMoneySpent() {
        return totalMoneySpent;
    }

    /**
     * update the money that is spent by the school which
     * is the salary given by the school to its teachers.
     * @param moneySpent the money spent by school.
     */
    public static void updateTotalMoneySpent(int moneySpent) {
        totalMoneyEarned-=moneySpent;
    }

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
        database.insertIntoStudentsTable("students", studentName, gradeLevel, 0, 5000);

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
                // accept integer only.
                System.out.println("This is not an accepted value.");
                flag=true;
            }
        }
        while(flag);
        return n;
    }

    public void createTeacher(){
        System.out.print("Teacher Name: ");
        String teacherName = scnr.nextLine();

        while(stringVerification(teacherName)){
            System.out.print("Teacher Name: ");
            teacherName = scnr.nextLine();
        }

        //Getting Grade Level
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

    public int assignTeacherId(){
        teacherIdCounter += 1;
        // int id = rand.nextInt( 100) + 1;
//        for(Student a : studentsList){
//            int studentID = a.getId();

//            while(studentID == id) {
//                id = rand.nextInt(100) + 1;
//            }
        //}
        System.out.print("The teacher has been giving the ID: " + teacherIdCounter);
        return teacherIdCounter;

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

    public void payStudentFees(){
        String studentName;

        do {
            System.out.print("Who would you like to pay fees for? (Full Name) ");
            studentName = scnr.nextLine();

        }while (stringVerification(studentName));

        boolean flag = stud.verifyStudentIsRegistered(studentName);


        if(!flag){
            System.out.println("Student is not enrolled");
        }
        else {
            int currentFeesPaid = getCurrentFeesPaid(studentName);
            System.out.println(studentName + " has currently paid $" + currentFeesPaid + " out of the $5000 owed.");

            int feesToPay = stud.getFeesToPay();
            int totalFeesPaid = 0;

            String sqlUpdate = String.format("UPDATE students SET feesPaid = feesPaid + %d WHERE name = \"%s\"", feesToPay, studentName);
            try{
                ResultSet resultSet = database.getResultSet("students");
                while (resultSet.next()){
                    if(resultSet.getString("name").equalsIgnoreCase(studentName)){
                        totalFeesPaid = feesToPay + resultSet.getInt("feesPaid");
                        database.statement.executeUpdate(sqlUpdate);
                    }
                }
            }catch (SQLException se){
                se.printStackTrace();
            }
            stud.checkIfFeesOverPaid(studentName);

            System.out.println(studentName + " now has paid: $" + totalFeesPaid + " out of the $5000 owed.");
        }

    }

    public int getCurrentFeesPaid(String studentName){
        int studentFees = 0;

        try{
            ResultSet resultSet = database.statement.executeQuery("SELECT * FROM students");
            while (resultSet.next()){
                if(resultSet.getString("name").equalsIgnoreCase(studentName)){
                    studentFees = resultSet.getInt("feesPaid");
                }
            }

        }catch (SQLException se){
            se.printStackTrace();
        }

        return studentFees;
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
        else if(numberDecision == 4){
            getStudents();
        }
        else if(numberDecision == 5){
            getTeachers();
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
                // accept integer only.
                System.out.println("This is not an accepted value.");
                flag=true;
            }
        }
        while(flag);
        return decision;
    }

    public void payTeacherSalary(){

        String teacherToPay;
        do{
            System.out.print("Who's salary would you like to pay? (Full Name) ");
            teacherToPay = scnr.nextLine();
        }while (stringVerification(teacherToPay));

        boolean teacherVerification = teach.verifyTeacherIsRegistered(teacherToPay);
        if(teacherVerification){
            Teacher foundTeacher = teach.getTeacher(teacherToPay);
            System.out.println(foundTeacher.getName() + " has been paid $" + foundTeacher.getSalaryEarned() + " of their $" + foundTeacher.getSalary() + " salary.");
            System.out.print("How much of " + foundTeacher.getName() + "'s salary would you like to pay? ");
            int salaryToPay = scnr.nextInt();
            foundTeacher.receiveSalary(salaryToPay);
            System.out.println(foundTeacher.getName() +" has now been paid $" + foundTeacher.getSalaryEarned() + " of their $" + foundTeacher.getSalary() + " salary.");
        }
        else{
            System.out.println("This teacher was not found in the system.");
        }

    }

    public void displaySchoolEarnings(){
        System.out.printf("The school has earned $%d\n", getTotalMoneyEarned());
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
            String studentOrTeacher ="";
            String gradeLevelOrExperience = "";
            String salaryOrFeesString = "";

            while (resultSet.next()){
                if(name.equalsIgnoreCase(resultSet.getString("name"))){
                    //name = resultSet.getString("name");
                    if(table.equals("teachers")) {
                        experienceOrGradeLevel = resultSet.getInt("yearsExperience");
                        id = resultSet.getInt("teacherId");
                        salaryEarnedOrFeesPaid = resultSet.getInt("salaryEarned");
                        salaryOrFeesTotal = resultSet.getInt("salary");
                        studentOrTeacher = "Teacher's";
                        gradeLevelOrExperience = "Year's Experience";
                        salaryOrFeesString = "Salary";
                    }
                    else if(table.equals("students")) {
                        experienceOrGradeLevel = resultSet.getInt("gradeLevel");
                        id = resultSet.getInt("studentId");
                        salaryEarnedOrFeesPaid = resultSet.getInt("feesPaid");
                        salaryOrFeesTotal = resultSet.getInt("feesTotal");
                        studentOrTeacher = "Student's";
                        gradeLevelOrExperience = "Grade Level";
                        salaryOrFeesString = "Fees Paid";
                    }
                }
            }
            System.out.printf("%n%s Information:%n" +
                    "Name: %s%n" +
                    "%s: %d%n" +
                    "ID: %d%n" +
                    "%s: $%d of $%d has been paid to %s%n", studentOrTeacher, name, gradeLevelOrExperience, experienceOrGradeLevel ,id, salaryOrFeesString ,salaryEarnedOrFeesPaid, salaryOrFeesTotal, name);
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

        System.out.println(sc.getCurrentFeesPaid("Pacen Whited"));
    }

}
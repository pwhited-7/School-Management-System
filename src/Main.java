import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        Display display = new Display();
        Controller control = new Controller();
        //StudentNavigation studentNav = new StudentNavigation();
        //Student stud = new Student();
        //Teacher teach = new Teacher();
        //TeacherNavigation teachNav = new TeacherNavigation();


        display.printIntro();
        boolean exit = false;

        while (!exit) {

            if (exit) {
                exit = true;
            } else {
                exit = control.usersDecision();
            }
        }

//        for(Student a : control.studentsList){
//            System.out.println(a.getName() + ": " + a.getId());
//        }

//        for(Teacher a: control.teachersList){
//            System.out.println(a.getName() + " " + a.getId());
//
//        }










       /* Teacher lizzy = new Teacher(1,"Lizzy",500);
        Teacher mellisa = new Teacher(2,"Mellisa",700);
        Teacher vanderhorn = new Teacher(3,"Vanderhorn",600);

        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(lizzy);
        teacherList.add(mellisa);
        teacherList.add(vanderhorn);


        Student tamasha = new Student(1,"Tamasha",4);
        Student rakshith = new Student(2,"Rakshith Vasudev",12);
        Student rabbi = new Student(3,"Rabbi",5);
        List<Student> studentList = new ArrayList<>();

        studentList.add(tamasha);
        studentList.add(rabbi);
        studentList.add(rakshith);




        School ghs = new School(teacherList,studentList);

        Teacher megan = new Teacher(6,"Megan", 900);

        ghs.addTeacher(megan);


        tamasha.payFees(5000);
        rakshith.payFees(6000);
        System.out.println("GHS has earned $"+ ghs.getTotalMoneyEarned());

        System.out.println("------Making SCHOOL PAY SALARY----");
        lizzy.receiveSalary(lizzy.getSalary());
        System.out.println("GHS has spent for salary to " + lizzy.getName()
                +" and now has $" + ghs.getTotalMoneyEarned());

        vanderhorn.receiveSalary(vanderhorn.getSalary());
        System.out.println("GHS has spent for salary to " + vanderhorn.getName()
                +" and now has $" + ghs.getTotalMoneyEarned());


        System.out.println(rakshith);

        mellisa.receiveSalary(mellisa.getSalary());

        System.out.println(mellisa);
        */

    }
}
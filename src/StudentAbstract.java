import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class StudentAbstract {

//    Random rand = new Random();
//
//    protected final List<Student> studentList = new ArrayList<>();
//
//    public void createStudent(){};
//    public int assignStudentId(){
//        int id = rand.nextInt( 100) + 1;
//        for(Student a : studentList){
//            int studentID = a.getId();
//
//            while(studentID == id) {
//                id = rand.nextInt(100) + 1;
//            }
//        }
//        System.out.println("The student has been giving the ID: " + id);
//        System.out.println();
//        return id;
//    }
//
//    public int correctGrade(){
//        int n=0;
//        boolean flag;
//
//        do {
//            try {
//                Scanner sc = new Scanner(System.in);
//                System.out.print("Grade (1-12): ");
//                n=sc.nextInt();
//                flag=false;
//            }
//            catch(Exception e) {
//                // accept integer only.
//                System.out.println("This is not an accepted grade level.");
//                flag=true;
//            }
//        }
//        while(flag);
//        return n;
//    }
//
//    public boolean isInt(String str){
//        boolean flag = false;
//        for (int i = 0; i < str.length(); i++){
//            flag = Character.isDigit(str.charAt(i));
//            if(flag){
//                System.out.println("Can't contain a number.");
//                return true;
//            }
//
//        }
//
//        return false;
//    }
}

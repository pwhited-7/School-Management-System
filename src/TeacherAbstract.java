import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class TeacherAbstract {
    Random rand = new Random();
    Teacher teach = new Teacher();

//    List<Teacher> teachersList = new ArrayList<>();
//
//    public void createTeacher() {
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
//
//    public int assignTeacherId(){
//        int id = rand.nextInt( 100) + 1;
//        for(Teacher teacher : teachersList){
//            int teacherId = teacher.getId();
//
//            while(teacherId == id) {
//                id = rand.nextInt(100) + 1;
//            }
//        }
//        System.out.println("The teacher has been giving the ID: " + id);
//        return id;
//
//    }
//
//    public int getYearsExperience(){
//
//        int experience = 0;
//        boolean flag;
//
//        do {
//            try {
//                Scanner sc = new Scanner(System.in);
//                System.out.print("Years of experience: ");
//                experience = sc.nextInt();
//                flag=false;
//            }
//            catch(Exception e) {
//                // accept integer only.
//                System.out.println("This is not an accepted grade level.");
//                flag=true;
//            }
//        }
//        while(flag);
//        return experience;
//    }
}

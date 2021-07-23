import java.util.*;

public class Controller extends School{
    Display display = new Display();
    Scanner scnr = new Scanner(System.in);

    public boolean usersDecision(){
        String usersDecision;

        do {
            display.printPrompt();
            usersDecision = scnr.nextLine();

        }while (stringVerification(usersDecision));

        if(usersDecision.equalsIgnoreCase("t") || usersDecision.equalsIgnoreCase("teacher")){
            createTeacher();
        }
        else if (usersDecision.equalsIgnoreCase("s") || usersDecision.equalsIgnoreCase("student")){
            createStudent();
        }
        else if(usersDecision.equalsIgnoreCase("exit") || usersDecision.equalsIgnoreCase("goodbye")){
            return display.exit();
        }
        else if(usersDecision.equalsIgnoreCase("f") || usersDecision.equalsIgnoreCase("financials")){
            display.printFinancialsPrompt();
            financialsSection();
        }
        else if (usersDecision.equalsIgnoreCase("remove student")){
            removeStudent();
        }
        else if(usersDecision.equalsIgnoreCase("remove teacher")){
            removeTeacher();
        }
        else if (usersDecision.equalsIgnoreCase("help")){
            display.printHelpMenu();
        }
        return false;

    }


    public static void main(String[] args) {
//        Display display = new Display();
//        Controller control = new Controller();
//        display.printIntro();
//
//        boolean exit = false;
//
//        while (!exit) {
//
//            if (exit) {
//                exit = true;
//            } else {
//                exit = control.usersDecision();
//            }
//        }
//
//        System.out.println(control.studentsList.get(0).getId());
    }

}

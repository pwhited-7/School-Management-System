public class Display {

    public void printIntro(){

        System.out.print("\n\tSCHOOL MANAGEMENT SYSTEM\n" +
                "Here are a few options to get started:\n\n" +
                "teacher or t - registers a new teacher\n" +
                "student or s - registers a new student\n" +
                "financials or f - pay student fees or access all school finances\n" +
                "remove student - removes a student\n" +
                "remove teacher - removes a teacher\n" +
                "exit or goodbye - leaves the School Management System\n");

    }

    public void printPrompt(){
        System.out.print("\nWhat would you like to do? ");
    }

    public boolean exit(){
        System.out.println("Have a good day!");
        return true;
    }

    public void printFinancialsPrompt(){
        System.out.println("\tSCHOOL FINANCIALS\n" +
                "1 - pay student fees\n" +
                "2 - pay teachers their salary\n" +
                "3 - shows how much money the school has made\n" +
                "4 - display all students and how much of their fees have been paid\n" +
                "5 - display all teachers and how much of their salary has been paid\n");
    }

    public void printHelpMenu(){
        System.out.print(
                "Here are a few options to get started:\n\n" +
                "teacher or t - registers a new teacher\n" +
                "student or s - registers a new student\n" +
                "financials or f - pay student fees or access all school finances\n" +
                "remove student - removes a student\n" +
                "remove teacher - removes a teacher\n" +
                "exit or goodbye - leaves the School Management System\n" +
                "help - shows your options if you forget\n");
    }
}

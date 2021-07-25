public class Display {

    public void printIntro(){

        System.out.print("""

                \tSCHOOL MANAGEMENT SYSTEM
                Here are a few options to get started:

                teacher or t     - registers a new teacher
                student or s     - registers a new student
                financials or f  - pay student fees or access all school finances
                find student     - display all student info (by student ID)
                find teacher     - display all teacher info (by teacher ID)
                remove student   - removes a student
                remove teacher   - removes a teacher
                exit or goodbye  - leaves the School Management System
                """);

    }

    public void printPrompt(){
        System.out.print("\nWhat would you like to do? ");
    }

    public boolean exit(){
        System.out.println("Have a good day!");
        return true;
    }

    public void printFinancialsPrompt(){
        System.out.println("""
                \tSCHOOL FINANCIALS
                1 - pay student fees
                2 - pay teachers their salary
                3 - shows how much money the school has made
                4 - display all students and how much of their fees have been paid
                5 - display all teachers and how much of their salary has been paid
                """);
    }

    public void printHelpMenu(){
        System.out.print(
                """
                        Here are a few options to get started:

                        teacher or t    - registers a new teacher
                        student or s    - registers a new student
                        financials or f - pay student fees or access all school finances
                        find student    - display all student info (by student ID)
                        remove student  - removes a student
                        remove teacher  - removes a teacher
                        exit or goodbye - leaves the School Management System
                        help or h       - shows your options if you forget
                        """);
    }
}

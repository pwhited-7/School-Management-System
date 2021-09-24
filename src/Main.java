public class Main {

    public static void main(String[] args) {
        Display display = new Display();
        Controller control = new Controller();

        display.printIntro();
        boolean exit = false;

        while (!exit) {

            if (exit) {
                exit = true;
            } else {
                exit = control.usersDecision();
            }
        }
    }
}
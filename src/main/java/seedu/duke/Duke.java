package seedu.duke;

import java.util.Scanner;
import seedu.duke.FlightManager;


public class Duke {

    static boolean isFinished = true;
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        welcomeMessage();

        Scanner sc = new Scanner(System.in);
        while (isFinished) {
            String command = sc.nextLine();
            if(readInput(command) == false) {
                break;
            }
        }
        goodbyeMessage();

    }

    // can be placed in parser class
    public static boolean readInput(String command) {

        String[] splitCommand = command.split(" ");
        switch (splitCommand[0]) {
        case "bye":
            isFinished = false;
            return isFinished;
        case "flight_add":
            FlightManager.addFlight(command);
            break;
        case "flight_list":
            FlightManager.printFlights();
            break;
        default:
            // temporary sout, change to exception handling
            System.out.println("Unknown command, please re-enter.");
        }

        return isFinished;
    }

    // can be placed in UI class
    public static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Enter Command:");

    }

    public static void goodbyeMessage() {
        System.out.println("Thank you, come again! :)\n");
    }
}

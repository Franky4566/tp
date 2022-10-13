package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.flightcommand.AddFlightCommand;
import seedu.duke.command.flightcommand.ListFlightCommand;
import seedu.duke.command.passengercommand.AddPassengerCommand;
import seedu.duke.command.passengercommand.DeletePassengerCommand;
import seedu.duke.command.passengercommand.ListPassengerCommand;
import seedu.duke.exceptions.SkyControlException;
import seedu.duke.operationlist.FlightList;
import seedu.duke.operationlist.OperationList;
import seedu.duke.operationlist.PassengerList;
import seedu.duke.parsers.Parser;
import seedu.duke.ui.Ui;

public class SkyControl {
    private Ui ui;
    private OperationList passengers;
    private OperationList flights;
    private static boolean isPassenger = false;
    private static boolean isFlight = false;

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public SkyControl() {
        ui = new Ui();
        passengers = new PassengerList();
        flights = new FlightList();
    }

    private void executeEntity(String lineInput, Command command) {
        assert lineInput != null;
        checkEntity(lineInput);
        if (isPassenger) {
            command.execute(passengers, lineInput);
        } else if (isFlight) {
            command.execute(flights, lineInput);
        } else {
            command.execute(flights, lineInput);
        }
    }

    private void checkEntity(String lineInput) {
        isPassenger = Parser.isPassengerEntity(lineInput);
        isFlight = Parser.isFlightEntity(lineInput);
    }

    private void setUpAllLogger() {
        Parser.setupLogger();
        AddPassengerCommand.setupLogger();
        DeletePassengerCommand.setupLogger();
        ListPassengerCommand.setupLogger();
        AddFlightCommand.setupLogger();
        ListFlightCommand.setupLogger();
    }

    public void run() {
        ui.showWelcomeMessage();
        setUpAllLogger();
        boolean isExit = false;
        while (!isExit) {
            try {
                String lineInput = ui.readCommand();
                ui.showLineSeparator();
                Command command = Parser.parse(lineInput);
                executeEntity(lineInput, command);
                isExit = command.isExit();
            } catch (SkyControlException e) {
                ui.showError(e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new SkyControl().run();
    }
}

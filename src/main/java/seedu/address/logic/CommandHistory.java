package seedu.address.logic;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;

public class CommandHistory {
    public static final String MESSAGE_TOO_FAR_BACK = "Do not have more history commands - execute a new command!"
        + "\n Note that executing an erroneous command will also allow you to start using /up from the latest "
        + "commmand.";
    private static CommandHistory chInstance;
    private ArrayList<String> previousCommands;
    private int counter;

    private CommandHistory() {
        previousCommands = new ArrayList<>();
        counter = 0;
    }

    public static CommandHistory getInstance() {
        if (chInstance == null) {
            chInstance = new CommandHistory();
        }
        return chInstance;
    }

    /**
     * some function
     *
     * @param command
     */
    public void addCommandToHistory(String command) {
        previousCommands.add(command);
        resetCounter();
    }

    /**
     * some function.
     *
     * @return some function
     * @throws CommandException
     */
    public String retrievePreviousCommand() throws CommandException {
        return retrievePreviousCommand(counter++);
    }

    /**
     * some function
     *
     * @param previousIndex
     * @return some function
     * @throws CommandException
     */
    public String retrievePreviousCommand(int previousIndex) throws CommandException {
        if (previousIndex >= previousCommands.size()) {
            throw new CommandException(MESSAGE_TOO_FAR_BACK);
        }

        return previousCommands.get(previousCommands.size() - previousIndex - 1);
    }

    public void resetCounter() {
        counter = 0;
    }
}

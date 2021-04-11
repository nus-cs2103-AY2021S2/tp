package seedu.cakecollate.logic.commands;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.cakecollate.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;
    public static final String SHOWING_HELP_MESSAGE = "Switched to help window.";
    public static final String SHOWING_RETURN_MESSAGE = "Switched to order window.";
    private static boolean isAdded = false;

    private static final ObservableList<String> listOfCommands = FXCollections.observableArrayList();

    /**
     * Constructor which adds the command descriptions of all the commands present in CakeCollate to the listOfCommands.
     */
    public HelpCommand() {
        if (!isAdded) {
            addAllCommands();
            isAdded = true;
        }
    }

    /**
     * Adds all the MESSAGE_USAGE strings in all of the commands into one observable list.
     */
    public static void addAllCommands() {
        listOfCommands.addAll(HelpCommand.MESSAGE_USAGE, AddCommand.MESSAGE_USAGE, ListCommand.MESSAGE_USAGE,
                EditCommand.MESSAGE_USAGE, FindCommand.MESSAGE_USAGE, DeleteCommand.MESSAGE_USAGE,
                ClearCommand.MESSAGE_USAGE, ExitCommand.MESSAGE_USAGE, RemindCommand.MESSAGE_USAGE,
                DeliveryStatusCommand.getMessageUsage(DeliveryStatusCommand.UNDELIVERED_COMMAND_WORD),
                DeliveryStatusCommand.getMessageUsage(DeliveryStatusCommand.DELIVERED_COMMAND_WORD),
                DeliveryStatusCommand.getMessageUsage(DeliveryStatusCommand.CANCELLED_COMMAND_WORD),
                RequestCommand.MESSAGE_USAGE, AddOrderItemCommand.MESSAGE_USAGE, DeleteOrderItemCommand.MESSAGE_USAGE);
    }

    public static ObservableList<String> getListOfCommands() {
        return listOfCommands;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}

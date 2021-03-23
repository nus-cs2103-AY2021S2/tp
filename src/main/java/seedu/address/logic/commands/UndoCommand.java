package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.util.Stack;

/**
 * Undoes Undo the previous command that adds, deletes or edits an appointment or a property
 * or clears appointments or properties"
 */
public class UndoCommand extends Command {
    private static Stack<Command> commandHistory = new Stack<>();

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Undo the previous add, delete or edit command";

    public static final String MESSAGE_NO_COMMAND_TO_UNDO = "No add, delete or edit commands in the" +
            " command history";

    public static void logCommand(Command command) {
        if (command instanceof AddAppointmentCommand
                || command instanceof AddPropertyCommand
                || command instanceof ClearAppointmentCommand
                || command instanceof DeleteAppointmentCommand
                || command instanceof DeletePropertyCommand
                || command instanceof EditAppointmentCommand
                || command instanceof EditPropertyCommand) {
            commandHistory.push(command);
        }
        System.out.println(commandHistory);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (commandHistory.empty()) {
            throw new CommandException(MESSAGE_NO_COMMAND_TO_UNDO);
        }
        Command commandToUndo = commandHistory.pop();
        if (commandToUndo instanceof AddAppointmentCommand
                || commandToUndo instanceof DeleteAppointmentCommand
                || commandToUndo instanceof EditAppointmentCommand) {
            model.undoAppointmentBook();
        } else {
            model.undoPropertyBook();
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }


}

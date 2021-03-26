package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Stack;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.PocketEstateParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Undos the previous add, delete or edit command
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Undid %s command";

    public static final String MESSAGE_NO_COMMAND_TO_UNDO = "No add, delete or edit commands in the"
            + " command history to undo";

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command in command history: %s";

    private static Stack<String> commandHistory = new Stack<>();

    /**
     * Logs the command string {@code commandString} to the command history stack.
     */
    public static void logCommand(String commandString) throws CommandException {
        try {
            final String commandWord = PocketEstateParser.getCommandWord(commandString);
            switch (commandWord) {
            case AddPropertyCommand.COMMAND_WORD:
            case AddAppointmentCommand.COMMAND_WORD:
            case EditPropertyCommand.COMMAND_WORD:
            case EditAppointmentCommand.COMMAND_WORD:
            case DeleteAppointmentCommand.COMMAND_WORD:
            case DeletePropertyCommand.COMMAND_WORD:
                commandHistory.push(commandString);
                break;
            default:
                // do nothing
            }
        } catch (ParseException e) {
            throw new CommandException(String.format(MESSAGE_UNKNOWN_COMMAND, commandString));
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (commandHistory.empty()) {
            Logger emptyCommandHistoryLogger = LogsCenter.getLogger(UndoCommand.class);
            emptyCommandHistoryLogger.info(MESSAGE_NO_COMMAND_TO_UNDO);
            throw new CommandException(MESSAGE_NO_COMMAND_TO_UNDO);
        }

        String commandString = commandHistory.pop();

        try {
            final String commandWord = PocketEstateParser.getCommandWord(commandString);
            switch (commandWord) {
            case AddAppointmentCommand.COMMAND_WORD:
            case EditAppointmentCommand.COMMAND_WORD:
            case DeleteAppointmentCommand.COMMAND_WORD:
                model.undoAppointmentBook();
                break;
            case AddPropertyCommand.COMMAND_WORD:
            case EditPropertyCommand.COMMAND_WORD:
            case DeletePropertyCommand.COMMAND_WORD:
                model.undoPropertyBook();
                break;
            default:
                throw new CommandException(String.format(MESSAGE_UNKNOWN_COMMAND, commandString));
            }
        } catch (ParseException e) {
            throw new CommandException(String.format(MESSAGE_UNKNOWN_COMMAND, commandString));
        }
        return new CommandResult(String.format(String.format(MESSAGE_SUCCESS, commandString)));
    }

}

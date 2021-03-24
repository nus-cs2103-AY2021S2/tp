package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.exceptions.CommandException;
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

    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+(\\s(appointment|property|all))?)(?<arguments>.*)");

    /**
     * Logs the command string {@code commandString} to the command history stack.
     */
    public static void logCommand(String commandString) {
        commandString = commandString.trim();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(commandString);

        if (matcher.matches()) {
            final String commandWord = matcher.group("commandWord");
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
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (commandHistory.empty()) {
            throw new CommandException(MESSAGE_NO_COMMAND_TO_UNDO);
        }

        String commandString = commandHistory.pop();
        commandString = commandString.trim();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(commandString);
        if (!matcher.matches()) {
            throw new CommandException(String.format(MESSAGE_UNKNOWN_COMMAND, commandString));
        }

        final String commandWord = matcher.group("commandWord");
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
        return new CommandResult(String.format(String.format(MESSAGE_SUCCESS, commandString)));
    }

}

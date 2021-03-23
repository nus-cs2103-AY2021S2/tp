package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Undoes the previous add, delete or edit command
 */
public class UndoCommand extends Command {
    private static Stack<String> commandHistory = new Stack<>();

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Undo %s command";

    public static final String MESSAGE_NO_COMMAND_TO_UNDO = "No add, delete or edit commands in the" +
            " command history to undo";

    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+(\\s(appointment|property|all))?)(?<arguments>.*)");

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
        if (matcher.matches()) {
            final String commandWord = matcher.group("commandWord");
            switch (commandWord) {
            case AddAppointmentCommand.COMMAND_WORD:
            case EditAppointmentCommand.COMMAND_WORD:
            case DeleteAppointmentCommand.COMMAND_WORD:
                model.undoAppointmentBook();
                break;
            default:
                model.undoPropertyBook();
            }
        }
        return new CommandResult(String.format(String.format(MESSAGE_SUCCESS, commandString)));
    }


}

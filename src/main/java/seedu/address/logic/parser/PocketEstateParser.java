package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.logic.commands.ClearAllCommand;
import seedu.address.logic.commands.ClearAppointmentCommand;
import seedu.address.logic.commands.ClearPropertyCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeletePropertyCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditPropertyCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.commands.FindClientCommand;
import seedu.address.logic.commands.FindPropertyCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAllCommand;
import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.logic.commands.ListPropertyCommand;
import seedu.address.logic.commands.SortAppointmentCommand;
import seedu.address.logic.commands.SortPropertyCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.update.UpdateCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class PocketEstateParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+(\\s(appointment|property|all|client))?)(?<arguments>.*)");

    private static final String INVALID_COMMAND_ADD = "add";
    private static final String INVALID_COMMAND_EDIT = "edit";
    private static final String INVALID_COMMAND_SORT = "sort";
    private static final String INVALID_COMMAND_LIST = "list";
    private static final String INVALID_COMMAND_CLEAR = "clear";

    /**
     * Matches command string to command word and args.
     *
     * @param commandString full command string
     * @return the matcher based on the command string
     * @throws ParseException if the command string does not conform the expected format
     */
    private static Matcher getCommandStringMatcher(String commandString) throws ParseException {
        commandString = commandString.trim();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(commandString);

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        return matcher;
    }

    /**
     * Returns command word based on the command string.
     *
     * @param commandString full command string
     * @return the command word based on the command string
     * @throws ParseException if the command string does not conform the expected format
     */
    public static String getCommandWord(String commandString) throws ParseException {
        final Matcher matcher = getCommandStringMatcher(commandString);

        return matcher.group("commandWord");
    }

    /**
     * Returns arguments based on the command string.
     *
     * @param commandString full command string
     * @return the arguments based on the command string
     * @throws ParseException if the command string does not conform the expected format
     */
    public static String getArguments(String commandString) throws ParseException {
        final Matcher matcher = getCommandStringMatcher(commandString);

        return matcher.group("arguments");
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final String commandWord = getCommandWord(userInput);
        final String arguments = getArguments(userInput);

        // To satisfy the condition of "extraneous parameters will be ignored" in command format description
        if (commandWord.startsWith(HelpCommand.COMMAND_WORD)) {
            return new HelpCommand();
        } else if (commandWord.startsWith(ExitCommand.COMMAND_WORD)) {
            return new ExitCommand();
        }

        switch (commandWord) {
        case AddPropertyCommand.COMMAND_WORD:
            return new AddPropertyCommandParser().parse(arguments);

        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case EditPropertyCommand.COMMAND_WORD:
            return new EditPropertyCommandParser().parse(arguments);

        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parse(arguments);

        case FindAppointmentCommand.COMMAND_WORD:
            return new FindAppointmentCommandParser().parse(arguments);

        case FindPropertyCommand.COMMAND_WORD:
            return new FindPropertyCommandParser().parse(arguments);

        case FindClientCommand.COMMAND_WORD:
            return new FindClientCommandParser().parse(arguments);

        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case DeletePropertyCommand.COMMAND_WORD:
            return new DeletePropertyCommandParser().parse(arguments);

        case SortAppointmentCommand.COMMAND_WORD:
            return new SortAppointmentCommandParser().parse(arguments);

        case SortPropertyCommand.COMMAND_WORD:
            return new SortPropertyCommandParser().parse(arguments);

        case ClearAllCommand.COMMAND_WORD:
            return new ClearAllCommand();

        case ClearAppointmentCommand.COMMAND_WORD:
            return new ClearAppointmentCommand();

        case ClearPropertyCommand.COMMAND_WORD:
            return new ClearPropertyCommand();

        case UpdateCommand.COMMAND_WORD:
            return new UpdateCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case ListAllCommand.COMMAND_WORD:
            return new ListAllCommand();

        case ListPropertyCommand.COMMAND_WORD:
            return new ListPropertyCommand();

        case ListAppointmentCommand.COMMAND_WORD:
            return new ListAppointmentCommand();

        case INVALID_COMMAND_ADD:
        case INVALID_COMMAND_EDIT:
        case INVALID_COMMAND_SORT:
            throw new ParseException(Messages.missingPropertyAppointmentError(commandWord));

        case INVALID_COMMAND_CLEAR:
        case INVALID_COMMAND_LIST:
            throw new ParseException(Messages.missingAllPropertyAppointmentError(commandWord));

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.ClearAppointmentCommand;
import seedu.address.logic.commands.ClearPatientCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAppointmentCommand;
import seedu.address.logic.commands.ListPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class UserInputParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        // Appointments
        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case ClearAppointmentCommand.COMMAND_WORD:
            return new ClearAppointmentCommand();

        case ListAppointmentCommand.COMMAND_WORD:
            return new ListAppointmentCommand();

        // Patients
        case AddPatientCommand.COMMAND_WORD:
            return new AddPatientCommandParser().parse(arguments);

        case EditPatientCommand.COMMAND_WORD:
            return new EditPatientCommandParser().parse(arguments);

        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parse(arguments);

        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case DeletePatientCommand.COMMAND_WORD:
            return new DeletePatientCommandParser().parse(arguments);

        case ClearPatientCommand.COMMAND_WORD:
            return new ClearPatientCommand();

        case FindPatientCommand.COMMAND_WORD:
            return new FindPatientCommandParser().parse(arguments);

        case FindAppointmentCommand.COMMAND_WORD:
            return new FindAppointmentCommandParser().parse(arguments);

        case ListPatientCommand.COMMAND_WORD:
            return new ListPatientCommand();

        // Common Commands
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.student.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.student.logic.commands.AddAppointmentCommand;
import seedu.student.logic.commands.AddCommand;
import seedu.student.logic.commands.ClearCommand;
import seedu.student.logic.commands.Command;
import seedu.student.logic.commands.DeleteApptCommand;
import seedu.student.logic.commands.DeleteStudentCommand;
import seedu.student.logic.commands.EditAppointmentCommand;
import seedu.student.logic.commands.EditCommand;
import seedu.student.logic.commands.ExitCommand;
import seedu.student.logic.commands.FilterCommand;
import seedu.student.logic.commands.FindCommand;
import seedu.student.logic.commands.HelpCommand;
import seedu.student.logic.commands.ListCommand;
import seedu.student.logic.commands.statscommands.StatsApptCommand;
import seedu.student.logic.commands.statscommands.StatsCommand;
import seedu.student.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class StudentBookParser {

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

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteStudentCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteApptCommand.COMMAND_WORD:
            return new DeleteApptCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case StatsCommand.COMMAND_WORD:
            return new StatsCommandParser().parse(arguments);

        case StatsApptCommand.COMMAND_WORD:
            return new StatsApptCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

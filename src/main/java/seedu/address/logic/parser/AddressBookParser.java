package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.statscommands.StatsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        assert userInput != null : "userInput cannot be null!";
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            logger.info("----------------[ADD COMMAND][" + userInput + "]");
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            logger.info("----------------[EDIT COMMAND][" + userInput + "]");
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            logger.info("----------------[DELETE COMMAND][" + userInput + "]");
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            logger.info("----------------[CLEAR COMMAND][" + userInput + "]");
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            logger.info("----------------[FIND COMMAND][" + userInput + "]");
            return new FindCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            logger.info("----------------[FILTER COMMAND][" + userInput + "]");
            return new FilterCommandParser().parse(arguments);
        case StatsCommand.COMMAND_WORD:
            logger.info("----------------[STATS COMMAND][" + userInput + "]");
            return new StatsCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            logger.info("----------------[LIST COMMAND][" + userInput + "]");
            return new ListCommand();

        case AddAppointmentCommand.COMMAND_WORD:
            logger.info("----------------[ADDAPPOINTMENT COMMAND][" + userInput + "]");
            return new AddAppointmentCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            logger.info("----------------[EXIT COMMAND][" + userInput + "]");
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            logger.info("----------------[HELP COMMAND][" + userInput + "]");
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

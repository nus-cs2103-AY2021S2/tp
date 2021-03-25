package seedu.address.logic.parser.components;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.customer.CustomerAddCommand;
import seedu.address.logic.commands.customer.CustomerClearCommand;
import seedu.address.logic.commands.customer.CustomerDeleteCommand;
import seedu.address.logic.commands.customer.CustomerEditCommand;
import seedu.address.logic.commands.customer.CustomerFindCommand;
import seedu.address.logic.commands.customer.CustomerListCommand;
import seedu.address.logic.parser.commands.customer.CustomerAddCommandParser;
import seedu.address.logic.parser.commands.customer.CustomerDeleteCommandParser;
import seedu.address.logic.parser.commands.customer.CustomerEditCommandParser;
import seedu.address.logic.parser.commands.customer.CustomerFindCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses user input without component.
 */
public class CustomerParser implements ComponentParser {

    public static final String COMPONENT_WORD = "customer";
    private final Logger logger = LogsCenter.getLogger(CustomerParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param args user input string without components
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String args) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        logger.info("----------------[COMMAND = " + commandWord + "][ARGUMENTS = " + arguments + "]");

        switch (commandWord) {

        case CustomerAddCommand.COMMAND_WORD:
            return new CustomerAddCommandParser().parse(arguments);

        case CustomerEditCommand.COMMAND_WORD:
            return new CustomerEditCommandParser().parse(arguments);

        case CustomerDeleteCommand.COMMAND_WORD:
            return new CustomerDeleteCommandParser().parse(arguments);

        case CustomerClearCommand.COMMAND_WORD:
            return new CustomerClearCommand();

        case CustomerFindCommand.COMMAND_WORD:
            return new CustomerFindCommandParser().parse(arguments);

        case CustomerListCommand.COMMAND_WORD:
            return new CustomerListCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

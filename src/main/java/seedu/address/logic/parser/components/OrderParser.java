package seedu.address.logic.parser.components;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.order.OrderAddCommand;
import seedu.address.logic.commands.order.OrderCompleteCommand;
import seedu.address.logic.commands.order.OrderDeleteCommand;
import seedu.address.logic.commands.order.OrderEditCommand;
import seedu.address.logic.commands.order.OrderFindCommand;
import seedu.address.logic.commands.order.OrderHistoryCommand;
import seedu.address.logic.commands.order.OrderListCommand;
import seedu.address.logic.parser.commands.order.OrderAddCommandParser;
import seedu.address.logic.parser.commands.order.OrderCompleteCommandParser;
import seedu.address.logic.parser.commands.order.OrderDeleteCommandParser;
import seedu.address.logic.parser.commands.order.OrderEditCommandParser;
import seedu.address.logic.parser.commands.order.OrderFindCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input without component.
 */
public class OrderParser implements ComponentParser {

    public static final String COMPONENT_WORD = "order";
    private final Logger logger = LogsCenter.getLogger(OrderParser.class);

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

        case OrderAddCommand.COMMAND_WORD:
            return new OrderAddCommandParser().parse(arguments);

        case OrderDeleteCommand.COMMAND_WORD:
            return new OrderDeleteCommandParser().parse(arguments);

        case OrderEditCommand.COMMAND_WORD:
            return new OrderEditCommandParser().parse(arguments);

        case OrderFindCommand.COMMAND_WORD:
            return new OrderFindCommandParser().parse(arguments);

        case OrderListCommand.COMMAND_WORD:
            return new OrderListCommand();

        case OrderCompleteCommand.COMMAND_WORD:
            return new OrderCompleteCommandParser().parse(arguments);

        case OrderHistoryCommand.COMMAND_WORD:
            return new OrderHistoryCommand();

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }
}

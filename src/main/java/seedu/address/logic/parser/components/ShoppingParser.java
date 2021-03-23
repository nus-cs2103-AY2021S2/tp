package seedu.address.logic.parser.components;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.shopping.ShoppingAddCommand;
import seedu.address.logic.commands.shopping.ShoppingDeleteCommand;
import seedu.address.logic.commands.shopping.ShoppingListCommand;
import seedu.address.logic.parser.commands.shopping.ShoppingDeleteCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input without component.
 */
public class ShoppingParser implements ComponentParser {

    public static final String COMPONENT_WORD = "shop";

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
        final String messageNotImplemented = COMPONENT_WORD + " " + commandWord + " is still work in progress!";

        switch (commandWord) {

        case ShoppingAddCommand.COMMAND_WORD:
            throw new ParseException(messageNotImplemented);
            // return new ShoppingAddCommandParser().parse(arguments);

        case ShoppingDeleteCommand.COMMAND_WORD:
            return new ShoppingDeleteCommandParser().parse(arguments);

        case ShoppingListCommand.COMMAND_WORD:
            return new ShoppingListCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

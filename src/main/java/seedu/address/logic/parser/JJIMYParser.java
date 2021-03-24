package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.components.CustomerParser;
import seedu.address.logic.parser.components.InventoryParser;
import seedu.address.logic.parser.components.MenuParser;
import seedu.address.logic.parser.components.OrderParser;
import seedu.address.logic.parser.components.ShoppingParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class JJIMYParser {
    private final Logger logger = LogsCenter.getLogger(JJIMYParser.class);

    /**
     * Used for initial separation of component word and args.
     */
    private static final Pattern BASIC_COMPO_FORMAT = Pattern.compile("(?<compoWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCompo(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMPO_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String compoWord = matcher.group("compoWord");
        final String arguments = matcher.group("arguments");
        logger.info("----------------[COMPONENT = " + compoWord + "][ARGUMENTS = " + arguments + "]");

        switch (compoWord) {

        case CustomerParser.COMPONENT_WORD:
            return new CustomerParser().parseCommand(arguments);

        case OrderParser.COMPONENT_WORD:
            return new OrderParser().parseCommand(arguments);

        case InventoryParser.COMPONENT_WORD:
            return new InventoryParser().parseCommand(arguments);

        case ShoppingParser.COMPONENT_WORD:
            return new ShoppingParser().parseCommand(arguments);

        case MenuParser.COMPONENT_WORD:
            return new MenuParser().parseCommand(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

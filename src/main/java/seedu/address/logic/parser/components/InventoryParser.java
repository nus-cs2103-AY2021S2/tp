package seedu.address.logic.parser.components;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.inventory.InventoryAddCommand;
import seedu.address.logic.commands.inventory.InventoryDeleteCommand;
import seedu.address.logic.commands.inventory.InventoryListCommand;
import seedu.address.logic.parser.commands.inventory.InventoryAddCommandParser;
import seedu.address.logic.parser.commands.inventory.InventoryDeleteCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input without component.
 */
public class InventoryParser implements ComponentParser {

    public static final String COMPONENT_WORD = "inventory";
    private final Logger logger = LogsCenter.getLogger(InventoryParser.class);

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

        case InventoryAddCommand.COMMAND_WORD:
            return new InventoryAddCommandParser().parse(arguments);

        case InventoryDeleteCommand.COMMAND_WORD:
            return new InventoryDeleteCommandParser().parse(arguments);

        case InventoryListCommand.COMMAND_WORD:
            return new InventoryListCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

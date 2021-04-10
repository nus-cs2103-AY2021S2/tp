package seedu.address.logic.parser.commands.inventory;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.inventory.InventoryDecreaseCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.commands.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InventoryDecreaseCommand object
 */
public class InventoryDecreaseCommandParser implements Parser<InventoryDecreaseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InventoryDecreaseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_QUANTITY);

        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            int quantity = ParserUtil.parseNonNegativeInt(argMultimap.getValue(PREFIX_QUANTITY).get());
            return new InventoryDecreaseCommand(index, quantity);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, InventoryDecreaseCommand.MESSAGE_USAGE), pe);
        }
    }
}

package seedu.address.logic.parser.commands.inventory;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.address.logic.commands.inventory.InventoryAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.commands.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class InventoryAddCommandParser implements Parser<InventoryAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InventoryAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_QUANTITY);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_QUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InventoryAddCommand.MESSAGE_USAGE));
        }

        String name = ParserUtil.parseIngredientName(argMultimap.getValue(PREFIX_NAME).get());
        int quantity = ParserUtil.parseNonNegativeInt(argMultimap.getValue(PREFIX_QUANTITY).get());

        Ingredient ingredient = new Ingredient(name, quantity);

        return new InventoryAddCommand(ingredient);
    }
}

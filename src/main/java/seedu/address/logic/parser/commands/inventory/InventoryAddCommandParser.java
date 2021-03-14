package seedu.address.logic.parser.commands.inventory;

import java.util.stream.Stream;

import seedu.address.logic.commands.inventory.InventoryAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.commands.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.IngredientStub;

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
        IngredientStub ingredient = new IngredientStub();

        return new InventoryAddCommand(ingredient);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

package seedu.address.logic.parser.commands.menu;

import java.util.stream.Stream;

import seedu.address.logic.commands.menu.MenuAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.commands.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.dish.DishStub;

/**
 * Parses input arguments and creates a new MenuAddCommand object
 */
public class MenuAddCommandParser implements Parser<MenuAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MenuAddCommand parse(String args) throws ParseException {
        DishStub dish = new DishStub();

        return new MenuAddCommand(dish);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

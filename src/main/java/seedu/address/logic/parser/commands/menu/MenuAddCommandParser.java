package seedu.address.logic.parser.commands.menu;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.menu.MenuAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.commands.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE, PREFIX_INGREDIENT, PREFIX_QUANTITY);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PRICE, PREFIX_INGREDIENT, PREFIX_QUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MenuAddCommand.MESSAGE_USAGE));
        }

        String name = ParserUtil.parseDishName(argMultimap.getValue(PREFIX_NAME).get());
        double price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());

        List<String> ingredientNumbers = argMultimap.getAllValues(PREFIX_INGREDIENT);
        List<String> ingredientQuantities = argMultimap.getAllValues(PREFIX_QUANTITY);
        ParserUtil.validateListLengths(ingredientNumbers, ingredientQuantities);

        List<Pair<Index, Integer>> ingredientIdsQuantityList = new ArrayList<>();
        for (int i = 0; i < ingredientNumbers.size(); i++) {
            Pair<Index, Integer> dishComponent =
                    new Pair<>(ParserUtil.parseIndex(ingredientNumbers.get(i)),
                            ParserUtil.parseNonNegativeInt(ingredientQuantities.get(i)));
            ingredientIdsQuantityList.add(dishComponent);
        }

        return new MenuAddCommand(name, price, ingredientIdsQuantityList);
    }
}

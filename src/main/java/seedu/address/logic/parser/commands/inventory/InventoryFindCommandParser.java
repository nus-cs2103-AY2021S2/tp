package seedu.address.logic.parser.commands.inventory;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.inventory.InventoryFindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.commands.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientNameContainsWordsPredicate;
import seedu.address.model.ingredient.IngredientQuantityLessThanEqualsPredicate;

public class InventoryFindCommandParser implements Parser<InventoryFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public InventoryFindCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_QUANTITY);

        boolean namePresent = ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME);
        boolean quantityPresent = ParserUtil.arePrefixesPresent(argMultimap, PREFIX_QUANTITY);

        if (!namePresent && !quantityPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InventoryFindCommand.MESSAGE_USAGE));
        }

        List<Predicate<Ingredient>> predicates = getPredicates(argMultimap);
        return new InventoryFindCommand(predicates);
    }

    private List<Predicate<Ingredient>> getPredicates(ArgumentMultimap argMultimap) throws ParseException {
        List<Predicate<Ingredient>> predicates = new ArrayList<>();

        Optional<String> nameArgs = argMultimap.getValue(PREFIX_NAME);
        Optional<String> quantityArg = argMultimap.getValue(PREFIX_QUANTITY);

        if (nameArgs.isPresent()) {
            List<String> keywords;
            try {
                keywords = ParserUtil.parseKeywords(nameArgs.get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        InventoryFindCommand.MESSAGE_USAGE), pe);
            }
            predicates.add(new IngredientNameContainsWordsPredicate(keywords));
        }

        if (quantityArg.isPresent()) {
            int quantity;
            try {
                quantity = ParserUtil.parseNonNegativeInt(quantityArg.get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        InventoryFindCommand.MESSAGE_USAGE), pe);
            }
            predicates.add(new IngredientQuantityLessThanEqualsPredicate(quantity));
        }

        return predicates;
    }
}

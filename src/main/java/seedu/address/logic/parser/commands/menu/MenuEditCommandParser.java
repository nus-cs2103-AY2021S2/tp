package seedu.address.logic.parser.commands.menu;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.menu.MenuEditCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class MenuEditCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MenuEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE, PREFIX_INGREDIENT, PREFIX_QUANTITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MenuEditCommand.MESSAGE_USAGE), pe);
        }

        MenuEditCommand.EditDishDescriptor editDishDescriptor = new MenuEditCommand.EditDishDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editDishDescriptor.setName(ParserUtil.parseDishName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editDishDescriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_INGREDIENT).isPresent()
                && argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            List<String> editedIngredientNumbers = argMultimap.getAllValues(PREFIX_INGREDIENT);
            List<String> editedIngredientQuantities = argMultimap.getAllValues(PREFIX_QUANTITY);
            ParserUtil.validateListLengths(editedIngredientNumbers, editedIngredientQuantities);

            List<Pair<Index, Integer>> editedIngredientIdsQuantityList = new ArrayList<>();
            for (int i = 0; i < editedIngredientNumbers.size(); i++) {
                Pair<Index, Integer> editedDishComponent =
                        new Pair<>(ParserUtil.parseIndex(editedIngredientNumbers.get(i)),
                                ParserUtil.parseNonNegativeInt(editedIngredientQuantities.get(i)));
                editedIngredientIdsQuantityList.add(editedDishComponent);
            }

            editDishDescriptor.setIngredientIdsQuantityList(editedIngredientIdsQuantityList);
        }

        if (!editDishDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MenuEditCommand.MESSAGE_NOT_EDITED);
        }

        return new MenuEditCommand(index, editDishDescriptor);
    }
}

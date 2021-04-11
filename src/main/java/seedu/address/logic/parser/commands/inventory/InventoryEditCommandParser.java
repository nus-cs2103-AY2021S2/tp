package seedu.address.logic.parser.commands.inventory;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.inventory.InventoryEditCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class InventoryEditCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public InventoryEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_QUANTITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InventoryEditCommand.MESSAGE_USAGE), pe);
        }

        InventoryEditCommand.EditIngredientDescriptor editIngredientDescriptor =
                new InventoryEditCommand.EditIngredientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editIngredientDescriptor.setName(
                    ParserUtil.parseIngredientName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            editIngredientDescriptor.setQuantity(
                    ParserUtil.parseNonNegativeInt(argMultimap.getValue(PREFIX_QUANTITY).get()));
        }

        if (!editIngredientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(InventoryEditCommand.MESSAGE_NOT_EDITED);
        }

        return new InventoryEditCommand(index, editIngredientDescriptor);
    }
}

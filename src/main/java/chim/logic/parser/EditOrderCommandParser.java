package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static chim.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static chim.logic.parser.CliSyntax.PREFIX_PHONE;
import static chim.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static java.util.Objects.requireNonNull;

import chim.commons.core.index.Index;
import chim.logic.commands.EditOrderCommand;
import chim.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditOrderCommand object
 */
public class EditOrderCommandParser implements Parser<EditOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CHEESE_TYPE,
                        PREFIX_ORDER_DATE, PREFIX_QUANTITY, PREFIX_PHONE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOrderCommand.MESSAGE_USAGE), pe);
        }

        EditOrderCommand.EditOrderDescriptor editOrderDescriptor = new EditOrderCommand.EditOrderDescriptor();
        if (argMultimap.getValue(PREFIX_CHEESE_TYPE).isPresent()) {
            editOrderDescriptor.setCheeseType(
                    ParserUtil.parseCheeseType(argMultimap.getValue(PREFIX_CHEESE_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_ORDER_DATE).isPresent()) {
            editOrderDescriptor.setOrderDate(
                    ParserUtil.parseOrderDate(argMultimap.getValue(PREFIX_ORDER_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            editOrderDescriptor.setQuantity(
                    ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editOrderDescriptor.setPhone(
                    ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (!editOrderDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditOrderCommand.MESSAGE_NOT_EDITED);
        }

        return new EditOrderCommand(index, editOrderDescriptor);
    }

}

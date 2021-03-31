package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.cakecollate.logic.commands.AddOrderItemCommand;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.orderitem.OrderItem;

/**
 * Parses input arguments and creates a new AddOrderItemCommand object
 */
public class AddOrderItemCommandParser implements Parser<AddOrderItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderItemCommand parse(String args) throws ParseException {
        try {
            OrderItem orderItem = ParserUtil.parseOrderItem(args);
            return new AddOrderItemCommand(orderItem);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderItemCommand.MESSAGE_USAGE), pe);
        }
    }

}

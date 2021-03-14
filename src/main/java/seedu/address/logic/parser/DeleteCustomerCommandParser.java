package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.customer.Phone;

/**
 * Parses input arguments and creates a new DeleteCustomerCommand object
 */
public class DeleteCustomerCommandParser implements Parser<DeleteCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCustomerCommand
     * and returns a DeleteCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCustomerCommand parse(String args) throws ParseException {
        try {
            Phone phone = ParserUtil.parsePhone(args);
            return new DeleteCustomerCommand(phone);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCustomerCommand.MESSAGE_USAGE), pe);
        }
    }

}

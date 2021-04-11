package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.commands.DeleteOrderItemCommand;
import seedu.cakecollate.logic.parser.exceptions.IndexOutOfBoundsException;
import seedu.cakecollate.logic.parser.exceptions.NegativeIndexException;
import seedu.cakecollate.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteOrderItemCommandParser implements Parser<DeleteOrderItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteOrderItemCommand parse(String args) throws ParseException {
        try {
            IndexList indexList = ParserUtil.parseIndexList(args);
            return new DeleteOrderItemCommand(indexList);
        } catch (IndexOutOfBoundsException pe) {
            throw new IndexOutOfBoundsException(Messages.MESSAGE_INVALID_ORDER_ITEM_INDEX);
        } catch (NegativeIndexException pe) {
            throw new NegativeIndexException(pe.getMessage());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrderItemCommand.MESSAGE_USAGE), pe);
        }
    }
}

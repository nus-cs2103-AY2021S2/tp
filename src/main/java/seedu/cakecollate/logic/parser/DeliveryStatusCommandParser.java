package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.commands.DeliveryStatusCommand;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.order.DeliveryStatus;

public class DeliveryStatusCommandParser implements Parser<DeliveryStatusCommand> {

    public final DeliveryStatus status;

    DeliveryStatusCommandParser(DeliveryStatus status) {
        this.status = status;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeliveryStatusCommand
     * and returns a DeliveryStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeliveryStatusCommand parse(String args) throws ParseException {
        try {
            assert status != null;
            IndexList indexList = ParserUtil.parseIndexList(args);
            return new DeliveryStatusCommand(indexList, status);
        } catch (ParseException exception) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeliveryStatusCommand.getMessageUsage(status.toString())), exception);
        }
    }
}

package seedu.address.logic.parser.medical;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.medical.ViewMedicalRecordCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewMedicalRecordCommand object
 */
public class ViewMedicalRecordCommandParser implements Parser<ViewMedicalRecordCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewMedicalRecordCommand
     * and returns a ViewMedicalRecordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewMedicalRecordCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewMedicalRecordCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewMedicalRecordCommand.MESSAGE_USAGE), pe);
        }
    }
}

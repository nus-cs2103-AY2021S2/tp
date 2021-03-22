package seedu.iscam.logic.parser;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.DoneMeetingCommand;
import seedu.iscam.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DoneMeetingCommandParser implements Parser<DoneMeetingCommand> {

    public DoneMeetingCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DoneMeetingCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneMeetingCommand.MESSAGE_USAGE));
        }

    }
}

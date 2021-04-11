package seedu.flashback.logic.parser;

import static seedu.flashback.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.flashback.commons.core.index.Index;
import seedu.flashback.logic.commands.ViewCommand;
import seedu.flashback.logic.parser.exceptions.ParseException;

public class ViewCommandParser implements Parser<ViewCommand> {
    @Override
    public ViewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        }
    }
}

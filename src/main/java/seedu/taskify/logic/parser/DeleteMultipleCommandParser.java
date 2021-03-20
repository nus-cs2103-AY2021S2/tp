package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.taskify.commons.core.index.Index;
import seedu.taskify.logic.commands.DeleteMultipleCommand;
import seedu.taskify.logic.parser.exceptions.ParseException;

public class DeleteMultipleCommandParser implements Parser<DeleteMultipleCommand> {

    @Override
    public DeleteMultipleCommand parse(String args) throws ParseException {
        try {
            List<Index> indexes = ParserUtil.parseMultipleIndex(args);
            return new DeleteMultipleCommand(indexes);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteMultipleCommand.MESSAGE_USAGE), pe);
        }
    }
}

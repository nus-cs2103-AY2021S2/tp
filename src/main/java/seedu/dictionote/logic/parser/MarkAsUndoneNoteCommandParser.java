package seedu.dictionote.logic.parser;

import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.MarkAsDoneNoteCommand;
import seedu.dictionote.logic.commands.MarkAsUndoneNoteCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class MarkAsUndoneNoteCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkAsUndoneNoteCommand
     * and returns a MarkAsUndoneNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkAsUndoneNoteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkAsUndoneNoteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, 
                            MarkAsUndoneNoteCommand.MESSAGE_USAGE), pe);
        }
    }
}

package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.MarkAsDoneNoteCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;


public class MarkAsDoneNoteCommandParser implements Parser<MarkAsDoneNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAsDoneNoteCommand
     * and returns a MarkAsDoneNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkAsDoneNoteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkAsDoneNoteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAsDoneNoteCommand.MESSAGE_USAGE), pe);
        }
    }
}

package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.CopyContentToNoteCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowDictionaryContentCommand object.
 */
public class CopyContentToNoteCommandParser implements Parser<CopyContentToNoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ShowDictionaryContentCommand
     * and returns a ShowDictionaryContentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyContentToNoteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CopyContentToNoteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyContentToNoteCommand.MESSAGE_USAGE), pe);
        }
    }
}

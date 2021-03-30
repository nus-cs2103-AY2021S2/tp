package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.MergeNoteCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;

public class MergeNoteCommandParser implements Parser<MergeNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MergeNoteCommand
     * and returns a MergeNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MergeNoteCommand parse(String args) throws ParseException {
        try {
            String[] splittedArgs = args.split(" ");
            Index firstIndex = ParserUtil.parseIndex(splittedArgs[1]);
            Index secondIndex = ParserUtil.parseIndex(splittedArgs[2]);
            return new MergeNoteCommand(firstIndex, secondIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MergeNoteCommand.MESSAGE_USAGE), pe);
        }
    }
}

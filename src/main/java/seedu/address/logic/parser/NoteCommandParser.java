package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_VIEW;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Note;

/**
 * Parses input arguments and creates a new NoteCommand object
 */
public class NoteCommandParser implements Parser<NoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the NoteCommand
     * and returns a NoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTE_RECORD, PREFIX_NOTE_VIEW, PREFIX_CLEAR);

        Prefix singlePrefix;
        try {
            singlePrefix = getSinglePrefix(argMultimap, PREFIX_NOTE_RECORD, PREFIX_NOTE_VIEW, PREFIX_CLEAR);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE), pe);
        }
        assert singlePrefix != null : "Unexpected null value";

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Note note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE_RECORD).orElse("placeholder"));
        return new NoteCommand(index, singlePrefix, note);
    }

    /**
     * Returns the singular prefix contained in the given {@code ArgumentMultimap}.
     * @throws ParseException if more or less than 1 prefix is provided by user.
     */
    private static Prefix getSinglePrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) throws ParseException {
        Prefix singlePrefix = null;
        for (Prefix prefix : prefixes) {
            if (argumentMultimap.getValue(prefix).isPresent()) {
                if (singlePrefix != null) {
                    throw new ParseException("More than 1 prefix provided.");
                }
                singlePrefix = prefix;
            }
        }
        if (singlePrefix == null) {
            throw new ParseException("No prefix provided. ");
        }
        return singlePrefix;
    }
}

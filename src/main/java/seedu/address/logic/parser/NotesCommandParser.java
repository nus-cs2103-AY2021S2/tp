package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.NotesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Notes;

public class NotesCommandParser implements Parser<NotesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code NotesCommand} and returns a {@code
     * NotesCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public NotesCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NOTES);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE), ive);
        }

        String notes = argMultimap.getValue(PREFIX_NOTES).orElse("");

        return new NotesCommand(index, new Notes(notes));
    }
}

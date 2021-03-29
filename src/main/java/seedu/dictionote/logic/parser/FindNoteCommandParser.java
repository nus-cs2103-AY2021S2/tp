package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.stream.Stream;

import seedu.dictionote.logic.commands.FindNoteCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.note.NoteContainsKeywordsPredicate;
import seedu.dictionote.model.note.TagNoteContainKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindNoteCommandParser implements Parser<FindNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindContactCommand
     * and returns a FindNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindNoteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CONTENT, PREFIX_TAG);

        if (!isAnyOfPrefixesPresent(argMultimap, PREFIX_CONTENT, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindNoteCommand.MESSAGE_USAGE));
        }

        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_CONTENT);
        List<String> tagsKeywords = argMultimap.getAllValues(PREFIX_TAG);

        return new FindNoteCommand(
                new NoteContainsKeywordsPredicate(nameKeywords),
                new TagNoteContainKeywordsPredicate(tagsKeywords)
        );
    }

    /**
     * Returns true if at least one of the prefixes contains some {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAnyOfPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

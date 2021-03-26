package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.stream.Stream;

import seedu.dictionote.logic.commands.FindContactCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.contact.EmailContainsKeywordsPredicate;
import seedu.dictionote.model.contact.NameContainsKeywordsPredicate;
import seedu.dictionote.model.contact.TagsContainKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindContactCommandParser implements Parser<FindContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindContactCommand
     * and returns a FindContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindContactCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TAG);

        if (!isAnyOfPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
        }

        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
        List<String> emailKeywords = argMultimap.getAllValues(PREFIX_EMAIL);
        List<String> tagsKeywords = argMultimap.getAllValues(PREFIX_TAG);

        return new FindContactCommand(
                new NameContainsKeywordsPredicate(nameKeywords),
                new EmailContainsKeywordsPredicate(emailKeywords),
                new TagsContainKeywordsPredicate(tagsKeywords)
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

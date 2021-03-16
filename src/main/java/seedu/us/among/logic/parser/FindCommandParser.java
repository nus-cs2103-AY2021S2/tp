package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.us.among.logic.commands.FindCommand;
import seedu.us.among.logic.parser.exceptions.ParseException;
import seedu.us.among.model.endpoint.AddressContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.DataContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.EndPointContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.HeadersContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.MethodContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.TagsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args,
                PREFIX_METHOD,
                PREFIX_ADDRESS,
                PREFIX_DATA,
                PREFIX_HEADER,
                PREFIX_TAG);

        String[] nameKeywords;

        //to-do jun xiong update this to use swtich case? or find a better way to do this
        if (argMultimap.arePrefixesPresent(PREFIX_METHOD)) {
            String input = argMultimap.getValue(PREFIX_METHOD).get();
            nameKeywords = getNameKeywords(input);
            return new FindCommand(new MethodContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.arePrefixesPresent(PREFIX_ADDRESS)) {
            String input = argMultimap.getValue(PREFIX_ADDRESS).get();
            nameKeywords = getNameKeywords(input);
            return new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.arePrefixesPresent(PREFIX_DATA)) {
            String input = argMultimap.getValue(PREFIX_DATA).get();
            nameKeywords = getNameKeywords(input);
            return new FindCommand(new DataContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.arePrefixesPresent(PREFIX_HEADER)) {
            String input = argMultimap.getValue(PREFIX_HEADER).get();
            nameKeywords = getNameKeywords(input);
            return new FindCommand(new HeadersContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.arePrefixesPresent(PREFIX_TAG)) {
            String input = argMultimap.getValue(PREFIX_TAG).get();
            nameKeywords = getNameKeywords(input);
            return new FindCommand(new TagsContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        nameKeywords = getNameKeywords(args);
        return new FindCommand(new EndPointContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

    public String[] getNameKeywords(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return trimmedArgs.split("\\s+");
    }

}

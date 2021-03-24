package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import seedu.us.among.logic.commands.FindCommand;
import seedu.us.among.logic.parser.exceptions.ParseException;
import seedu.us.among.model.endpoint.AddressContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.DataContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.EndPointContainsKeywordsPredicate;
import seedu.us.among.model.endpoint.Endpoint;
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
        Boolean hasPrefix = false;

        ArrayList<Predicate<Endpoint>> predicateList = new ArrayList<>();

        if (argMultimap.arePrefixesPresent(PREFIX_METHOD)) {
            hasPrefix = true;
            String input = argMultimap.getValue(PREFIX_METHOD).get();
            nameKeywords = getNameKeywords(input);
            predicateList.add(new MethodContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.arePrefixesPresent(PREFIX_ADDRESS)) {
            hasPrefix = true;
            String input = argMultimap.getValue(PREFIX_ADDRESS).get();
            nameKeywords = getNameKeywords(input);
            predicateList.add(new AddressContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.arePrefixesPresent(PREFIX_DATA)) {
            hasPrefix = true;
            String input = argMultimap.getValue(PREFIX_DATA).get();
            nameKeywords = getNameKeywords(input);
            predicateList.add(new DataContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.arePrefixesPresent(PREFIX_HEADER)) {
            hasPrefix = true;
            String input = argMultimap.getValue(PREFIX_HEADER).get();
            nameKeywords = getNameKeywords(input);
            predicateList.add(new HeadersContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.arePrefixesPresent(PREFIX_TAG)) {
            hasPrefix = true;
            String input = argMultimap.getValue(PREFIX_TAG).get();
            nameKeywords = getNameKeywords(input);
            predicateList.add(new TagsContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (!hasPrefix) {
            nameKeywords = getNameKeywords(args);
            return new FindCommand(new EndPointContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else {
            return new FindCommand(predicateList.stream().reduce(x -> true, Predicate::and));
        }

    }

    public String[] getNameKeywords(String args) throws ParseException {
        assert args != null;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return trimmedArgs.split("\\s+");
    }

}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.passenger.AddressContainsKeywordsPredicate;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesValid(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<Prefix> presentPrefixes =
                presentPrefixes(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_TAG);
        assert(presentPrefixes.size() == 1);

        Prefix specifiedPrefix = presentPrefixes.get(0);
        String keywords = parseValue(argMultimap, specifiedPrefix).trim();

        return new FindCommand(parsePredicate(specifiedPrefix,
                Collections.singletonList(keywords).toArray(String[]::new)));
    }

    /**
     * Returns true if only one of the prefixes are provided
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesValid(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return presentPrefixes(argumentMultimap, prefixes).size() == 1
                && doesPrefixHaveOneValue(argumentMultimap, prefixes);
    }

    /**
     * Returns the prefixes that have values
     * {@code ArgumentMultimap}.
     */
    private static boolean doesPrefixHaveOneValue(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getAllValues(prefix).size() <= 1);
    }

    /**
     * Returns the prefixes that have values
     * {@code ArgumentMultimap}.
     */
    private static List<Prefix> presentPrefixes(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix ->
                argumentMultimap.getValue(prefix).isPresent()).collect(Collectors.toList());
    }

    /**
     * Returns the value for the specified prefix
     * {@code ArgumentMultimap}.
     */
    private static String parseValue(ArgumentMultimap argumentMultimap, Prefix prefix) throws ParseException {
        if (PREFIX_NAME.equals(prefix) && argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            return ParserUtil.parseName(argumentMultimap.getValue(PREFIX_NAME).get()).toString();

        } else if (PREFIX_ADDRESS.equals(prefix) && argumentMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            return ParserUtil.parseAddress(argumentMultimap.getValue(PREFIX_ADDRESS).get()).toString();

        } else if (PREFIX_PHONE.equals(prefix) && argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            return ParserUtil.parsePhone(argumentMultimap.getValue(PREFIX_PHONE).get()).toString();

        } else if (PREFIX_TAG.equals(prefix) && argumentMultimap.getValue(PREFIX_TAG).isPresent()) {
            return ParserUtil.parseTag(argumentMultimap.getValue(PREFIX_TAG).get()).toString();

        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns the predicate specified by prefix
     * {@code ArgumentMultimap}.
     * @return the predicate specified.
     */
    private static Predicate<Passenger> parsePredicate(Prefix prefix, String[] arguments) throws ParseException {
        if (PREFIX_NAME.equals(prefix)) {
            return new NameContainsKeywordsPredicate(Arrays.asList(arguments));

        } else if (PREFIX_ADDRESS.equals(prefix)) {
            return new AddressContainsKeywordsPredicate(Arrays.asList(arguments));

        } else if (PREFIX_PHONE.equals(prefix)) {
            return new PhoneContainsKeywordsPredicate(Arrays.asList(arguments));

        } else if (PREFIX_TAG.equals(prefix)) {
            return new TagContainsKeywordsPredicate(Arrays.asList(arguments));

        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}

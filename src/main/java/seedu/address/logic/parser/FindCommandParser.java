package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
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
import seedu.address.model.person.passenger.PriceContainsKeywordsPredicate;
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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_PRICE);

        if (!arePrefixesValid(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_TAG, PREFIX_PRICE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<Prefix> presentPrefixes =
                presentPrefixes(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_TAG, PREFIX_PRICE);
        assert(presentPrefixes.size() == 1);

        Prefix specifiedPrefix = presentPrefixes.get(0);
        List<String> keywords = parseValue(argMultimap, specifiedPrefix);

        return new FindCommand(parsePredicate(specifiedPrefix, keywords));
    }

    /**
     * Returns true if only one of the prefixes are provided
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesValid(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return presentPrefixes(argumentMultimap, prefixes).size() == 1;
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
    private static List<String> parseValue(ArgumentMultimap argumentMultimap, Prefix prefix) throws ParseException {
        List<String> outputList = new ArrayList<>();

        if (PREFIX_NAME.equals(prefix) && argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            for (String s : argumentMultimap.getAllValues(PREFIX_NAME)) {
                String parsedNameAsString = ParserUtil.parseName(s).toString();
                outputList.add(parsedNameAsString.trim());
            }
        } else if (PREFIX_ADDRESS.equals(prefix) && argumentMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            for (String s : argumentMultimap.getAllValues(PREFIX_ADDRESS)) {
                String parsedAddrAsString = ParserUtil.parseAddress(s).toString();
                outputList.add(parsedAddrAsString.trim());
            }

        } else if (PREFIX_PHONE.equals(prefix) && argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            for (String s : argumentMultimap.getAllValues(PREFIX_PHONE)) {
                String parsedPhoneAsString = ParserUtil.parseAddress(s).toString();
                outputList.add(parsedPhoneAsString.trim());
            }
        } else if (PREFIX_TAG.equals(prefix) && argumentMultimap.getValue(PREFIX_TAG).isPresent()) {
            for (String s : argumentMultimap.getAllValues(PREFIX_TAG)) {
                String parsedTagAsString = ParserUtil.parseTag(s).toString();
                outputList.add(parsedTagAsString.trim());
            }
        } else if (PREFIX_PRICE.equals(prefix)
                && argumentMultimap.getValue(PREFIX_PRICE).isPresent()
                && doesPrefixHaveOneValue(argumentMultimap, PREFIX_PRICE)) {
            String parsedPriceAsString =
                    ParserUtil.parsePrice(argumentMultimap.getValue(PREFIX_PRICE).get()).toString();
            outputList.add(parsedPriceAsString);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return outputList;
    }

    /**
     * Returns the predicate specified by prefix
     * {@code ArgumentMultimap}.
     * @return the predicate specified.
     */
    private static Predicate<Passenger> parsePredicate(Prefix prefix, List<String> arguments) throws ParseException {
        if (PREFIX_NAME.equals(prefix)) {
            return new NameContainsKeywordsPredicate(arguments);

        } else if (PREFIX_ADDRESS.equals(prefix)) {
            return new AddressContainsKeywordsPredicate(arguments);

        } else if (PREFIX_PHONE.equals(prefix)) {
            return new PhoneContainsKeywordsPredicate(arguments);

        } else if (PREFIX_TAG.equals(prefix)) {
            return new TagContainsKeywordsPredicate(arguments);

        } else if (PREFIX_PRICE.equals(prefix)) {
            Double price = Double.parseDouble(arguments.get(0));
            return new PriceContainsKeywordsPredicate(price);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}

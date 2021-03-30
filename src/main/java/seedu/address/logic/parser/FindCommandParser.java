package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL;
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
import seedu.address.model.person.AttributeContainsKeywordsPredicate;
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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_PRICE,
                        PREFIX_ALL);

        if (!arePrefixesValid(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_TAG, PREFIX_PRICE,
                PREFIX_ALL)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<Prefix> presentPrefixes =
                presentPrefixes(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_TAG, PREFIX_PRICE,
                        PREFIX_ALL);
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


        if (PREFIX_NAME.equals(prefix) && argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            return parseValueHelper(argumentMultimap, PREFIX_NAME);
        } else if (PREFIX_ADDRESS.equals(prefix) && argumentMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            return parseValueHelper(argumentMultimap, PREFIX_ADDRESS);
        } else if (PREFIX_PHONE.equals(prefix) && argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            return parseValueHelper(argumentMultimap, PREFIX_PHONE);
        } else if (PREFIX_TAG.equals(prefix) && argumentMultimap.getValue(PREFIX_TAG).isPresent()) {
            return parseValueHelper(argumentMultimap, PREFIX_TAG);
        } else if (PREFIX_PRICE.equals(prefix)
                && argumentMultimap.getValue(PREFIX_PRICE).isPresent()
                && doesPrefixHaveOneValue(argumentMultimap, PREFIX_PRICE)) {
            List<String> outputList = new ArrayList<>();

            String parsedPriceAsString =
                    ParserUtil.parsePrice(argumentMultimap.getValue(PREFIX_PRICE).get()).toString();
            outputList.add(parsedPriceAsString);

            return outputList;
        } else if (PREFIX_ALL.equals(prefix)) {
            return parseValueHelper(argumentMultimap, PREFIX_ALL);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns the value for the specified prefix
     * {@code ArgumentMultimap}.
     */
    private static List<String> parseValueHelper(ArgumentMultimap argumentMultimap, Prefix prefix)
            throws ParseException {
        List<String> outputList = new ArrayList<>();

        for (String s : argumentMultimap.getAllValues(prefix)) {
            String argument = "";
            switch (prefix.toString()) {
            case "n/":
                argument = ParserUtil.parseName(s).toString();
                break;
            case "a/":
                argument = ParserUtil.parseAddress(s).toString();
                break;
            case "p/":
                argument = ParserUtil.parsePhone(s).toString();
                break;
            case "tag/":
                argument = ParserUtil.parseTag(s).toString();
                break;
            case "pr/":
                argument = ParserUtil.parsePrice(s).toString();
                break;
            case "all/":
                argument = s;
                break;
            default:
                break;
            }
            outputList.add(argument.trim());
        }

        return outputList;
    }

    /**
     * Returns the predicate specified by prefix
     * {@code ArgumentMultimap}.
     * @return the predicate specified.
     */
    private static Predicate<Passenger> parsePredicate(Prefix prefix, List<String> arguments) throws ParseException {
        switch (prefix.toString()) {
        case "n/":
            return new NameContainsKeywordsPredicate(arguments);
        case "a/":
            return new AddressContainsKeywordsPredicate(arguments);
        case "p/":
            return new PhoneContainsKeywordsPredicate(arguments);
        case "tag/":
            return new TagContainsKeywordsPredicate(arguments);
        case "pr/":
            Double price = Double.parseDouble(arguments.get(0));
            return new PriceContainsKeywordsPredicate(price);
        case "all/":
            return new AttributeContainsKeywordsPredicate(arguments);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}

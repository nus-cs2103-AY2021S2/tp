package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.checkOnePrefixProvided;
import static seedu.address.logic.parser.ArgumentMultimap.findPresentPrefixes;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS_STRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL_STRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME_STRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_STRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_STRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_STRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPDAY_STRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPTIME_STRING;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TripDayContainsKeywordsPredicate;
import seedu.address.model.TripTimeContainsKeywordsPredicate;
import seedu.address.model.person.AttributeContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.passenger.AddressContainsKeywordsPredicate;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.PriceIsGreaterThanAmountPredicate;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @param args Arguments to be parsed.
     * @return The FindCommand created from parsing the arguments.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_PRICE,
                        PREFIX_ALL, PREFIX_TRIPDAY, PREFIX_TRIPTIME);

        if (!checkOnePrefixProvided(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_TAG, PREFIX_PRICE,
                PREFIX_ALL, PREFIX_TRIPDAY, PREFIX_TRIPTIME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<Prefix> presentPrefixes =
                findPresentPrefixes(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_TAG, PREFIX_PRICE,
                        PREFIX_ALL, PREFIX_TRIPDAY, PREFIX_TRIPTIME);
        assert(presentPrefixes.size() == 1);

        Prefix specifiedPrefix = presentPrefixes.get(0);
        List<String> keywords = parseValue(argMultimap, specifiedPrefix);

        return new FindCommand(parsePredicate(specifiedPrefix, keywords));
    }

    /**
     * Returns the prefixes that have values
     * {@code ArgumentMultimap}.
     */
    private static boolean doesPrefixHaveOneValue(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getAllValues(prefix).size() <= 1);
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
        } else if (PREFIX_TRIPDAY.equals(prefix)) {
            return parseValueHelper(argumentMultimap, PREFIX_TRIPDAY);
        } else if (PREFIX_TRIPTIME.equals(prefix)) {
            return parseValueHelper(argumentMultimap, PREFIX_TRIPTIME);
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
            case PREFIX_NAME_STRING:
                String parsedName = ParserUtil.parseName(s).toString();
                argument = parsedName.replaceAll("\\s+", " ");
                break;
            case PREFIX_ADDRESS_STRING:
                String parsedAddress = ParserUtil.parseAddress(s).toString();
                argument = parsedAddress.replaceAll("\\s+", " ");
                break;
            case PREFIX_PHONE_STRING:
                argument = ParserUtil.parsePhone(s).toString();
                break;
            case PREFIX_TAG_STRING:
                argument = ParserUtil.parseTag(s).toString();
                break;
            case PREFIX_PRICE_STRING:
                argument = ParserUtil.parsePrice(s).toString();
                break;
            case PREFIX_ALL_STRING:
                argument = s.replaceAll("\\s+", " ");
                break;
            case PREFIX_TRIPDAY_STRING:
                argument = ParserUtil.parseTripDay(s).toString();
                break;
            case PREFIX_TRIPTIME_STRING:
                argument = ParserUtil.parseTripTime(s).toString();
                break;
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            if (argument.length() > 0) {
                String formattedArgument = argument.trim().toLowerCase();
                outputList.add(formattedArgument);
            }
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
        case PREFIX_NAME_STRING:
            return new NameContainsKeywordsPredicate(arguments);
        case PREFIX_ADDRESS_STRING:
            return new AddressContainsKeywordsPredicate(arguments);
        case PREFIX_PHONE_STRING:
            return new PhoneContainsKeywordsPredicate(arguments);
        case PREFIX_TAG_STRING:
            return new TagContainsKeywordsPredicate(arguments);
        case PREFIX_PRICE_STRING:
            Double price = Double.parseDouble(arguments.get(0));
            return new PriceIsGreaterThanAmountPredicate(price);
        case PREFIX_ALL_STRING:
            return new AttributeContainsKeywordsPredicate(arguments);
        case PREFIX_TRIPDAY_STRING:
            return new TripDayContainsKeywordsPredicate(arguments);
        case PREFIX_TRIPTIME_STRING:
            return new TripTimeContainsKeywordsPredicate(arguments);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}

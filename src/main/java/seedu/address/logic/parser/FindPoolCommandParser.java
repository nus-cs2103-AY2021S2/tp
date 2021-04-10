package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindPoolCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pool.Pool;
import seedu.address.model.pool.PooledPassengerContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindPoolCommandParser implements Parser<FindPoolCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand.
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindPoolCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesValid(argMultimap, PREFIX_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPoolCommand.MESSAGE_USAGE));
        }

        List<Prefix> presentPrefixes =
                presentPrefixes(argMultimap, PREFIX_NAME);
        assert(presentPrefixes.size() == 1);

        Prefix specifiedPrefix = presentPrefixes.get(0);
        List<String> keywords = parseValue(argMultimap, specifiedPrefix);

        return new FindPoolCommand(parsePredicate(specifiedPrefix, keywords));
    }

    /**
     * Returns true if only one of the prefixes are provided.
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesValid(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return presentPrefixes(argumentMultimap, prefixes).size() == 1;
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
        }
        return outputList;
    }

    /**
     * Returns the predicate specified by prefix
     * {@code ArgumentMultimap}.
     * @return the predicate specified.
     */
    private static Predicate<Pool> parsePredicate(Prefix prefix, List<String> arguments) throws ParseException {
        if (PREFIX_NAME.equals(prefix)) {
            return new PooledPassengerContainsKeywordsPredicate(arguments);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPoolCommand.MESSAGE_USAGE));
        }
    }
}

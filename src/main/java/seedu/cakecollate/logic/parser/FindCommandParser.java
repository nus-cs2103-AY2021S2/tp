package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ALL;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_DELIVERY_STATUS;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ORDER_DESCRIPTION;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_REQUEST;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.cakecollate.logic.commands.FindCommand;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.order.ContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private final Prefix[] allPrefixes = new Prefix[] {PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
        PREFIX_ORDER_DESCRIPTION, PREFIX_TAG, PREFIX_DATE, PREFIX_REQUEST, PREFIX_DELIVERY_STATUS};

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, allPrefixes);
        List<Prefix> prefixes = Arrays.asList(allPrefixes);
        HashMap<Prefix, List<String>> prefixesToFind = new HashMap<>();

        // User input at least 1 valid prefix
        if (!arePrefixesEmpty(argMultimap, allPrefixes)) {
            String preamble = argMultimap.getPreamble();
            if (!preamble.trim().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            prefixes.forEach(prefix -> {
                List<String> values = argMultimap.getAllValues(prefix);
                if (!values.isEmpty()) {
                    List<String> processed = processAllKeywords(values);
                    prefixesToFind.put(prefix, processed);
                }
            });

            checkInputs(prefixesToFind);
        // User did not input any known prefix
        } else {
            String trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            List<String> processed = Arrays.asList(trimmedArgs.split("\\s+"));
            // PREFIX_ALL to indicate searching in all fields of an order
            // (User will not be able to use "all/" as a valid and known prefix)
            prefixesToFind.put(PREFIX_ALL, processed);
        }

        return new FindCommand(new ContainsKeywordsPredicate(prefixesToFind));
    }

    /**
     * Process and return a {@code List<String>} containing the keywords retrieved from {@code ArgumentMultimap}.
     */
    public List<String> processAllKeywords(List<String> allKeywords) {
        return allKeywords.stream()
                .map(value -> value.trim().split("\\s+"))
                .flatMap(Stream::of)
                .collect(Collectors.toList());
    }

    /**
     * Returns true if all of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesEmpty(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isEmpty());
    }

    /**
     * Checks if keywords input by user is non-blank. Throws a {@code ParseException} otherwise.
     */
    public void checkInputs(HashMap<Prefix, List<String>> toTest) throws ParseException {
        for (Map.Entry<Prefix, List<String>> entry : toTest.entrySet()) {
            List<String> value = entry.getValue();
            for (String v : value) {
                if (v.isBlank()) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
                }
            }
        }
    }
}

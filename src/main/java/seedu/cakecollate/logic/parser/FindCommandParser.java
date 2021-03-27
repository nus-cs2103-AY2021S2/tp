package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cakecollate.logic.parser.CliSyntax.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.cakecollate.logic.commands.FindCommand;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.order.NameContainsKeywordsPredicate;

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
                args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_ORDER_DESCRIPTION, PREFIX_TAG, PREFIX_DATE, PREFIX_REQUEST);
        List<Prefix> prefixes = Arrays.asList(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_ORDER_DESCRIPTION, PREFIX_TAG, PREFIX_DATE, PREFIX_REQUEST);
        HashMap<Prefix, List<String>> prefixesToFind = new HashMap<>();

        prefixes.forEach(prefix -> {
            List<String> values = argMultimap.getAllValues(prefix);
            if (!values.isEmpty()) {
                List<String> processed = processAllKeywords(values);
                prefixesToFind.put(prefix, processed);
            }
        });

        return new FindCommand(new NameContainsKeywordsPredicate(prefixesToFind));
    }

    public List<String> processAllKeywords(List<String> allKeywords) {
        return allKeywords.stream()
                .map(value -> value.trim().split("\\s+"))
                .flatMap(Stream::of)
                .collect(Collectors.toList());
    }
}

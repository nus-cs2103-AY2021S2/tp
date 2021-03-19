package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPropertyCommand object
 */
public class FindPropertyCommandParser implements Parser<FindPropertyCommand> {

    private Predicate<Property> whichPredicate(String key) throws ParseException {
        String[] components = key.split("/");
        if (components.length > 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertyCommand.MESSAGE_USAGE));
        }
        switch (components[0]) {
        case "pm":

        }
    }
    /**
     * Parses the given {@code String} of arguments in the context of the FindPropertyCommand
     * and returns a FindPropertyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPropertyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertyCommand.MESSAGE_USAGE));
        }


        String[] nameKeywords = trimmedArgs.split("\\s+");

        List<Predicate<Property>> predicates = new ArrayList<>();
        List<String> keywords = new ArrayList<>();

        for (String s : nameKeywords) {
            if (s.contains("\\")) {

            } else {
                keywords.add(s);
            }
        }

        return new FindPropertyCommand(new PropertyContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}

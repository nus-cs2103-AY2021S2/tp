package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PRICE_LESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PRICE_MORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.find.FindPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyContainsKeywordsPredicate;
import seedu.address.model.property.PropertyPredicateList;
import seedu.address.model.property.PropertyPricePredicate;
import seedu.address.model.property.PropertyTypePredicate;

/**
 * Parses input arguments and creates a new FindPropertyCommand object
 */
public class FindPropertyCommandParser implements Parser<FindPropertyCommand> {
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
            if (s.startsWith(String.valueOf(PREFIX_PROPERTY_PRICE_MORE))) {
                predicates.add(new PropertyPricePredicate(s.split("/")[1], false));
            } else if (s.startsWith(String.valueOf(PREFIX_PROPERTY_PRICE_LESS))) {
                predicates.add(new PropertyPricePredicate(s.split("/")[1], true));
            } else if (s.startsWith(String.valueOf(PREFIX_TYPE))) {
                predicates.add(new PropertyTypePredicate(s.split("/")[1]));
            } else {
                keywords.add(s);
            }
        }

        if (keywords.size() > 0) {
            predicates.add(new PropertyContainsKeywordsPredicate(keywords));
        }

        return new FindPropertyCommand(new PropertyPredicateList(predicates));
    }

}

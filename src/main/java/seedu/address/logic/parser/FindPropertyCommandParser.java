package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PRICE_LESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PRICE_MORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyAddressPredicate;
import seedu.address.model.property.PropertyContainsKeywordsPredicate;
import seedu.address.model.property.PropertyPostalCodePredicate;
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

        for (int i = 0; i < nameKeywords.length; i++) {
            String s = nameKeywords[i];
            if (s.contains("/")) {
                String key = s.split("/")[1];
                if (s.startsWith(String.valueOf(PREFIX_PROPERTY_PRICE_MORE))) {
                    predicates.add(new PropertyPricePredicate(key, false));
                } else if (s.startsWith(String.valueOf(PREFIX_PROPERTY_PRICE_LESS))) {
                    predicates.add(new PropertyPricePredicate(key, true));
                } else if (s.startsWith(String.valueOf(PREFIX_TYPE))) {
                    predicates.add(new PropertyTypePredicate(key);
                } else if (s.startsWith(String.valueOf(PREFIX_POSTAL))) {
                    predicates.add(new PropertyPostalCodePredicate(key));
                } else if (s.startsWith(String.valueOf(PREFIX_ADDRESS))) {
                    predicates.add(new PropertyAddressPredicate(String.valueOf(PREFIX_ADDRESS)));
                } else if (s.startsWith(String.valueOf(PREFIX_REMARK))) {
                    List<String> remarks = new ArrayList<>();
                    remarks.add(key);
                    int j = i + 1;
                    while (j < nameKeywords.length && !nameKeywords[j].contains("/")) {
                        remarks.add(nameKeywords[j].strip());
                        j++;
                    }
                    i = j - 1; //reduce by 1 for for loop increment
                    predicates.add(new PropertyRemarkPredicate(remarks));
                } else if (s.startsWith(String.valueOf(PREFIX_DEADLINE))) {
                    predicates.add(new PropertyDeadlinePredicate(key));
                } else if (s.startsWith(String.valueOf(PREFIX_TAGS))) {
                    StringBuilder tags = new StringBuilder(key);
                    int j = i + 1;
                    while (j < nameKeywords.length && !nameKeywords[j].contains("/")) {
                        tags.append(nameKeywords[j]);
                        j++;
                    }
                    i = j - 1; //reduce by 1 for for loop increment
                    predicates.add(new PropertyTagsPredicate(tags.toString()));
                } else {
                    throw new ParseException("You have used an unknown parameter! \n"
                        + String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertyCommand.MESSAGE_USAGE));
                }
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

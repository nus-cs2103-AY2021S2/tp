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
import static seedu.address.logic.parser.ParserUtil.parsePropertyAddress;
import static seedu.address.logic.parser.ParserUtil.parsePropertyDeadline;
import static seedu.address.logic.parser.ParserUtil.parsePropertyPostal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyAddressPredicate;
import seedu.address.model.property.PropertyContainsKeywordsPredicate;
import seedu.address.model.property.PropertyDeadlinePredicate;
import seedu.address.model.property.PropertyPostalCodePredicate;
import seedu.address.model.property.PropertyPredicateList;
import seedu.address.model.property.PropertyPricePredicate;
import seedu.address.model.property.PropertyRemarksPredicate;
import seedu.address.model.property.PropertyTagsPredicate;
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
                    predicates.add(new PropertyTypePredicate(key));
                } else if (s.startsWith(String.valueOf(PREFIX_POSTAL))) {
                    try {
                        predicates.add(new PropertyPostalCodePredicate(parsePropertyPostal(key)));
                    } catch (ParseException e) {
                        throw new ParseException("Wrong postal code format! \n"
                            + e.getMessage()
                            + "\n"
                            + FindPropertyCommand.MESSAGE_USAGE);
                    }
                } else if (s.startsWith(String.valueOf(PREFIX_ADDRESS))) {
                    try {
                        predicates.add(new PropertyAddressPredicate(parsePropertyAddress(key)));
                    } catch (ParseException e) {
                        throw new ParseException("Wrong address format! \n"
                                + e.getMessage()
                                + "\n"
                                + FindPropertyCommand.MESSAGE_USAGE);
                    }
                } else if (s.startsWith(String.valueOf(PREFIX_REMARK))) {
                    StringBuilder remarks = new StringBuilder(key);
                    int j = i + 1;
                    while (j < nameKeywords.length && !nameKeywords[j].contains("/")) {
                        remarks.append(nameKeywords[j].strip());
                        j++;
                    }
                    i = j - 1; //reduce by 1 for for loop increment
                    predicates.add(new PropertyRemarksPredicate(remarks.toString()));
                } else if (s.startsWith(String.valueOf(PREFIX_DEADLINE))) {
                    try {
                        predicates.add(new PropertyDeadlinePredicate(parsePropertyDeadline(key)));
                    } catch (ParseException e) {
                        throw new ParseException("Wrong deadline format! \n"
                                + e.getMessage()
                                + "\n"
                                + FindPropertyCommand.MESSAGE_USAGE);
                    }
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
                        + FindPropertyCommand.MESSAGE_USAGE);
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

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_BAD_AGE_FILTER_KEYWORD_NOTATION;
import static seedu.address.commons.core.Messages.MESSAGE_BAD_AGE_RANGE_NOTATION;
import static seedu.address.commons.core.Messages.MESSAGE_BAD_LOWER_UPPER_AGE_RANGE_NOTATION;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_ARGUMENT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_AGE_INPUT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_GENDER_INPUT;
import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_WORD_PER_ATTRIBUTE;


import java.util.Arrays;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.AttributeContainsKeywordsPredicate;

public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = trimmedArgs.split("\\s+");

        try {
            isValidQuery(nameKeywords);
        } catch (ParseException e) {
            throw e;
        }

        return new FilterCommand(new AttributeContainsKeywordsPredicate(Arrays.asList(nameKeywords)));


    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isValidQuery(String[] nameKeywords) throws ParseException {

        for (String keyword : nameKeywords) {
            FilterKeywordChecker input = new FilterKeywordChecker(keyword);
            if (input.getAttributeType().equals("age")) {
                if (input.value() == null) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_AGE_INPUT, FilterCommand.MESSAGE_USAGE));
                } else if (input.value().split("-").length >= 3) {
                    throw new ParseException(
                            String.format(MESSAGE_BAD_AGE_RANGE_NOTATION, FilterCommand.MESSAGE_USAGE));
                } else if (input.value().split("-").length == 2) {
                    String[] ageBoundsArgs = input.value().split("-");
                    try {
                        int lowerBound = Integer.parseInt(ageBoundsArgs[0]);
                        int upperBound = Integer.parseInt(ageBoundsArgs[1]);
                        if (lowerBound > upperBound) {
                            throw new ParseException(
                                    String.format(MESSAGE_BAD_LOWER_UPPER_AGE_RANGE_NOTATION,
                                            FilterCommand.MESSAGE_USAGE));
                        }
                    } catch (NumberFormatException e) {
                        throw new ParseException(
                                String.format(MESSAGE_BAD_AGE_FILTER_KEYWORD_NOTATION, FilterCommand.MESSAGE_USAGE));
                    }

                }
            } else if (input.getAttributeType().equals("gender")) {
                if (!input.value().equalsIgnoreCase("m")
                        && !input.value().equalsIgnoreCase("n")
                        && !input.value().equalsIgnoreCase("f")) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_GENDER_INPUT, FilterCommand.MESSAGE_USAGE));
                }
            } else if (input.isInvalid()) {
                if (!nameKeywords[0].equalsIgnoreCase(keyword) && !keyword.contains("/")) {
                    throw new ParseException(
                            String.format(MESSAGE_MULTIPLE_WORD_PER_ATTRIBUTE, FilterCommand.MESSAGE_USAGE));
                } else {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
                }

            } else if (input.value().equals("")) {
                throw new ParseException(
                        String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));

            }
        }
        return true;
    }
}

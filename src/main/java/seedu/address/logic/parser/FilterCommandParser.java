package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.*;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;


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
                }
            } else if (input.isInvalid()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
        }
        return true;
    }
}

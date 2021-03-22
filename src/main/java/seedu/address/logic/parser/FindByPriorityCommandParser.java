package seedu.address.logic.parser;

import java.util.Arrays;

import seedu.address.logic.commands.FindByPriorityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Priority;
import seedu.address.model.task.PriorityContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindByPriorityCommand object
 */
public class FindByPriorityCommandParser implements Parser<FindByPriorityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindByPriorityCommand
     * and returns a FindByPriorityCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindByPriorityCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        try {
            assert !trimmedArgs.isEmpty() : "You must input a priority value!";
            assert Priority.isValidValue(trimmedArgs) : Priority.MESSAGE_CONSTRAINTS;
        } catch (AssertionError e) {
            throw new ParseException(
                    String.format(e.getMessage(), FindByPriorityCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = {args};

        return new FindByPriorityCommand(new PriorityContainsKeywordPredicate(Arrays.asList(nameKeywords)));
    }

}

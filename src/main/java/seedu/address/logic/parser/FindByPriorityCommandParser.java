package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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
        String[] parsedArgs = trimmedArgs.split(" ");

        try {
            assert !trimmedArgs.isEmpty() : "You must input a priority value!";
            assert parsedArgs.length == 1 : MESSAGE_INVALID_COMMAND_FORMAT;
            assert Priority.isValidValue(trimmedArgs) : Priority.MESSAGE_CONSTRAINTS;

        } catch (AssertionError e) {
            throw new ParseException(
                    String.format(e.getMessage(), FindByPriorityCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = {parsedArgs[0]};

        return new FindByPriorityCommand(new PriorityContainsKeywordPredicate(Arrays.asList(nameKeywords)));
    }

}

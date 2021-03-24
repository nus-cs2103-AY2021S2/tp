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

        if (trimmedArgs.isEmpty() || parsedArgs.length != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindByPriorityCommand.MESSAGE_USAGE));
        }

        if (!Priority.isValidValue(trimmedArgs)) {
            throw new ParseException(String.format(Priority.MESSAGE_CANNOT_FIND_PRIORITY,
                    FindByPriorityCommand.MESSAGE_USAGE));
        }


        String[] nameKeywords = {parsedArgs[0]};

        return new FindByPriorityCommand(new PriorityContainsKeywordPredicate(Arrays.asList(nameKeywords)));
    }

}

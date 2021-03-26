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

        if (parsedArgs.length != 1 || parsedArgs[0].equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindByPriorityCommand.MESSAGE_USAGE));
        }

        String keyword = parsedArgs[0];

        if (!Priority.isValidValue(keyword)) {
            throw new ParseException(String.format(Priority.MESSAGE_CANNOT_FIND_PRIORITY,
                    FindByPriorityCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = {keyword};

        return new FindByPriorityCommand(new PriorityContainsKeywordPredicate(Arrays.asList(nameKeywords)));
    }

}

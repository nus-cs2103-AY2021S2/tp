package seedu.iscam.logic.parser.clientcommands;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.iscam.logic.commands.FindPlansCommand;
import seedu.iscam.logic.parser.Parser;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.logic.parser.exceptions.ParseFormatException;
import seedu.iscam.model.client.PlanContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPlansCommand object
 */
public class FindPlansCommandParser implements Parser<FindPlansCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindPlansCommand
     * and returns a FindPlansCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPlansCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseFormatException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPlansCommand.MESSAGE_USAGE));
        }

        String[] planKeywords = trimmedArgs.split("\\s+");

        return new FindPlansCommand(new PlanContainsKeywordsPredicate(Arrays.asList(planKeywords)));
    }
}

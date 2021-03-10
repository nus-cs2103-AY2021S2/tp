package seedu.address.logic.parser.resident;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.resident.FindResidentCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.resident.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindResidentCommand object
 */
public class FindResidentCommandParser implements Parser<FindResidentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindResidentCommand
     * and returns a FindResidentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindResidentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindResidentCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindResidentCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}

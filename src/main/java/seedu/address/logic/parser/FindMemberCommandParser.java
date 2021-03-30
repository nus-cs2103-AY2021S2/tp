package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindMembersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DetailsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindMemberCommand object
 */
public class FindMemberCommandParser implements Parser<FindMembersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMemberCommand
     * and returns a FindMemberCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMembersCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMembersCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindMembersCommand(new DetailsContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}

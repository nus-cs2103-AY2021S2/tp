package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");
        boolean hasFlag = keywords[0].charAt(0) == '-';

        if (!hasFlag) {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        String field = keywords[0];
        switch (field) {

        case "-p":
            return new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(keywords)));

        case "-e":
            return new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList(keywords)));

        case "-a":
            return new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(keywords)));

        case "-t":
            return new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList(keywords)));

        case "-i":
            return new FindCommand(new InsurancePolicyContainsKeywordsPredicate(Arrays.asList(keywords)));

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.InsurancePolicyContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    public static final String KEYWORDS_REGEX = "\\s*&\\s*";

    //@@author wongkokian
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

        int flagCount = StringUtil.countMatches(trimmedArgs, "/");

        if (flagCount != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String flag = trimmedArgs.substring(0, 2);
        String noFlagArgs = trimmedArgs.substring(2).trim();
        List<String> keywords = Arrays.asList(noFlagArgs.split(KEYWORDS_REGEX, -1));

        for (String keyword : keywords) {
            if (keyword.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }

        switch (flag) {

        case "n/":
            return new FindCommand(new NameContainsKeywordsPredicate(keywords));

        case "p/":
            return new FindCommand(new PhoneContainsKeywordsPredicate(keywords));

        case "e/":
            return new FindCommand(new EmailContainsKeywordsPredicate(keywords));

        case "a/":
            return new FindCommand(new AddressContainsKeywordsPredicate(keywords));

        case "t/":
            return new FindCommand(new TagContainsKeywordsPredicate(keywords));

        case "i/":
            return new FindCommand(new InsurancePolicyContainsKeywordsPredicate(keywords));

        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}

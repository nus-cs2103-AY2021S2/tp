package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.InsurancePolicyContainsKeywordsPredicate;
import seedu.address.model.person.MeetingContainsKeywordsPredicate;
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

        String flag = getFlag(trimmedArgs);
        String noFlagArgs = getArgsWithoutFlag(trimmedArgs);
        List<String> keywords = getKeywords(noFlagArgs);
        List<String> attributeStrings = getAttributeStrings(noFlagArgs);
        List<Attribute> parsedAttributes = ParserUtil.parseAttributes(attributeStrings);

        for (String keyword : keywords) {
            if (keyword.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }

        switch (flag) {

        case "n/":
            return new FindCommand(new NameContainsKeywordsPredicate(keywords), parsedAttributes);

        case "p/":
            return new FindCommand(new PhoneContainsKeywordsPredicate(keywords), parsedAttributes);

        case "e/":
            return new FindCommand(new EmailContainsKeywordsPredicate(keywords), parsedAttributes);

        case "a/":
            return new FindCommand(new AddressContainsKeywordsPredicate(keywords), parsedAttributes);

        case "t/":
            return new FindCommand(new TagContainsKeywordsPredicate(keywords), parsedAttributes);

        case "i/":
            return new FindCommand(new InsurancePolicyContainsKeywordsPredicate(keywords), parsedAttributes);

        case "m/":
            return new FindCommand(new MeetingContainsKeywordsPredicate(keywords), parsedAttributes);

        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    private String getArgsWithoutFlag(String args) {
        return args.substring(2).trim();
    }

    private String getFlag(String args) {
        return args.substring(0, 2);
    }

    // takes in arguments from user and returns a list of keywords to find
    private List<String> getKeywords(String args) {
        List<String> splitStrings = Arrays.asList(args.split("\\s+"));
        ArrayList<String> removeAttributeStrings = new ArrayList<>(splitStrings);

        for (String string : splitStrings) {
            if (string.contains("-")) {
                removeAttributeStrings.remove(string);
            }
        }

        String trimmedKeywords = String.join(" ", removeAttributeStrings);
        List<String> keywords = Arrays.asList(trimmedKeywords.split(KEYWORDS_REGEX, -1));

        return keywords;
    }

    // takes in arguments from user and returns a list of options for filter
    private List<String> getAttributeStrings(String args) {
        List<String> splitStrings = Arrays.asList(args.split("\\s+"));
        ArrayList<String> attributes = new ArrayList<>();

        for (String string : splitStrings) {
            if (string.contains("-")) {
                attributes.add(string);
            }
        }

        return attributes;
    }

}

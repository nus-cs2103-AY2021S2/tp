package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TagCommand object.
 */
public class TagCommandParser implements Parser<TagCommand> {

    /**
     * Used for separation of sub command word and args.
     */
    private static final Pattern TAG_COMMAND_FORMAT = Pattern.compile("(?<subCommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses arguments into tag command for execution.
     *
     * @param args full command arguments
     * @return the tag command based on the arguments
     * @throws ParseException if the arguments does not conform the expected format
     */
    public TagCommand parse(String args) throws ParseException {
        final Matcher matcher = TAG_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        }

        final String subCommandWord = matcher.group("subCommandWord");
        final String arguments = matcher.group("arguments");

        switch (subCommandWord) {

        case TagCommand.ADD_SUB_COMMAND_WORD:
            return new AddTagCommandParser().parse(arguments);

        case TagCommand.DELETE_SUB_COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns true if arguments are valid to be aliased.
     */
    @Override
    public boolean isValidCommandToAlias(String userInput) {
        if (userInput.trim().isEmpty()) {
            return true;
        }

        final Matcher matcher = TAG_COMMAND_FORMAT.matcher(userInput.trim());
        matcher.matches();

        final String subCommandWord = matcher.group("subCommandWord");
        final String arguments = matcher.group("arguments");

        switch (subCommandWord) {

        case TagCommand.ADD_SUB_COMMAND_WORD:
            return new AddTagCommandParser().isValidCommandToAlias(arguments);

        case TagCommand.DELETE_SUB_COMMAND_WORD:
            return new DeleteTagCommandParser().isValidCommandToAlias(arguments);

        default:
            return false;
        }
    }

}

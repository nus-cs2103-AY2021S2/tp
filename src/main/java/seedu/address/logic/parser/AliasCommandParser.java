package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.ListAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AliasCommand object
 */
public class AliasCommandParser implements Parser<AliasCommand> {

    /**
     * Used for separation of sub command word and args.
     */
    private static final Pattern ALIAS_COMMAND_FORMAT = Pattern.compile("(?<subCommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses arguments into alias command for execution.
     *
     * @param args full command arguments
     * @return the alias command based on the arguments
     * @throws ParseException if the arguments does not conform the expected format
     */
    public AliasCommand parse(String args) throws ParseException {
        final Matcher matcher = ALIAS_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        }

        final String subCommandWord = matcher.group("subCommandWord");
        final String arguments = matcher.group("arguments");

        switch (subCommandWord) {

        case AliasCommand.ADD_SUB_COMMAND_WORD:
            return new AddAliasCommandParser().parse(arguments);

        case AliasCommand.DELETE_SUB_COMMAND_WORD:
            return new DeleteAliasCommandParser().parse(arguments);

        case AliasCommand.LIST_SUB_COMMAND_WORD:
            return new ListAliasCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AliasCommand
     * and returns true if arguments are valid to be aliased.
     */
    @Override
    public boolean isValidCommandToAlias(String userInput) {
        if (userInput.trim().isEmpty()) {
            return true;
        }

        final Matcher matcher = ALIAS_COMMAND_FORMAT.matcher(userInput.trim());
        matcher.matches();

        final String subCommandWord = matcher.group("subCommandWord");
        final String arguments = matcher.group("arguments");

        switch (subCommandWord) {

        case AliasCommand.ADD_SUB_COMMAND_WORD:
            return new AddAliasCommandParser().isValidCommandToAlias(arguments);

        case AliasCommand.DELETE_SUB_COMMAND_WORD:
            return new DeleteAliasCommandParser().isValidCommandToAlias(arguments);

        case AliasCommand.LIST_SUB_COMMAND_WORD:
            return true;

        default:
            return false;
        }
    }

}

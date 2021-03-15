package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.Command;
import seedu.address.model.alias.CommandAlias;

/**
 * Parses input arguments and creates a new AddAliasCommand object
 */
public class AddAliasCommandParser implements Parser<AddAliasCommand> {

    /**
     * Used for separation of alias and command.
     */
    private static final Pattern ADD_ALIAS_COMMAND_FORMAT = Pattern.compile("(?<alias>\\S+)(?<command>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the AddAliasCommand
     * and returns an AddAliasCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAliasCommand parse(String args) throws ParseException {
        final Matcher matcher = ADD_ALIAS_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAliasCommand.MESSAGE_USAGE));
        }

        final String aliasArg = matcher.group("alias");
        final String commandArg = matcher.group("command");

        Alias alias = ParserUtil.parseAlias(aliasArg);
        Command command = ParserUtil.parseCommand(commandArg);

        CommandAlias commandAlias = new CommandAlias(alias, command);

        return new AddAliasCommand(commandAlias);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddAliasCommand
     * and returns true if arguments are valid to be aliased. Alias cannot be aliased to
     * adding another alias as it may cause infinite looping.
     */
    @Override
    public boolean isValidCommandToAlias(String userInput) {
        return userInput.trim().isEmpty();
    }

}

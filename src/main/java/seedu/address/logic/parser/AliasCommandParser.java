package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND;

import seedu.address.commons.core.Alias;
import seedu.address.logic.commands.alias.AliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AliasCommandParser implements Parser<AliasCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AliasCommand
     * and returns an AliasCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AliasCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ALIAS, PREFIX_COMMAND);

        if (argMultimap.getValue(PREFIX_ALIAS).isEmpty() || argMultimap.getValue(PREFIX_COMMAND).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        }

        String[] parsedArgs = args.split("\\s+", 3);
        String aliasName = parsedArgs[1].substring(PREFIX_ALIAS.toString().length());
        String command = parsedArgs[2].substring(PREFIX_COMMAND.toString().length());

        Alias alias = ParserUtil.parseAlias(aliasName, command);

        return new AliasCommand(alias);
    }
}

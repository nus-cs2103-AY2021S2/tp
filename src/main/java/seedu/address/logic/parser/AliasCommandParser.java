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

        String aliasName = argMultimap.getValue(PREFIX_ALIAS).orElse("");
        String command = argMultimap.getValue(PREFIX_COMMAND).orElse("");

        if (aliasName.isEmpty() || command.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        }

        String[] parsedArgs = args.split("\\s+", 3);
        Alias alias = ParserUtil.parseAlias(parsedArgs[1].substring(2), parsedArgs[2].substring(4));

        return new AliasCommand(alias);
    }
}

package seedu.address.logic.parser.alias;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.alias.AliasCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.alias.Alias;

/**
 * Parses input arguments and creates a new AliasCommand object.
 */
public class AliasCommandParser implements Parser<AliasCommand> {
    private static final int ARGUMENT_NO = 3;

    private final Logger logger = LogsCenter.getLogger(AliasCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the AliasCommand
     * and returns an AliasCommand object for execution.
     *
     * @param args Arguments string.
     * @return An Alias Command object.
     * @throws ParseException If the user input does not conform the expected format.
     * @throws NullPointerException If the argument is null.
     */
    public AliasCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ALIAS, PREFIX_COMMAND);
        String[] parsedArgs = args.split("\\s+", ARGUMENT_NO);

        if (argMultimap.getValue(PREFIX_ALIAS).isEmpty()
                || argMultimap.getValue(PREFIX_COMMAND).isEmpty()
                || parsedArgs.length < 3) {
            logger.warning("Invalid alias command format");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        }

        if (!parsedArgs[2].startsWith("cmd/")) {
            logger.warning("Invalid alias name given");
            throw new ParseException(Alias.MESSAGE_NAME_CONSTRAINTS);
        }

        assert parsedArgs[1].startsWith("a/");
        assert parsedArgs[2].startsWith("cmd/");

        String aliasName = parsedArgs[1].substring(PREFIX_ALIAS.toString().length());
        String command = parsedArgs[2].substring(PREFIX_COMMAND.toString().length());

        Alias alias = ParserUtil.parseAlias(aliasName, command);

        return new AliasCommand(alias);
    }
}

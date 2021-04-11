package seedu.flashback.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.flashback.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_ALIAS_COMMAND;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_ALIAS_NAME;

import java.util.stream.Stream;

import seedu.flashback.logic.commands.AliasCommand;
import seedu.flashback.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AliasCommand object
 */
public class AliasCommandParser implements Parser<AliasCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AliasCommand
     * and returns an AliasCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AliasCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ALIAS_COMMAND,
                PREFIX_ALIAS_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_ALIAS_COMMAND, PREFIX_ALIAS_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        }

        String command = argMultimap.getValue(PREFIX_ALIAS_COMMAND).get();
        String alias = ParserUtil.parseAlias(argMultimap.getValue(PREFIX_ALIAS_NAME).get());

        return new AliasCommand(command, alias);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

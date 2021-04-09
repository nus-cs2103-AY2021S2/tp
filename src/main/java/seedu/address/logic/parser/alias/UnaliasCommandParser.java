package seedu.address.logic.parser.alias;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.alias.UnaliasCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnaliasCommand object.
 */
public class UnaliasCommandParser implements Parser<UnaliasCommand> {
    private final Logger logger = LogsCenter.getLogger(UnaliasCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the UnaliasCommand
     * and returns an UnaliasCommand object for execution.
     *
     * @param args Arguments string.
     * @return An Unalias Command object.
     * @throws ParseException If the user input does not conform the expected format.
     * @throws NullPointerException If the argument is null.
     */
    public UnaliasCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ALIAS);

        String aliasName = argMultimap.getValue(PREFIX_ALIAS).orElse("");

        if (aliasName.isEmpty()) {
            logger.warning("Invalid unalias command format");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnaliasCommand.MESSAGE_USAGE));
        }

        return new UnaliasCommand(aliasName);
    }
}

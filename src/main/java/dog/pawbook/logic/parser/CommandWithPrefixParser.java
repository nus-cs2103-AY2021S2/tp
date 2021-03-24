package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import dog.pawbook.logic.commands.Command;
import dog.pawbook.logic.parser.exceptions.ParseException;

public abstract class CommandWithPrefixParser<T extends Command> implements Parser<T> {
    /**
     * Returns an array containing all compulsory prefixes.
     */
    protected abstract Prefix[] getCompulsoryPrefixes();

    /**
     * Returns an array containing all accepted prefixes.
     */
    protected abstract Prefix[] getAllPrefixes();

    /**
     * Returns the help message for the add command.
     */
    protected abstract String getUsageText();

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    protected final boolean areCompulsoryPrefixesPresent(ArgumentMultimap argumentMultimap) {
        return Stream.of(getCompulsoryPrefixes()).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Extracts all argument values tagged by the corresponding prefixes and guarantees that all compulsory arguments
     * are supplied.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    protected final ArgumentMultimap extractArguments(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, getAllPrefixes());

        if (!areCompulsoryPrefixesPresent(argMultimap) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, getUsageText()));
        }

        return argMultimap;
    }
}

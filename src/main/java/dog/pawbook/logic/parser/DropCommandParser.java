package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PROGRAMID;

import java.util.stream.Stream;

import dog.pawbook.logic.commands.DropCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;

public class DropCommandParser implements Parser<DropCommand> {

    private static final Prefix[] DROP_COMPULSORY_PREFIXES = { PREFIX_DOGID, PREFIX_PROGRAMID };
    private static final Prefix[] DROP_ALL_PREFIXES =
            Stream.of(DROP_COMPULSORY_PREFIXES).toArray(Prefix[]::new);

    /**
     * Returns an array containing all compulsory prefixes.
     */
    protected Prefix[] getCompulsoryPrefixes() {
        return DROP_COMPULSORY_PREFIXES;
    }

    /**
     * Returns an array containing all accepted prefixes.
     */
    protected Prefix[] getAllPrefixes() {
        return DROP_ALL_PREFIXES;
    }

    /**
     * Returns the help message for the add command.
     */
    protected String getUsageText() {
        return DropCommand.MESSAGE_USAGE;
    }

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

    /**
     * Parses the given {@code String} of arguments in the context of the DropCommand
     * and returns an DropCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DropCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = extractArguments(args);

        int dogId = ParserUtil.parseId(argMultimap.getValue(PREFIX_DOGID).get());
        int programId = ParserUtil.parseId(argMultimap.getValue(PREFIX_PROGRAMID).get());

        return new DropCommand(dogId, programId);
    }
}

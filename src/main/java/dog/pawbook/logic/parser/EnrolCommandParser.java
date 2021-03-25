package dog.pawbook.logic.parser;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PROGRAMID;

import java.util.stream.Stream;

import dog.pawbook.logic.commands.EnrolCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;

public class EnrolCommandParser extends CommandWithCompulsoryPrefixNoPreambleParser<EnrolCommand> {

    private static final Prefix[] ENROL_COMPULSORY_PREFIXES = { PREFIX_DOGID, PREFIX_PROGRAMID };
    private static final Prefix[] ENROL_ALL_PREFIXES =
            Stream.of(ENROL_COMPULSORY_PREFIXES).toArray(Prefix[]::new);

    /**
     * Returns an array containing all compulsory prefixes.
     */
    protected Prefix[] getCompulsoryPrefixes() {
        return ENROL_COMPULSORY_PREFIXES;
    }

    /**
     * Returns an array containing all accepted prefixes.
     */
    protected Prefix[] getAllPrefixes() {
        return ENROL_ALL_PREFIXES;
    }

    /**
     * Returns the help message for the add command.
     */
    protected String getUsageText() {
        return EnrolCommand.MESSAGE_USAGE;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EnrolCommand
     * and returns an EnrolCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EnrolCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = extractArguments(args);

        int dogId = ParserUtil.parseId(argMultimap.getValue(PREFIX_DOGID).get());
        int programId = ParserUtil.parseId(argMultimap.getValue(PREFIX_PROGRAMID).get());

        return new EnrolCommand(dogId, programId);
    }
}

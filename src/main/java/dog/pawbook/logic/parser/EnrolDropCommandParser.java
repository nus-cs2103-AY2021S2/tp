package dog.pawbook.logic.parser;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOGID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PROGRAMID;

import java.util.Set;

import dog.pawbook.logic.commands.DropCommand;
import dog.pawbook.logic.commands.EnrolCommand;
import dog.pawbook.logic.commands.ProgramRegistrationCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;

//@@author shaelynl
/**
 * A parser capable of parsing for both enrol and drop commands.
 */
public class EnrolDropCommandParser extends CommandWithCompulsoryPrefixNoPreambleParser<ProgramRegistrationCommand> {
    public static final String MESSAGE_UNSUPPORTED_BATCH_ENROL_OPERATION = "Only enrollment of multiple dogs into a "
            + "single program or enrollment of a single dog into multiple programs are supported!";
    public static final String MESSAGE_UNSUPPORTED_BATCH_DROP_OPERATION = "Only dropping of multiple dogs from a "
            + "single program or dropping of a single dog from multiple programs are supported!";

    private static final Prefix[] ENROL_COMPULSORY_PREFIXES = { PREFIX_DOGID, PREFIX_PROGRAMID };
    private final boolean enrol;

    /**
     * Construct a parser for enrol and drop command, depending on the value of {@code enrol}.
     */
    public EnrolDropCommandParser(boolean enrol) {
        this.enrol = enrol;
    }

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
        return ENROL_COMPULSORY_PREFIXES;
    }

    /**
     * Returns the help message for the add command.
     */
    protected String getUsageText() {
        return enrol ? EnrolCommand.MESSAGE_USAGE : DropCommand.MESSAGE_USAGE;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EnrolCommand
     * and returns an EnrolCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ProgramRegistrationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = extractArguments(args);

        Set<Integer> dogIdList = ParserUtil.parseIds(argMultimap.getAllValues(PREFIX_DOGID));
        Set<Integer> programIdList = ParserUtil.parseIds(argMultimap.getAllValues(PREFIX_PROGRAMID));

        /*
         * Since both prefixes are compulsory, it is safe to assume that the number of both IDs are > 1 respectively
         * but we don't support enrollment of multiple dogs into multiple programs since that is error-prone for the
         * user.
         */
        if (dogIdList.size() > 1 && programIdList.size() > 1) {
            throw new ParseException(enrol
                    ? MESSAGE_UNSUPPORTED_BATCH_ENROL_OPERATION
                    : MESSAGE_UNSUPPORTED_BATCH_DROP_OPERATION);
        }

        return enrol ? new EnrolCommand(dogIdList, programIdList) : new DropCommand(dogIdList, programIdList);
    }
}

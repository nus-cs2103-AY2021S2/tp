package seedu.address.logic.parser.patient;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.patient.DeletePatientCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePatientCommand object
 */
public class DeletePatientCommandParser implements Parser<DeletePatientCommand> {

    /**
     * Used for the separation of force delete format and patient index.
     */
    private static final Pattern FORCE_DELETE_FORMAT = Pattern.compile("(?<forceDelete>\\D+)(?<patientIndex>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePatientCommand
     * and returns a DeletePatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePatientCommand parse(String args) throws ParseException {
        final Matcher forceDeleteMatcher = FORCE_DELETE_FORMAT.matcher(args.trim());
        boolean isForceDelete = false;
        String indexToParse = args;

        try {
            if (forceDeleteMatcher.matches()) {
                final String forceDelete = forceDeleteMatcher.group("forceDelete").trim();
                final String patientIndex = forceDeleteMatcher.group("patientIndex");

                if (forceDelete.equals("--force")) {
                    isForceDelete = true;
                    indexToParse = patientIndex;
                }
            }

            Index index = ParserUtil.parseIndex(indexToParse);

            return new DeletePatientCommand(index, isForceDelete);
        } catch (ParseException pe) {
            String messageUsage = isForceDelete ? DeletePatientCommand.FORCE_DELETE_MESSAGE_USAGE
                    : DeletePatientCommand.MESSAGE_USAGE;

            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, messageUsage), pe);
        }
    }

}

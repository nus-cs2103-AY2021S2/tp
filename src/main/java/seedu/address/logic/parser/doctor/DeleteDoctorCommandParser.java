package seedu.address.logic.parser.doctor;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.doctor.DeleteDoctorCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteDoctorCommand object
 */
public class DeleteDoctorCommandParser implements Parser<DeleteDoctorCommand> {

    /**
     * Used for the separation of force delete format and doctor index from the format '--force INDEX'.
     */
    private static final Pattern FORCE_DELETE_FORMAT = Pattern.compile("(?<forceDelete>\\D+)(?<doctorIndex>.*)");

    /**
     * Parses the given {@code args} in the context of the DeleteDoctorCommand
     * and returns a DeleteDoctorCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteDoctorCommand parse(String args) throws ParseException {
        final Matcher forceDeleteMatcher = FORCE_DELETE_FORMAT.matcher(args.trim());
        boolean isForceDelete = false;
        String indexToParse = args;

        if (forceDeleteMatcher.matches()) {
            final String forceDelete = forceDeleteMatcher.group("forceDelete").trim();
            final String doctorIndex = forceDeleteMatcher.group("doctorIndex");

            if (forceDelete.equals("--force")) {
                isForceDelete = true;
                indexToParse = doctorIndex;
            }
        }

        try {
            Index index = ParserUtil.parseIndex(indexToParse);

            return new DeleteDoctorCommand(index, isForceDelete);
        } catch (ParseException pe) {
            String messageUsage = isForceDelete ? DeleteDoctorCommand.FORCE_DELETE_MESSAGE_USAGE
                    : DeleteDoctorCommand.MESSAGE_USAGE;

            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, messageUsage), pe);
        }
    }
}

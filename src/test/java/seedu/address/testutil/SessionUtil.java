package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.AddSessionCommand;
import seedu.address.model.session.Session;
import seedu.address.model.student.Student;

/**
 * A utility class for Session to simplify testing
 */
public class SessionUtil {

    /**
     * Returns an add session command string for adding the {@code session}.
     */
    public static String getAddSessionCommand(Student student, Session session) {
        return AddSessionCommand.COMMAND_WORD + " " + getSessionDetails(student, session);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getSessionDetails(Student student, Session session) {
        StringBuilder sb = new StringBuilder();
        String dateTime = session.getSessionDate().toString();
        String sessionDate = extractSessionDate(dateTime);
        String sessionTime = extractSessionTime(dateTime);
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_DATE + sessionDate + " ");
        sb.append(PREFIX_TIME + sessionTime + " ");
        sb.append(PREFIX_DURATION + session.getDuration().toString() + " ");
        sb.append(PREFIX_SUBJECT + session.getSubject().toString() + " ");
        sb.append(PREFIX_FEE + session.getFee().toString() + " ");
        return sb.toString();
    }

    public static String extractSessionDate(String dateTime) {
        return dateTime.split("T")[0];
    }
    public static String extractSessionTime(String dateTime) {
        return dateTime.split("T")[1];
    }
}

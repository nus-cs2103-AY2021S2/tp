package seedu.address.logic.commands.meetings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.NameContainsKeywordsPredicate;



/**
 * Contains helper methods for testing commands.
 */
public class MeetingCommandTestUtil {

    public static final String VALID_NAME_MEETING1 = "JCP Conference";
    public static final String VALID_NAME_MEETING2 = "NUS Seminar";
    public static final String VALID_START_MEETING1 = "2021-01-01 19:00";
    public static final String VALID_START_MEETING2 = "2021-03-15 17:00";
    public static final String VALID_TERMINATE_MEETING1 = "2021-01-01 20:00";
    public static final String VALID_TERMINATE_MEETING2 = "2021-03-15 18:00";
    public static final String VALID_PRIORITY_MEETING1 = "2";
    public static final String VALID_PRIORITY_MEETING2 = "5";
    public static final String VALID_DESCRIPTION_MEETING1 = "JCP Conference Test";
    public static final String VALID_DESCRIPTION_MEETING2 = "NUS Seminar Test";
    public static final String VALID_TAG_MEETING1 = "SoC";
    public static final String VALID_TAG_MEETING2 = "University";

    public static final String NAME_DESC_MEETING1 = " " + PREFIX_NAME + VALID_NAME_MEETING1;
    public static final String NAME_DESC_MEETING2 = " " + PREFIX_NAME + VALID_NAME_MEETING2;
    public static final String START_DESC_MEETING1 = " " + PREFIX_START_TIME + VALID_START_MEETING1;
    public static final String START_DESC_MEETING2 = " " + PREFIX_START_TIME + VALID_START_MEETING2;
    public static final String END_DESC_MEETING1 = " " + PREFIX_END_TIME + VALID_TERMINATE_MEETING1;
    public static final String END_DESC_MEETING2 = " " + PREFIX_END_TIME + VALID_TERMINATE_MEETING2;
    public static final String PRIORITY_DESC_MEETING1 = " " + PREFIX_PRIORITY + VALID_PRIORITY_MEETING1;
    public static final String PRIORITY_DESC_MEETING2 = " " + PREFIX_PRIORITY + VALID_PRIORITY_MEETING2;
    public static final String DESCRIPTION_DESC_MEETING1 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING1;
    public static final String DESCRIPTION_DESC_MEETING2 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING2;
    public static final String TAG_DESC_MEETING1 = " " + PREFIX_GROUP + VALID_TAG_MEETING1;
    public static final String TAG_DESC_MEETING2 = " " + PREFIX_GROUP + VALID_TAG_MEETING2;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Lectures&"; // '&' not allowed in names
    public static final String INVALID_DATETIME_DESC = " " + PREFIX_START_TIME + "2020/02/03 09:00";
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "-2";
    public static final String INVALID_TAG_DESC = " " + PREFIX_GROUP + "hubby*"; // '*' not allowed in tags

    /**
     * Updates {@code model}'s filtered meetings list to show only the meeting at the given {@code targetIndex} in the
     * {@code model}'s meeting book. It is mandatory for this case meeting names are unique with respect to their first
     * word in their name.
    */
    public static void showMeetingAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMeetingList().size());

        Meeting meeting = model.getFilteredMeetingList().get(targetIndex.getZeroBased());
        final String[] splitName = meeting.getName().fullName.split("\\s+");
        model.updateFilteredMeetingList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredMeetingList().size());
    }
}


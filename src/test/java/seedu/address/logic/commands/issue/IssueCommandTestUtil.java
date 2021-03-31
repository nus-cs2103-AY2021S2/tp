package seedu.address.logic.commands.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;
import seedu.address.testutil.issue.EditIssueDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class IssueCommandTestUtil {

    public static final String VALID_ISSUE_ROOM_NUMBER_10_100 = "10-100";
    public static final String VALID_ISSUE_DESCRIPTION_10_100 = "Broken table";
    public static final String VALID_ISSUE_TIMESTAMP_10_100 = "2021/01/01 12:00pm";
    public static final String VALID_ISSUE_STATUS_10_100 = "pending";
    public static final String VALID_ISSUE_CATEGORY_10_100 = "Furniture";
    public static final String VALID_ISSUE_ROOM_NUMBER_20_109 = "20-109";
    public static final String VALID_ISSUE_DESCRIPTION_20_109 = "Flickering light";
    public static final String VALID_ISSUE_TIMESTAMP_20_109 = "2021/03/20 8:35am";
    public static final String VALID_ISSUE_STATUS_20_109 = "closed";
    public static final String VALID_ISSUE_CATEGORY_20_109 = "Electrical";

    public static final String ROOM_NUMBER_DESC_10_100 = " " + PREFIX_ROOM_NUMBER + VALID_ISSUE_ROOM_NUMBER_10_100;
    public static final String DESCRIPTION_DESC_10_100 = " " + PREFIX_DESCRIPTION + VALID_ISSUE_DESCRIPTION_10_100;
    public static final String TIMESTAMP_DESC_10_100 = " " + PREFIX_TIMESTAMP + VALID_ISSUE_TIMESTAMP_10_100;
    public static final String STATUS_DESC_10_100 = " " + PREFIX_STATUS + VALID_ISSUE_STATUS_10_100;
    public static final String CATEGORY_DESC_10_100 = " " + PREFIX_CATEGORY + VALID_ISSUE_CATEGORY_10_100;
    public static final String ROOM_NUMBER_DESC_20_109 = " " + PREFIX_ROOM_NUMBER + VALID_ISSUE_ROOM_NUMBER_20_109;
    public static final String DESCRIPTION_DESC_20_109 = " " + PREFIX_DESCRIPTION + VALID_ISSUE_DESCRIPTION_20_109;
    public static final String TIMESTAMP_DESC_20_109 = " " + PREFIX_TIMESTAMP + VALID_ISSUE_TIMESTAMP_20_109;
    public static final String STATUS_DESC_20_109 = " " + PREFIX_STATUS + VALID_ISSUE_STATUS_20_109;
    public static final String CATEGORY_DESC_20_109 = " " + PREFIX_CATEGORY + VALID_ISSUE_CATEGORY_20_109;

    public static final String INVALID_ROOM_NUMBER_DESC = " " + PREFIX_ROOM_NUMBER + "100-100"; // extra floor digit
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + ""; // empty description
    public static final String INVALID_TIMESTAMP_DESC = " " + PREFIX_TIMESTAMP + "2020/01/32 12:00pm"; // invalid date
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "done"; // done is invalid
    public static final String INVALID_CATEGORY_DESC = " " + PREFIX_CATEGORY + "@Furniture"; // non alphanumerical

    public static final EditIssueCommand.EditIssueDescriptor DESC_10_100;
    public static final EditIssueCommand.EditIssueDescriptor DESC_20_109;

    static {
        DESC_10_100 = new EditIssueDescriptorBuilder()
                .withRoomNumber(VALID_ISSUE_ROOM_NUMBER_10_100)
                .withDescription(VALID_ISSUE_DESCRIPTION_10_100)
                .withTimestamp(VALID_ISSUE_TIMESTAMP_10_100)
                .withStatus(VALID_ISSUE_STATUS_10_100)
                .withCategory(VALID_ISSUE_CATEGORY_10_100)
                .build();
        DESC_20_109 = new EditIssueDescriptorBuilder()
                .withRoomNumber(VALID_ISSUE_ROOM_NUMBER_20_109)
                .withDescription(VALID_ISSUE_DESCRIPTION_20_109)
                .withTimestamp(VALID_ISSUE_TIMESTAMP_20_109)
                .withStatus(VALID_ISSUE_STATUS_20_109)
                .withCategory(VALID_ISSUE_CATEGORY_20_109)
                .build();
    }

    /**
     * Updates {@code model}'s filtered list to show only the issue at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showIssueAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredIssueList().size());

        Issue issue = model.getFilteredIssueList().get(targetIndex.getZeroBased());
        model.updateFilteredIssueList((i) -> i.equals(issue));

        assertEquals(1, model.getFilteredIssueList().size());
    }

}

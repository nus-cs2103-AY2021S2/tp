package seedu.address.model.issue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.issue.TypicalIssues.ISSUE_10_100;
import static seedu.address.testutil.issue.TypicalIssues.ISSUE_11_110;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.issue.IssueBuilder;

public class IssueTest {

    /*
     * @Test
     * public void asObservableList_modifyList_throwsUnsupportedOperationException() {
     * Issue issue = new IssueBuilder().build();
     * assertThrows(UnsupportedOperationException.class, () -> issue.getPhone().remove());
     * }
     */

    @Test
    public void equals() {
        // same values -> returns true
        Issue issue10100Copy = new IssueBuilder(ISSUE_10_100).build();
        assertTrue(ISSUE_10_100.equals(issue10100Copy));

        // same object -> returns true
        assertTrue(ISSUE_10_100.equals(ISSUE_10_100));

        // null -> returns false
        assertFalse(ISSUE_10_100.equals(null));

        // different type -> returns false
        assertFalse(ISSUE_10_100.equals(5));

        // different issue -> returns false
        assertFalse(ISSUE_10_100.equals(ISSUE_11_110));

        // different room number -> returns false
        Issue editedIssue10100 = new IssueBuilder(ISSUE_10_100)
                .withRoomNumber(ISSUE_11_110.getRoomNumber().value)
                .build();
        assertFalse(ISSUE_10_100.equals(editedIssue10100));

        // different description -> returns false
        editedIssue10100 = new IssueBuilder(ISSUE_10_100)
                .withDescription(ISSUE_11_110.getDescription().value)
                .build();
        assertFalse(ISSUE_10_100.equals(editedIssue10100));

        // different timestamp -> returns false
        editedIssue10100 = new IssueBuilder(ISSUE_10_100)
                .withTimestamp(ISSUE_11_110.getTimestamp().toString())
                .build();
        assertFalse(ISSUE_10_100.equals(editedIssue10100));

        // different status -> returns false
        editedIssue10100 = new IssueBuilder(ISSUE_10_100)
                .withStatus(ISSUE_11_110.getStatus().value.toString())
                .build();
        assertFalse(ISSUE_10_100.equals(editedIssue10100));

        // different category -> returns false
        editedIssue10100 = new IssueBuilder(ISSUE_10_100)
                .withCategory(ISSUE_11_110.getCategory().value)
                .build();
        assertFalse(ISSUE_10_100.equals(editedIssue10100));
    }
}

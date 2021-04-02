package seedu.address.logic.commands.issue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.DESC_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.DESC_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_CATEGORY_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_DESCRIPTION_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_ROOM_NUMBER_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_STATUS_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_TIMESTAMP_11_110;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.issue.EditIssueCommand.EditIssueDescriptor;
import seedu.address.testutil.issue.EditIssueDescriptorBuilder;

public class EditIssueDescriptorTest {
    @Test
    public void equals() {
        // Same values -> return true
        EditIssueDescriptor descriptorWithSameValues = new EditIssueDescriptor(DESC_10_100);
        assertTrue(DESC_10_100.equals(descriptorWithSameValues));

        // Same object -> return true
        assertTrue(DESC_10_100.equals(DESC_10_100));

        // null -> return false
        assertFalse(DESC_10_100.equals(null));

        // different types -> return false
        assertFalse(DESC_10_100.equals(5));

        // different values -> return false
        assertFalse(DESC_10_100.equals(DESC_11_110));

        // tests for each of the different fields
        EditIssueDescriptor editedOne;

        // different room number -> return false
        editedOne = new EditIssueDescriptorBuilder(DESC_10_100)
                .withRoomNumber(VALID_ISSUE_ROOM_NUMBER_11_110)
                .build();
        assertFalse(DESC_10_100.equals(editedOne));

        // different room number -> return false
        editedOne = new EditIssueDescriptorBuilder(DESC_10_100)
                .withDescription(VALID_ISSUE_DESCRIPTION_11_110)
                .build();
        assertFalse(DESC_10_100.equals(editedOne));

        // different room number -> return false
        editedOne = new EditIssueDescriptorBuilder(DESC_10_100)
                .withTimestamp(VALID_ISSUE_TIMESTAMP_11_110)
                .build();
        assertFalse(DESC_10_100.equals(editedOne));

        // different room number -> return false
        editedOne = new EditIssueDescriptorBuilder(DESC_10_100)
                .withStatus(VALID_ISSUE_STATUS_11_110)
                .build();
        assertFalse(DESC_10_100.equals(editedOne));

        // different room number -> return false
        editedOne = new EditIssueDescriptorBuilder(DESC_10_100)
                .withCategory(VALID_ISSUE_CATEGORY_11_110)
                .build();
        assertFalse(DESC_10_100.equals(editedOne));
    }
}

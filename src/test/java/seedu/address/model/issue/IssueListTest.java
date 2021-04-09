package seedu.address.model.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.issue.TypicalIssues.ISSUE_10_100;
import static seedu.address.testutil.issue.TypicalIssues.ISSUE_11_110;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.issue.exceptions.DuplicateIssueException;
import seedu.address.model.issue.exceptions.IssueNotFoundException;

public class IssueListTest {

    private final IssueList issueList = new IssueList();

    @Test
    public void contains_nullIssue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> issueList.contains(null));
    }

    @Test
    public void contains_issueNotInList_returnsFalse() {
        assertFalse(issueList.contains(ISSUE_10_100));
    }

    @Test
    public void contains_issueInList_returnsTrue() {
        issueList.add(ISSUE_10_100);
        assertTrue(issueList.contains(ISSUE_10_100));
    }

    @Test
    public void add_nullIssue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> issueList.add(null));
    }

    @Test
    public void add_duplicateIssue_throwsDuplicateIssueException() {
        issueList.add(ISSUE_10_100);
        assertThrows(DuplicateIssueException.class, () -> issueList.add(ISSUE_10_100));
    }

    @Test
    public void setIssue_nullTargetIssue_throwsNullPointerException() {
        issueList.add(ISSUE_10_100);
        assertThrows(NullPointerException.class, () -> issueList.setIssue(null, ISSUE_10_100));
    }

    @Test
    public void setIssue_nullEditedIssue_throwsNullPointerException() {
        issueList.add(ISSUE_10_100);
        assertThrows(NullPointerException.class, () -> issueList.setIssue(ISSUE_10_100, null));
    }

    @Test
    public void setIssue_targetIssueNotInList_throwsIssueNotFoundException() {
        assertThrows(IssueNotFoundException.class, () -> issueList.setIssue(ISSUE_10_100, ISSUE_10_100));
    }

    @Test
    public void setIssue_editedIssueIsSameIssue_success() {
        issueList.add(ISSUE_10_100);
        issueList.setIssue(ISSUE_10_100, ISSUE_10_100);
        IssueList expectedIssueList = new IssueList();
        expectedIssueList.add(ISSUE_10_100);
        assertEquals(expectedIssueList, issueList);
    }

    @Test
    public void setIssue_editedIssueIsDifferentIssue_success() {
        issueList.add(ISSUE_10_100);
        issueList.setIssue(ISSUE_10_100, ISSUE_11_110);
        IssueList expectedIssueList = new IssueList();
        expectedIssueList.add(ISSUE_11_110);
        assertEquals(expectedIssueList, issueList);
    }

    @Test
    public void setIssue_editedIssueHasNonIdentity_throwsDuplicateIssueException() {
        issueList.add(ISSUE_10_100);
        issueList.add(ISSUE_11_110);
        assertThrows(DuplicateIssueException.class, () -> issueList.setIssue(ISSUE_10_100, ISSUE_11_110));
    }

    @Test
    public void remove_nullIssue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> issueList.remove(null));
    }

    @Test
    public void remove_issueDoesNotExist_throwsIssueNotFoundException() {
        assertThrows(IssueNotFoundException.class, () -> issueList.remove(ISSUE_10_100));
    }

    @Test
    public void remove_existingIssue_removesIssue() {
        issueList.add(ISSUE_10_100);
        issueList.remove(ISSUE_10_100);
        IssueList expectedIssueList = new IssueList();
        assertEquals(expectedIssueList, issueList);
    }

    @Test
    public void setIssues_nullIssueList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> issueList.setIssues((IssueList) null));
    }

    @Test
    public void setIssues_uniqueIssueList_replacesOwnListWithProvidedIssueList() {
        issueList.add(ISSUE_10_100);
        IssueList expectedIssueList = new IssueList();
        expectedIssueList.add(ISSUE_11_110);
        issueList.setIssues(expectedIssueList);
        assertEquals(expectedIssueList, issueList);
    }

    @Test
    public void setIssues_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> issueList.setIssues((List<Issue>) null));
    }

    @Test
    public void setIssues_list_replacesOwnListWithProvidedList() {
        issueList.add(ISSUE_10_100);
        List<Issue> newInternalIssueList = Collections.singletonList(ISSUE_11_110);
        issueList.setIssues(newInternalIssueList);
        IssueList expectedIssueList = new IssueList();
        expectedIssueList.add(ISSUE_11_110);
        assertEquals(expectedIssueList, issueList);
    }

    @Test
    public void setIssues_listWithDuplicateIssues_throwsDuplicateIssueException() {
        List<Issue> listWithDuplicateIssues = Arrays.asList(ISSUE_10_100, ISSUE_10_100);
        assertThrows(DuplicateIssueException.class, () -> issueList
                .setIssues(listWithDuplicateIssues));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> issueList.asUnmodifiableObservableList().remove(0));
    }
}

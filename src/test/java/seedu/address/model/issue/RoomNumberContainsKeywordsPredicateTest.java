package seedu.address.model.issue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.issue.IssueBuilder;

public class RoomNumberContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("10");
        List<String> secondPredicateKeywordList = Arrays.asList("10", "20");

        RoomNumberOrTagContainsKeywordsPredicate firstPredicate = new RoomNumberOrTagContainsKeywordsPredicate(
                firstPredicateKeywordList);
        RoomNumberOrTagContainsKeywordsPredicate secondPredicate = new RoomNumberOrTagContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RoomNumberOrTagContainsKeywordsPredicate firstPredicateCopy = new RoomNumberOrTagContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        // assertFa
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different resident -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_roomNumberContainsKeywords_returnsTrue() {
        // One keyword
        RoomNumberOrTagContainsKeywordsPredicate predicate = new RoomNumberOrTagContainsKeywordsPredicate(
                Collections.singletonList("10-"));
        assertTrue(predicate.test(new IssueBuilder().withRoomNumber("10-100").build()));

        // Multiple keywords
        predicate = new RoomNumberOrTagContainsKeywordsPredicate(Arrays.asList("10-", "11-"));
        assertTrue(predicate.test(new IssueBuilder().withRoomNumber("10-100").build()));
        assertTrue(predicate.test(new IssueBuilder().withRoomNumber("11-100").build()));

        // Only one matching keyword
        predicate = new RoomNumberOrTagContainsKeywordsPredicate(Arrays.asList("11-", "12-"));
        assertFalse(predicate.test(new IssueBuilder().withRoomNumber("10-100").build()));
        assertTrue(predicate.test(new IssueBuilder().withRoomNumber("11-100").build()));
    }

    @Test
    public void test_roomNumberDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RoomNumberOrTagContainsKeywordsPredicate predicate = new RoomNumberOrTagContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new IssueBuilder().withRoomNumber("10-100").build()));

        // Non-matching keyword
        predicate = new RoomNumberOrTagContainsKeywordsPredicate(Arrays.asList("123"));
        assertFalse(predicate.test(new IssueBuilder().withRoomNumber("10-100").build()));

        // Keywords match description, timestamp, status, category, but does not match room number
        predicate = new RoomNumberOrTagContainsKeywordsPredicate(
                Arrays.asList(IssueBuilder.DEFAULT_ROOM_NUMBER,
                        IssueBuilder.DEFAULT_DESCRIPTION,
                        IssueBuilder.DEFAULT_TIMESTAMP,
                        IssueBuilder.DEFAULT_STATUS,
                        IssueBuilder.DEFAULT_CATEGORY));
        assertFalse(predicate.test(new IssueBuilder().withRoomNumber("01-001").build()));
    }

}

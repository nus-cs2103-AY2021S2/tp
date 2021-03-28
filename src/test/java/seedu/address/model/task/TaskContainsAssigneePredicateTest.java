package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskContainsAssigneePredicateTest {
    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        TaskContainsAssigneePredicate firstPredicate = new TaskContainsAssigneePredicate(firstPredicateKeyword);
        TaskContainsAssigneePredicate secondPredicate = new TaskContainsAssigneePredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskContainsAssigneePredicate firstPredicateCopy = new TaskContainsAssigneePredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    //    @Test
    //    public void test_taskContainsAssignee_returnsTrue() {
    //        // One keyword
    //        TaskContainsAssigneePredicate predicate = new TaskContainsAssigneePredicate("Meeting");
    //        assertTrue(predicate.test(new TaskBuilder().withTitle("Meeting").build()));
    //
    //        // Multiple keywords
    //        predicate = new TaskContainsAssigneePredicate(Arrays.asList("Meeting", "CS2103T"));
    //        assertTrue(predicate.test(new TaskBuilder().withTitle("Meeting").withDescription("CS2103T").build()));
    //
    //        // Only one matching keyword
    //        predicate = new TaskContainsAssigneePredicate(Arrays.asList("Meeting", "CS3243"));
    //        assertTrue(predicate.test(new TaskBuilder().withTitle("Meeting").withDescription("CS2103T").build()));
    //
    //        // Mixed-case keywords
    //        predicate = new TaskContainsAssigneePredicate(Arrays.asList("mEEtINg", "cs2103"));
    //        assertTrue(predicate.test(new TaskBuilder().withTitle("Meeting").withDescription("CS2103T").build()));
    //    }
    //
    //    @Test
    //    public void test_taskDoesNotContainKeywords_returnsFalse() {
    //        // Zero keywords
    //        TaskContainsAssigneePredicate predicate = new TaskContainsAssigneePredicate(Collections.emptyList());
    //        assertFalse(predicate.test(new TaskBuilder().withTitle("Meeting").build()));
    //
    //        // Non-matching keyword for both title and description
    //        predicate = new TaskContainsAssigneePredicate(Arrays.asList("Proposal", "CS3243"));
    //        assertFalse(predicate.test(new TaskBuilder().withTitle("Meeting").withDescription("CS1101").build()));
    //    }
}

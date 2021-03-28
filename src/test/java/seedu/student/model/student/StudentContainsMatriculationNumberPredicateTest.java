package seedu.student.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.student.testutil.StudentBuilder;

public class StudentContainsMatriculationNumberPredicateTest {

    @Test
    public void equals() {
        MatriculationNumber firstMatriculationNumberPredicate = new MatriculationNumber("A7890123H");
        MatriculationNumber secondMatriculationNumberPredicate = new MatriculationNumber("A0284765R");

        StudentContainsMatriculationNumberPredicate firstPredicate =
                new StudentContainsMatriculationNumberPredicate(firstMatriculationNumberPredicate);
        StudentContainsMatriculationNumberPredicate secondPredicate =
                new StudentContainsMatriculationNumberPredicate(secondMatriculationNumberPredicate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentContainsMatriculationNumberPredicate firstPredicateCopy =
                new StudentContainsMatriculationNumberPredicate(firstMatriculationNumberPredicate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_matriculationNumberContainsKeywords_returnsTrue() {

        // Matching keyword
        StudentContainsMatriculationNumberPredicate predicate =
                new StudentContainsMatriculationNumberPredicate(new MatriculationNumber("A9012345J"));
        assertTrue(predicate.test(new StudentBuilder().withName("George Best").withMatric("A9012345J").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {

        // Zero matriculation number
        StudentContainsMatriculationNumberPredicate predicate =
                new StudentContainsMatriculationNumberPredicate(new MatriculationNumber("A1234245T"));
        assertFalse(predicate.test(new StudentBuilder().withMatric("A0984245T").build()));

        // Non-matching matriculation number
        predicate = new StudentContainsMatriculationNumberPredicate(new MatriculationNumber("A0234673E"));
        assertFalse(predicate.test(new StudentBuilder().withMatric("A0394852T").build()));

        /*
        // Matriculation number that includes  phone, email and address
        predicate = new StudentContainsMatriculationNumberPredicate(new MatriculationNumber("A3458911D" +
                "12345 alice@email.com Main Street)"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withMatric("A3456789D").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));

         */
    }
}

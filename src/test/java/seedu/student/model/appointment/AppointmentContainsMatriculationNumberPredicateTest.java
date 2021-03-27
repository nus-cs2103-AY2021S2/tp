package seedu.student.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.student.model.student.MatriculationNumber;

public class AppointmentContainsMatriculationNumberPredicateTest {

    @Test
    public void equals() {
        MatriculationNumber firstMatriculationNumberPredicate = new MatriculationNumber("A0987234T");
        MatriculationNumber secondMatriculationNumberPredicate = new MatriculationNumber("A1234567Y");

        AppointmentContainsMatriculationNumberPredicate firstPredicate =
                new AppointmentContainsMatriculationNumberPredicate(firstMatriculationNumberPredicate);
        AppointmentContainsMatriculationNumberPredicate secondPredicate =
                new AppointmentContainsMatriculationNumberPredicate(secondMatriculationNumberPredicate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AppointmentContainsMatriculationNumberPredicate firstPredicateCopy =
                new AppointmentContainsMatriculationNumberPredicate(firstMatriculationNumberPredicate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    /*@Test
    public void test_matriculationNumberContainsKeywords_returnsTrue() {

        AppointmentContainsMatriculationNumberPredicate predicate =
                new AppointmentContainsMatriculationNumberPredicate(new MatriculationNumber("A9012345J"));
       //assertTrue(predicate.test(new AppointmentBuilder().withDate()));
    }

   */

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {

      /* Zero matriculation numbeer
        AppointmentContainsMatriculationNumberPredicate predicate =
                new AppointmentContainsMatriculationNumberPredicate(new MatriculationNumber(""));
        // assertFalse(predicate.test(new StudentBuilder().withMatric("A0984245T").build()));

        // Non-matching matriculation number
        predicate = new AppointmentContainsMatriculationNumberPredicate(new MatriculationNumber("A0234673E"));
        //  assertFalse(predicate.test(new StudentBuilder().withMatric("A0394852T").build()));

        // Matriculation number that includes  phone, email and address
        predicate = new AppointmentContainsMatriculationNumberPredicate(new MatriculationNumber("A3458911D  " +
                "12345 alice@email.com Main Street)"));
     /*   assertFalse(predicate.test(new StudentBuilder().withName("Alice").withMatric("A3456789D").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

      */
    }
}

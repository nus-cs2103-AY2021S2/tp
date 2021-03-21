package seedu.address.model.tuition;

import org.junit.jupiter.api.Test;
import seedu.address.model.session.Session;
import seedu.address.testutil.SessionBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

class TuitionTest {

    @Test
    void getStudent() {
        Session exampleSession = new SessionBuilder().build();
        Tuition aliceInTuition = new Tuition(ALICE, exampleSession, 0 , 0);
        assertEquals(aliceInTuition.getStudent(), ALICE);

        assertNotEquals(aliceInTuition.getStudent(), BOB);

        assertNotEquals(aliceInTuition.getStudent(), null);
    }

    @Test
    void getSession() {
        Session exampleSession = new SessionBuilder().build();
        Tuition bobInTuition = new Tuition(BOB, exampleSession, 0, 0);
        assertEquals(bobInTuition.getSession(), exampleSession);

        Session tenMinuteSession = new SessionBuilder().withDuration("10").build();
        assertNotEquals(bobInTuition.getSession(), tenMinuteSession);

        assertNotEquals(bobInTuition.getSession(), null);
    }

    @Test
    void getSessionIndex() {
    }

    @Test
    void getStudentIndex() {
    }

    @Test
    void testEquals() {
    }
}
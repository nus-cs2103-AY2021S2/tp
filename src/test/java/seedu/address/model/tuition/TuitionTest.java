package seedu.address.model.tuition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.session.Session;
import seedu.address.model.student.Student;
import seedu.address.testutil.SessionBuilder;
import seedu.address.testutil.StudentBuilder;

class TuitionTest {

    private static final Session exampleSession = new SessionBuilder().build();

    @Test
    void getStudent() {
        Tuition aliceInTuition = new Tuition(ALICE, exampleSession, 0 , 0);
        assertEquals(aliceInTuition.getStudent(), ALICE);

        assertNotEquals(aliceInTuition.getStudent(), BOB);

        assertNotEquals(aliceInTuition.getStudent(), null);
    }

    @Test
    void getSession() {
        Tuition bobInTuition = new Tuition(BOB, exampleSession, 0, 0);
        assertEquals(bobInTuition.getSession(), exampleSession);

        Session tenMinuteSession = new SessionBuilder().withDuration("10").build();
        assertNotEquals(bobInTuition.getSession(), tenMinuteSession);

        assertNotEquals(bobInTuition.getSession(), null);
    }

    @Test
    void getSessionIndex() {
        Tuition aliceInTuition = new Tuition(ALICE, exampleSession, 1, 2);
        assertEquals(aliceInTuition.getSessionIndex(), 3);

        assertNotEquals(aliceInTuition.getSessionIndex(), 2);
    }

    @Test
    void getStudentIndex() {
        Tuition bobInTuition = new Tuition(BOB, exampleSession, 3, 4);
        assertEquals(bobInTuition.getStudentIndex(), 4);

        assertNotEquals(bobInTuition.getStudentIndex(), 5);
    }

    @Test
    void testEquals() {
        Tuition aliceInTuition = new Tuition(ALICE, exampleSession, 0, 0);
        Tuition copyAliceInTuition = new Tuition(ALICE, exampleSession, 0, 0);
        assertEquals(aliceInTuition, copyAliceInTuition);

        assertNotEquals(aliceInTuition, null);

        Tuition bobInTuition = new Tuition(BOB, exampleSession, 0, 0);
        assertNotEquals(bobInTuition, aliceInTuition);

        Student edittedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        Tuition edittedAliceInTuition = new Tuition(edittedAlice, exampleSession, 0, 0);
        assertNotEquals(edittedAliceInTuition, aliceInTuition);


    }

}

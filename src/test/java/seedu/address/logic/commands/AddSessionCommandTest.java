package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.session.Session;
import seedu.address.model.student.Student;
import seedu.address.testutil.SessionBuilder;
import seedu.address.testutil.StudentBuilder;


/**
 * Test cases are a mixture of unit and integration tests
 */
public class AddSessionCommandTest {

    // unit tests
    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSessionCommand(null, null));
        Session session = new SessionBuilder().build();
        assertThrows(NullPointerException.class, () -> new AddSessionCommand(session, null));
        assertThrows(NullPointerException.class, () -> new AddSessionCommand(null, ALICE.getName()));
    }

    // integrated tests
    @Test
    public void execute_newSessionThatAlreadyExists_throwsCommandException() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        Student validStudent = new StudentBuilder().build();
        Session validSession = new SessionBuilder().build();

        validStudent.addSession(validSession);
        model.addStudent(validStudent);

        AddSessionCommand addSessionCommand = new AddSessionCommand(validSession, validStudent.getName());
        assertThrows(CommandException.class, () -> addSessionCommand.execute(model));
    }

    @Test
    public void execute_studentWithNoSuchName_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Session validSession = new SessionBuilder().build();

        AddSessionCommand addSessionCommand = new AddSessionCommand(validSession, ALICE.getName());
        assertThrows(CommandException.class, () -> addSessionCommand.execute(model));
    }

    @Test
    public void equals() {
        Session session = new SessionBuilder().build();
        Session anotherSession = new SessionBuilder().withSessionDate("2020-12-12", "10:00").build();
        AddSessionCommand addSessionCommand = new AddSessionCommand(session, ALICE.getName());
        AddSessionCommand anotherAddSessionCommand = new AddSessionCommand(anotherSession, BOB.getName());

        // same command -> returns true
        assertTrue(addSessionCommand.equals(addSessionCommand));
        assertTrue(anotherAddSessionCommand.equals(anotherAddSessionCommand));

        // different types -> returns false
        assertFalse(addSessionCommand.equals(1));

        // null -> returns false
        assertFalse(addSessionCommand.equals(null));

        // different add session command -> returns false
        assertFalse(addSessionCommand.equals(anotherAddSessionCommand));
    }


}

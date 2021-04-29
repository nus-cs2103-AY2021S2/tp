package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudents.getTypicalStudents;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EmailCommand.
 */
public class EmailCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_noStudent_throwsCommandException() {
        model.updateFilteredStudentList(p -> false);
        EmailCommand emailCommand = new EmailCommand();
        assertThrows(CommandException.class, () -> emailCommand.execute(model));
    }

    @Test
    public void execute_duplicateEmail_filtered() throws CommandException {
        model.updateFilteredStudentList(p -> false);
        model.addStudent(TypicalStudents.CHIARA);
        model.addStudent(TypicalStudents.CHARLENE);

        EmailCommand emailCommand = new EmailCommand();
        String feedback = emailCommand.execute(model).getFeedbackToUser();
        String[] emailsFromFeedback = feedback.split(";");
        Set<String> uniqueEmails = new HashSet<String>(Arrays.asList(emailsFromFeedback));

        // if valid: total number of emails should be size of typical students + 1 (chiara & charlene shares one email)
        assertEquals(uniqueEmails.size(), getTypicalStudents().size() + 1);
    }

    @Test
    public void execute_getEmails_success() throws CommandException {
        EmailCommand emailCommand = new EmailCommand();
        CommandResult commandResult = emailCommand.execute(model);
        String[] emails = commandResult.getFeedbackToUser().split(";");
        List<Student> typicalStudents = getTypicalStudents();

        // first layer: check if length matches
        assertEquals(emails.length, typicalStudents.size());

        // second layer: check if all emails are in the response
        for (Student student : typicalStudents) {
            assertTrue(Arrays.asList(emails).contains(student.getEmail().toString()));
        }

        // third layer: check final message returned to user
        String intendedMessage = Arrays.stream(emails)
                .reduce("", (accumulatedEmails, currentEmail) -> accumulatedEmails + currentEmail + ";");
        assertCommandSuccess(new EmailCommand(), model, intendedMessage, model);
    }
}

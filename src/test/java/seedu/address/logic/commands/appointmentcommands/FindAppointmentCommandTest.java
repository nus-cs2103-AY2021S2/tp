package seedu.address.logic.commands.appointmentcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.MATHS_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.SCIENCE_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.NamePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindAppointmentCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalAppointmentBook(), getTypicalGradeBook());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalAppointmentBook(), model.getGradeBook());

    @Test
    public void equals() {
        NamePredicate firstPredicate =
                new NamePredicate(Collections.singletonList("first"));
        NamePredicate secondPredicate =
                new NamePredicate(Collections.singletonList("second"));

        FindAppointmentCommand findFirstCommand = new FindAppointmentCommand(firstPredicate);
        FindAppointmentCommand findSecondCommand = new FindAppointmentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAppointmentCommand findFirstCommandCopy = new FindAppointmentCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW, 0);
        NamePredicate predicate = preparePredicate(" ");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }


    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW, 2);
        NamePredicate predicate = preparePredicate("Alice John");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MATHS_APPOINTMENT, SCIENCE_APPOINTMENT), model.getFilteredAppointmentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NamePredicate preparePredicate(String userInput) {
        return new NamePredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

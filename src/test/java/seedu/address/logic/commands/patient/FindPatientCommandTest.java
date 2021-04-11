package seedu.address.logic.commands.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PATIENTS_FOUND_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppObjects.CARL;
import static seedu.address.testutil.TypicalAppObjects.ELLE;
import static seedu.address.testutil.TypicalAppObjects.FIONA;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.patient.FindPatientCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentSchedule;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPatientCommand}.
 */
public class FindPatientCommandTest {
    private Model model = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
            getTypicalAppointmentSchedule(), new UserPrefs());

    private Model expectedModel = new ModelManager(
        new AddressBook<>(getTypicalPatientRecords()),
        new AddressBook<>(getTypicalDoctorRecords()),
        new AppointmentSchedule(getTypicalAppointmentSchedule()),
        new UserPrefs()
    );

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPatientCommand findFirstCommand = new FindPatientCommand(firstPredicate);
        FindPatientCommand findSecondCommand = new FindPatientCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPatientCommand findFirstCommandCopy = new FindPatientCommand(firstPredicate);
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
        String expectedMessage = String.format(MESSAGE_PATIENTS_FOUND_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindPatientCommand command = new FindPatientCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPatientList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_FOUND_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindPatientCommand command = new FindPatientCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPatientList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

package seedu.address.logic.commands.doctor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DOCTORS_FOUND_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppObjects.DR_GREY;
import static seedu.address.testutil.TypicalAppObjects.DR_STRANGE;
import static seedu.address.testutil.TypicalAppObjects.DR_WHO;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentSchedule;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindDoctorCommand}.
 */
public class FindDoctorCommandTest {
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

        FindDoctorCommand findFirstCommand = new FindDoctorCommand(firstPredicate);
        FindDoctorCommand findSecondCommand = new FindDoctorCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindDoctorCommand findFirstCommandCopy = new FindDoctorCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noDoctorFound() {
        String expectedMessage = String.format(MESSAGE_DOCTORS_FOUND_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindDoctorCommand command = new FindDoctorCommand(predicate);
        expectedModel.updateFilteredDoctorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDoctorList());
    }

    @Test
    public void execute_multipleKeywords_multipleDoctorsFound() {
        String expectedMessage = String.format(MESSAGE_DOCTORS_FOUND_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Grey Who Strange");
        FindDoctorCommand command = new FindDoctorCommand(predicate);
        expectedModel.updateFilteredDoctorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DR_GREY, DR_WHO, DR_STRANGE), model.getFilteredDoctorList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

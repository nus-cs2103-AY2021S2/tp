package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class PolicyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredListNoPolicies_success() {
        Person personPoliciesToDisplay = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        PolicyCommand policyCommand = new PolicyCommand(INDEX_THIRD_PERSON);

        String expectedMessage = String.format(PolicyCommand.NO_POLICIES, personPoliciesToDisplay.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(policyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredListWithPolicies_success() {
        Person personPoliciesToDisplay = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PolicyCommand policyCommand = new PolicyCommand(INDEX_FIRST_PERSON);

        String expectedMessage = personPoliciesToDisplay.getPersonNameAndAllPoliciesInString();

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(policyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PolicyCommand policyCommand = new PolicyCommand(outOfBoundIndex);

        assertCommandFailure(policyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredListNoPolicies_success() {
        showPersonAtIndex(model, INDEX_THIRD_PERSON);

        Person personPoliciesToDisplay = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PolicyCommand policyCommand = new PolicyCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(PolicyCommand.NO_POLICIES, personPoliciesToDisplay.getName());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        // there should be no change to the model, since this is not a command which modifies the {@code model}
        showPersonAtIndex(expectedModel, INDEX_THIRD_PERSON);

        assertCommandSuccess(policyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredListWithPolicies_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person personPoliciesToDisplay = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PolicyCommand policyCommand = new PolicyCommand(INDEX_FIRST_PERSON);

        String expectedMessage = personPoliciesToDisplay.getPersonNameAndAllPoliciesInString();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        // there should be no change to the model, since this is not a command which modifies the {@code model}
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);

        assertCommandSuccess(policyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        PolicyCommand policyCommand = new PolicyCommand(outOfBoundIndex);

        assertCommandFailure(policyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        PolicyCommand policyFirstCommand = new PolicyCommand(INDEX_FIRST_PERSON);
        PolicyCommand policySecondCommand = new PolicyCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(policyFirstCommand.equals(policyFirstCommand));

        // same values -> returns true
        PolicyCommand policyFirstCommandCopy = new PolicyCommand(INDEX_FIRST_PERSON);
        assertTrue(policyFirstCommand.equals(policyFirstCommandCopy));

        // different types -> returns false
        assertFalse(policyFirstCommand.equals(1));

        // null -> returns false
        assertFalse(policyFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(policyFirstCommand.equals(policySecondCommand));
    }

}

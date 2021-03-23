package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DetailCommand}.
 */
public class DetailCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DetailCommand detailCommand = new DetailCommand(outOfBoundIndex);

        assertCommandFailure(detailCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDisplay = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DetailCommand detailCommand = new DetailCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DetailCommand.MESSAGE_DISPLAY_PERSON_SUCCESS, personToDisplay);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person expectedPersonToDisplay = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        assertCommandSuccess(detailCommand, model, expectedMessage, expectedPersonToDisplay);
    }

}

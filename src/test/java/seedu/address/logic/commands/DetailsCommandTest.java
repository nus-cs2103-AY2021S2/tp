package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TestDataUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.DetailsPanelTab;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class DetailsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validInputsUnfilteredList_success() {
        Person personToDisplay = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(DetailsCommand.MESSAGE_DETAILS_SUCCESS, personToDisplay.getName());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, DetailsPanelTab.PERSON_DETAILS);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateDetailedPerson(personToDisplay);

        DetailsCommand detailsCommand = new DetailsCommand(INDEX_FIRST_PERSON);

        assertCommandSuccess(detailsCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DetailsCommand detailsCommand = new DetailsCommand(outOfBoundIndex);

        assertCommandFailure(detailsCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validInputsFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person personToDisplay = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(DetailsCommand.MESSAGE_DETAILS_SUCCESS, personToDisplay.getName());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, DetailsPanelTab.PERSON_DETAILS);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateDetailedPerson(personToDisplay);
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);

        DetailsCommand detailsCommand = new DetailsCommand(INDEX_FIRST_PERSON);

        assertCommandSuccess(detailsCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        DetailsCommand detailsCommand = new DetailsCommand(INDEX_SECOND_PERSON);

        assertCommandFailure(detailsCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}

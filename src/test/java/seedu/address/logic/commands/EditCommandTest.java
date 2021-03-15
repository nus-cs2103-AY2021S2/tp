package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_RESIDENCE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CLEAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_UNCLEAN;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showResidenceAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RESIDENCE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditResidenceDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ResidenceTracker;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.residence.Residence;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditResidenceDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ResidenceBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Residence editedResidence = new ResidenceBuilder().build();
        EditResidenceDescriptor descriptor = new EditResidenceDescriptorBuilder(editedResidence).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_RESIDENCE, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RESIDENCE_SUCCESS, editedResidence);

        Model expectedModel = new ModelManager(new ResidenceTracker(model.getResidenceTracker()), new UserPrefs());
        expectedModel.setResidence(model.getFilteredResidenceList().get(0), editedResidence);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastResidence = Index.fromOneBased(model.getFilteredResidenceList().size());
        Residence lastResidence = model.getFilteredResidenceList().get(indexLastResidence.getZeroBased());

        ResidenceBuilder residenceInList = new ResidenceBuilder(lastResidence);
        Residence editedResidence = residenceInList.withName(VALID_NAME_RESIDENCE1).withCleanStatusTags(VALID_TAG_CLEAN).build();

        EditResidenceDescriptor descriptor = new EditResidenceDescriptorBuilder().withName(VALID_NAME_RESIDENCE1)
                .withCleanStatusTags(VALID_TAG_UNCLEAN).build();


        EditCommand editCommand = new EditCommand(indexLastResidence, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RESIDENCE_SUCCESS, editedResidence);

        Model expectedModel = new ModelManager(new ResidenceTracker(model.getResidenceTracker()), new UserPrefs());
        expectedModel.setResidence(lastResidence, editedResidence);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_RESIDENCE, new EditResidenceDescriptor());
        Residence editedResidence = model.getFilteredResidenceList().get(INDEX_FIRST_RESIDENCE.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RESIDENCE_SUCCESS, editedResidence);

        Model expectedModel = new ModelManager(new ResidenceTracker(model.getResidenceTracker()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showResidenceAtIndex(model, INDEX_FIRST_RESIDENCE);

        Residence residenceInFilteredList = model.getFilteredResidenceList().get(INDEX_FIRST_RESIDENCE.getZeroBased());
        Residence editedResidence = new ResidenceBuilder(residenceInFilteredList).withName(VALID_NAME_RESIDENCE1).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_RESIDENCE,
                new EditResidenceDescriptorBuilder().withName(VALID_NAME_RESIDENCE1).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RESIDENCE_SUCCESS, editedResidence);

        Model expectedModel = new ModelManager(new ResidenceTracker(model.getResidenceTracker()), new UserPrefs());
        expectedModel.setResidence(model.getFilteredResidenceList().get(0), editedResidence);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getResidenceTracker().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResidenceTracker().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}

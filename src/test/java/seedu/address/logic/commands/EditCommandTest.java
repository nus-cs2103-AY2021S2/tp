package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGarmentAtIndex;
import static seedu.address.testutil.TypicalGarments.getTypicalWardrobe;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GARMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GARMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditGarmentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.Wardrobe;
import seedu.address.model.garment.Garment;
import seedu.address.testutil.EditGarmentDescriptorBuilder;
import seedu.address.testutil.GarmentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalWardrobe(), new UserPrefs());

    /*
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Garment editedGarment = new GarmentBuilder().build(); //Potential issue from LastUse
        EditGarmentDescriptor descriptor = new EditGarmentDescriptorBuilder(editedGarment).build(); //Potential issue from LastUse
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GARMENT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_GARMENT_SUCCESS, editedGarment);

        Model expectedModel = new ModelManager(new Wardrobe(model.getWardrobe()), new UserPrefs());
        expectedModel.setGarment(model.getFilteredGarmentList().get(0), editedGarment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
    */

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastGarment = Index.fromOneBased(model.getFilteredGarmentList().size());
        Garment lastGarment = model.getFilteredGarmentList().get(indexLastGarment.getZeroBased());

        GarmentBuilder garmentInList = new GarmentBuilder(lastGarment);
        Garment editedGarment = garmentInList.withName(VALID_NAME_BOB).withSize(VALID_SIZE_BOB)
                .withDescriptions(VALID_DESCRIPTION_HUSBAND).build();

        EditGarmentDescriptor descriptor = new EditGarmentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withSize(VALID_SIZE_BOB).withDescriptions(VALID_DESCRIPTION_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastGarment, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_GARMENT_SUCCESS, editedGarment);

        Model expectedModel = new ModelManager(new Wardrobe(model.getWardrobe()), new UserPrefs());
        expectedModel.setGarment(lastGarment, editedGarment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GARMENT, new EditGarmentDescriptor());
        Garment editedGarment = model.getFilteredGarmentList().get(INDEX_FIRST_GARMENT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_GARMENT_SUCCESS, editedGarment);

        Model expectedModel = new ModelManager(new Wardrobe(model.getWardrobe()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showGarmentAtIndex(model, INDEX_FIRST_GARMENT);

        Garment garmentInFilteredList = model.getFilteredGarmentList().get(INDEX_FIRST_GARMENT.getZeroBased());
        Garment editedGarment = new GarmentBuilder(garmentInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GARMENT,
                new EditGarmentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_GARMENT_SUCCESS, editedGarment);

        Model expectedModel = new ModelManager(new Wardrobe(model.getWardrobe()), new UserPrefs());
        expectedModel.setGarment(model.getFilteredGarmentList().get(0), editedGarment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateGarmentUnfilteredList_failure() {
        Garment firstGarment = model.getFilteredGarmentList().get(INDEX_FIRST_GARMENT.getZeroBased());
        EditGarmentDescriptor descriptor = new EditGarmentDescriptorBuilder(firstGarment).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_GARMENT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_GARMENT);
    }

    @Test
    public void execute_duplicateGarmentFilteredList_failure() {
        showGarmentAtIndex(model, INDEX_FIRST_GARMENT);

        // edit garment in filtered list into a duplicate in wardrobe
        Garment garmentInList = model.getWardrobe().getGarmentList().get(INDEX_SECOND_GARMENT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_GARMENT,
                new EditGarmentDescriptorBuilder(garmentInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_GARMENT);
    }

    @Test
    public void execute_invalidGarmentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGarmentList().size() + 1);
        EditGarmentDescriptor descriptor = new EditGarmentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_GARMENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of wardrobe
     */
    @Test
    public void execute_invalidGarmentIndexFilteredList_failure() {
        showGarmentAtIndex(model, INDEX_FIRST_GARMENT);
        Index outOfBoundIndex = INDEX_SECOND_GARMENT;
        // ensures that outOfBoundIndex is still in bounds of wardrobe list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWardrobe().getGarmentList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditGarmentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_GARMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_GARMENT, DESC_AMY);

        // same values -> returns true
        EditGarmentDescriptor copyDescriptor = new EditGarmentDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_GARMENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_GARMENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_GARMENT, DESC_BOB)));
    }

}

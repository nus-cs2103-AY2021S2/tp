package seedu.smartlib.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartlib.logic.commands.CommandTestUtil.showReaderAtIndex;
import static seedu.smartlib.testutil.TypicalIndexes.INDEX_FIRST_READER;
import static seedu.smartlib.testutil.TypicalIndexes.INDEX_SECOND_READER;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.Messages;
import seedu.smartlib.commons.core.index.Index;
import seedu.smartlib.logic.commands.EditCommand.EditReaderDescriptor;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.UserPrefs;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.testutil.EditReaderDescriptorBuilder;
import seedu.smartlib.testutil.ReaderBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalSmartLib(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Reader editedReader = new ReaderBuilder().build();
        EditCommand.EditReaderDescriptor descriptor = new EditReaderDescriptorBuilder(editedReader).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_READER, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_READER_SUCCESS, editedReader);

        Model expectedModel = new ModelManager(new SmartLib(model.getSmartLib()), new UserPrefs());
        expectedModel.setReader(model.getFilteredReaderList().get(0), editedReader);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastReader = Index.fromOneBased(model.getFilteredReaderList().size());
        Reader lastReader = model.getFilteredReaderList().get(indexLastReader.getZeroBased());

        ReaderBuilder readerInList = new ReaderBuilder(lastReader);
        Reader editedReader = readerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditCommand.EditReaderDescriptor descriptor = new EditReaderDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastReader, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_READER_SUCCESS, editedReader);

        Model expectedModel = new ModelManager(new SmartLib(model.getSmartLib()), new UserPrefs());
        expectedModel.setReader(lastReader, editedReader);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_READER, new EditCommand.EditReaderDescriptor());
        Reader editedReader = model.getFilteredReaderList().get(INDEX_FIRST_READER.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_READER_SUCCESS, editedReader);

        Model expectedModel = new ModelManager(new SmartLib(model.getSmartLib()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showReaderAtIndex(model, INDEX_FIRST_READER);

        Reader readerInFilteredList = model.getFilteredReaderList().get(INDEX_FIRST_READER.getZeroBased());
        Reader editedReader = new ReaderBuilder(readerInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_READER,
                new EditReaderDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_READER_SUCCESS, editedReader);

        Model expectedModel = new ModelManager(new SmartLib(model.getSmartLib()), new UserPrefs());
        expectedModel.setReader(model.getFilteredReaderList().get(0), editedReader);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateReaderUnfilteredList_failure() {
        Reader firstReader = model.getFilteredReaderList().get(INDEX_FIRST_READER.getZeroBased());
        EditCommand.EditReaderDescriptor descriptor = new EditReaderDescriptorBuilder(firstReader).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_READER, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_READER);
    }

    @Test
    public void execute_duplicateReaderFilteredList_failure() {
        showReaderAtIndex(model, INDEX_FIRST_READER);

        // edit reader in filtered list into a duplicate in SmartLib
        Reader readerInList = model.getSmartLib().getReaderList().get(INDEX_SECOND_READER.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_READER,
                new EditReaderDescriptorBuilder(readerInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_READER);
    }

    @Test
    public void execute_invalidReaderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredReaderList().size() + 1);
        EditCommand.EditReaderDescriptor descriptor = new EditReaderDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_READER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of smartLib
     */
    @Test
    public void execute_invalidReaderIndexFilteredList_failure() {
        showReaderAtIndex(model, INDEX_FIRST_READER);
        Index outOfBoundIndex = INDEX_SECOND_READER;
        // ensures that outOfBoundIndex is still in bounds of SmartLib list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSmartLib().getReaderList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditReaderDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_READER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_READER, DESC_AMY);

        // same values -> returns true
        EditCommand.EditReaderDescriptor copyDescriptor = new EditReaderDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_READER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_READER, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_READER, DESC_BOB)));
    }

}

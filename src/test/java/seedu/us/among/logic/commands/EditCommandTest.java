package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.us.among.commons.core.Messages;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.model.EndpointList;
import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.testutil.EditEndpointDescriptorBuilder;
import seedu.us.among.testutil.EndpointBuilder;
import seedu.us.among.testutil.TypicalIndexes;
import seedu.us.among.testutil.TypicalEndpoints;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(TypicalEndpoints.getTypicalEndpointList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Endpoint editedEndpoint = new EndpointBuilder().build();
        EditCommand.EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder(editedEndpoint).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ENDPOINT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENDPOINT_SUCCESS, editedEndpoint);

        Model expectedModel = new ModelManager(new EndpointList(model.getEndpointList()), new UserPrefs());
        expectedModel.setEndpoint(model.getFilteredEndpointList().get(0), editedEndpoint);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEndpoint = Index.fromOneBased(model.getFilteredEndpointList().size());
        Endpoint lastEndpoint = model.getFilteredEndpointList().get(indexLastEndpoint.getZeroBased());

        EndpointBuilder endpointInList = new EndpointBuilder(lastEndpoint);
        Endpoint editedEndpoint = endpointInList.withName(CommandTestUtil.VALID_NAME_BOB).withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();

        EditCommand.EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastEndpoint, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENDPOINT_SUCCESS, editedEndpoint);

        Model expectedModel = new ModelManager(new EndpointList(model.getEndpointList()), new UserPrefs());
        expectedModel.setEndpoint(lastEndpoint, editedEndpoint);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ENDPOINT, new EditCommand.EditEndpointDescriptor());
        Endpoint editedEndpoint = model.getFilteredEndpointList().get(TypicalIndexes.INDEX_FIRST_ENDPOINT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENDPOINT_SUCCESS, editedEndpoint);

        Model expectedModel = new ModelManager(new EndpointList(model.getEndpointList()), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showEndpointAtIndex(model, TypicalIndexes.INDEX_FIRST_ENDPOINT);

        Endpoint endpointInFilteredList = model.getFilteredEndpointList().get(TypicalIndexes.INDEX_FIRST_ENDPOINT.getZeroBased());
        Endpoint editedEndpoint = new EndpointBuilder(endpointInFilteredList).withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ENDPOINT,
                new EditEndpointDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENDPOINT_SUCCESS, editedEndpoint);

        Model expectedModel = new ModelManager(new EndpointList(model.getEndpointList()), new UserPrefs());
        expectedModel.setEndpoint(model.getFilteredEndpointList().get(0), editedEndpoint);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEndpointUnfilteredList_failure() {
        Endpoint firstEndpoint = model.getFilteredEndpointList().get(TypicalIndexes.INDEX_FIRST_ENDPOINT.getZeroBased());
        EditCommand.EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder(firstEndpoint).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SECOND_ENDPOINT, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENDPOINT);
    }

    @Test
    public void execute_duplicateEndpointFilteredList_failure() {
        CommandTestUtil.showEndpointAtIndex(model, TypicalIndexes.INDEX_FIRST_ENDPOINT);

        // edit endpoint in filtered list into a duplicate in the API endpoint list
        Endpoint endpointInList = model.getEndpointList().getEndpointList().get(TypicalIndexes.INDEX_SECOND_ENDPOINT.getZeroBased());
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ENDPOINT,
                new EditEndpointDescriptorBuilder(endpointInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENDPOINT);
    }

    @Test
    public void execute_invalidEndpointIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEndpointList().size() + 1);
        EditCommand.EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of the API endpoint list
     */
    @Test
    public void execute_invalidEndpointIndexFilteredList_failure() {
        CommandTestUtil.showEndpointAtIndex(model, TypicalIndexes.INDEX_FIRST_ENDPOINT);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_ENDPOINT;
        // ensures that outOfBoundIndex is still in bounds of API endpoint list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEndpointList().getEndpointList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditEndpointDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ENDPOINT, CommandTestUtil.DESC_AMY);

        // same values -> returns true
        EditCommand.EditEndpointDescriptor copyDescriptor = new EditCommand.EditEndpointDescriptor(CommandTestUtil.DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(TypicalIndexes.INDEX_FIRST_ENDPOINT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_SECOND_ENDPOINT, CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_FIRST_ENDPOINT, CommandTestUtil.DESC_BOB)));
    }

}

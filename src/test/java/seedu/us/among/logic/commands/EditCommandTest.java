package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_ERROR;
import static seedu.us.among.logic.commands.CommandTestUtil.DESC_GET;
import static seedu.us.among.logic.commands.CommandTestUtil.DESC_POST;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_RANDOM;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_DATA_PAIR;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_HEADER_PAIR;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_METHOD_POST;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_CAT;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_COOL;
import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.us.among.logic.commands.CommandTestUtil.assertEditCommandSuccess;
import static seedu.us.among.logic.commands.CommandTestUtil.showEndpointAtIndex;
import static seedu.us.among.testutil.TypicalEndpoints.getTypicalEndpointList;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_FIRST_ENDPOINT;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_SECOND_ENDPOINT;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.us.among.commons.core.Messages;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.logic.commands.EditCommand.EditEndpointDescriptor;
import seedu.us.among.model.EndpointList;
import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.testutil.EditEndpointDescriptorBuilder;
import seedu.us.among.testutil.EndpointBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalEndpointList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Endpoint editedEndpoint = new EndpointBuilder()
                .withMethod(VALID_METHOD_POST)
                .withAddress(VALID_ADDRESS_RANDOM)
                .withData(VALID_DATA_PAIR)
                .withHeaders(VALID_HEADER_PAIR)
                .withTags(VALID_TAG_COOL)
                .build();
        EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder(editedEndpoint).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENDPOINT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENDPOINT_SUCCESS, editedEndpoint);

        Model expectedModel = new ModelManager(new EndpointList(model.getEndpointList()), new UserPrefs());
        expectedModel.setEndpoint(model.getFilteredEndpointList().get(0), editedEndpoint);

        assertEditCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEndpoint = Index.fromOneBased(model.getFilteredEndpointList().size());
        Endpoint lastEndpoint = model.getFilteredEndpointList().get(indexLastEndpoint.getZeroBased());

        EndpointBuilder endpointInList = new EndpointBuilder(lastEndpoint);
        Endpoint editedEndpoint = endpointInList.withMethod(VALID_METHOD_POST).withTags(VALID_TAG_CAT).build();

        EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder().withMethod(VALID_METHOD_POST)
                .withTags(VALID_TAG_CAT).build();
        EditCommand editCommand = new EditCommand(indexLastEndpoint, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENDPOINT_SUCCESS, editedEndpoint);

        Model expectedModel = new ModelManager(new EndpointList(model.getEndpointList()), new UserPrefs());
        expectedModel.setEndpoint(lastEndpoint, editedEndpoint);

        assertEditCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENDPOINT, new EditEndpointDescriptor());
        Endpoint editedEndpoint = model.getFilteredEndpointList().get(INDEX_FIRST_ENDPOINT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENDPOINT_SUCCESS, editedEndpoint);

        Model expectedModel = new ModelManager(new EndpointList(model.getEndpointList()), new UserPrefs());

        assertEditCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showEndpointAtIndex(model, INDEX_FIRST_ENDPOINT);
        Predicate<Endpoint> filteredModelPred = model.getFilteredPredicate();

        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENDPOINT,
             new EditEndpointDescriptorBuilder().withMethod(VALID_METHOD_POST).build());

        Endpoint endpointInFilteredList = model.getFilteredEndpointList().get(INDEX_FIRST_ENDPOINT.getZeroBased());
        Endpoint editedEndpoint = new EndpointBuilder(endpointInFilteredList).withMethod(VALID_METHOD_POST).build();

        Model expectedModel = new ModelManager(new EndpointList(model.getEndpointList()), new UserPrefs());
        expectedModel.setEndpoint(model.getFilteredEndpointList().get(0), editedEndpoint);
        expectedModel.updateFilteredEndpointList(filteredModelPred);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENDPOINT_SUCCESS, editedEndpoint);
        assertEditCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEndpointUnfilteredList_failure() {
        Endpoint firstEndpoint = model.getFilteredEndpointList().get(INDEX_FIRST_ENDPOINT.getZeroBased());
        EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder(firstEndpoint).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ENDPOINT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENDPOINT);
    }

    @Test
    public void execute_duplicateEndpointFilteredList_failure() {
        showEndpointAtIndex(model, INDEX_FIRST_ENDPOINT);
        // edit endpoint in filtered list into a duplicate in the API endpoint list
        Endpoint endpointInList = model.getEndpointList().getEndpointList()
              .get(INDEX_SECOND_ENDPOINT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENDPOINT,
             new EditEndpointDescriptorBuilder(endpointInList).build());
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENDPOINT);
    }

    @Test
    public void execute_invalidEndpointIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEndpointList().size() + 1);
        EditEndpointDescriptor descriptor = new EditEndpointDescriptorBuilder().withMethod(VALID_METHOD_POST).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        String expectedOutput = String.format(MESSAGE_INVALID_COMMAND_ERROR, Messages.MESSAGE_INDEX_NOT_WITHIN_LIST,
                EditCommand.MESSAGE_USAGE);

        assertCommandFailure(editCommand, model, expectedOutput);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list, but
     * smaller than size of the API endpoint list
     */
    @Test
    public void execute_invalidEndpointIndexFilteredList_failure() {
        showEndpointAtIndex(model, INDEX_FIRST_ENDPOINT);
        Index outOfBoundIndex = INDEX_SECOND_ENDPOINT;
        // ensures that outOfBoundIndex is still in bounds of API endpoint list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEndpointList().getEndpointList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditEndpointDescriptorBuilder().withMethod(VALID_METHOD_POST).build());

        String expectedOutput = String.format(MESSAGE_INVALID_COMMAND_ERROR, Messages.MESSAGE_INDEX_NOT_WITHIN_LIST,
                EditCommand.MESSAGE_USAGE);
        assertCommandFailure(editCommand, model, expectedOutput);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ENDPOINT, DESC_GET);

        // same values -> returns true
        EditEndpointDescriptor copyDescriptor = new EditEndpointDescriptor(DESC_GET);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ENDPOINT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ENDPOINT, DESC_GET)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ENDPOINT, DESC_POST)));
    }

}

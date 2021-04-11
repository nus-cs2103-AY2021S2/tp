package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ROXY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SYLPH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_LEADER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.ColabFolder;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.testutil.GroupmateBuilder;
import seedu.address.testutil.UpdateGroupmateDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UpdateGroupmateCommand.
 */
class UpdateGroupmateCommandTest {

    private Model model = new ModelManager(getTypicalColabFolder(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Index projectIndex = INDEX_FIRST;
        Groupmate editedGroupmate = new GroupmateBuilder().build();
        UpdateGroupmateCommand.UpdateGroupmateDescriptor descriptor = new UpdateGroupmateDescriptorBuilder(
                editedGroupmate
        ).build();
        UpdateGroupmateCommand updateGroupmateCommand = new UpdateGroupmateCommand(
                projectIndex, INDEX_FIRST, descriptor
        );

        String expectedMessage = String.format(UpdateGroupmateCommand.MESSAGE_UPDATE_GROUPMATE_SUCCESS,
                editedGroupmate);

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        expectedModel.getFilteredProjectList().get(projectIndex.getZeroBased())
                .setGroupmate(INDEX_FIRST.getZeroBased(), editedGroupmate);

        assertCommandSuccess(updateGroupmateCommand, model, expectedMessage,
                new ViewProjectAndOverviewUiCommand(projectIndex), expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index projectIndex = INDEX_FIRST;
        Index indexLastGroupmate = Index.fromOneBased(
                model.getFilteredProjectList().get(projectIndex.getZeroBased()).getGroupmates().size()
        );
        Groupmate lastGroupmate = model.getFilteredProjectList().get(projectIndex.getZeroBased())
                .getGroupmate(indexLastGroupmate.getZeroBased());

        GroupmateBuilder groupmateInList = new GroupmateBuilder(lastGroupmate);
        Groupmate editedGroupmate = groupmateInList.withName(VALID_NAME_BOB)
                .withRoles(VALID_ROLE_LEADER).build();

        UpdateGroupmateCommand.UpdateGroupmateDescriptor descriptor = new UpdateGroupmateDescriptorBuilder()
                .withName(VALID_NAME_BOB).withRoles(VALID_ROLE_LEADER).build();
        UpdateGroupmateCommand updateGroupmateCommand = new UpdateGroupmateCommand(INDEX_FIRST,
                indexLastGroupmate, descriptor);

        String expectedMessage = String.format(UpdateGroupmateCommand.MESSAGE_UPDATE_GROUPMATE_SUCCESS,
                editedGroupmate);

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        expectedModel.getFilteredProjectList().get(projectIndex.getZeroBased())
                .setGroupmate(indexLastGroupmate.getZeroBased(), editedGroupmate);

        assertCommandSuccess(updateGroupmateCommand, model, expectedMessage,
                new ViewProjectAndOverviewUiCommand(projectIndex), expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_failure() {
        Index projectIndex = INDEX_FIRST;
        UpdateGroupmateCommand updateGroupmateCommand = new UpdateGroupmateCommand(projectIndex, INDEX_FIRST,
                new UpdateGroupmateCommand.UpdateGroupmateDescriptor());
        Groupmate editedGroupmate = model.getFilteredProjectList().get(projectIndex.getZeroBased()).getGroupmates()
                .get(INDEX_FIRST.getZeroBased());

        assertCommandFailure(updateGroupmateCommand, model, UpdateGroupmateCommand.MESSAGE_UNCHANGED_GROUPMATE);
    }

    @Test
    public void execute_invalidProjectIndex_failure() {
        Index projectIndex = Index.fromOneBased(100);
        UpdateGroupmateCommand updateGroupmateCommand = new UpdateGroupmateCommand(projectIndex, INDEX_FIRST,
                new UpdateGroupmateCommand.UpdateGroupmateDescriptor());

        assertCommandFailure(updateGroupmateCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidGroupmateIndex_failure() {
        Index projectIndex = INDEX_FIRST;
        Index groupmateIndex = Index.fromOneBased(100);
        UpdateGroupmateCommand updateGroupmateCommand = new UpdateGroupmateCommand(projectIndex, groupmateIndex,
                new UpdateGroupmateCommand.UpdateGroupmateDescriptor());

        assertCommandFailure(updateGroupmateCommand, model, Messages.MESSAGE_INVALID_GROUPMATE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateGroupmate_failure() {
        Index projectIndex = INDEX_FIRST;
        Index groupmateIndex = INDEX_FIRST;
        Groupmate updatedGroupmate = model.getFilteredProjectList().get(projectIndex.getZeroBased())
                .getGroupmate(INDEX_SECOND.getZeroBased());
        UpdateGroupmateCommand.UpdateGroupmateDescriptor updatedGroupmateDescriptor = new UpdateGroupmateCommand
                .UpdateGroupmateDescriptor();
        updatedGroupmateDescriptor.setName(updatedGroupmate.getName());
        UpdateGroupmateCommand updateGroupmateCommand = new UpdateGroupmateCommand(projectIndex, groupmateIndex,
                updatedGroupmateDescriptor);

        assertCommandFailure(updateGroupmateCommand, model, UpdateGroupmateCommand.MESSAGE_DUPLICATE_GROUPMATE);
    }

    @Test
    public void equals() {
        final UpdateGroupmateCommand standardCommand = new UpdateGroupmateCommand(INDEX_FIRST, INDEX_FIRST, DESC_SYLPH);

        // same values -> returns true
        UpdateGroupmateCommand.UpdateGroupmateDescriptor copyDescriptor =
                new UpdateGroupmateCommand.UpdateGroupmateDescriptor(DESC_SYLPH);
        UpdateGroupmateCommand commandWithSameValues =
                new UpdateGroupmateCommand(INDEX_FIRST, INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UpdateGroupmateCommand(INDEX_FIRST, INDEX_SECOND, DESC_SYLPH)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UpdateGroupmateCommand(INDEX_FIRST, INDEX_FIRST, DESC_ROXY)));
    }

}

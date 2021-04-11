package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_DUPLICATE_PROGRAM;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_PROGRAM_ID;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.logic.commands.CommandTestUtil.getOutOfBoundId;
import static dog.pawbook.logic.commands.EditProgramCommand.MESSAGE_EDIT_PROGRAM_SUCCESS;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_FIFTEEN;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_SIXTEEN;
import static dog.pawbook.testutil.TypicalId.ID_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EditProgramCommand.EditProgramDescriptor;
import dog.pawbook.model.Database;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.IdMatchPredicate;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.testutil.EditProgramDescriptorBuilder;
import dog.pawbook.testutil.ProgramBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditProgramCommand.
 */
public class EditProgramCommandTest {

    private final Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    private Model expectedModel;

    @Test
    public void execute_allFieldsSpecified_success() {
        Program firstProgram = (Program) model.getEntity(ID_FIFTEEN);
        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder(firstProgram).build();
        EditProgramCommand editProgramCommand = new EditProgramCommand(ID_FIFTEEN, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_PROGRAM_SUCCESS, firstProgram);

        expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_FIFTEEN));

        assertCommandSuccess(editProgramCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Program toEditProgram = (Program) model.getEntity(ID_FIFTEEN);
        ProgramBuilder programInList = new ProgramBuilder(toEditProgram);
        Program editedProgram = programInList.withName(VALID_NAME_OBEDIENCE_TRAINING)
                .withSessions(VALID_SESSION_OBEDIENCE_TRAINING)
                .build();

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withName(VALID_NAME_OBEDIENCE_TRAINING)
                .withSessions(VALID_SESSION_OBEDIENCE_TRAINING).build();
        EditProgramCommand editProgramCommand = new EditProgramCommand(ID_FIFTEEN, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_PROGRAM_SUCCESS, editedProgram);

        expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setEntity(ID_FIFTEEN, editedProgram);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_FIFTEEN));

        assertCommandSuccess(editProgramCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        EditProgramCommand editEntityCommand = new EditProgramCommand(ID_SIXTEEN, new EditProgramDescriptor());
        Program editedProgram = (Program) model.getEntity(ID_SIXTEEN);

        String expectedMessage = String.format(MESSAGE_EDIT_PROGRAM_SUCCESS, editedProgram);

        expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_SIXTEEN));

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateProgram_failure() {
        Program firstProgram = (Program) model.getEntity(ID_FIFTEEN);
        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder(firstProgram).build();
        EditProgramCommand editProgramCommand = new EditProgramCommand(ID_SIXTEEN, descriptor);

        assertCommandFailure(editProgramCommand, model, MESSAGE_DUPLICATE_PROGRAM);
    }

    @Test
    public void execute_outOfBoundProgramId_failure() {
        int outOfBoundId = getOutOfBoundId(model);
        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder()
                .withName(VALID_NAME_OBEDIENCE_TRAINING).build();
        EditProgramCommand editProgramCommand = new EditProgramCommand(outOfBoundId, descriptor);

        assertCommandFailure(editProgramCommand, model, MESSAGE_INVALID_PROGRAM_ID);
    }

    @Test
    public void execute_validIdDifferentEntityType_failure() {
        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder()
                .withName(VALID_NAME_OBEDIENCE_TRAINING).build();
        EditProgramCommand editProgramCommand = new EditProgramCommand(ID_ONE, descriptor);

        assertCommandFailure(editProgramCommand, model, MESSAGE_INVALID_PROGRAM_ID);
    }

    @Test
    public void equals() {
        final EditProgramCommand standardCommand = new EditProgramCommand(ID_ONE, DESC_OBEDIENCE_TRAINING);

        // same values -> returns true
        EditProgramDescriptor copyDescriptor = new EditProgramDescriptor(DESC_OBEDIENCE_TRAINING);
        EditProgramCommand commandWithSameValues = new EditProgramCommand(ID_ONE, copyDescriptor);
        assertEquals(commandWithSameValues, standardCommand);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(new HelpCommand(), standardCommand);

        // different id -> returns false
        assertNotEquals(new EditProgramCommand(ID_TWO, DESC_OBEDIENCE_TRAINING), standardCommand);

        // different descriptor -> returns false
        assertNotEquals(new EditProgramCommand(ID_ONE, DESC_POTTY_TRAINING), standardCommand);
    }
}

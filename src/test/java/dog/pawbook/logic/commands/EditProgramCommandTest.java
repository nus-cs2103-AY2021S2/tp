package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_PROGRAM_A;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_PROGRAM_B;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_PROGRAM_A;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_PROGRAM_A;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_DOGS;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_FIFTEEN;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.logic.commands.EditProgramCommand.EditProgramDescriptor;
import dog.pawbook.model.Database;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.IdMatchPredicate;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.testutil.EditProgramDescriptorBuilder;
import dog.pawbook.testutil.ProgramBuilder;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditProgramCommand.
 */
public class EditProgramCommandTest {

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        Pair<Integer, Entity> firstIdEntity = model.getDatabase().getEntityList().get(14);
        Program editedProgram = new ProgramBuilder((Program) firstIdEntity.getValue()).build();
        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder(editedProgram).build();
        EditProgramCommand editProgramCommand = new EditProgramCommand(firstIdEntity.getKey(), descriptor);

        String expectedMessage = String.format(EditProgramCommand.MESSAGE_EDIT_PROGRAM_SUCCESS, editedProgram);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setEntity(firstIdEntity.getKey(), editedProgram);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(firstIdEntity.getKey()));

        assertCommandSuccess(editProgramCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Program toEditProgram = (Program) model.getEntity(ID_FIFTEEN);
        ProgramBuilder programInList = new ProgramBuilder(toEditProgram);
        Program editedProgram = programInList.withName(VALID_NAME_PROGRAM_A).withSessions(VALID_SESSION_PROGRAM_A)
                .withTags(VALID_TAG_DOGS).build();

        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withName(VALID_NAME_PROGRAM_A)
                .withSessions(VALID_SESSION_PROGRAM_A).withTags(VALID_TAG_DOGS).build();
        EditProgramCommand editEntityCommand = new EditProgramCommand(ID_FIFTEEN, descriptor);

        String expectedMessage = String.format(EditProgramCommand.MESSAGE_EDIT_PROGRAM_SUCCESS, editedProgram);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setEntity(ID_FIFTEEN, editedProgram);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_FIFTEEN));

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        EditProgramCommand editEntityCommand = new EditProgramCommand(ID_FIFTEEN, new EditProgramDescriptor());
        Program editedProgram = (Program) model.getEntity(ID_FIFTEEN);

        String expectedMessage = String.format(EditProgramCommand.MESSAGE_EDIT_PROGRAM_SUCCESS, editedProgram);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_FIFTEEN));

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidProgramId_failure() {
        Integer outOfBoundId = model.getUnfilteredEntityList().stream()
                .map(Pair::getKey).sorted().collect(toList()).get(model.getUnfilteredEntityList().size() - 1) + 1;
        EditProgramDescriptor descriptor = new EditProgramDescriptorBuilder().withName(VALID_NAME_PROGRAM_A).build();
        EditProgramCommand editEntityCommand = new EditProgramCommand(outOfBoundId, descriptor);

        assertCommandFailure(editEntityCommand, model, Messages.MESSAGE_INVALID_PROGRAM_ID);
    }

    @Test
    public void equals() {
        final EditProgramCommand standardCommand = new EditProgramCommand(ID_ONE, DESC_PROGRAM_A);

        // same values -> returns true
        EditProgramDescriptor copyDescriptor = new EditProgramDescriptor(DESC_PROGRAM_A);
        EditProgramCommand commandWithSameValues = new EditProgramCommand(ID_ONE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditProgramCommand(ID_TWO, DESC_PROGRAM_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditProgramCommand(ID_ONE, DESC_PROGRAM_B)));
    }
}

//@@author branzuelajohn
package dog.pawbook.logic.commands;

import static dog.pawbook.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.GuiSettings;
import dog.pawbook.commons.core.Messages;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Database;
import dog.pawbook.model.Model;
import dog.pawbook.model.ReadOnlyDatabase;
import dog.pawbook.model.ReadOnlyUserPrefs;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.testutil.ProgramBuilder;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class AddProgramCommandTest {

    @Test
    public void constructor_nullProgram_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProgramCommand(null));
    }

    @Test
    public void execute_programAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEntityAdded modelStub = new ModelStubAcceptingEntityAdded();
        Program validProgram = new ProgramBuilder().build();

        CommandResult commandResult = new AddProgramCommand(validProgram).execute(modelStub);

        assertEquals(AddProgramCommand.MESSAGE_SUCCESS + validProgram, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProgram), modelStub.entitiesAdded);
    }

    @Test
    public void execute_duplicateProgram_throwsCommandException() {
        Program validProgram = new ProgramBuilder().build();
        AddProgramCommand addProgramCommand = new AddProgramCommand(validProgram);
        ModelStub modelStub = new ModelStubWithProgram(validProgram);

        assertThrows(CommandException.class,
            Messages.MESSAGE_DUPLICATE_PROGRAM, () -> addProgramCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Program program1 = new ProgramBuilder().withName("Basic Obedience Training").build();
        Program program2 = new ProgramBuilder().withName("Basic Behavioural Training").build();
        AddProgramCommand addProgram1Command = new AddProgramCommand(program1);
        AddProgramCommand addProgram2Command = new AddProgramCommand(program2);

        // same object -> returns true
        assertTrue(addProgram1Command.equals(addProgram1Command));

        // same values -> returns true
        AddProgramCommand addProgram1CommandCopy = new AddProgramCommand(program1);
        assertTrue(addProgram1Command.equals(addProgram1CommandCopy));

        // different types -> returns false
        assertFalse(addProgram1Command.equals(1));

        // null -> returns false
        assertFalse(addProgram1Command.equals(null));

        // different program -> returns false
        assertFalse(addProgram1Command.equals(addProgram2Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Entity getEntity(int targetID) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getDatabaseFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDatabaseFilePath(Path databaseFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int addEntity(Entity entity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDatabase(ReadOnlyDatabase database) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyDatabase getDatabase() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEntity(Entity entity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEntity(int id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEntity(int targetID) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEntity(int targetId, Entity editedEntity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Pair<Integer, Entity>> getFilteredEntityList() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ObservableList<Pair<Integer, Entity>> getUnfilteredEntityList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEntityList(Predicate<Pair<Integer, Entity>> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortEntities(Comparator<Pair<Integer, Entity>> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single program.
     */
    private class ModelStubWithProgram extends ModelStub {
        private final Program program;

        ModelStubWithProgram(Program program) {
            requireNonNull(program);
            this.program = program;
        }

        @Override
        public boolean hasEntity(Entity entity) {
            requireNonNull(entity);
            return this.program.equals(entity);
        }
    }

    /**
     * A Model stub that always accept the program being added.
     */
    private class ModelStubAcceptingEntityAdded extends ModelStub {
        final ArrayList<Entity> entitiesAdded = new ArrayList<>();

        @Override
        public boolean hasEntity(Entity entity) {
            requireNonNull(entity);
            return entitiesAdded.stream().anyMatch(entity::equals);
        }

        @Override
        public int addEntity(Entity entity) {
            requireNonNull(entity);
            entitiesAdded.add(entity);
            return entitiesAdded.indexOf(entity);
        }

        @Override
        public ReadOnlyDatabase getDatabase() {
            return new Database();
        }

        @Override
        public void updateFilteredEntityList(Predicate<Pair<Integer, Entity>> predicate) {
            return;
        }
    }

}

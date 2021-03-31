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
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.testutil.OwnerBuilder;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class AddOwnerCommandTest {

    @Test
    public void constructor_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOwnerCommand(null));
    }

    @Test
    public void execute_ownerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEntityAdded modelStub = new ModelStubAcceptingEntityAdded();
        Owner validOwner = new OwnerBuilder().build();

        CommandResult commandResult = new AddOwnerCommand(validOwner).execute(modelStub);

        assertEquals(AddOwnerCommand.MESSAGE_SUCCESS + validOwner, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOwner), modelStub.entitiesAdded);
    }

    @Test
    public void execute_duplicateOwner_throwsCommandException() {
        Owner validOwner = new OwnerBuilder().build();
        AddOwnerCommand addOwnerCommand = new AddOwnerCommand(validOwner);
        ModelStub modelStub = new ModelStubWithOwner(validOwner);

        assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_OWNER, () -> addOwnerCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Owner alice = new OwnerBuilder().withName("Alice").build();
        Owner bob = new OwnerBuilder().withName("Bob").build();
        AddOwnerCommand addAliceCommand = new AddOwnerCommand(alice);
        AddOwnerCommand addBobCommand = new AddOwnerCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddOwnerCommand addAliceCommandCopy = new AddOwnerCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different owner -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
     * A Model stub that contains a single owner.
     */
    private class ModelStubWithOwner extends ModelStub {
        private final Owner owner;

        ModelStubWithOwner(Owner owner) {
            requireNonNull(owner);
            this.owner = owner;
        }

        @Override
        public boolean hasEntity(Entity entity) {
            requireNonNull(entity);
            return this.owner.equals(entity);
        }
    }

    /**
     * A Model stub that always accept the owner being added.
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

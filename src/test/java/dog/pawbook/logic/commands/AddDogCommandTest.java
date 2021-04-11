package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_OWNER_ID;
import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static dog.pawbook.logic.commands.AddDogCommand.MESSAGE_SUCCESS;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalEntities.HAPPY_PUPPY;
import static dog.pawbook.testutil.TypicalEntities.HOON;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.testutil.DogBuilder;
import dog.pawbook.testutil.OwnerBuilder;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class AddDogCommandTest {
    @Test
    public void constructor_nullDog_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDogCommand(null));
    }

    @Test
    public void execute_dogAcceptedByModel_success() throws CommandException {
        ModelStubAcceptingDogAdded modelStub = new ModelStubAcceptingDogAdded();
        Dog validDog = new DogBuilder().withOwnerID(1).build();

        CommandResult commandResult = new AddDogCommand(validDog).execute(modelStub);
        assertEquals(MESSAGE_SUCCESS + validDog, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(new OwnerBuilder(HOON).withDogs(3).build(), HAPPY_PUPPY, validDog),
                modelStub.entitiesAdded);
    }

    @Test
    public void execute_dogOwnerNotOwner_throwsCommandException() {
        ModelStubAcceptingDogAdded modelStub = new ModelStubAcceptingDogAdded();
        Dog validDog = new DogBuilder().withOwnerID(2).build();

        AddDogCommand command = new AddDogCommand(validDog);
        assertThrows(CommandException.class, MESSAGE_INVALID_OWNER_ID, () -> command.execute(modelStub));
    }

    @Test
    public void execute_dogOwnerIdInvalid_throwsCommandException() {
        ModelStubAcceptingDogAdded modelStub = new ModelStubAcceptingDogAdded();
        Dog validDog = new DogBuilder().withOwnerID(4).build();

        AddDogCommand command = new AddDogCommand(validDog);
        assertThrows(CommandException.class, MESSAGE_INVALID_OWNER_ID, () -> command.execute(modelStub));
    }

    @Test
    public void execute_duplicateDog_throwsCommandException() {
        Dog validDog = new DogBuilder().build();
        Owner validOwner = new OwnerBuilder().build();
        AddDogCommand addDogCommand = new AddDogCommand(validDog);
        ModelStub modelStub = new ModelStubWithDogAndOwner(validDog, validOwner);

        assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_DOG, () -> addDogCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Dog apple = new DogBuilder().withName("Apple").build();
        Dog bubbles = new DogBuilder().withName("Bubbles").build();
        AddDogCommand addAppleCommand = new AddDogCommand(apple);
        AddDogCommand addBubblesCommand = new AddDogCommand(bubbles);

        // same object -> returns true
        assertEquals(addAppleCommand, addAppleCommand);

        // same values -> returns true
        AddDogCommand addAppleCommandCopy = new AddDogCommand(apple);
        assertEquals(addAppleCommandCopy, addAppleCommand);

        // different types -> returns false
        assertNotEquals(addAppleCommand, 1);

        // null -> returns false
        assertNotEquals(addAppleCommand, null);

        // different dog -> returns false
        assertNotEquals(addBubblesCommand, addAppleCommand);
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
        public Entity getEntity(int targetID) {
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
     * A Model stub that contains a single dog.
     */
    private class ModelStubWithDogAndOwner extends ModelStub {
        private final Dog dog;
        private final Owner owner;

        ModelStubWithDogAndOwner(Dog dog, Owner owner) {
            requireAllNonNull(dog, owner);
            this.dog = dog;
            this.owner = owner;
        }

        @Override
        public boolean hasEntity(Entity entity) {
            requireNonNull(entity);
            if (entity instanceof Owner) {
                return this.owner.equals(entity);
            }

            return this.dog.equals(entity);
        }

        @Override
        public boolean hasEntity(int unused) {
            return true;
        }

        @Override
        public Entity getEntity(int targetID) {
            return owner;
        }
    }

    /**
     * A Model stub that always accept the owner being added.
     */
    private class ModelStubAcceptingDogAdded extends ModelStub {
        final ArrayList<Entity> entitiesAdded = new ArrayList<>();

        public ModelStubAcceptingDogAdded() {
            entitiesAdded.add(HOON);
            entitiesAdded.add(HAPPY_PUPPY);
        }

        @Override
        public boolean hasEntity(Entity entity) {
            requireNonNull(entity);
            return entitiesAdded.stream().anyMatch(entity::equals);
        }

        @Override
        public boolean hasEntity(int id) {
            return id <= entitiesAdded.size();
        }

        @Override
        public Entity getEntity(int targetId) {
            return entitiesAdded.get(targetId - 1);
        }

        @Override
        public void setEntity(int targetId, Entity editedEntity) {
            entitiesAdded.set(targetId - 1, editedEntity);
        }

        @Override
        public int addEntity(Entity entity) {
            requireNonNull(entity);
            entitiesAdded.add(entity);
            return entitiesAdded.indexOf(entity) + 1;
        }

        @Override
        public ReadOnlyDatabase getDatabase() {
            return new Database();
        }

        @Override
        public void updateFilteredEntityList(Predicate<Pair<Integer, Entity>> predicate) {}
    }

}

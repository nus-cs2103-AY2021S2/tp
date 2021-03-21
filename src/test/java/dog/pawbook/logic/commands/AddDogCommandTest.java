package dog.pawbook.logic.commands;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static dog.pawbook.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.GuiSettings;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.AddressBook;
import dog.pawbook.model.Model;
import dog.pawbook.model.ReadOnlyAddressBook;
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

    //TO DO execute_dogAcceptedByModel_addSuccessful()
    @Test
    public void execute_duplicateDog_throwsCommandException() {
        Dog validDog = new DogBuilder().build();
        Owner validOwner = new OwnerBuilder().build();
        AddDogCommand addDogCommand = new AddDogCommand(validDog);
        ModelStub modelStub = new ModelStubWithDogAndOwner(validDog, validOwner);

        assertThrows(CommandException.class,
                AddDogCommand.MESSAGE_DUPLICATE_DOG, () -> addDogCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Dog apple = new DogBuilder().withName("Apple").build();
        Dog bubbles = new DogBuilder().withName("Bubbles").build();
        AddDogCommand addAppleCommand = new AddDogCommand(apple);
        AddDogCommand addBubblesCommand = new AddDogCommand(bubbles);

        // same object -> returns true
        assertTrue(addAppleCommand.equals(addAppleCommand));

        // same values -> returns true
        AddDogCommand addAppleCommandCopy = new AddDogCommand(apple);
        assertTrue(addAppleCommand.equals(addAppleCommandCopy));

        // different types -> returns false
        assertFalse(addAppleCommand.equals(1));

        // null -> returns false
        assertFalse(addAppleCommand.equals(null));

        // different dog -> returns false
        assertFalse(addAppleCommand.equals(addBubblesCommand));
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
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
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
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
        public void updateFilteredEntityList(Predicate<Pair<Integer, Entity>> predicate) {
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
     * A Model stub that always accept the dog being added.
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
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}

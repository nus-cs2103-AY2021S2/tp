package dog.pawbook.logic.commands;

import static dog.pawbook.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.GuiSettings;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.AddressBook;
import dog.pawbook.model.Model;
import dog.pawbook.model.ReadOnlyAddressBook;
import dog.pawbook.model.ReadOnlyUserPrefs;
import dog.pawbook.model.owner.Owner;
import dog.pawbook.testutil.OwnerBuilder;
import javafx.collections.ObservableList;

public class AddCommandTest {

    @Test
    public void constructor_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_ownerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingOwnerAdded modelStub = new ModelStubAcceptingOwnerAdded();
        Owner validOwner = new OwnerBuilder().build();

        CommandResult commandResult = new AddCommand(validOwner).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validOwner), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOwner), modelStub.ownersAdded);
    }

    @Test
    public void execute_duplicateOwner_throwsCommandException() {
        Owner validOwner = new OwnerBuilder().build();
        AddCommand addCommand = new AddCommand(validOwner);
        ModelStub modelStub = new ModelStubWithOwner(validOwner);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_OWNER, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Owner alice = new OwnerBuilder().withName("Alice").build();
        Owner bob = new OwnerBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
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
        public void addOwner(Owner owner) {
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
        public boolean hasOwner(Owner owner) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOwner(Owner target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOwner(Owner target, Owner editedOwner) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Owner> getFilteredOwnerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOwnerList(Predicate<Owner> predicate) {
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
        public boolean hasOwner(Owner owner) {
            requireNonNull(owner);
            return this.owner.isSameOwner(owner);
        }
    }

    /**
     * A Model stub that always accept the owner being added.
     */
    private class ModelStubAcceptingOwnerAdded extends ModelStub {
        final ArrayList<Owner> ownersAdded = new ArrayList<>();

        @Override
        public boolean hasOwner(Owner owner) {
            requireNonNull(owner);
            return ownersAdded.stream().anyMatch(owner::isSameOwner);
        }

        @Override
        public void addOwner(Owner owner) {
            requireNonNull(owner);
            ownersAdded.add(owner);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}

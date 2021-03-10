package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Garment;
import seedu.address.testutil.GarmentBuilder;
import seedu.address.model.ReadOnlyWardrobe;
import seedu.address.model.Wardrobe;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingGarmentAdded modelStub = new ModelStubAcceptingGarmentAdded();
        Garment validGarment = new GarmentBuilder().build();

        CommandResult commandResult = new AddCommand(validGarment).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validGarment), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validGarment), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Garment validGarment = new GarmentBuilder().build();
        AddCommand addCommand = new AddCommand(validGarment);
        ModelStub modelStub = new ModelStubWithGarment(validGarment);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Garment alice = new GarmentBuilder().withName("Alice").build();
        Garment bob = new GarmentBuilder().withName("Bob").build();
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

        // different person -> returns false
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
        public Path getWardrobeFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setWardrobeFilePath(Path wardrobeFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setWardrobe(ReadOnlyWardrobe newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyWardrobe getWardrobe() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Garment garment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Garment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Garment target, Garment editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Garment> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Garment> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithGarment extends ModelStub {
        private final Garment garment;

        ModelStubWithPerson(Garment garment) {
            requireNonNull(garment);
            this.garment = garment;
        }

        @Override
        public boolean hasPerson(Garment garment) {
            requireNonNull(garment);
            return this.garment.isSameGarment(garment);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingGarmentAdded extends ModelStub {
        final ArrayList<Garment> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Garment garment) {
            requireNonNull(garment);
            return personsAdded.stream().anyMatch(garment::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyWardrobe getWardrobe() {
            return new Wardrobe();
        }
    }

}

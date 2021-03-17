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
import seedu.address.model.ReadOnlyResidenceTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ResidenceTracker;
import seedu.address.model.residence.Residence;
import seedu.address.testutil.ResidenceBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingResidenceAdded modelStub = new ModelStubAcceptingResidenceAdded();
        Residence validResidence = new ResidenceBuilder().build();

        CommandResult commandResult = new AddCommand(validResidence).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validResidence), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validResidence), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Residence validResidence = new ResidenceBuilder().build();
        AddCommand addCommand = new AddCommand(validResidence);
        ModelStub modelStub = new ModelStubWithResidence(validResidence);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_RESIDENCE,
                () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Residence residence1 = new ResidenceBuilder().withName("Amber Heights").build();
        Residence residence2 = new ResidenceBuilder().withName("Beauty World").build();
        AddCommand addFirstCommand = new AddCommand(residence1);
        AddCommand addSecondCommand = new AddCommand(residence2);

        // same object -> returns true
        assertTrue(addFirstCommand.equals(addFirstCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(residence1);
        assertTrue(addFirstCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(addFirstCommand.equals(addSecondCommand));
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
        public Path getResidenceTrackerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setResidenceTrackerFilePath(Path residenceTrackerFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addResidence(Residence residence) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setResidenceTracker(ReadOnlyResidenceTracker newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyResidenceTracker getResidenceTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasResidence(Residence residence) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteResidence(Residence target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setResidence(Residence target, Residence editedResidence) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Residence> getFilteredResidenceList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredResidenceList(Predicate<Residence> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single residence.
     */
    private class ModelStubWithResidence extends ModelStub {
        private final Residence residence;

        ModelStubWithResidence(Residence residence) {
            requireNonNull(residence);
            this.residence = residence;
        }

        @Override
        public boolean hasResidence(Residence residence) {
            requireNonNull(residence);
            return this.residence.isSameResidence(residence);
        }
    }

    /**
     * A Model stub that always accept the residence being added.
     */
    private class ModelStubAcceptingResidenceAdded extends ModelStub {
        final ArrayList<Residence> personsAdded = new ArrayList<>();

        @Override
        public boolean hasResidence(Residence residence) {
            requireNonNull(residence);
            return personsAdded.stream().anyMatch(residence::isSameResidence);
        }

        @Override
        public void addResidence(Residence residence) {
            requireNonNull(residence);
            personsAdded.add(residence);
        }

        @Override
        public ReadOnlyResidenceTracker getResidenceTracker() {
            return new ResidenceTracker();
        }
    }

}

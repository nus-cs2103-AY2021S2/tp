package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.property.Property;
import seedu.address.testutil.PropertyBuilder;

public class AddPropertyCommandTest {

    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPropertyCommand(null));
    }

    @Test
    public void execute_propertyAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPropertyAdded modelStub = new ModelStubAcceptingPropertyAdded();
        Property validProperty = new PropertyBuilder().build();

        CommandResult commandResult = new AddPropertyCommand(validProperty).execute(modelStub);

        assertEquals(String.format(AddPropertyCommand.MESSAGE_SUCCESS, validProperty),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProperty), modelStub.propertiesAdded);
    }

    @Test
    public void execute_duplicateProperty_throwsCommandException() {
        Property validProperty = new PropertyBuilder().build();
        AddPropertyCommand addPropertyCommand = new AddPropertyCommand(validProperty);
        ModelStub modelStub = new ModelStubWithProperty(validProperty);

        assertThrows(CommandException.class,
                AddPropertyCommand.MESSAGE_DUPLICATE_PROPERTY, () -> addPropertyCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Property mayfair = new PropertyBuilder().withName("Mayfair").build();
        Property burghleyDrive = new PropertyBuilder().withName("Burghley Drive").build();
        AddPropertyCommand addMayfairCommand = new AddPropertyCommand(mayfair);
        AddPropertyCommand addBurghleyDriveCommand = new AddPropertyCommand(burghleyDrive);

        // same object -> returns true
        assertTrue(addMayfairCommand.equals(addMayfairCommand));

        // same values -> returns true
        AddPropertyCommand addMayfairCommandCopy = new AddPropertyCommand(mayfair);
        assertTrue(addMayfairCommand.equals(addMayfairCommandCopy));

        // different types -> returns false
        assertFalse(addMayfairCommand.equals(1));

        // null -> returns false
        assertFalse(addMayfairCommand.equals(null));

        // different property -> returns false
        assertFalse(addMayfairCommand.equals(addBurghleyDriveCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void undoAppointmentBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoPropertyBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortAppointmentList(Comparator<Appointment> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortPropertyList(Comparator<Property> comparator) {
            throw new AssertionError("This method should not be called.");
        }

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
        public Path getPropertyBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPropertyBookFilePath(Path propertyBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPropertyBook(ReadOnlyPropertyBook propertyBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPropertyBook getPropertyBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProperty(Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProperty(Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getPropertySize() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Property getProperty(int i) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProperty(int i, Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProperty(Property target, Property editedProperty) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProperty(Property target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Property> getFilteredPropertyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPropertyList(Predicate<Property> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAppointmentBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointmentBookFilePath(Path appointmentBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointmentBook(ReadOnlyAppointmentBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAppointmentBook getAppointmentBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Appointment getAppointment(int i) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getAppointmentSize() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single property.
     */
    private class ModelStubWithProperty extends ModelStub {
        private final Property property;

        ModelStubWithProperty(Property property) {
            requireNonNull(property);
            this.property = property;
        }

        @Override
        public boolean hasProperty(Property property) {
            requireNonNull(property);
            return this.property.isSameProperty(property);
        }
    }

    /**
     * A Model stub that always accept the property being added.
     */
    private class ModelStubAcceptingPropertyAdded extends ModelStub {
        final ArrayList<Property> propertiesAdded = new ArrayList<>();

        @Override
        public boolean hasProperty(Property property) {
            requireNonNull(property);
            return propertiesAdded.stream().anyMatch(property::isSameProperty);
        }

        @Override
        public void addProperty(Property property) {
            requireNonNull(property);
            propertiesAdded.add(property);
        }
    }
}

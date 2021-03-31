package seedu.address.logic.commands.addcommand;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyRemindMe;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.RemindMe;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddPersonCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPersonCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddPersonCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddPersonCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddPersonCommand addPersonCommand = new AddPersonCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class,
                AddPersonCommand.MESSAGE_DUPLICATE_PERSON, () -> addPersonCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").withBirthday("12/12/1999").build();
        Person bob = new PersonBuilder().withName("Bob").withBirthday("12/12/1999").build();
        AddPersonCommand addAliceCommand = new AddPersonCommand(alice);
        AddPersonCommand addBobCommand = new AddPersonCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddPersonCommand addAliceCommandCopy = new AddPersonCommand(alice);
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
        public Path getRemindMeFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRemindMeFilePath(Path remindMeFilePath) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Module module) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Module getModule(Module module) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Module getModule(int index) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setModule(Module target, Module editedMod) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean hasAssignment(Module module, Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAssignment(Module module, int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editAssignment(Module module, int index, Description edit) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editAssignment(Module module, int index, LocalDateTime edit) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAssignment(Module module, Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExam(Module module, Exam exam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExam(Module module, int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExam(Module module, Exam exam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editExam(Module module, int index, LocalDateTime edit) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRemindMe(RemindMe remindMe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetModules() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetPersons() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetEvents() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editModule(int index, Title title) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyRemindMe getRemindMe() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(GeneralEvent event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(GeneralEvent event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editEvent(int index, Description edit) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editEvent(int index, LocalDateTime edit) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(GeneralEvent event) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public GeneralEvent getEvent(int index) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<GeneralEvent> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<GeneralEvent> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

    }

}

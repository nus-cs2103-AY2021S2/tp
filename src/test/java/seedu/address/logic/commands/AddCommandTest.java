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
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDatesBook;
import seedu.address.model.ReadOnlyLessonBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.date.ImportantDate;
import seedu.address.model.lesson.Day;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, String.format(AddCommand.MESSAGE_DUPLICATE_PERSON,
                validPerson.getPhone(), validPerson.getName()), () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getDatesBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDatesBookFilePath(Path datesBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
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
        public void setSelectedPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getSelectedPerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isSavedState() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSavedState(boolean isSavedState) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPotentialPerson(Person person) {
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
        public void filterPerson(Predicate<Person> predicate) {
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
        public ObservableList<Person> getSortedPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedPersonList(Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public ObservableList<Person> getTransformedPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void filterThenSortPersonList(Predicate<Person> predicate, Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateTransformedPersonList(Function<Person, Person> function) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void filterIndicesThenTransformPersonList(List<Index> index, Function<Person, Person> function) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDatesBook(ReadOnlyDatesBook datesBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyDatesBook getDatesBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasImportantDate(ImportantDate importantDate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteImportantDate(ImportantDate target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addImportantDate(ImportantDate importantDate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void filterImportantDates(Predicate<ImportantDate> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ImportantDate> getFilteredImportantDatesList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredImportantDatesList(Predicate<ImportantDate> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ImportantDate> getSortedImportantDatesList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedImportantDatesList(Comparator<ImportantDate> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ImportantDate> getTransformedImportantDatesList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLessonBook(ReadOnlyLessonBook lessonBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTransformedDayList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyLessonBook getLessonBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Lesson getLesson(Lesson lesson) {
            return null;
        }

        @Override
        public void deleteLesson(Lesson target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateLessonDayList(ArrayList<Day> lessonDays) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPersonToLesson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removePersonFromLesson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void filterLesson(Predicate<Lesson> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getFilteredLessonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredLessonList(Predicate<Lesson> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getSortedLessonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedLessonList(Comparator<Lesson> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getTransformedLessonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void filterThenSortLessonList(Predicate<Lesson> predicate, Comparator<Lesson> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDailyLessonList(FilteredList<Lesson> lessons,
                Predicate<Lesson> predicate, ObservableList<Lesson> transformedList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getMondayLesson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getTuesdayLesson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getWednesdayLesson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getThursdayLesson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getFridayLesson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getSaturdayLesson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getSundayLesson() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;
        private boolean isSavedState;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
            this.isSavedState = false;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }

        @Override
        public boolean isSavedState() {
            return this.isSavedState;
        }

        @Override
        public void setSavedState(boolean isSavedState) {
            this.isSavedState = isSavedState;
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        private boolean isSavedState = false;

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public boolean hasPotentialPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isPotentialSamePerson);
        }

        @Override
        public void addPersonToLesson(Person person) {
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public boolean isSavedState() {
            return this.isSavedState;
        }

        @Override
        public void setSavedState(boolean isSavedState) {
            this.isSavedState = isSavedState;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}

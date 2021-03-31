package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.date.ImportantDate;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.comparators.ImportantDateDetailsComparator;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final DatesBook datesBook;
    private final LessonBook lessonBook;
    private final UserPrefs userPrefs;
    private final FilteredList<ImportantDate> filteredImportantDates;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Lesson> filteredLessons;
    private final SortedList<ImportantDate> sortedImportantDates;
    private final SortedList<Person> sortedPersons;
    private final SortedList<Lesson> sortedLessons;
    private final ObservableList<ImportantDate> transformedImportantDates;
    private final ObservableList<Person> transformedPersons;
    private final ObservableList<Lesson> transformedLessons;
    private Person selectedPerson;
    private boolean isSavedState;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyDatesBook datesBook,
                        ReadOnlyLessonBook lessonBook) {
        super();
        requireAllNonNull(addressBook, userPrefs, datesBook, lessonBook);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.datesBook = new DatesBook(datesBook);
        this.lessonBook = new LessonBook(lessonBook);

        filteredImportantDates = new FilteredList<>(this.datesBook.getImportantDatesList());
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredLessons = new FilteredList<>(this.lessonBook.getLessonList());

        sortedImportantDates = new SortedList<>(this.datesBook.getImportantDatesList());
        sortedPersons = new SortedList<>(this.addressBook.getPersonList());
        sortedLessons = new SortedList<>(this.lessonBook.getLessonList());

        transformedImportantDates = FXCollections.observableArrayList(this.datesBook.getImportantDatesList());
        transformedPersons = FXCollections.observableArrayList(this.addressBook.getPersonList());
        transformedLessons = FXCollections.observableArrayList(this.lessonBook.getLessonList());
        selectedPerson = null;
        isSavedState = false;
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new DatesBook(), new LessonBook());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getDatesBookFilePath() {
        return userPrefs.getDatesBookFilePath();
    }

    @Override
    public void setDatesBookFilePath(Path datesBookFilePath) {
        requireNonNull(datesBookFilePath);
        userPrefs.setDatesBookFilePath(datesBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public Person getSelectedPerson() {
        return selectedPerson;
    }

    @Override
    public void setSelectedPerson(Person person) {
        this.selectedPerson = person;
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
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void filterPerson(Predicate<Person> predicate) {
        updateFilteredPersonList(predicate);
    }

    //=========== LessonBook ================================================================================

    @Override
    public void setLessonBook(ReadOnlyLessonBook lessonBook) {
        this.lessonBook.resetData(lessonBook);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
    }

    @Override
    public ReadOnlyLessonBook getLessonBook() {
        return lessonBook;
    }

    @Override
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessonBook.hasLesson(lesson);
    }

    @Override
    public Lesson getLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessonBook.getLesson(lesson);
    }

    @Override
    public void deleteLesson(Lesson target) {
        lessonBook.removeLesson(target);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
    }

    @Override
    public void addLesson(Lesson lesson) {
        lessonBook.addLesson(lesson);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
    }

    @Override
    public void addPersonToLesson(Person person) {
        lessonBook.addPersonToLesson(person);
    }

    @Override
    public void removePersonFromLesson(Person person) {
        lessonBook.removePersonFromLesson(person);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
    }

    @Override
    public void filterLesson(Predicate<Lesson> predicate) {
        updateFilteredLessonList(predicate);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
        transformedPersons.setAll(filteredPersons);
        /*
        requireNonNull(predicate);
        FilteredList<Person> newFilteredPersons = transformedPersons.filtered(predicate);
        newFilteredPersons.setPredicate(predicate);
        transformedPersons.setAll(newFilteredPersons);
        */
    }

    //=========== Sorted Person List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the sorted list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getSortedPersonList() {
        return sortedPersons;
    }

    @Override
    public void updateSortedPersonList(Comparator<Person> comparator) throws NullPointerException {
        requireNonNull(comparator);
        sortedPersons.setComparator(comparator);
        transformedPersons.setAll(sortedPersons);
        /*
        requireNonNull(comparator);
        SortedList<Person> newSortedPersons = transformedPersons.sorted(comparator);
        newSortedPersons.setComparator(comparator);
        transformedPersons.setAll(newSortedPersons);
         */
    }

    //=========== Transformed Person List Accessors =============================================================

    @Override
    public ObservableList<Person> getTransformedPersonList() {
        return transformedPersons;
    }

    @Override
    public void filterThenSortPersonList(Predicate<Person> predicate, Comparator<Person> comparator)
            throws NullPointerException {

        requireNonNull(comparator);
        filteredPersons.setPredicate(predicate);
        transformedPersons.setAll(filteredPersons);
        SortedList<Person> newSortedPersons = transformedPersons.sorted(comparator);
        newSortedPersons.setComparator(comparator);
        transformedPersons.setAll(newSortedPersons);
    }

    //=========== DatesBook ================================================================================

    @Override
    public void setDatesBook(ReadOnlyDatesBook datesBook) {
        this.datesBook.resetData(datesBook);
        updateFilteredImportantDatesList(PREDICATE_SHOW_ALL_IMPORTANT_DATES);
    }

    @Override
    public ReadOnlyDatesBook getDatesBook() {
        return datesBook;
    }


    @Override
    public boolean hasImportantDate(ImportantDate importantDate) {
        requireNonNull(importantDate);
        return datesBook.hasImportantDate(importantDate);
    }

    @Override
    public void deleteImportantDate(ImportantDate target) {
        datesBook.removeImportantDate(target);
        updateSortedImportantDatesList(new ImportantDateDetailsComparator());
    }

    @Override
    public void addImportantDate(ImportantDate importantDate) {
        datesBook.addImportantDate(importantDate);
        updateSortedImportantDatesList(new ImportantDateDetailsComparator());
    }

    @Override
    public void filterImportantDates(Predicate<ImportantDate> predicate) {
        updateFilteredImportantDatesList(predicate);
    }

    //=========== Filtered Important Dates List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<ImportantDate> getFilteredImportantDatesList() {
        return filteredImportantDates;
    }

    @Override
    public void updateFilteredImportantDatesList(Predicate<ImportantDate> predicate) {
        requireNonNull(predicate);
        filteredImportantDates.setPredicate(predicate);
        transformedImportantDates.setAll(filteredImportantDates);
    }

    //=========== Sorted Important Dates List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the sorted list of {@code ImportantDate}
     */
    @Override
    public ObservableList<ImportantDate> getSortedImportantDatesList() {
        return sortedImportantDates;
    }

    @Override
    public void updateSortedImportantDatesList(Comparator<ImportantDate> comparator) throws NullPointerException {
        requireNonNull(comparator);
        sortedImportantDates.setComparator(comparator);
        transformedImportantDates.setAll(sortedImportantDates);
    }

    //=========== Transformed Important Dates List Accessors ========================================================

    @Override
    public ObservableList<ImportantDate> getTransformedImportantDatesList() {
        return transformedImportantDates;
    }


    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && datesBook.equals(other.datesBook)
                && lessonBook.equals(other.lessonBook);
    }

    //=========== Filtered Lesson List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Lesson} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Lesson> getFilteredLessonList() {
        return filteredLessons;
    }

    @Override
    public void updateFilteredLessonList(Predicate<Lesson> predicate) {
        requireNonNull(predicate);
        filteredLessons.setPredicate(predicate);
        transformedLessons.setAll(filteredLessons);
    }

    //=========== Sorted Lesson List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the sorted list of {@code Lesson}
     */
    @Override
    public ObservableList<Lesson> getSortedLessonList() {
        return sortedLessons;
    }

    @Override
    public void updateSortedLessonList(Comparator<Lesson> comparator) throws NullPointerException {
        requireNonNull(comparator);
        sortedLessons.setComparator(comparator);
        transformedLessons.setAll(sortedLessons);
    }

    //=========== Transformed Lesson List Accessors ========================================================

    @Override
    public ObservableList<Lesson> getTransformedLessonList() {
        return transformedLessons;
    }

    @Override
    public void filterThenSortLessonList(Predicate<Lesson> predicate, Comparator<Lesson> comparator)
            throws NullPointerException {
        requireNonNull(comparator);
        filteredLessons.setPredicate(predicate);
        transformedLessons.setAll(filteredLessons);
        SortedList<Lesson> newSortedLessons = transformedLessons.sorted(comparator);
        newSortedLessons.setComparator(comparator);
        transformedLessons.setAll(newSortedLessons);
    }
}

package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.date.ImportantDate;
import seedu.address.model.lesson.Day;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.comparators.ImportantDateDetailsComparator;
import seedu.address.model.person.comparators.LessonTimeComparator;
import seedu.address.model.person.predicate.LessonDayPredicate;

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
    private final ObservableList<Lesson> transformedLessonsForPerson;

    private final FilteredList<Lesson> mondayLessons;
    private final FilteredList<Lesson> tuesdayLessons;
    private final FilteredList<Lesson> wednesdayLessons;
    private final FilteredList<Lesson> thursdayLessons;
    private final FilteredList<Lesson> fridayLessons;
    private final FilteredList<Lesson> saturdayLessons;
    private final FilteredList<Lesson> sundayLessons;

    private final ObservableList<Lesson> transformedMondayList;
    private final ObservableList<Lesson> transformedTuesdayList;
    private final ObservableList<Lesson> transformedWednesdayList;
    private final ObservableList<Lesson> transformedThursdayList;
    private final ObservableList<Lesson> transformedFridayList;
    private final ObservableList<Lesson> transformedSaturdayList;
    private final ObservableList<Lesson> transformedSundayList;


    private Person selectedPerson;
    private boolean isSavedState;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyDatesBook datesBook,
                        ReadOnlyLessonBook lessonBook) {
        super();
        requireAllNonNull(addressBook, userPrefs, datesBook, lessonBook);

        logger.fine("Initializing with address book: " + addressBook + ", dates book: " + datesBook
            + ", lesson book: " + lessonBook + " and user prefs " + userPrefs);

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
        transformedLessonsForPerson = FXCollections.observableArrayList(this.lessonBook.getLessonList());;

        mondayLessons = new FilteredList<>(transformedLessons);
        tuesdayLessons = new FilteredList<>(transformedLessons);
        wednesdayLessons = new FilteredList<>(transformedLessons);
        thursdayLessons = new FilteredList<>(transformedLessons);
        fridayLessons = new FilteredList<>(transformedLessons);
        saturdayLessons = new FilteredList<>(transformedLessons);
        sundayLessons = new FilteredList<>(transformedLessons);

        transformedMondayList = FXCollections.observableArrayList(this.mondayLessons);
        transformedTuesdayList = FXCollections.observableArrayList(this.tuesdayLessons);
        transformedWednesdayList = FXCollections.observableArrayList(this.wednesdayLessons);
        transformedThursdayList = FXCollections.observableArrayList(this.thursdayLessons);
        transformedFridayList = FXCollections.observableArrayList(this.fridayLessons);
        transformedSaturdayList = FXCollections.observableArrayList(this.saturdayLessons);
        transformedSundayList = FXCollections.observableArrayList(this.sundayLessons);


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
    public boolean hasPotentialPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPotentialPerson(person);
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
        if (selectedPerson != null && target.equals(selectedPerson)) {
            selectedPerson = editedPerson;
        }
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
        setTransformedDayList();
    }

    @Override
    public void setTransformedDayList() {
        transformedMondayList.setAll(this.transformedLessons);
        transformedTuesdayList.setAll(this.transformedLessons);
        transformedWednesdayList.setAll(this.transformedLessons);
        transformedThursdayList.setAll(this.transformedLessons);
        transformedFridayList.setAll(this.transformedLessons);
        transformedSaturdayList.setAll(this.transformedLessons);
        transformedSundayList.setAll(this.transformedLessons);
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
    public void updateLessonDayList(ArrayList<Day> lessonDays) {
        for (int i = 0; i < lessonDays.size(); i++) {

            switch (lessonDays.get(i).toString().toLowerCase(Locale.ROOT)) {
            case "monday":
                updateFilteredDailyLessonList(mondayLessons, new LessonDayPredicate("monday"),
                    transformedMondayList);
                break;
            case "tuesday":
                updateFilteredDailyLessonList(tuesdayLessons, new LessonDayPredicate("tuesday"),
                    transformedTuesdayList);
                break;
            case "wednesday":
                updateFilteredDailyLessonList(wednesdayLessons, new LessonDayPredicate("wednesday"),
                    transformedWednesdayList);
                break;
            case "thursday":
                updateFilteredDailyLessonList(thursdayLessons, new LessonDayPredicate("thursday"),
                    transformedThursdayList);
                break;
            case "friday":
                updateFilteredDailyLessonList(fridayLessons, new LessonDayPredicate("friday"),
                    transformedFridayList);
                break;
            case "saturday":
                updateFilteredDailyLessonList(saturdayLessons, new LessonDayPredicate("saturday"),
                    transformedSaturdayList);
                break;
            case "sunday":
                updateFilteredDailyLessonList(sundayLessons, new LessonDayPredicate("sunday"),
                    transformedSundayList);
                break;
            default:
                break;
            }
        }
    }

    @Override
    public void addPersonToLesson(Person person) {
        lessonBook.addPersonToLesson(person);
        ArrayList<Day> lessonDays = person.getLessonsDays();
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        updateLessonDayList(lessonDays);
    }

    @Override
    public void removePersonFromLesson(Person person) {
        lessonBook.removePersonFromLesson(person);
        ArrayList<Day> lessonDays = person.getLessonsDays();
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        updateLessonDayList(lessonDays);
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

    @Override
    public void updateTransformedPersonList(Function<Person, Person> function) {
        filteredPersons.setPredicate(PREDICATE_SHOW_ALL_PERSONS);
        transformedPersons.setAll(transform(filteredPersons, function));
    }

    private ObservableList<Person> transform(ObservableList<Person> observableList,
            Function<Person, Person> function) {
        ArrayList<Person> oldList = new ArrayList<>(observableList);
        ArrayList<Person> newList = new ArrayList<>(oldList.size());
        for (Person p : oldList) {
            newList.add(function.apply(p));
        }
        ObservableList<Person> newObservableList = FXCollections.observableArrayList(newList);
        return newObservableList;
    }

    @Override
    public void filterIndicesThenTransformPersonList(List<Index> indices, Function<Person, Person> function) {
        UnaryOperator<Person> newOperator = x -> {
            if (!indices.contains(Index.fromZeroBased(transformedPersons.indexOf(x)))) {
                return function.apply(x);
            } else {
                return x;
            }
        };
        transformedPersons.replaceAll(newOperator);
    }

    //=========== Lesson Day Accessors =============================================================

    @Override
    public void updateFilteredDailyLessonList(FilteredList<Lesson> lessons, Predicate<Lesson> predicate,
                                              ObservableList<Lesson> transformedList) throws NullPointerException {
        requireNonNull(predicate);
        Comparator<Lesson> comparator = new LessonTimeComparator();
        lessons.setPredicate(predicate);
        SortedList<Lesson> sortedDayList = lessons.sorted(comparator);
        transformedList.setAll(sortedDayList);
    }


    @Override
    public ObservableList<Lesson> getMondayLesson() {
        updateFilteredDailyLessonList(mondayLessons, new LessonDayPredicate("monday"),
            transformedMondayList);
        return transformedMondayList;
    }

    @Override
    public ObservableList<Lesson> getTuesdayLesson() {
        updateFilteredDailyLessonList(tuesdayLessons, new LessonDayPredicate("tuesday"),
            transformedTuesdayList);
        return transformedTuesdayList;
    }

    @Override
    public ObservableList<Lesson> getWednesdayLesson() {
        updateFilteredDailyLessonList(wednesdayLessons, new LessonDayPredicate("wednesday"),
            transformedWednesdayList);
        return transformedWednesdayList;
    }

    @Override
    public ObservableList<Lesson> getThursdayLesson() {
        updateFilteredDailyLessonList(thursdayLessons, new LessonDayPredicate("thursday"),
            transformedThursdayList);
        return transformedThursdayList;
    }

    @Override
    public ObservableList<Lesson> getFridayLesson() {
        updateFilteredDailyLessonList(fridayLessons, new LessonDayPredicate("friday"),
            transformedFridayList);
        return transformedFridayList;
    }

    @Override
    public ObservableList<Lesson> getSaturdayLesson() {
        updateFilteredDailyLessonList(saturdayLessons, new LessonDayPredicate("saturday"),
            transformedSaturdayList);
        return transformedSaturdayList;
    }

    @Override
    public ObservableList<Lesson> getSundayLesson() {
        updateFilteredDailyLessonList(sundayLessons, new LessonDayPredicate("sunday"),
            transformedSundayList);
        return transformedSundayList;
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
     * Returns an unmodifiable view of the list of {@code ImportantDate}
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
    public ObservableList<Lesson> getTransformedLessonListForPerson() {
        return transformedLessonsForPerson;
    }

    @Override
    public void filterThenSortLessonList(Predicate<Lesson> predicate, Comparator<Lesson> comparator)
            throws NullPointerException {
        requireNonNull(comparator);
        filteredLessons.setPredicate(predicate);
        transformedLessonsForPerson.setAll(filteredLessons);
        SortedList<Lesson> newSortedLessons = transformedLessonsForPerson.sorted(comparator);
        newSortedLessons.setComparator(comparator);
        transformedLessonsForPerson.setAll(newSortedLessons);
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
            && datesBook.equals(other.datesBook)
            && lessonBook.equals(other.lessonBook)
            && filteredPersons.equals(other.filteredPersons)
            && filteredLessons.equals(other.filteredLessons)
            && filteredImportantDates.equals(other.filteredImportantDates);
    }

}

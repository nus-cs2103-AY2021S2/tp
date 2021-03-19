package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.DescriptionContainsKeywordsPredicate;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.module.TitleContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final RemindMe remindMe;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Module> filteredModules;
    private final FilteredList<GeneralEvent> filteredEvents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyRemindMe remindMeApp,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(remindMeApp, userPrefs);

        logger.fine("Initializing with RemindMe: " + remindMeApp + " and user prefs " + userPrefs);

        this.remindMe = new RemindMe(remindMeApp);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.remindMe.getPersonList());
        filteredModules = new FilteredList<>(this.remindMe.getModuleList());
        filteredEvents = new FilteredList<>(this.remindMe.getEventList());
    }

    public ModelManager() {
        this(new RemindMe(), new UserPrefs());
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
    public Path getRemindMeFilePath () {
        return userPrefs.getRemindMeFilePath();
    }

    @Override
    public void setRemindMeFilePath(Path remindMeFilePath) {
        requireNonNull(remindMeFilePath);
        userPrefs.setRemindMeFilePath(remindMeFilePath);
    }

    //=========== RemindMe Person ================================================================================


    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return remindMe.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        remindMe.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        remindMe.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        remindMe.setPerson(target, editedPerson);
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
        return remindMe.equals(other.remindMe)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredModules.equals(other.filteredModules);
    }

    //=========== RemindMe =============================================================

    @Override
    public void setRemindMe(RemindMe remindMe) {
        this.remindMe.resetData(remindMe);
    }

    @Override
    public void resetModules() {
        this.remindMe.resetModules();
    }

    @Override
    public void resetPersons() {
        this.remindMe.resetPersons();
    }

    @Override
    public void resetEvents() {
        this.remindMe.resetEvents();
    }

    @Override
    public ReadOnlyRemindMe getRemindMe() {
        return remindMe;
    }

    @Override
    public void updateFilteredModuleList(TitleContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredEventList(DescriptionContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    @Override
    public ObservableList<GeneralEvent> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return remindMe.hasModule(module);
    }

    @Override
    public boolean hasModule(int index) {
        return remindMe.hasModule(index);
    }

    @Override
    public void addModule(Module module) {
        requireNonNull(module);
        remindMe.addModule(module);
    }

    @Override
    public void deleteModule(Module target) {
        remindMe.removeModule(target);
    }

    @Override
    public void editModule(int index, Title title) {
        requireNonNull(title);
        remindMe.editModule(index, title);
    }

    @Override
    public boolean hasAssignment(Module module, Assignment assignment) {
        requireAllNonNull(module, assignment);
        return remindMe.hasAssignment(module, assignment);
    }

    @Override
    public void addAssignment(Module module, Assignment assignment) {
        requireAllNonNull(module, assignment);
        remindMe.addAssignment(module, assignment);
    }

    @Override
    public boolean hasExam(Module module, Exam exam) {
        requireAllNonNull(module, exam);
        return remindMe.hasExam(module, exam);
    }

    @Override
    public void addExam(Module module, Exam exam) {
        requireAllNonNull(module, exam);
        remindMe.addExam(module, exam);
    }
}

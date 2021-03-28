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
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.AlreadyEnrolledException;
import seedu.address.model.person.exceptions.StudentTutorNotFoundException;
import seedu.address.model.person.exceptions.TimeClashException;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionId;
import seedu.address.model.session.Time;
import seedu.address.model.session.exceptions.SessionNotFoundException;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Session> filteredSessions;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredSessions = new FilteredList<>(this.addressBook.getSessionList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
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
    public void addSession(Session session) {
        requireNonNull(session);
        addressBook.addSession(session);
    }

    @Override
    public void deleteSession(Session target) {
        addressBook.removeSession(target);
    }

    /**
     * Assigns the student/tutor to a session in addressBook
     * @param assignment
     */
    public void registerAssignment(Assignment assignment) throws TimeClashException, AlreadyEnrolledException,
            SessionNotFoundException, StudentTutorNotFoundException {
        Session session = addressBook.getSession(assignment.getSessionId());
        if (session == null) {
            throw new SessionNotFoundException("Session id :" + assignment.getSessionId() + " does not exist");
        }
        if (assignment.getStudentId() != null) {
            Person currentStudent = addressBook.getPerson(assignment.getStudentId());
            if (currentStudent == null) {
                throw new StudentTutorNotFoundException("student id: " + assignment.getStudentId() + " does not exist");
            }
            if (!checkEnrollement(currentStudent, session)) {
                if (checkTimeSlot(currentStudent, session)) {
                    currentStudent.addSession(assignment.getSessionId());
                    session.assignStudent(assignment.getStudentId());
                } else {
                    throw new TimeClashException("There is timing clash between "
                            + "the student sessions and session to be added!");
                }
            } else {
                throw new AlreadyEnrolledException("The student of student Id: "
                        + currentStudent.getPersonId().getPersonId()
                        + " is already enrolled in this class");
            }
        }
        if (assignment.getTutorId() != null) {
            Person currentTutor = addressBook.getPerson(assignment.getTutorId());
            if (currentTutor == null) {
                throw new StudentTutorNotFoundException("tutor id: " + assignment.getTutorId() + " does not exist");
            }
            if (!checkEnrollement(currentTutor, session)) {
                if (checkTimeSlot(currentTutor, session)) {
                    currentTutor.addSession(assignment.getSessionId());
                    session.assignTutor(assignment.getTutorId());
                } else {
                    throw new TimeClashException("There is timing clash between "
                            + "the tutor sessions and session to be added!");
                }
            } else {
                throw new AlreadyEnrolledException("The student of tutor Id: "
                        + currentTutor.getPersonId().getPersonId()
                        + " is already assigned to this class");
            }
        }
    }

    private boolean checkEnrollement(Person person, Session session) {
        for (SessionId sessionid : person.getSessions()) {
            if (sessionid.equals(session.getClassId())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkTimeSlot(Person person, Session session) {
        for (SessionId sessionid : person.getSessions()) {
            Session currentSession = addressBook.getSession(sessionid);
            if (!currentSession.getDay().toString().equals(session.getDay().toString())) {
                return true;
            } else {
                Time sessionStart = session.getTimeslot().getStart();
                Time sessionEnd = session.getTimeslot().getEnd();
                Time start = currentSession.getTimeslot().getStart();
                Time end = currentSession.getTimeslot().getEnd();
                if (sessionStart.isAfter(start) && sessionStart.isBefore(end)) {
                    return false;
                } else if (sessionEnd.isAfter(start) && sessionEnd.isBefore(end)) {
                    return false;
                } else if (sessionStart.isBefore(start) && sessionEnd.isAfter(end)) {
                    return false;
                } else if (sessionStart.isSame(start) || sessionEnd.isSame(end)) {
                    return false;
                }
            }
        }
        return true;
    }

    //=========== Filtered Session List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Session} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Session> getFilteredSessionList() {
        return filteredSessions;
    }

    @Override
    public void updateFilteredSessionList(Predicate<Session> predicate) {
        requireNonNull(predicate);
        filteredSessions.setPredicate(predicate);
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}

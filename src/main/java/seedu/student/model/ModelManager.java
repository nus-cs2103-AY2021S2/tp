package seedu.student.model;

import static java.util.Objects.requireNonNull;
import static seedu.student.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.student.commons.core.GuiSettings;
import seedu.student.commons.core.LogsCenter;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.appointment.SameDateAppointmentList;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StudentBook studentBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<SameDateAppointmentList> filteredAppointments;

    /**
     * Initializes a ModelManager with the given studentBook and userPrefs.
     */
    public ModelManager(ReadOnlyStudentBook studentBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(studentBook, userPrefs);

        logger.fine("Initializing with student book: " + studentBook + " and user prefs " + userPrefs);

        this.studentBook = new StudentBook(studentBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.studentBook.getStudentList());
        filteredAppointments = new FilteredList<>(this.studentBook.getAppointmentList());
    }

    public ModelManager() {
        this(new StudentBook(), new UserPrefs());
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
    public Path getStudentBookFilePath() {
        return userPrefs.getStudentBookFilePath();
    }

    @Override
    public void setStudentBookFilePath(Path studentBookFilePath) {
        requireNonNull(studentBookFilePath);
        userPrefs.setStudentBookFilePath(studentBookFilePath);
    }

    //=========== studentBook ================================================================================

    @Override
    public void setStudentBook(ReadOnlyStudentBook studentBook) {
        this.studentBook.resetData(studentBook);
    }

    @Override
    public ReadOnlyStudentBook getStudentBook() {
        return studentBook;
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return studentBook.getStudentList();
    }

    @Override
    public boolean isExistingMatricNumber(MatriculationNumber matricNum) {
        return studentBook.isExistingMatricNumber(matricNum);
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return studentBook.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        studentBook.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        studentBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        studentBook.setStudent(target, editedStudent);
    }

    public Student getStudent(MatriculationNumber matriculationNumber) {
        requireNonNull(matriculationNumber);
        assert studentBook != null;
        assert MatriculationNumber.isValidMatric(matriculationNumber.value);
        return studentBook.getStudent(matriculationNumber);
    }

    @Override
    public List<Appointment> getAppointmentList() {
        return studentBook.getFlatAppointmentList();
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) throws CommandException {
        requireAllNonNull(target, editedAppointment);
        studentBook.setAppointment(target, editedAppointment);
    }

    @Override
    public Appointment getAppointment(MatriculationNumber matriculationNumber) {
        return studentBook.getAppointment(matriculationNumber);
    }

    @Override
    public Appointment getAppointmentToEdit(MatriculationNumber matriculationNumber) {
        Appointment appointmentToEdit = null;
        List<SameDateAppointmentList> lastShownList = getStudentBook().getAppointmentList();
        for (SameDateAppointmentList sList : lastShownList) {
            for (Appointment a : sList) {
                if (a.getMatriculationNumber().equals(matriculationNumber)) {
                    appointmentToEdit = a;
                }
            }
        }
        return appointmentToEdit;

    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return studentBook.hasAppointment(appointment);
    }

    @Override
    public boolean hasOverlappingAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return studentBook.hasOverlappingAppointment(appointment);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        studentBook.addAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        studentBook.removeAppointment(appointment);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedstudentBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    //=========== Filtered Appointment List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedstudentBook}
     * @return
     */
    @Override
    public ObservableList<SameDateAppointmentList> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<SameDateAppointmentList> listPredicate,
                                              Predicate<Appointment> predicate) {
        requireAllNonNull(listPredicate, predicate);
        filteredAppointments.setPredicate(listPredicate);
        filteredAppointments.stream().forEach(apptFilteredList ->
                apptFilteredList.updateFilteredAppointmentList(predicate));
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
        return studentBook.equals(other.studentBook)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}

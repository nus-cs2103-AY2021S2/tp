package seedu.student.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.student.commons.exceptions.IllegalValueException;
import seedu.student.model.ReadOnlyStudentBook;
import seedu.student.model.StudentBook;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.Student;

/**
 * An Immutable StudentBook that is serializable to JSON format.
 */
@JsonRootName(value = "studentbook")
class JsonSerializableStudentBook {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate student(s).";
    public static final String MESSAGE_CLASHING_APPOINTMENT = "Appointment list contains clashing appointment(s),";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointment list contains duplicate appointment(s),";
    public static final String MESSAGE_MISSING_STUDENT = "The student does not exist in the records.";
    public static final String MESSAGE_INVALID_START_TIME = "Time should be a valid time of the form HH:00 or HH:30";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStudentBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableStudentBook(@JsonProperty("students") List<JsonAdaptedStudent> student,
                                       @JsonProperty("appointments") List<JsonAdaptedAppointment> appointment) {
        this.students.addAll(student);
        this.appointments.addAll(appointment);
    }

    /**
     * Converts a given {@code ReadOnlyStudentBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStudentBook}.
     */
    public JsonSerializableStudentBook(ReadOnlyStudentBook source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        appointments.addAll(source.getFlatAppointmentList().stream()
                .map(JsonAdaptedAppointment::new).collect(Collectors.toList()));
    }

    /**
     * Converts this student book into the model's {@code StudentBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StudentBook toModelType() throws IllegalValueException {
        StudentBook studentBook = new StudentBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();

            if (studentBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            studentBook.addStudent(student);
        }
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();

            boolean isExistingStudent = studentBook.isExistingMatricNumber(appointment.getMatriculationNumber());

            if (!isExistingStudent) {
                throw new IllegalValueException(MESSAGE_MISSING_STUDENT);
            }

            if (studentBook.hasOverlappingAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_CLASHING_APPOINTMENT);
            }

            if (studentBook.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }

            studentBook.addAppointment(appointment);
        }
        return studentBook;
    }

}

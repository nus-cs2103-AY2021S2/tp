package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.StudentBook;
import seedu.address.model.ReadOnlyStudentBook;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "studentbook")
class JsonSerializableStudentBook {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> student = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStudentBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableStudentBook(@JsonProperty("persons") List<JsonAdaptedStudent> student) {
        this.student.addAll(student);
    }

    /**
     * Converts a given {@code ReadOnlyStudentBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStudentBook}.
     */
    public JsonSerializableStudentBook(ReadOnlyStudentBook source) {
        student.addAll(source.getPersonList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this student book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StudentBook toModelType() throws IllegalValueException {
        StudentBook studentBook = new StudentBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : student) {
            Person person = jsonAdaptedStudent.toModelType();
            if (studentBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            studentBook.addPerson(person);
        }
        return studentBook;
    }

}

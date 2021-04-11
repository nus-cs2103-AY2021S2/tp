package seedu.student.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.student.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.student.commons.exceptions.IllegalValueException;
import seedu.student.commons.util.JsonUtil;
import seedu.student.model.StudentBook;
import seedu.student.testutil.TypicalStudents;

public class JsonSerializableStudentBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStudentBookTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsStudentBook.json");
    private static final Path INVALID_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("invalidStudentsStudentBook.json");
    private static final Path DUPLICATE_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentsStudentBook.json");
    private static final Path DUPLICATE_JSON_KEY_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentsStudentBook.json");
    private static final Path NO_STUDENT_FOR_APPOINTMENT_FILE = TEST_DATA_FOLDER
            .resolve("invalidAppointmentStudentBook.json");
    private static final Path INVALID_APPOINTMENT_FILE = TEST_DATA_FOLDER
            .resolve("invalidDuplicateAppointmentStudentBook.json");
    private static final Path INVALID_APPOINTMENT_TIME_FILE = TEST_DATA_FOLDER
            .resolve("invalidAppointmentTimeStudentBook.json");
    private static final Path INVALID_APPOINTMENT_OVERLAP_FILE = TEST_DATA_FOLDER
            .resolve("invalidAppointmentOverlapStudentBook.json");

    @Test
    public void toModelType_validStudentBook() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableStudentBook.class).get();
        JsonSerializableStudentBook dataFromFile2 = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableStudentBook.class).get();
        assertEquals(dataFromFile.toModelType().getStudentList(), dataFromFile2.toModelType().getStudentList());
    }

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableStudentBook.class).get();
        StudentBook addressBookFromFile = dataFromFile.toModelType();
        StudentBook typicalStudentsStudentBook = TypicalStudents.getTypicalStudentBook();
        assertEquals(addressBookFromFile, typicalStudentsStudentBook);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENTS_FILE,
                JsonSerializableStudentBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENTS_FILE,
                JsonSerializableStudentBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStudentBook.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateJsonKey_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_JSON_KEY_FILE,
                JsonSerializableStudentBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStudentBook.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_missingStudentForAppointment_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(NO_STUDENT_FOR_APPOINTMENT_FILE,
                JsonSerializableStudentBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStudentBook.MESSAGE_MISSING_STUDENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidAppointmentStartTime_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(INVALID_APPOINTMENT_TIME_FILE,
                JsonSerializableStudentBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStudentBook.MESSAGE_INVALID_START_TIME,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidExtraAppointment_throwsIllegalValueException() throws Exception {

        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(INVALID_APPOINTMENT_FILE,
                JsonSerializableStudentBook.class).get();

        assertThrows(IllegalValueException.class, JsonSerializableStudentBook.MESSAGE_DUPLICATE_APPOINTMENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidAppointmentOverlap_throwsIllegalValueException() throws Exception {

        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(INVALID_APPOINTMENT_OVERLAP_FILE,
                JsonSerializableStudentBook.class).get();

        assertThrows(IllegalValueException.class, JsonSerializableStudentBook.MESSAGE_CLASHING_APPOINTMENT,
                dataFromFile::toModelType);
    }
}

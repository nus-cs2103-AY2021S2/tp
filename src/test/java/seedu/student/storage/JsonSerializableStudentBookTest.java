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

}

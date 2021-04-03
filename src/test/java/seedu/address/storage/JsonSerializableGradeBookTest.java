package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.GradeBook;
import seedu.address.testutil.TypicalGrades;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSerializableGradeBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableGradeBookTest");
    private static final Path TYPICAL_GRADES_FILE = TEST_DATA_FOLDER.resolve("typicalGradeGradeBook.json");
    private static final Path INVALID_GRADES_FILE = TEST_DATA_FOLDER.resolve("invalidGradeGradeBook.json");
    private static final Path DUPLICATE_GRADES_FILE = TEST_DATA_FOLDER.resolve("duplicateGradeGradeBook.json");

    @Test
    public void toModelType_typicalGradesFile_success() throws Exception {
        JsonSerializableGradeBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_GRADES_FILE,
                JsonSerializableGradeBook.class).get();
        GradeBook gradeBookFromFile = dataFromFile.toModelType();
        GradeBook typicalGradesTutorBook = TypicalGrades.getTypicalGradeBook();
        assertEquals(gradeBookFromFile, typicalGradesTutorBook);
    }

    @Test
    public void toModelType_invalidGradeFile_throwsIllegalValueException() throws Exception {
        JsonSerializableGradeBook dataFromFile = JsonUtil.readJsonFile(INVALID_GRADES_FILE,
                JsonSerializableGradeBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateGrades_throwsIllegalValueException() throws Exception {
        JsonSerializableGradeBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_GRADES_FILE,
                JsonSerializableGradeBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableGradeBook.MESSAGE_DUPLICATE_GRADE,
                dataFromFile::toModelType);
    }
}

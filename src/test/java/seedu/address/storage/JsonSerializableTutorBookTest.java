package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TutorBook;
import seedu.address.testutil.TypicalTutors;

public class JsonSerializableTutorBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTutorBookTest");
    private static final Path TYPICAL_TUTORS_FILE = TEST_DATA_FOLDER.resolve("typicalTutorTutorBook.json");
    private static final Path INVALID_TUTORS_FILE = TEST_DATA_FOLDER.resolve("invalidTutorTutorBook.json");
    private static final Path DUPLICATE_TUTORS_FILE = TEST_DATA_FOLDER.resolve("duplicateTutorTutorBook.json");

    @Test
    public void toModelType_typicalTutorsFile_success() throws Exception {
        JsonSerializableTutorBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TUTORS_FILE,
                JsonSerializableTutorBook.class).get();
        TutorBook tutorBookFromFile = dataFromFile.toModelType();
        TutorBook typicalTutorsTutorBook = TypicalTutors.getTypicalTutorBook();
        assertEquals(tutorBookFromFile, typicalTutorsTutorBook);
    }

    @Test
    public void toModelType_invalidTutorFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTutorBook dataFromFile = JsonUtil.readJsonFile(INVALID_TUTORS_FILE,
                JsonSerializableTutorBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTutors_throwsIllegalValueException() throws Exception {
        JsonSerializableTutorBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TUTORS_FILE,
                JsonSerializableTutorBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTutorBook.MESSAGE_DUPLICATE_TUTOR,
                dataFromFile::toModelType);
    }

}

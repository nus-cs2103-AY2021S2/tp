package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Picture;

public class FileUtilTest {

    public static final Path PNG_FILE = Path.of("src", "test", "data", "FileTest", "png_file.png");
    public static final Path JPG_FILE = Path.of("src", "test", "data", "FileTest", "jpg_file.jpg");
    public static final Path JSON_FILE = Path.of("src", "test", "data", "FileTest", "json_file.json");
    public static final Path PDF_FILE = Path.of("src", "test", "data", "FileTest", "pdf_file.pdf");
    public static final Path TEXT_FILE = Path.of("src", "test", "data", "FileTest", "text_file.txt");

    @Test
    public void isValidPath() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

    @Test
    public void isValidImage_success() {
        assertTrue(assertDoesNotThrow(() -> FileUtil.hasMagicNumber(PNG_FILE, Picture.IMAGE_MAGIC_NUMBERS)));
        assertTrue(assertDoesNotThrow(() -> FileUtil.hasMagicNumber(JPG_FILE, Picture.IMAGE_MAGIC_NUMBERS)));
    }

    @Test
    public void isInvalidImage_success() {
        assertFalse(assertDoesNotThrow(() -> FileUtil.hasMagicNumber(JSON_FILE, Picture.IMAGE_MAGIC_NUMBERS)));
        assertFalse(assertDoesNotThrow(() -> FileUtil.hasMagicNumber(PDF_FILE, Picture.IMAGE_MAGIC_NUMBERS)));
        assertFalse(assertDoesNotThrow(() -> FileUtil.hasMagicNumber(TEXT_FILE, Picture.IMAGE_MAGIC_NUMBERS)));
    }

}

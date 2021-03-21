package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_FILE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPictureCommand;

public class AddPictureCommandParserTest {
    private final AddPictureCommandParser parser = new AddPictureCommandParser();

    private final FileSystem fs = FileSystems.getDefault();

    private final String relativePathStr = concatPaths("Desktop", "steven.jpg");
    private final Path relativePath = Path.of("Desktop", "steven.jpg");

    private final String absolutePathStr = concatPathsFromRoot("Users", "bob", "Desktop", "steven.jpg");
    private final Path absolutePath = Path.of("/", "Users", "bob", "Desktop", "steven.jpg");

    private final String pathWithSpaceStr = concatPaths("Desktop", "steven ng.jpg");
    private final Path pathWithSpace = Path.of("Desktop", "steven ng.jpg");

    private String concatPaths(String... content) {
        return String.join(fs.getSeparator(), content);
    }

    private String concatPathsFromRoot(String... content) {
        String root = Path.of("").toAbsolutePath().getRoot().toString();
        return root + String.join(fs.getSeparator(), content);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, "1 " + relativePathStr,
                new AddPictureCommand(Index.fromOneBased(1), relativePath));
        assertParseSuccess(parser, "1 " + absolutePathStr,
                new AddPictureCommand(Index.fromOneBased(1), absolutePath));
        assertParseSuccess(parser, "1 " + pathWithSpaceStr,
                new AddPictureCommand(Index.fromOneBased(1), pathWithSpace));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPictureCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, relativePathStr, expectedMessage);

        // missing path
        assertParseFailure(parser, "1", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Out of bound indexes
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPictureCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "0 somefile", expectedMessage);
        assertParseFailure(parser, "-1 somefile", expectedMessage);

        // null byte
        assertParseFailure(parser, "1 \0somefile", String.format(MESSAGE_INVALID_FILE, "\0somefile"));
    }
}

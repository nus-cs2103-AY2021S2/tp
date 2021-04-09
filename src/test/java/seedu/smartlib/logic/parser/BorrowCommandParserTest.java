package seedu.smartlib.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.logic.commands.BorrowCommand;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.record.IncompleteRecord;
import seedu.smartlib.testutil.BookBuilder;
import seedu.smartlib.testutil.ReaderBuilder;

import java.time.LocalDateTime;

import static seedu.smartlib.logic.commands.CommandTestUtil.AUTHOR_DESC_HARRY;
import static seedu.smartlib.logic.commands.CommandTestUtil.AUTHOR_DESC_MAZE;
import static seedu.smartlib.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.GENRE_DESC_HARRY;
import static seedu.smartlib.logic.commands.CommandTestUtil.GENRE_DESC_MAZE;
import static seedu.smartlib.logic.commands.CommandTestUtil.ISBN_DESC_HARRY;
import static seedu.smartlib.logic.commands.CommandTestUtil.ISBN_DESC_MAZE;
import static seedu.smartlib.logic.commands.CommandTestUtil.NAME_DESC_HARRY;
import static seedu.smartlib.logic.commands.CommandTestUtil.NAME_DESC_MAZE;
import static seedu.smartlib.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.smartlib.logic.commands.CommandTestUtil.PUBLISHER_DESC_HARRY;
import static seedu.smartlib.logic.commands.CommandTestUtil.PUBLISHER_DESC_MAZE;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_BOOK;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;
import static seedu.smartlib.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.smartlib.testutil.TypicalModels.MAZE;
import static seedu.smartlib.testutil.TypicalModels.BOB;

public class BorrowCommandParserTest {
    BorrowCommandParser parser = new BorrowCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {

    }

    @Test
    public void parse_invalidValue_failure() {

    }
}

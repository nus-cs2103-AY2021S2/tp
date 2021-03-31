package seedu.smartlib.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_BOOK;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_PUBLISHER;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.smartlib.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.smartlib.commons.core.index.Index;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.book.Barcode;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.book.BookNameContainsKeywordsPredicate;
import seedu.smartlib.model.reader.NameContainsKeywordsPredicate;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.Record;
import seedu.smartlib.model.record.RecordContainsBarcodePredicate;
import seedu.smartlib.testutil.EditReaderDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    //=========== Books ==================================================================================
    public static final String VALID_NAME_HARRY = "Harry Potter";
    public static final String VALID_NAME_MAZE = "Maze Runner";
    public static final String VALID_AUTHOR_HARRY = "JK Rowling";
    public static final String VALID_AUTHOR_MAZE = "James Dashner";
    public static final String VALID_PUBLISHER_HARRY = "Scholastic";
    public static final String VALID_PUBLISHER_MAZE = "Dell Publishing";
    public static final String VALID_ISBN_HARRY = "9780439708180";
    public static final String VALID_ISBN_MAZE = "9780307582881";
    public static final String VALID_GENRE_HARRY = "Fantasy";
    public static final String VALID_GENRE_MAZE = "SciFi";

    public static final String NAME_DESC_HARRY = " " + PREFIX_BOOK + VALID_NAME_HARRY;
    public static final String NAME_DESC_MAZE = " " + PREFIX_BOOK + VALID_NAME_MAZE;
    public static final String AUTHOR_DESC_HARRY = " " + PREFIX_AUTHOR + VALID_AUTHOR_HARRY;
    public static final String AUTHOR_DESC_MAZE = " " + PREFIX_AUTHOR + VALID_AUTHOR_MAZE;
    public static final String PUBLISHER_DESC_HARRY = " " + PREFIX_PUBLISHER + VALID_PUBLISHER_HARRY;
    public static final String PUBLISHER_DESC_MAZE = " " + PREFIX_PUBLISHER + VALID_PUBLISHER_MAZE;
    public static final String ISBN_DESC_HARRY = " " + PREFIX_ISBN + VALID_ISBN_HARRY;
    public static final String ISBN_DESC_MAZE = " " + PREFIX_ISBN + VALID_ISBN_MAZE;
    public static final String GENRE_DESC_HARRY = " " + PREFIX_GENRE + VALID_GENRE_HARRY;
    public static final String GENRE_DESC_MAZE = " " + PREFIX_GENRE + VALID_GENRE_MAZE;

    public static final String INVALID_BOOKNAME_DESC = " " + PREFIX_BOOK + "Sorcerer's Stone";
    public static final String INVALID_AUTHOR_DESC = " " + PREFIX_AUTHOR + "J.K. Rowling";
    public static final String INVALID_PUBLISHER_DESC = " " + PREFIX_PUBLISHER;
    public static final String INVALID_ISBN_DESC = " " + PREFIX_ISBN + "837aa";
    public static final String INVALID_GENRE_DESC = " " + PREFIX_GENRE + "Sci-Fi";
    //=========== Readers ==================================================================================
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_READER + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_READER + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_READER + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditReaderDescriptor DESC_AMY;
    public static final EditCommand.EditReaderDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditReaderDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditReaderDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the smartlib, filtered reader list and selected reader in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        SmartLib expectedSmartLib = new SmartLib(actualModel.getSmartLib());
        List<Reader> expectedFilteredList = new ArrayList<>(actualModel.getFilteredReaderList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedSmartLib, actualModel.getSmartLib());
        assertEquals(expectedFilteredList, actualModel.getFilteredReaderList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the reader at the given {@code targetIndex} in the
     * {@code model}'s smartlib.
     */
    public static void showReaderAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredReaderList().size());

        Reader reader = model.getFilteredReaderList().get(targetIndex.getZeroBased());
        final String[] splitName = reader.getName().toString().split("\\s+");
        model.updateFilteredReaderList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredReaderList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the book at the given {@code targetIndex} in the
     * {@code model}'s smartlib.
     */
    public static void showBookAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBookList().size());

        Book book = model.getFilteredBookList().get(targetIndex.getZeroBased());
        final String[] splitName = book.getName().toString().split("\\s+");
        model.updateFilteredBookList(new BookNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredBookList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the record at the given {@code targetIndex} in the
     * {@code model}'s smartlib.
     */
    public static void showRecordAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredRecordList().size());

        Record record = model.getFilteredRecordList().get(targetIndex.getZeroBased());
        final Barcode barcode = record.getBookBarcode();
        model.updateFilteredRecordList((new RecordContainsBarcodePredicate(barcode)));
        assertEquals(1, model.getFilteredRecordList().size());
    }


}

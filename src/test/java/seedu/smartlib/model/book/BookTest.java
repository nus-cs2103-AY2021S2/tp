package seedu.smartlib.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_AUTHOR_HARRY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_ISBN_HARRY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_NAME_MAZE;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_PUBLISHER_HARRY;
import static seedu.smartlib.testutil.TypicalModels.ALICE;
import static seedu.smartlib.testutil.TypicalModels.HARRY_PORTER;
import static seedu.smartlib.testutil.TypicalModels.MAZE;
import static seedu.smartlib.testutil.TypicalModels.PROMISE_LAND;
import static seedu.smartlib.testutil.TypicalModels.SECRET;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.testutil.BookBuilder;

public class BookTest {

    @Test
    public void isSameBook() {
        // same object -> returns true
        assertTrue(HARRY_PORTER.isSameBook(HARRY_PORTER));

        // null -> returns false
        assertFalse(HARRY_PORTER.isSameBook(null));

        // same book name, all other attributes different -> returns true
        Book editedHarry = new BookBuilder(HARRY_PORTER).withAuthor(VALID_AUTHOR_HARRY)
                .withPublisher(VALID_PUBLISHER_HARRY).withIsbn(VALID_ISBN_HARRY).build();
        assertTrue(HARRY_PORTER.isSameBook(editedHarry));

        // different name, all other attributes same -> returns false
        editedHarry = new BookBuilder(HARRY_PORTER).withName(VALID_NAME_MAZE).build();
        assertFalse(HARRY_PORTER.isSameBook(editedHarry));

        // name differs in case, all other attributes same -> returns false
        Book editedMaze = new BookBuilder(MAZE).withName(VALID_NAME_MAZE.toLowerCase()).build();
        assertFalse(MAZE.isSameBook(editedMaze));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_MAZE + " ";
        editedMaze = new BookBuilder(MAZE).withName(nameWithTrailingSpaces).build();
        assertFalse(MAZE.isSameBook(editedMaze));
    }

    @Test
    public void isOverdue() {
        // not borrowed
        assertFalse(HARRY_PORTER.isOverdue());
        assertFalse(PROMISE_LAND.isOverdue());

        // borrowed but not overdue
        Book book = new Book(
                HARRY_PORTER.getName(),
                HARRY_PORTER.getAuthor(),
                HARRY_PORTER.getPublisher(),
                HARRY_PORTER.getIsbn(),
                HARRY_PORTER.getBarcode(),
                HARRY_PORTER.getGenre(),
                ALICE.getName(),
                new DateBorrowed(LocalDateTime.now())
        );
        assertFalse(book.isOverdue());

        // borrowed and overdue
        assertTrue(SECRET.isOverdue());
    }

    @Test
    public void equals() {
        Book book = new Book(
                HARRY_PORTER.getName(),
                HARRY_PORTER.getAuthor(),
                HARRY_PORTER.getPublisher(),
                HARRY_PORTER.getIsbn(),
                HARRY_PORTER.getBarcode(),
                HARRY_PORTER.getGenre()
        );

        // null -> returns false
        assertFalse(book.equals(null));

        // different types -> returns false
        assertFalse(book.equals(0.5f));
        assertFalse(book.equals(" "));

        // same object -> returns true
        assertTrue(book.equals(book));

        // same values -> returns true
        Book bookCopy = new Book(
                HARRY_PORTER.getName(),
                HARRY_PORTER.getAuthor(),
                HARRY_PORTER.getPublisher(),
                HARRY_PORTER.getIsbn(),
                HARRY_PORTER.getBarcode(),
                HARRY_PORTER.getGenre()
        );
        assertTrue(book.equals(bookCopy));

        // different values -> returns false
        assertFalse(book.equals(PROMISE_LAND));
    }

    @Test
    public void hashcode() {
        Book book = new Book(
                HARRY_PORTER.getName(),
                HARRY_PORTER.getAuthor(),
                HARRY_PORTER.getPublisher(),
                HARRY_PORTER.getIsbn(),
                HARRY_PORTER.getBarcode(),
                HARRY_PORTER.getGenre()
        );

        // same object -> returns same hashcode
        assertEquals(book.hashCode(), book.hashCode());

        // same values -> returns same hashcode
        Book bookCopy = new Book(
                HARRY_PORTER.getName(),
                HARRY_PORTER.getAuthor(),
                HARRY_PORTER.getPublisher(),
                HARRY_PORTER.getIsbn(),
                HARRY_PORTER.getBarcode(),
                HARRY_PORTER.getGenre()
        );
        assertEquals(book.hashCode(), bookCopy.hashCode());

        // different values -> returns different hashcode
        assertNotEquals(book.hashCode(), PROMISE_LAND.hashCode());
    }

}

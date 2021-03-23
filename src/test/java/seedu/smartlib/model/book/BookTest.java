package seedu.smartlib.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_AUTHOR_HARRY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_ISBN_HARRY;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_NAME_MAZE;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_PUBLISHER_HARRY;
import static seedu.smartlib.testutil.TypicalModels.HARRY_PORTER;
import static seedu.smartlib.testutil.TypicalModels.MAZE;

import org.junit.jupiter.api.Test;

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

}

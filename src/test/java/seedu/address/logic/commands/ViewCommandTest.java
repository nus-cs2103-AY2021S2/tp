package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashBack;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());

    //TODO: Add more test cases after the TypicalPerson.java is updated
    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_FLASHCARD);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_FLASHCARD);

        //same object => return true
        assertEquals(viewFirstCommand, viewFirstCommand);
        //same value => return true
        assertEquals(viewFirstCommand, new ViewCommand(INDEX_FIRST_FLASHCARD));
        assertEquals(viewSecondCommand, new ViewCommand(INDEX_SECOND_FLASHCARD));
        //different type
        assertNotEquals("view", viewFirstCommand);
        assertNotEquals(null, viewFirstCommand);
        assertNotEquals(viewFirstCommand, viewSecondCommand);
    }
}

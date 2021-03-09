package seedu.dictionote.logic.commands;

import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
<<<<<<< HEAD
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;
import static seedu.dictionote.testutil.TypicalPersons.getTypicalAddressBook;
=======
import static seedu.dictionote.testutil.TypicalContacts.getTypicalAddressBook;
>>>>>>> aa56a9f5d6f489f0ec7f45011daa26f5e5fec218

import org.junit.jupiter.api.Test;

import seedu.dictionote.model.AddressBook;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNoteBook());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalNoteBook());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}

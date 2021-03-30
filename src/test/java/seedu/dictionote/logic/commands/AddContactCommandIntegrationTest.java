package seedu.dictionote.logic.commands;

import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dictionote.model.DefinitionBook;
import seedu.dictionote.model.Dictionary;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.NoteBook;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.testutil.ContactBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddContactCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalContactsList(),
                new UserPrefs(), new NoteBook(), new Dictionary(), new DefinitionBook());
    }

    @Test
    public void execute_newContact_success() {
        Contact validContact = new ContactBuilder().build();

        Model expectedModel = new ModelManager(model.getContactsList(),
                new UserPrefs(), new NoteBook(), new Dictionary(), new DefinitionBook());
        expectedModel.addContact(validContact);

        assertCommandSuccess(new AddContactCommand(validContact), model,
                String.format(AddContactCommand.MESSAGE_SUCCESS, validContact), expectedModel);
    }

    @Test
    public void execute_duplicateContact_throwsCommandException() {
        Contact contactInList = model.getContactsList().getContactList().get(0);
        assertCommandFailure(new AddContactCommand(contactInList), model, AddContactCommand.MESSAGE_DUPLICATE_CONTACT);
    }

}

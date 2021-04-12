package seedu.ta.logic.commands;

import static seedu.ta.commons.core.Messages.MESSAGE_DUPLICATE_CONTACT;
import static seedu.ta.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ta.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ta.testutil.TypicalTeachingAssistant.getTypicalTeachingAssistant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.ta.model.Model;
import seedu.ta.model.ModelManager;
import seedu.ta.model.UserPrefs;
import seedu.ta.model.contact.Contact;
import seedu.ta.testutil.ContactBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddContactCommand}.
 */
public class AddContactCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTeachingAssistant(), new UserPrefs());
    }

    @Test
    public void execute_newContact_success() {
        Contact validContact = new ContactBuilder().build();

        Model expectedModel = new ModelManager(model.getTeachingAssistant(), new UserPrefs());
        expectedModel.addContact(validContact);

        assertCommandSuccess(new AddContactCommand(validContact), model,
                String.format(AddContactCommand.MESSAGE_SUCCESS, validContact), expectedModel);
    }

    @Test
    public void execute_duplicateContact_throwsCommandException() {
        Contact contactInList = model.getTeachingAssistant().getContactList().get(0);
        assertCommandFailure(new AddContactCommand(contactInList), model, MESSAGE_DUPLICATE_CONTACT);
    }
}

package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersonIds.FIRST_PERSON_ID;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.PersonIdPredicate;

public class ViewPersonCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_viewStudent() {
        expectedModel.updateFilteredPersonList(new PersonIdPredicate(FIRST_PERSON_ID));
        assertCommandSuccess(new ViewPersonCommand(new PersonIdPredicate(FIRST_PERSON_ID)),
                model, ViewPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_viewTutor_NotFound_showMessage() {
        expectedModel.updateFilteredPersonList(new PersonIdPredicate(new PersonId("t/1000")));
        assertCommandSuccess(new ViewPersonCommand(new PersonIdPredicate(new PersonId("t/1000"))),
                model, ViewPersonCommand.MESSAGE_NO_PERSON_FOUND, expectedModel);

    }

    @Test
    public void execute_invalidCommand_throwError() {
        
    }
}

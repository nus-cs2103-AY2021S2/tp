package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.PersonIdPredicate;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.PersonTypePredicate;



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
        PersonId personId = new PersonId("s/1");
        assertCommandSuccess(new ViewPersonCommand(new PersonIdPredicate(personId)),
                model, ViewPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }
}

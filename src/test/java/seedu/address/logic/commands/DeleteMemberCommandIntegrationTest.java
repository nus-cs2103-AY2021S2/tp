package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalHeyMatez;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class DeleteMemberCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());
    }

    @Test
    public void execute_deletePerson_success() {
        Person validPerson = model.getHeyMatez().getPersonList().get(0);
        Model expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
        expectedModel.deletePerson(validPerson);

        assertCommandSuccess(new DeleteMemberCommand(validPerson.getName()), model,
                String.format(DeleteMemberCommand.MESSAGE_DELETE_PERSON_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_deletePerson_throwsNameNotValid() {
        Name name = new PersonBuilder().withName("Not a name").build().getName();
        assertCommandFailure(new DeleteMemberCommand(name), model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
    }

}

package seedu.heymatez.logic.commands;

import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.testutil.TypicalPersons.getTypicalHeyMatez;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.heymatez.commons.core.Messages;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.person.Name;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code DeleteMemberCommand}.
 */
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

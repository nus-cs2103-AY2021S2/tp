package seedu.heymatez.logic.commands;

import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.heymatez.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.heymatez.testutil.TypicalPersons.getTypicalHeyMatez;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddMemberCommand}.
 */
public class AddMemberCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHeyMatez(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();
        Model expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddMemberCommand(validPerson), model,
                String.format(AddMemberCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getHeyMatez().getPersonList().get(0);
        assertCommandFailure(new AddMemberCommand(personInList), model, AddMemberCommand.MESSAGE_DUPLICATE_PERSON);
    }

}

package fooddiary.logic.commands;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fooddiary.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fooddiary.model.Model;
import fooddiary.model.ModelManager;
import fooddiary.model.UserPrefs;
import fooddiary.model.entry.Entry;
import fooddiary.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Entry validEntry = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getFoodDiary(), new UserPrefs());
        expectedModel.addPerson(validEntry);

        assertCommandSuccess(new AddCommand(validEntry), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validEntry), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Entry entryInList = model.getFoodDiary().getPersonList().get(0);
        CommandTestUtil.assertCommandFailure(new AddCommand(entryInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}

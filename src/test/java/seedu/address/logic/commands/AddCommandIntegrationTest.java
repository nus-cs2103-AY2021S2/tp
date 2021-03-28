package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.resident.AddResidentCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.resident.Resident;
import seedu.address.testutil.ResidentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddResidentCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newResident_success() {
        Resident validResident = new ResidentBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addResident(validResident);

        assertCommandSuccess(new AddResidentCommand(validResident), model,
                String.format(AddResidentCommand.MESSAGE_SUCCESS, validResident), expectedModel);
    }

    @Test
    public void execute_duplicateResident_throwsCommandException() {
        Resident residentInList = model.getAddressBook().getResidentList().get(0);
        assertCommandFailure(new AddResidentCommand(residentInList), model,
            AddResidentCommand.MESSAGE_DUPLICATE_RESIDENT);
    }

}

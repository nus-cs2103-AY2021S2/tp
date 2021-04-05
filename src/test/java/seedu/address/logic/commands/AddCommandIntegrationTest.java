package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPassengers.getTypicalAddressBookPassengers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.testutil.PassengerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookPassengers(), new UserPrefs());
    }

    @Test
    public void execute_newPassenger_success() {
        Passenger validPassenger = new PassengerBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPassenger(validPassenger);

        assertCommandSuccess(new AddCommand(validPassenger), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPassenger), expectedModel);
    }

    @Test
    public void execute_duplicatePassenger_throwsCommandException() {
        Passenger passengerInList = model.getAddressBook().getPassengerList().get(0);
        assertCommandFailure(new AddCommand(passengerInList), model, AddCommand.MESSAGE_DUPLICATE_PASSENGER);
    }

}

package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestDataUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Debt;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ChangeDebtCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

    @Test
    public void execute_addDebtUnfilteredList_success() {
        Person editedPerson = new PersonBuilder(firstPerson).withDebt("10").build();
        Debt addedDebt = new Debt("10");
        ChangeDebtCommand cmd = new ChangeDebtCommand(INDEX_FIRST_PERSON, addedDebt, true);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        String expectedMessage = String.format(ChangeDebtCommand.MESSAGE_ADD_DEBT_SUCCESS,
                addedDebt.toUi(), editedPerson.getName());
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_subtractDebtUnfilteredList_success() {
        Debt expectedDebt = Debt.subtract(new Debt("0"), new Debt("10"));
        Person editedPerson = new PersonBuilder(firstPerson).withDebt(expectedDebt).build();
        Debt subtractedDebt = new Debt("10");
        ChangeDebtCommand cmd = new ChangeDebtCommand(INDEX_FIRST_PERSON, subtractedDebt, false);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        String expectedMessage = String.format(ChangeDebtCommand.MESSAGE_SUBTRACT_DEBT_SUCCESS,
                subtractedDebt.toUi(), editedPerson.getName());
        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Debt addedDebt = new Debt("10");
        ChangeDebtCommand cmd = new ChangeDebtCommand(outOfBoundIndex, addedDebt, true);
        assertCommandFailure(cmd, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}


package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.index.Index.fromZeroBased;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.state.State;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class UndoCommandTest {
    private AddressBook addressBook = getTypicalAddressBook();
    private Model model = new ModelManager(addressBook, new UserPrefs());
    private State state;

    @BeforeEach
    public void setUp() {
        state = new State();
        state.addState(addressBook);
    }

    @Test
    public void execute_oneState_failure() {
        assertCommandFailure(new UndoCommand(state), model, UndoCommand.MESSAGE_FAILED);
    }

    @Test
    public void execute_atLeastTwoStates_success() {
        try {
            Person editedPerson = new PersonBuilder().build();
            EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
            EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
            editCommand.execute(model);
            state.addState(new AddressBook(model.getAddressBook()));

            Model expectedModel1 = new ModelManager(getTypicalAddressBook(), new UserPrefs());
            assertCommandSuccess(new UndoCommand(state), model, UndoCommand.MESSAGE_SUCCESS, expectedModel1);
            assertEquals(getTypicalAddressBook(), state.getCurrentState());

            DeleteCommand deleteCommand = new DeleteCommand(fromZeroBased(0));
            deleteCommand.execute(model);
            ReadOnlyAddressBook afterDelete = new AddressBook(model.getAddressBook());
            state.addState(afterDelete);
            Model expectedModel2 = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

            AddCommand addCommand = new AddCommand(new PersonBuilder().build());
            addCommand.execute(model);
            state.addState(new AddressBook(model.getAddressBook()));
            assertCommandSuccess(new UndoCommand(state), model, UndoCommand.MESSAGE_SUCCESS, expectedModel2);
            assertEquals(afterDelete, state.getCurrentState());

        } catch (CommandException e) {
            throw new AssertionError("Command execution should not fail.");
        }
    }
}

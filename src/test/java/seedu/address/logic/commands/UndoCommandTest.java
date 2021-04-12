package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.index.Index.fromZeroBased;
import static seedu.address.logic.commands.CommandTestUtil.ADD_WORD;
import static seedu.address.logic.commands.CommandTestUtil.DELETE_WORD;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_WORD;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_COMMAND;
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
        state.addState(addressBook, EMPTY_COMMAND);
    }

    @Test
    public void execute_oneState_failure() {
        assertCommandFailure(new UndoCommand(state), model, UndoCommand.MESSAGE_NOTHING_TO_UNDO);
    }

    @Test
    public void execute_atLeastTwoStates_success() {
        try {
            // execute edit command
            Person editedPerson = new PersonBuilder().build();
            EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
            EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
            editCommand.execute(model);
            state.addState(new AddressBook(model.getAddressBook()), EDIT_WORD);

            // undo the edit command
            Model expectedModel1 = new ModelManager(getTypicalAddressBook(), new UserPrefs());
            assertCommandSuccess(new UndoCommand(state), model,
                    String.format(UndoCommand.MESSAGE_SUCCESS, EDIT_WORD), expectedModel1);
            assertEquals(getTypicalAddressBook(), state.getCurrentAddressBook());
            assertEquals(EMPTY_COMMAND, state.getCurrentCommand());

            // execute delete command
            DeleteCommand deleteCommand = new DeleteCommand(fromZeroBased(0));
            deleteCommand.execute(model);
            ReadOnlyAddressBook afterDelete = new AddressBook(model.getAddressBook());
            state.addState(afterDelete, DELETE_WORD);
            Model expectedModel2 = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

            // execute add command
            AddCommand addCommand = new AddCommand(new PersonBuilder().build());
            addCommand.execute(model);
            state.addState(new AddressBook(model.getAddressBook()), ADD_WORD);

            // undo add command
            assertCommandSuccess(new UndoCommand(state), model,
                    String.format(UndoCommand.MESSAGE_SUCCESS, ADD_WORD), expectedModel2);
            assertEquals(afterDelete, state.getCurrentAddressBook());
            assertEquals(DELETE_WORD, state.getCurrentCommand());

        } catch (CommandException e) {
            throw new AssertionError("Command execution should not fail.");
        }
    }
}

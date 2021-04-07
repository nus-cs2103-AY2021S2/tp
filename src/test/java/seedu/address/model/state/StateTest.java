package seedu.address.model.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.logic.commands.CommandTestUtil.DUMMY_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_LIST;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.testutil.PersonBuilder;

public class StateTest {
    private AddressBook addressBook = getTypicalAddressBook();
    private State state;

    @BeforeEach
    public void setUp() {
        state = new State();
        state.addState(addressBook, EMPTY_COMMAND);
    }

    @Test
    public void addState_addNullState_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> state.addState(null, DUMMY_COMMAND));
        assertThrows(AssertionError.class, () -> state.addState(addressBook, null));
    }

    @Test
    public void addState_addEmptyAddressBook_success() {
        state.addState(new AddressBook(), EMPTY_COMMAND);
        ReadOnlyAddressBook currState = state.getCurrentAddressBook();
        assertEquals(EMPTY_LIST, currState.toString());
    }

    @Test
    public void getPreviousState_oneState_returnsNull() {
        AddressBookCommandPair previousState = state.getPreviousState();
        assertNull(previousState);
    }

    @Test
    public void getPreviousState_moreThanTwoStates_returnSecondLast() {
        // add the first state in, i.e. an empty book.
        state.addState(new AddressBook(), EMPTY_COMMAND);
        AddressBookCommandPair previousState1 = state.getPreviousState();
        assertEquals(getTypicalAddressBook(), previousState1.getAddressBook());
        assertEquals(EMPTY_COMMAND, previousState1.getCurrentCommand());

        // add the second state in, a book with a person in it.
        AddressBook temp = new AddressBook();
        temp.addPerson(new PersonBuilder().build());
        state.addState(temp, DUMMY_COMMAND);
        AddressBookCommandPair previousState2 = state.getPreviousState();
        assertEquals(EMPTY_LIST, previousState2.getAddressBook().toString());
        assertEquals(EMPTY_COMMAND, previousState2.getCurrentCommand());
    }
}

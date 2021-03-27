package seedu.address.model.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        state.addState(addressBook);
    }

    @Test
    public void addState_addNullState_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> state.addState(null));
    }

    @Test
    public void addState_addEmptyAddressBook_success() {
        state.addState(new AddressBook());
        ReadOnlyAddressBook currState = state.getCurrentState();
        assertEquals("[]", currState.toString());
    }

    @Test
    public void getPreviousState_oneState_returnsNull() {
        ReadOnlyAddressBook previousState = state.getPreviousState();
        assertNull(previousState);
    }

    @Test
    public void getPrevious_moreThanTwoStates_returnSecondLast() {
        state.addState(new AddressBook());
        ReadOnlyAddressBook previousState1 = state.getPreviousState();
        assertEquals(getTypicalAddressBook(), previousState1);

        AddressBook temp = new AddressBook();
        temp.addPerson(new PersonBuilder().build());
        state.addState(temp);
        ReadOnlyAddressBook previousState2 = state.getPreviousState();
        assertEquals("[]", previousState2.toString());
    }
}

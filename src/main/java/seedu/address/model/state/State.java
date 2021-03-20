package seedu.address.model.state;

import java.util.LinkedList;

import seedu.address.model.ReadOnlyAddressBook;

/**
 * Represents the different states of the data. This is to facilitate the
 * implementation of undo feature.
 * Guarantees: immutability, non-null
 */
public class State {
    private final LinkedList<ReadOnlyAddressBook> addressBookStates;

    /**
     * Constructs a State object.
     */
    public State() {
        this.addressBookStates = new LinkedList<>();
    }

    /**
     * Adds a new state into the list.
     * @param currState Current state of the data.
     */
    public void addState(ReadOnlyAddressBook currState) {
        assert currState != null;
        this.addressBookStates.add(currState);
    }

    /**
     * Deletes the latest state.
     */
    public void deleteCurrentState() {
        this.addressBookStates.pollLast();
    }

    /**
     * Returns the current state of the data.
     * @return The current state of the data.
     */
    public ReadOnlyAddressBook getCurrentState() {
        return this.addressBookStates.peekLast();
    }

    /**
     * Returns the previous state.
     * @return The previous state.
     */
    public ReadOnlyAddressBook getPreviousState() {
        return addressBookStates.size() > 1
                ? this.addressBookStates.get(addressBookStates.size() - 2)
                : null;
    }
}

package seedu.partyplanet.commons.util;

import java.util.ArrayList;

import seedu.partyplanet.model.AddressBook;

/**
 * StateHistory records and retrieves the AddressBook state history
 */
public class StateHistory {
    private ArrayList<AddressBook> states;
    private int currentStatePointer;

    /**
     * Initialise StateHistory with the saved AddressBook
     */
    public StateHistory(AddressBook savedState) {
        this.states = new ArrayList<>();
        states.add(savedState);
    }

    /**
     * Adds a new AddressBook state to the history
     * @param newState the state to be added
     */
    public void addState(AddressBook newState) {
        if (currentStatePointer != states.size() - 1) {
            clearStatesAfterCurrent();
        }
        states.add(newState);
        currentStatePointer++;
    }

    /**
     * Retrieves the previous state of the AddressBook
     * @return the previous state of the AddressBook
     * @throws ArrayIndexOutOfBoundsException when there is no valid previous state
     */
    public AddressBook previousState() throws ArrayIndexOutOfBoundsException {
        currentStatePointer--;
        try {
            return states.get(currentStatePointer);
        } catch (ArrayIndexOutOfBoundsException e) {
            currentStatePointer++;
            throw e;
        }
    }

    /**
     * Retrieves the next state of the AddressBook
     * @return the next state of the AddressBook
     * @throws ArrayIndexOutOfBoundsException when there is no next state
     */
    public AddressBook nextState() throws ArrayIndexOutOfBoundsException {
        currentStatePointer++;
        try {
            return states.get(currentStatePointer);
        } catch (ArrayIndexOutOfBoundsException e) {
            currentStatePointer--;
            throw e;
        }
    }

    private void clearStatesAfterCurrent() {
        states.subList(currentStatePointer + 1, states.size() - 1).clear();
    }

}

package seedu.partyplanet.commons.util;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.partyplanet.commons.core.LogsCenter;
import seedu.partyplanet.model.ReadOnlyAddressBook;



/**
 * StateHistory records and retrieves the AddressBook state history
 */
public class StateHistory {
    private ArrayList<ReadOnlyAddressBook> states;
    private int currentStatePointer;


    private final Logger logger = LogsCenter.getLogger(StateHistory.class);

    /**
     * Initialise StateHistory with the saved AddressBook
     */
    public StateHistory(ReadOnlyAddressBook savedState) {
        this.states = new ArrayList<>();
        states.add(savedState);
        logger.info("Initialised stateHistory. Current state has " + states.get(states.size() - 1).toString()
                + ". Current number of states is: " + states.size());
    }

    /**
     * Adds a new AddressBook state to the history
     * @param newState the state to be added
     */
    public void addState(ReadOnlyAddressBook newState) {
        if (currentStatePointer != states.size() - 1) {
            clearStatesAfterCurrent();
        }
        states.add(newState);
        currentStatePointer++;
        logger.info("Added state to stateHistory. Current state has " + states.get(states.size() - 1).toString()
                + ". Current number of states is: " + states.size());
    }

    /**
     * Retrieves the previous state of the AddressBook
     * @return the previous state of the AddressBook
     * @throws ArrayIndexOutOfBoundsException when there is no valid previous state
     */
    public ReadOnlyAddressBook previousState() throws ArrayIndexOutOfBoundsException {
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
    public ReadOnlyAddressBook nextState() throws ArrayIndexOutOfBoundsException {
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

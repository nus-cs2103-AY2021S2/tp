package seedu.partyplanet.commons.util;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.partyplanet.commons.core.LogsCenter;
import seedu.partyplanet.model.AddressBook;
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
        this.states.add(new AddressBook(savedState));
        this.currentStatePointer = 0;
        logger.info("Initialised stateHistory." + " Current number of states is: " + states.size()
            + ". Currently on state: " + currentStatePointer);
    }

    /**
     * Adds a new AddressBook state to the history
     * @param newState the state to be added
     */
    public void addState(ReadOnlyAddressBook newState) {
        if (currentStatePointer != states.size() - 1) {
            clearStatesAfterCurrent();
        }
        states.add(new AddressBook(newState));
        currentStatePointer++;
        logger.info("Added state to stateHistory." + " Current number of states is: " + states.size()
                + ". Currently on state: " + currentStatePointer);
    }

    /**
     * Retrieves the previous state of the AddressBook
     * @return the previous state of the AddressBook
     * @throws IndexOutOfBoundsException when there is no valid previous state
     */
    public ReadOnlyAddressBook previousState() throws IndexOutOfBoundsException {
        currentStatePointer--;
        ReadOnlyAddressBook previousState;
        try {
            previousState = states.get(currentStatePointer);
        } catch (IndexOutOfBoundsException e) {
            currentStatePointer++;
            throw e;
        }
        logger.info("Went to previous state." + " Current number of states is: " + states.size()
                + ". Currently on state: " + currentStatePointer);
        return previousState;
    }

    // TODO  for redo command
    public ReadOnlyAddressBook nextState() {
        return states.get(currentStatePointer);
    }

    private void clearStatesAfterCurrent() {
        logger.info("called clear");
        states.subList(currentStatePointer + 1, states.size()).clear();
    }
}

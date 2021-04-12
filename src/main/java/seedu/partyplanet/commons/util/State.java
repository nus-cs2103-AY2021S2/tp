package seedu.partyplanet.commons.util;


import static seedu.partyplanet.commons.util.CollectionUtil.requireAllNonNull;

import seedu.partyplanet.model.AddressBook;
import seedu.partyplanet.model.EventBook;
import seedu.partyplanet.model.ReadOnlyAddressBook;
import seedu.partyplanet.model.ReadOnlyEventBook;

/**
 * Represents the state of PartyPlanet at a particular instance of time, and the command leading to this state.
 */
public class State {

    private String command;
    private ReadOnlyAddressBook addressBook;
    private ReadOnlyEventBook eventBook;

    /**
     * Constructs a {@code State}.
     * @param command the command leading to this state
     * @param addressBook the addressBook after the command is run
     * @param eventBook the eventBook after the command is run
     */
    public State(String command, ReadOnlyAddressBook addressBook, ReadOnlyEventBook eventBook) {
        requireAllNonNull(command, addressBook, eventBook);
        this.command = command;
        this.addressBook = new AddressBook(addressBook);
        this.eventBook = new EventBook(eventBook);
    }

    /**
     * Gets the command leading to this State
     * @return the command leading to this State
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Gets the addressBook in this State
     * @return the addressBook in this State
     */
    public ReadOnlyAddressBook getAddressBook() {
        return this.addressBook;
    }

    /**
     * Gets the eventBook in this State
     * @return the eventBook in this State
     */
    public ReadOnlyEventBook getEventBook() {
        return this.eventBook;
    }
}

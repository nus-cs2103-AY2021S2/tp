package seedu.address.model.state;

import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to contain a pair of address book and the command executed to obtain the address book state.
 * When the app is started, the currentCommand will be set as an empty string.
 *
 * Guarantees non-null and immutability.
 */
public class AddressBookCommandPair {
    private final ReadOnlyAddressBook addressBook;
    private final String currentCommand;

    /**
     * Constructor for creating a pair of address book and the relevant command.
     * @param addressBook The address book.
     * @param currentCommand The command executed to achieve the current state of address book.
     */
    public AddressBookCommandPair(ReadOnlyAddressBook addressBook, String currentCommand) {
        assert addressBook != null && currentCommand != null;
        this.addressBook = addressBook;
        this.currentCommand = currentCommand;
    }

    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    public String getCurrentCommand() {
        return currentCommand;
    }
}

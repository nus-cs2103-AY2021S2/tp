package seedu.address.model.budget;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.JsonAddressBookStorage;


/**
 * Class to help retrieve the address book without having to inititalize with
 * StorageManager.
 */
public class GetAddressBook {

    private ReadOnlyAddressBook addressBook;

    /**
     * Primary constructor.
     * @param relativeFilePath File path in string of the addressbook.json file
     */
    public GetAddressBook(String relativeFilePath) {
        try {
            Path path = Path.of(relativeFilePath);
            JsonAddressBookStorage jsonAddressBookStorage =
                    new JsonAddressBookStorage(path);

            Optional<ReadOnlyAddressBook> optionalAddressBook =
                    jsonAddressBookStorage.readAddressBook(path);

            this.addressBook =
                    optionalAddressBook.orElseGet(SampleDataUtil::getSampleAddressBook);

        } catch (Exception e) {
            // To use sample data book if address not found
            this.addressBook = SampleDataUtil.getSampleAddressBook();
        }

    }

    /**
     * Getter method that retrieves the {@code ReadOnlyAddressBook}
     */
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

}

package seedu.address.model.budget;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.JsonTutorBookStorage;


/**
 * Class to help retrieve the address book without having to inititalize with
 * StorageManager.
 */
public class GetAddressBook {

    private ReadOnlyTutorBook addressBook;

    /**
     * Primary constructor.
     * @param relativeFilePath File path in string of the addressbook.json file
     */
    public GetAddressBook(String relativeFilePath) {
        try {
            Path path = Path.of(relativeFilePath);
            JsonTutorBookStorage jsonAddressBookStorage =
                    new JsonTutorBookStorage(path);

            Optional<ReadOnlyTutorBook> optionalAddressBook =
                    jsonAddressBookStorage.readTutorBook(path);

            this.addressBook =
                    optionalAddressBook.orElseGet(SampleDataUtil::getSampleTutorBook);

        } catch (Exception e) {
            // To use sample data book if address not found
            this.addressBook = SampleDataUtil.getSampleTutorBook();
        }

    }

    /**
     * Getter method that retrieves the {@code ReadOnlyAddressBook}
     */
    public ReadOnlyTutorBook getAddressBook() {
        return addressBook;
    }

}

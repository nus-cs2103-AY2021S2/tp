package seedu.address.testutil;

import seedu.address.model.FlashBack;
import seedu.address.model.flashcard.Flashcard;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private FlashBack flashBack;

    public AddressBookBuilder() {
        flashBack = new FlashBack();
    }

    public AddressBookBuilder(FlashBack flashBack) {
        this.flashBack = flashBack;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Flashcard flashcard) {
        flashBack.addPerson(flashcard);
        return this;
    }

    public FlashBack build() {
        return flashBack;
    }
}

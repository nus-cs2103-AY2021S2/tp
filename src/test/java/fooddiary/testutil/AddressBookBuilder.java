package fooddiary.testutil;

import fooddiary.model.FoodDiary;
import fooddiary.model.entry.Entry;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private FoodDiary foodDiary;

    public AddressBookBuilder() {
        foodDiary = new FoodDiary();
    }

    public AddressBookBuilder(FoodDiary foodDiary) {
        this.foodDiary = foodDiary;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withEntry(Entry entry) {
        foodDiary.addEntry(entry);
        return this;
    }

    public FoodDiary build() {
        return foodDiary;
    }
}

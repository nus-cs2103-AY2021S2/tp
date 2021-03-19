package fooddiary.testutil;

import fooddiary.model.FoodDiary;
import fooddiary.model.entry.Entry;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class FoodDiaryBuilder {

    private FoodDiary foodDiary;

    public FoodDiaryBuilder() {
        foodDiary = new FoodDiary();
    }

    public FoodDiaryBuilder(FoodDiary foodDiary) {
        this.foodDiary = foodDiary;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public FoodDiaryBuilder withEntry(Entry entry) {
        foodDiary.addEntry(entry);
        return this;
    }

    public FoodDiary build() {
        return foodDiary;
    }
}

package seedu.storemando.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.StoreMando;
import seedu.storemando.model.expirydate.ExpiryDate;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.ItemName;
import seedu.storemando.model.item.Location;
import seedu.storemando.model.item.Quantity;
import seedu.storemando.model.tag.Tag;

/**
 * Contains utility methods for populating {@code StoreMando} with sample data.
 */
public class SampleDataUtil {
    public static Item[] getSampleItems() {
        return new Item[]{
            new Item(new ItemName("Cooking Oil"), new Quantity("10"), new ExpiryDate("2023-10-10"),
                new Location("Cupboard 3"),
                getTagSet("essential")),
            new Item(new ItemName("Pail"), new Quantity("4"), new ExpiryDate("No Expiry Date"),
                new Location("Toilet"),
                getTagSet("cleaning")),
            new Item(new ItemName("Beer"), new Quantity("10"),
                new ExpiryDate("2021-09-10"), new Location("Room 2"),
                getTagSet("favourite", "guests")),
            new Item(new ItemName("Chilli Sauce"), new Quantity("2"), new ExpiryDate("2023-10-10"),
                new Location("Cupboard 1"),
                getTagSet("sauces")),
            new Item(new ItemName("Tomato Sauce"), new Quantity("1"), new ExpiryDate("2023-09-10"),
                new Location("Cupboard 1"),
                getTagSet("sauces")),
            new Item(new ItemName("Mahjong Table"), new Quantity("2"), new ExpiryDate("No Expiry Date"),
                new Location("Storeroom"),
                getTagSet("guests")),
            new Item(new ItemName("Chair"), new Quantity("5"), new ExpiryDate("No Expiry Date"),
                new Location("Storeroom"),
                getTagSet("guests"))
        };
    }

    public static ReadOnlyStoreMando getSampleStoreMando() {
        StoreMando sampleAb = new StoreMando();
        for (Item sampleItem : getSampleItems()) {
            sampleAb.addItem(sampleItem);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}

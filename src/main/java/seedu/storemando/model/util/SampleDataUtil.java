package seedu.storemando.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.StoreMando;
import seedu.storemando.model.item.ExpiryDate;
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
            new Item(new ItemName("Koko Krunch"), new Quantity("1"), new ExpiryDate("2021-03-30"),
                new Location("Kitchen Cabinet"),
                getTagSet("Favourite")),
            new Item(new ItemName("Milk"), new Quantity("1"), new ExpiryDate("2021-03-15"),
                new Location("Refrigerator"),
                getTagSet("Cold")),
            new Item(new ItemName("Tomatoes"), new Quantity("10"),
                new ExpiryDate("2021-03-10"), new Location("Refrigerator"),
                getTagSet("Expiring")),
            new Item(new ItemName("Biscuit"), new Quantity("10"), new ExpiryDate("2021-10-10"),
                new Location("Kitchen Table"),
                getTagSet("Everyone's")),
            new Item(new ItemName("Kinder Bueno"), new Quantity("2"), new ExpiryDate("2021-03-30"),
                new Location("Freezer"),
                getTagSet("Favourite")),
            new Item(new ItemName("Oil"), new Quantity("1"), new ExpiryDate("2021-12-12"),
                new Location("Kitchen"),
                getTagSet("Cooking"))
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

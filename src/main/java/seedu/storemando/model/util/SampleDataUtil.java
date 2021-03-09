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
            new Item(new ItemName("Cooking Oil"), new Quantity("10"), new ExpiryDate("2020-10-10"),
                new Location("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Item(new ItemName("Milo"), new Quantity("9"), new ExpiryDate("2020-10-10"),
                new Location("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Item(new ItemName("Horlicks"), new Quantity("1"),
                new ExpiryDate("2020-09-10"), new Location("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Item(new ItemName("Chilli Sauce"), new Quantity("2"), new ExpiryDate("2023-10-10"),
                new Location("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("favourite")),
            new Item(new ItemName("French Fries"), new Quantity("1"), new ExpiryDate("2020-10-10"),
                new Location("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("favourite")),
            new Item(new ItemName("Olive Oil"), new Quantity("3"), new ExpiryDate("2021-10-10"),
                new Location("Room"),
                getTagSet("Need"))
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

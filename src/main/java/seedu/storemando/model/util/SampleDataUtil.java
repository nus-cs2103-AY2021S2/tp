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
            new Item(new ItemName("Alex Yeoh"), new Quantity("87438807"), new ExpiryDate("alexyeoh@example.com"),
                new Location("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Item(new ItemName("Bernice Yu"), new Quantity("99272758"), new ExpiryDate("berniceyu@example.com"),
                new Location("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Item(new ItemName("Charlotte Oliveiro"), new Quantity("93210283"),
                new ExpiryDate("charlotte@example.com"), new Location("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Item(new ItemName("David Li"), new Quantity("91031282"), new ExpiryDate("lidavid@example.com"),
                new Location("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Item(new ItemName("Irfan Ibrahim"), new Quantity("92492021"), new ExpiryDate("irfan@example.com"),
                new Location("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Item(new ItemName("Roy Balakrishnan"), new Quantity("92624417"), new ExpiryDate("royb@example.com"),
                new Location("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
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

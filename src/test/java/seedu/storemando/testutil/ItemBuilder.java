package seedu.storemando.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.storemando.model.expirydate.ExpiryDate;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.ItemName;
import seedu.storemando.model.item.Location;
import seedu.storemando.model.item.Quantity;
import seedu.storemando.model.tag.Tag;
import seedu.storemando.model.util.SampleDataUtil;

/**
 * A utility class to help with building Item objects.
 */
public class ItemBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_QUANTITY = "85355255";
    public static final String DEFAULT_EXPIRYDATE = "2021-10-10";
    public static final String DEFAULT_LOCATION = "123, Jurong West Ave 6, #08-111";

    private ItemName name;
    private Quantity quantity;
    private ExpiryDate expiryDate;
    private Location location;
    private Set<Tag> tags;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemBuilder() {
        name = new ItemName(DEFAULT_NAME);
        quantity = new Quantity(DEFAULT_QUANTITY);
        expiryDate = new ExpiryDate(DEFAULT_EXPIRYDATE);
        location = new Location(DEFAULT_LOCATION);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ItemBuilder with the data of {@code itemToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        name = itemToCopy.getItemName();
        quantity = itemToCopy.getQuantity();
        expiryDate = itemToCopy.getExpiryDate();
        location = itemToCopy.getLocation();
        tags = new HashSet<>(itemToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Item} that we are building.
     */
    public ItemBuilder withName(String name) {
        this.name = new ItemName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Item} that we are building.
     */
    public ItemBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Item} that we are building.
     */
    public ItemBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Item} that we are building.
     */
    public ItemBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code ExpiryDate} of the {@code Item} that we are building.
     */
    public ItemBuilder withExpiryDate(String expiryDate) {
        this.expiryDate = new ExpiryDate(expiryDate);
        return this;
    }

    public Item build() {
        return new Item(name, quantity, expiryDate, location, tags);
    }

}

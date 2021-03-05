package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.item.ExpiryDate;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Location;
import seedu.address.model.item.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Item objects.
 */
public class ItemBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EXPIRYDATE = "amy@gmail.com";
    public static final String DEFAULT_LOCATION = "123, Jurong West Ave 6, #08-111";

    private ItemName name;
    private Phone phone;
    private ExpiryDate expiryDate;
    private Location location;
    private Set<Tag> tags;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemBuilder() {
        name = new ItemName(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        expiryDate = new ExpiryDate(DEFAULT_EXPIRYDATE);
        location = new Location(DEFAULT_LOCATION);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ItemBuilder with the data of {@code itemToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        name = itemToCopy.getItemName();
        phone = itemToCopy.getPhone();
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
     * Sets the {@code Address} of the {@code Item} that we are building.
     */
    public ItemBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Item} that we are building.
     */
    public ItemBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Item} that we are building.
     */
    public ItemBuilder withExpiryDate(String expiryDate) {
        this.expiryDate = new ExpiryDate(expiryDate);
        return this;
    }

    public Item build() {
        return new Item(name, phone, expiryDate, location, tags);
    }

}

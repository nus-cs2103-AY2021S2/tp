package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.residence.BookingList;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Residence objects.
 */
public class ResidenceBuilder {
    public static final String DEFAULT_RESIDENCE_NAME = "Amber Park";
    public static final String DEFAULT_RESIDENCE_ADDRESS = "14 Amber Gardens, 439960";
    public static final String DEFAULT_BOOKING_DETAILS = "4 adults";
    public static final String DEFAULT_CLEAN_STATUS = "y";

    private ResidenceName name;
    private ResidenceAddress address;
    private BookingList bookingList;
    private CleanStatusTag cleanStatusTag;
    private Set<Tag> tags;

    /**
     * Creates a {@code ResidenceBuilder} with the default details.
     */
    public ResidenceBuilder() {
        name = new ResidenceName(DEFAULT_RESIDENCE_NAME);
        address = new ResidenceAddress(DEFAULT_RESIDENCE_ADDRESS);
        bookingList = new BookingList(DEFAULT_BOOKING_DETAILS);
        cleanStatusTag = new CleanStatusTag(DEFAULT_CLEAN_STATUS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ResidenceBuilder with the data of {@code residenceToCopy}.
     */
    public ResidenceBuilder(Residence residenceToCopy) {
        name = residenceToCopy.getResidenceName();
        address = residenceToCopy.getResidenceAddress();
        bookingList = residenceToCopy.getBookingDetails();
        cleanStatusTag = residenceToCopy.getCleanStatusTag();
        tags = new HashSet<>(residenceToCopy.getTags());
    }

    /**
     * Sets the {@code ResidenceName} of the {@code Residence} that we are building.
     */
    public ResidenceBuilder withName(String name) {
        this.name = new ResidenceName(name);
        return this;
    }

    /**
     * Sets the {@code ResidenceAddress} of the {@code Residence} that we are building.
     */
    public ResidenceBuilder withAddress(String address) {
        this.address = new ResidenceAddress(address);
        return this;
    }

    /**
     * Sets the {@code Booking} of the {@code Residence} that we are building.
     */
    public ResidenceBuilder withBookingDetails(String bookingDetails) {
        this.bookingList = new BookingList(bookingDetails);
        return this;
    }

    /**
     * Parses the {@code String cleanStatusTag} into a {@code cleanStatusTag} and set it to the {@code Residence}
     * that we are building.
     */
    public ResidenceBuilder withCleanStatusTags(String cleanStatusTag) {
        this.cleanStatusTag = SampleDataUtil.getCleanStatusTag(cleanStatusTag);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Residence} that we are building.
     */
    public ResidenceBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Residence build() {
        return new Residence(name, address, bookingList, cleanStatusTag, tags);
    }

}

package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.residence.BookingDetails;
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

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BOOKING_DETAILS = "Booking details";

    private ResidenceName name;
    private ResidenceAddress address;
    private BookingDetails bookingDetails;
    private Set<CleanStatusTag> cleanStatusTags;
    private Set<Tag> tags;

    /**
     * Creates a {@code ResidenceBuilder} with the default details.
     */
    public ResidenceBuilder() {
        name = new ResidenceName(DEFAULT_NAME);
        address = new ResidenceAddress(DEFAULT_ADDRESS);
        bookingDetails = new BookingDetails(DEFAULT_BOOKING_DETAILS);
        cleanStatusTags = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the ResidenceBuilder with the data of {@code residenceToCopy}.
     */
    public ResidenceBuilder(Residence residenceToCopy) {
        name = residenceToCopy.getResidenceName();
        address = residenceToCopy.getResidenceAddress();
        cleanStatusTags = new HashSet<>(residenceToCopy.getCleanStatusTag());
        tags = new HashSet<>(residenceToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Residence} that we are building.
     */
    public ResidenceBuilder withName(String name) {
        this.name = new ResidenceName(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Residence} that we are building.
     */
    public ResidenceBuilder withAddress(String address) {
        this.address = new ResidenceAddress(address);
        return this;
    }

    /**
     * Sets the {@code BookingDetails} of the {@code Residence} that we are building.
     */
    public ResidenceBuilder withBookingDetails(String bookingDetails) {
        this.bookingDetails = new BookingDetails(bookingDetails);
        return this;
    }

    /**
     * Parses the {@code cleanStatusTags} into a {@code Set<cleanStatusTag>} and set it to the {@code Residence}
     * that we are building.
     */
    public ResidenceBuilder withCleanStatusTags(String... cleanStatusTags) {
        this.cleanStatusTags = SampleDataUtil.getCleanStatusTagSet(cleanStatusTags);
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
        return new Residence(name, address, bookingDetails, cleanStatusTags, tags);
    }

}

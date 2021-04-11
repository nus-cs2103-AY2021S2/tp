package seedu.address.testutil;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.Client;
import seedu.address.model.property.status.Status;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.DateTimeFormat;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Property objects.
 */
public class PropertyBuilder {

    public static final String DEFAULT_NAME = "The Trilinq";
    public static final String DEFAULT_TYPE = "Condo";
    public static final String DEFAULT_ADDRESS = "28 Jln Lempeng";
    public static final String DEFAULT_POSTAL = "128807";
    public static final LocalDate DEFAULT_DEADLINE =
            LocalDate.parse("03-11-2021", DateTimeFormat.INPUT_DATE_FORMAT);

    private Name name;
    private Type type;
    private Address address;
    private PostalCode postal;
    private Deadline deadline;
    private Remark remark;
    private Client client;
    private Set<Tag> tags;
    private Status status;

    /**
     * Creates a {@code PropertyBuilder} with the default details.
     */
    public PropertyBuilder() {
        name = new Name(DEFAULT_NAME);
        type = new Type(DEFAULT_TYPE);
        address = new Address(DEFAULT_ADDRESS);
        postal = new PostalCode(DEFAULT_POSTAL);
        deadline = new Deadline(DEFAULT_DEADLINE);
        remark = null;
        client = null;
        tags = new HashSet<>();
        status = null;
    }

    /**
     * Initializes the PropertyBuilder with the data of {@code propertyToCopy}.
     */
    public PropertyBuilder(Property propertyToCopy) {
        name = propertyToCopy.getName();
        type = propertyToCopy.getPropertyType();
        address = propertyToCopy.getAddress();
        postal = propertyToCopy.getPostalCode();
        deadline = propertyToCopy.getDeadline();

        if (propertyToCopy.getRemarks() != null) {
            remark = propertyToCopy.getRemarks();
        } else {
            remark = null;
        }

        if (propertyToCopy.getClient() != null) {
            client = propertyToCopy.getClient();
        } else {
            client = null;
        }

        tags = new HashSet<>(propertyToCopy.getTags());

        if (propertyToCopy.getStatus() != null) {
            status = propertyToCopy.getStatus();
        } else {
            status = null;
        }
    }

    /**
     * Sets the {@code Name} of the {@code Property} that we are building.
     */
    public PropertyBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code Property} that we are building.
     */
    public PropertyBuilder withType(String type) {
        this.type = new Type(type);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Property} that we are building.
     */
    public PropertyBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code PostalCode} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPostal(String postal) {
        this.postal = new PostalCode(postal);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Property} that we are building.
     */
    public PropertyBuilder withDeadline(LocalDate deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Property} that we are building.
     */
    public PropertyBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Client} of the {@code Property} that we are building.
     */
    public PropertyBuilder withClient(Client client) {
        this.client = client;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Property} that we are building.
     */
    public PropertyBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Property} that we are building.
     */
    public PropertyBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    /**
     * Builds the {@code Property}.
     */
    public Property build() {
        return new Property(name, type, address, postal, deadline, remark, client, tags, status);
    }
}

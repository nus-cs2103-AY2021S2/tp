package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import seedu.address.model.person.Doctor;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

public class DoctorBuilder {
    public static final String DEFAULT_NAME = "Dr Amy";

    private UUID uuid;
    private Name name;
    private Set<Tag> tags;

    /**
     * Creates a {@code DoctorBuilder} with the default details.
     */
    public DoctorBuilder() {
        uuid = UUID.randomUUID();
        name = new Name(DEFAULT_NAME);
        tags = new HashSet<>();
    }

    /**
     * Initializes the DoctorBuilder with the data of {@code doctorToCopy}.
     */
    public DoctorBuilder(Doctor doctorToCopy) {
        uuid = doctorToCopy.getUuid();
        name = doctorToCopy.getName();
        tags = new HashSet<>(doctorToCopy.getTags());
    }

    /**
     * Sets the {@code UUID} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Doctor} that we are building.
     */
    public DoctorBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Doctor build() {
        return new Doctor(uuid, name, tags);
    }
}

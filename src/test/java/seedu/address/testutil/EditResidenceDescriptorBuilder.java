package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditResidenceDescriptor;
//import seedu.address.model.residence.BookingList;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditResidenceDescriptor objects.
 */
public class EditResidenceDescriptorBuilder {

    private EditResidenceDescriptor descriptor;

    public EditResidenceDescriptorBuilder() {
        descriptor = new EditResidenceDescriptor();
    }

    public EditResidenceDescriptorBuilder(EditResidenceDescriptor descriptor) {
        this.descriptor = new EditResidenceDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditResidenceDescriptor} with fields containing {@code residence}'s details
     */
    public EditResidenceDescriptorBuilder(Residence residence) {
        descriptor = new EditResidenceDescriptor();
        descriptor.setResidenceName(residence.getResidenceName());
        descriptor.setResidenceAddress(residence.getResidenceAddress());
        descriptor.setBookingDetails(residence.getBookingList());
        descriptor.setCleanStatusTag(residence.getCleanStatusTag());
        descriptor.setTags(residence.getTags());
    }

    /**
     * Sets the {@code ResidenceName} of the {@code EditResidenceDescriptor} that we are building.
     */
    public EditResidenceDescriptorBuilder withName(String name) {
        descriptor.setResidenceName(new ResidenceName(name));
        return this;
    }

    /**
     * Sets the {@code ResidenceAddress} of the {@code EditResidenceDescriptor} that we are building.
     */
    public EditResidenceDescriptorBuilder withAddress(String address) {
        descriptor.setResidenceAddress(new ResidenceAddress(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditResidenceDescriptorBuilder withCleanStatusTag(String cleanStatusTag) {
        descriptor.setCleanStatusTag(new CleanStatusTag(cleanStatusTag));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditResidenceDescriptor}
     * that we are building.
     */
    public EditResidenceDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditResidenceDescriptor build() {
        return descriptor;
    }
}

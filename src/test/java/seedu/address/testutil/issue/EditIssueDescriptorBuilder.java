package seedu.address.testutil.issue;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.issue.EditIssueCommand.EditIssueDescriptor;
import seedu.address.model.issue.Category;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.RoomNumber;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Timestamp;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditIssueDescriptor objects.
 */
public class EditIssueDescriptorBuilder {
    private EditIssueDescriptor descriptor;

    public EditIssueDescriptorBuilder() {
        this.descriptor = new EditIssueDescriptor();
    }

    public EditIssueDescriptorBuilder(EditIssueDescriptor descriptor) {
        this.descriptor = new EditIssueDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditIssueDescriptorBuilder} with fields containing {@code issue}'s details
     */
    public EditIssueDescriptorBuilder(Issue issue) {
        descriptor = new EditIssueDescriptor();
        descriptor.setRoomNumber(issue.getRoomNumber());
        descriptor.setDescription(issue.getDescription());
        descriptor.setTimestamp(issue.getTimestamp());
        descriptor.setStatus(issue.getStatus());
        descriptor.setCategory(issue.getCategory());
        descriptor.setTags(issue.getTags());
    }

    /**
     * Sets the {@code RoomNumber} of the {@code EditIssueDescriptorBuilder} that we are building.
     *
     * @param roomNumber Room number to be set.
     * @return the descriptor builder itself.
     */
    public EditIssueDescriptorBuilder withRoomNumber(String roomNumber) {
        descriptor.setRoomNumber(new RoomNumber(roomNumber));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditIssueDescriptorBuilder} that we are building.
     *
     * @param description Description to be set.
     * @return the descriptor builder itself.
     */
    public EditIssueDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Timestamp} of the {@code EditIssueDescriptorBuilder} that we are building.
     *
     * @param timestamp Timestamp to be set.
     * @return the descriptor builder itself.
     */
    public EditIssueDescriptorBuilder withTimestamp(String timestamp) {
        descriptor.setTimestamp(new Timestamp(timestamp));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditIssueDescriptorBuilder} that we are building.
     *
     * @param status Status to be set.
     * @return the descriptor builder itself.
     */
    public EditIssueDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new Status(status));
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code EditIssueDescriptorBuilder} that we are building.
     *
     * @param category Category to be set.
     * @return the descriptor builder itself.
     */
    public EditIssueDescriptorBuilder withCategory(String category) {
        descriptor.setCategory(new Category(category));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditIssueDescriptorBuilder}
     * that we are building.
     *
     * @param tags Tags to be set.
     * @return the descriptor builder itself.
     */
    public EditIssueDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Builds the EditIssueDescriptor based on the provided parameters
     *
     * @return EditIssueDescriptor with properties issued through the various
     *         methods of the {@code EditIssueDescriptorBuiler} class
     */
    public EditIssueDescriptor build() {
        return descriptor;
    }
}

package seedu.booking.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.booking.logic.commands.EditBookingCommand;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.util.SampleDataUtil;
import seedu.booking.model.venue.VenueName;

/**
 * A utility class to help with building EditBookingCommandDescriptorDescriptor objects.
 */
public class EditBookingCommandDescriptorBuilder {

    private EditBookingCommand.EditBookingDescriptor descriptor;

    public EditBookingCommandDescriptorBuilder() {
        descriptor = new EditBookingCommand.EditBookingDescriptor();
    }

    public EditBookingCommandDescriptorBuilder(EditBookingCommand.EditBookingDescriptor descriptor) {
        this.descriptor = new EditBookingCommand.EditBookingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBookingCommandDescriptor} with fields containing {@code person}'s details
     */
    public EditBookingCommandDescriptorBuilder(Booking booking) {
        descriptor = new EditBookingCommand.EditBookingDescriptor();
        descriptor.setBookerEmail(booking.getBookerEmail());
        descriptor.setVenueName(booking.getVenueName());
        descriptor.setDescription(booking.getDescription());
        descriptor.setBookingStart(booking.getBookingStart());
        descriptor.setBookingEnd(booking.getBookingEnd());
        descriptor.setTags(booking.getTags());

    }

    /**
     * Sets the {@code Email} of the {@code EditBookingCommandDescriptor} that we are building.
     */
    public EditBookingCommandDescriptorBuilder withBookerEmail(String email) {
        descriptor.setBookerEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code VenueName} of the {@code EditBookingCommandDescriptor} that we are building.
     */
    public EditBookingCommandDescriptorBuilder withVenueName(String venueName) {
        descriptor.setVenueName(new VenueName(venueName));
        return this;
    }

    /**
     * Sets the {@code VenueName} of the {@code EditBookingCommandDescriptor} that we are building.
     */
    public EditBookingCommandDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code EditBookingCommandDescriptor} that we are building.
     */
    public EditBookingCommandDescriptorBuilder withBookingStart(String bookingStart) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(bookingStart, formatter);
        descriptor.setBookingStart(new StartTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code EditBookingCommandDescriptor} that we are building.
     */
    public EditBookingCommandDescriptorBuilder withBookingEnd(String bookingEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(bookingEnd, formatter);
        descriptor.setBookingEnd(new EndTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code Tags} of the {@code EditBookingCommandDescriptor} that we are building.
     */
    public EditBookingCommandDescriptorBuilder withTags(String ... tags) {
        descriptor.setTags(SampleDataUtil.getTagSet(tags));
        return this;
    }

    public EditBookingCommand.EditBookingDescriptor build() {
        return descriptor;
    }
}

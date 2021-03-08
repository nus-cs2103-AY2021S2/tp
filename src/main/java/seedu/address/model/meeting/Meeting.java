package seedu.address.model.meeting;

import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.tag.Tag;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Meeting {
    private Client client;
    private LocalDateTime dateTime;
    private Address address;

    private Description description;
    private Set<Tag> tags = new HashSet<>();

    private boolean isDone;

    public Meeting(Client client, LocalDateTime dateTime, Address address, Description description, Set<Tag> tags) {
        requireAllNonNull(client, dateTime, address, description, tags);
        this.client = client;
        this.dateTime = dateTime;
        this.address = address;

        this.description = description;
        this.tags = tags;

        this.isDone = false;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Address getAddress() {
        return address;
    }

    public Description getDescription() {
        return description;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void relocate(Address newAddress) {
        this.address = newAddress;
    }

    public void reschedule(LocalDateTime newDateTime) {
        this.dateTime = newDateTime;
    }

    public void complete() {
        this.isDone = true;
    }

    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getDateTime().isEqual(this.dateTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if(!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getClient().equals(this.client)
                && otherMeeting.getDateTime().isEqual(this.dateTime)
                && otherMeeting.getAddress().equals(this.address)
                && otherMeeting.getDescription().equals(this.description)
                && otherMeeting.getTags().equals(this.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, dateTime, address, description, tags, isDone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(client.getName())
                .append("; Date & Time: ")
                .append(dateTime.toString())
                .append("; Address: ")
                .append(address.toString())
                .append("; Description: ")
                .append(description.toString());

        if(!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append("; Completed: ")
                .append(isDone);

        return builder.toString();
    }
}

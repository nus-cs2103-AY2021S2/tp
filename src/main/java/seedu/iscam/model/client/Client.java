package seedu.iscam.model.client;

import static seedu.iscam.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.commons.Tag;

/**
 * Represents a Client in the iscam book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private Set<InsurancePlan> insurancePlans = new HashSet<>();
    private Location location;
    private Set<Tag> tags = new HashSet<>();
    private Image imageRes;

    /**
     * Every field must be present and not null except for imageRes.
     * If insurance plan is not present, it will be a String of "No plans yet"
     */
    public Client(Name name, Phone phone, Email email, Location location, Set<InsurancePlan> plans, Set<Tag> tags,
                  Image imageRes) {
        requireAllNonNull(name, phone, email, location, plans, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.insurancePlans.addAll(plans);
        this.location = location;
        this.tags.addAll(tags);
        this.imageRes = imageRes;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Location getLocation() {
        return location;
    }

    public Set<InsurancePlan> getPlans() {
        return Collections.unmodifiableSet(insurancePlans);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an optional containing the imageRes String. May return an empty optional.
     */
    public Image getImageRes() {
        return imageRes;
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getName().equals(getName())
                && otherClient.getPhone().equals(getPhone())
                && otherClient.getEmail().equals(getEmail())
                && otherClient.getPlans().equals(getPlans())
                && otherClient.getLocation().equals(getLocation())
                && otherClient.getTags().equals(getTags())
                && otherClient.getImageRes().equals(getImageRes());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, location, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Location: ")
                .append(getLocation());

        // If insurance plan is present, display it
        Set<InsurancePlan> plan = getPlans();
        if (!insurancePlans.isEmpty()) {
            builder.append(", Insurance Plan: ");
            plan.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append("; Image: ")
                .append(imageRes);

        return builder.toString();
    }

}

//@@author branzuelajohn
package dog.pawbook.model.managedentity.program;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Represents a program in the database.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Program extends Entity {
    public static final String ENTITY_WORD = "program";
    public static final String TIMESLOT_PREFIX = "Timeslot(s): ";
    public static final String DOG_ID_PREFIX = "Participating Dog ID(s): ";

    // Data fields
    private final Set<Session> sessionSet = new HashSet<>();
    private final Set<Integer> dogIdSet = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Program(Name name, Set<Session> sessionSet, Set<Tag> tags) {
        super(name, tags);
        requireNonNull(sessionSet);
        this.sessionSet.addAll(sessionSet);
    }

    /**
     * Every field must be present and not null.
     * Overloaded constructor.
     */
    public Program(Name name, Set<Session> sessionSet, Set<Tag> tags, Set<Integer> dogIDs) {
        super(name, tags);
        requireAllNonNull(name, tags);
        this.sessionSet.addAll(sessionSet);
        this.dogIdSet.addAll(dogIDs);
    }

    public Set<Integer> getDogIdSet() {
        return Collections.unmodifiableSet(dogIdSet);
    }

    public Set<Session> getSessions() {
        return Collections.unmodifiableSet(sessionSet);
    }

    @Override
    public boolean isSameAs(Entity otherEntity) {
        if (!(otherEntity instanceof Program)) {
            return false;
        }

        return super.isSameAs(otherEntity);
    }

    /**
     * Returns true if both programs have the same identity and data fields.
     * This defines a stronger notion of equality between two programs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Program)) {
            return false;
        }

        Program otherProgram = (Program) other;
        return super.equals(other)
            && otherProgram.getSessions().equals(getSessions())
            && otherProgram.getDogIdSet().equals(getDogIdSet());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, sessionSet, dogIdSet, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        Set<Session> dates = getSessions();
        if (!dates.isEmpty()) {
            builder.append("; Sessions: ");
            dates.forEach(builder::append);
        }
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public List<Integer> getRelatedEntityIds() {
        return dogIdSet.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public List<String> getOtherPropertiesAsString() {
        List<String> properties = new ArrayList<>();

        String timeslots = sessionSet.stream()
                .sorted(Comparator.comparing(session -> session.dateTime))
                .map(session -> session.value)
                .collect(Collectors.joining(", "));
        properties.add(TIMESLOT_PREFIX + (sessionSet.size() > 0 ? timeslots : "None"));

        if (!dogIdSet.isEmpty()) {
            properties.add(dogIdSet.stream()
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", DOG_ID_PREFIX, "")));
        }
        return properties;
    }

}

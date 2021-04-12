package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.insurance.InsurancePlan;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in Link.me
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Gender gender;
    private final Birthdate birthdate;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final List<Note> notes = new ArrayList<>();

    //Functional fields
    private final Optional<Meeting> meeting;

    //Insurance fields
    private final List<InsurancePlan> plans = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Gender gender, Birthdate birthdate, Set<Tag> tags) {
        this(name, phone, email, address, gender, birthdate, tags, Optional.empty(),
                new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Full Constructor that is only called internally for testing.
     */
    public Person(Name name, Phone phone, Email email, Address address, Gender gender, Birthdate birthdate,
                  Set<Tag> tags, Optional<Meeting> meeting, List<InsurancePlan> plans, List<Note> notes) {
        requireAllNonNull(name, phone, email, address, gender, birthdate, tags, notes);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.birthdate = birthdate;
        this.tags.addAll(tags);
        this.meeting = meeting;
        this.notes.addAll(notes);
        this.plans.addAll(plans);
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

    public Address getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    public Birthdate getBirthdate() {
        return birthdate;
    }

    public List<Note> getNotes() {
        return Collections.unmodifiableList(notes);
    }

    public int getNumNotes() {
        return this.notes.size();
    }

    public String getNotesString() {
        String noteString = "";
        for (Note n : notes) {
            noteString += "\u2022 " + n.toString() + "\n";
        }
        if (noteString.equals("")) {
            noteString = "No notes taken yet";
        }
        return noteString;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an Optional object containing a Meeting.
     */
    public Optional<Meeting> getMeeting() {
        return meeting;
    }

    /**
     * Creates a Person object that is identical to the original, but contains a new Meeting.
     */
    public Person setMeeting(Optional<Meeting> meeting) {
        return new Person(name, phone, email, address, gender, birthdate, tags, meeting, plans, notes);
    }

    /**
     * Returns an immutable plan set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<InsurancePlan> getPlans() {
        return plans;
    }

    /**
     * Creates a Person object that is identical to the original, but has the insurance plans contained in
     * the Set.
     */
    public Person setPlans(List<InsurancePlan> plans) {
        return new Person(name, phone, email, address, gender, birthdate, tags, meeting, plans, notes);
    }

    /**
     * Creates a Person object that is identical to the original, but has an added InsurancePlan.
     */
    public Person addPlan(InsurancePlan plan) {
        List<InsurancePlan> plansCopy = new ArrayList<>(plans);
        plansCopy.add(plan);
        return new Person(name, phone, email, address, gender, birthdate, tags, meeting, plansCopy, notes);
    }

    /**
     * Creates a Person object that is identical to the original, but with the InsurancePlan at index removed.
     */
    public Person removePlan(int zeroBasedIndex) {
        List<InsurancePlan> plansCopy = new ArrayList<>(plans);
        plansCopy.remove(zeroBasedIndex);
        return new Person(name, phone, email, address, gender, birthdate, tags, meeting, plansCopy, notes);
    }

    /**
     * Returns the string representation of the InsurancePlan at the given index in the List of insurance plans.
     */
    public String getPlanString (int zeroBasedIndex) {
        return plans.get(zeroBasedIndex).toString();
    }

    /**
     * Returns a list of string representations of the person's insurance plans with numbering.
     */
    public List<String> getPlanStringsList () {
        List<String> planStrings = new ArrayList<>();
        for (int i = 0; i < plans.size(); i++) {
            planStrings.add((i + 1) + ". " + plans.get(i).toString() + " ");
        }
        return planStrings;
    }
    /**
     * Creates a Person object that is identical to the original, but with new note added.
     */
    public Person addNote(Note note) {
        List<Note> notesCopy = new ArrayList<>(notes);
        notesCopy.add(note);
        return new Person(name, phone, email, address, gender, birthdate, tags, meeting, plans, notesCopy);
    }

    /**
     * Creates a Person object that is identical to the original, but with no notes.
     */
    public Person clearNotes() {
        List<Note> notesEmpty = new ArrayList<>();
        return new Person(name, phone, email, address, gender, birthdate, tags, meeting, plans, notesEmpty);
    }

    /**
     * Creates a Person object that is identical to the original, but with notes as provided.
     */
    public Person setNotes(List<Note> notesNew) {
        return new Person(name, phone, email, address, gender, birthdate, tags, meeting, plans, notesNew);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }
        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getBirthdate().equals(getBirthdate())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getPlans().equals(getPlans())
                && otherPerson.getNotes().equals(getNotes());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, gender, birthdate, tags, meeting, plans, notes);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Gender: ")
                .append(getGender())
                .append("; Birthdate: ")
                .append(getBirthdate());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        List<InsurancePlan> plans = getPlans();
        if (!plans.isEmpty()) {
            builder.append("; Plans: ");
            plans.forEach(builder::append);
        }
        return builder.toString();
    }
}

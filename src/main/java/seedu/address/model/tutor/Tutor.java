package seedu.address.model.tutor;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.subject.SubjectList;
import seedu.address.model.subject.TutorSubject;
import seedu.address.model.tag.Tag;

/**
 * Represents a Tutor in Tutor Tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Tutor {

    // Identity fields
    private final Name name;
    private final Gender gender;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final SubjectList subjectList;
    private final Set<Tag> tags = new HashSet<>();
    private final Notes notes;

    private boolean isFavourite;

    /**
     * Every field must be present and not null.
     */
    public Tutor(Name name, Gender gender, Phone phone, Email email, Address address,
                 Notes notes, SubjectList subjectList, Set<Tag> tags) {
        requireAllNonNull(name, gender, phone, email, address, subjectList, tags);
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.notes = notes;
        this.subjectList = subjectList;
        this.tags.addAll(tags);
        this.isFavourite = false;
    }

    /**
     * Additional constructor to take in whether the Tutor is a favourite
     */
    public Tutor(Name name, Gender gender, Phone phone, Email email, Address address,
                 Notes notes, SubjectList subjectList, Set<Tag> tags, boolean isFavourite) {
        requireAllNonNull(name, gender, phone, email, address, subjectList, tags);
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.notes = notes;
        this.subjectList = subjectList;
        this.tags.addAll(tags);
        this.isFavourite = isFavourite;
    }

    /**
     * Checks whether given tutor is valid.
     *
     * @param tutor Tutor to check
     * @return Boolean representing whether given appointment is valid
     */
    public static boolean isValidTutor(Tutor tutor) {
        return Name.isValidName(tutor.getName().fullName)
                && Gender.isValidGender(tutor.getGender().personGender)
                && Phone.isValidPhone(tutor.getPhone().value)
                && Email.isValidEmail(tutor.getEmail().value)
                && Address.isValidAddress(tutor.getAddress().value)
                && Notes.isValidNote(tutor.getNotes().value)
                && SubjectList.isValidSubjectList(tutor.getSubjectList().asUnmodifiableObservableList())
                && Boolean.parseBoolean(String.valueOf(tutor.isFavourite));
    }

    public Name getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
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

    public boolean hasNotes() {
        return !notes.isEmpty();
    }

    public Notes getNotes() {
        return notes;
    }

    public SubjectList getSubjectList() {
        return subjectList;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both tutors have the same name.
     * This defines a weaker notion of equality between two tutors.
     */
    public boolean isSameTutor(Tutor otherTutor) {
        if (otherTutor == this) {
            return true;
        }

        return otherTutor != null
                && otherTutor.getName().equals(getName());
    }

    /**
     * Returns true if both tutors have the same identity and data fields.
     * This defines a stronger notion of equality between two tutors.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tutor)) {
            return false;
        }

        Tutor otherTutor = (Tutor) other;
        return otherTutor.getName().equals(getName())
                && otherTutor.getGender().equals(getGender())
                && otherTutor.getPhone().equals(getPhone())
                && otherTutor.getEmail().equals(getEmail())
                && otherTutor.getAddress().equals(getAddress())
                && otherTutor.getNotes().equals(getNotes())
                && otherTutor.getSubjectList().equals(getSubjectList())
                && otherTutor.getTags().equals(getTags())
                && otherTutor.isFavourite() == isFavourite();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, gender, phone, email, address, notes, subjectList, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Gender: ")
                .append(getGender())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        List<TutorSubject> subjectList = getSubjectList().asUnmodifiableObservableList();
        if (!subjectList.isEmpty()) {
            builder.append("; Subjects: ");
            subjectList.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}

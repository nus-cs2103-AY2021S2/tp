package seedu.address.model.subject;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a subject that a tutor offers.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TutorSubject {
    public static final String MESSAGE_CONSTRAINTS =
            "Each TutorSubject must contain name, level, rate, experience and qualification";
    private final SubjectName name;
    private final SubjectLevel level;
    private final SubjectRate rate;
    private final SubjectExperience experience;
    private final SubjectQualification qualification;

    /**
     * Every field must be present and not null.
     */
    public TutorSubject(SubjectName name, SubjectLevel level, SubjectRate rate,
            SubjectExperience experience, SubjectQualification qualification) {
        requireAllNonNull(name, level, rate, experience, qualification);
        this.name = name;
        this.level = level;
        this.rate = rate;
        this.experience = experience;
        this.qualification = qualification;
    }

    public SubjectName getName() {
        return name;
    }

    public SubjectLevel getLevel() {
        return level;
    }

    public SubjectRate getRate() {
        return rate;
    }

    public SubjectExperience getExperience() {
        return experience;
    }

    public SubjectQualification getQualification() {
        return qualification;
    }

    /**
     * Check if the {@code TutorSubject} is valid.
     */
    public static boolean isValidTutorSubject(TutorSubject tutorSubject) {
        return SubjectName.isValidName(tutorSubject.getName().name)
            && SubjectLevel.isValidLevel(tutorSubject.getLevel().level)
            && SubjectRate.isValidRate(tutorSubject.getRate().rate.toString())
            && SubjectExperience.isValidExperience(tutorSubject.getExperience().experience.toString())
            && SubjectQualification.isValidQualification(tutorSubject.getQualification().qualification);
    }

    /**
     * Returns true if both tutor subjects have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TutorSubject)) {
            return false;
        }

        TutorSubject otherTutorSubject = (TutorSubject) other;
        return otherTutorSubject.getName().equals(getName())
                && otherTutorSubject.getLevel().equals(getLevel())
                && otherTutorSubject.getRate().equals(getRate())
                && otherTutorSubject.getExperience().equals(getExperience())
                && otherTutorSubject.getQualification().equals(getQualification());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, level, rate, experience, qualification);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Level: ")
                .append(getLevel())
                .append("; Rate: ")
                .append(getRate())
                .append("; Years of Experience: ")
                .append(getExperience())
                .append("; Qualification: ")
                .append(getQualification());

        return builder.toString();
    }
}

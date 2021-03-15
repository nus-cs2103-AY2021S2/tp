package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

public class Tutor extends Person {

    private static int tutorCount = 0;

    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Tutor(PersonId personId, Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.setPersonType(new PersonType("tutor"));
        if (personId == null) {
            tutorCount++;
            this.setPersonId(new PersonId("t/" + tutorCount));
        } else {
            this.setPersonId(personId);
        }
    }

    public static void setTutorCount(String storedTutorCount) {
        tutorCount = Integer.valueOf(storedTutorCount);
    }

    public static String getTutorCount() {
        return String.valueOf(tutorCount);
    }
}

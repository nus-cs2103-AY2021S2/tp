package seedu.address.model.person;

import java.util.List;
import java.util.Set;

import seedu.address.model.session.SessionId;
import seedu.address.model.tag.Tag;

public class Tutor extends Person {

    private static int tutorCount = 0;

    /**
     * Every field must be present and not null.
     *
     * @param personId
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     * @param sessions
     */
    public Tutor(PersonId personId, Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                 List<SessionId> sessions) {
        super(name, phone, email, address, tags);
        this.setPersonType(new PersonType("tutor"));
        this.setPersonId(personId);
        this.setSessions(sessions);
    }

    /**
     * Every field must be present and not null.
     *
     * @param personId
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Tutor(PersonId personId, Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.setPersonType(new PersonType("tutor"));
        this.setPersonId(personId);
    }

    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Tutor(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.setPersonType(new PersonType("tutor"));

        tutorCount++;
        this.setPersonId(new PersonId("t/" + tutorCount));
    }

    public static void setTutorCount(String storedTutorCount) {
        tutorCount = Integer.valueOf(storedTutorCount);
    }

    public static String getTutorCount() {
        return String.valueOf(tutorCount);
    }
}

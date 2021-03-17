package seedu.address.testutil;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.session.Session;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "83501122";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "Blk 136 Jurong West Ave 6, #08-111";
    public static final String DEFAULT_STUDY_LEVEL = "Secondary 5";
    public static final String DEFAULT_GUARDIAN_PHONE = "90112344";
    public static final String DEFAULT_RELATIONSHIP = "Mother";
    public static final List<Session> DEFAULT_SESSION = new ArrayList<Session>() {
        {
            add(new SessionBuilder().withSessionDate("2020-01-01", "12:00").build());
        }
    };

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private String studyLevel;
    private Phone guardianPhone;
    private String relationship;
    private List<Session> sessions;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        studyLevel = DEFAULT_STUDY_LEVEL;
        guardianPhone = new Phone(DEFAULT_GUARDIAN_PHONE);
        relationship = DEFAULT_RELATIONSHIP;
        sessions = DEFAULT_SESSION;
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        studyLevel = studentToCopy.getStudyLevel();
        guardianPhone = studentToCopy.getGuardianPhone();
        relationship = studentToCopy.getRelationship();
        sessions = studentToCopy.getListOfSessions();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code studyLevel} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
        return this;
    }

    /**
     * Sets the {@code guardianPhone} of the {@code Student} that we are building.
     */
    public StudentBuilder withGuardianPhone(String guardianPhone) {
        this.guardianPhone = new Phone(guardianPhone);
        return this;
    }

    /**
     * Sets the {@code relationship} of the {@code Student} that we are building.
     */
    public StudentBuilder withRelationship(String relationship) {
        this.relationship = relationship;
        return this;
    }

    /**
     * Adds a {@code Session} to the {@code sessions} of the {@code Student}.
     */
    public StudentBuilder withSession(Session session) {
        requireAllNonNull(session);
        this.sessions.add(session);
        return this;
    }

    /**
     * Sets the {@code sessions} of the {@code Student} to the given session list.
     */
    public StudentBuilder withNewListOfSessions(List<Session> sessionList) {
        requireAllNonNull(sessionList);
        this.sessions = sessionList;
        return this;
    }

    /**
     * Appends a given list of sessions to the {@code sessions} of the {@code Student}.
     */
    public StudentBuilder withListOfSessions(List<Session> sessionList) {
        requireAllNonNull(sessionList);
        this.sessions.addAll(sessionList);
        return this;
    }

    /**
     * Builds the student object.
     * @return
     */
    public Student build() {
        Student s = new Student(name, phone, email, address, studyLevel, guardianPhone, relationship, sessions);
        return s;
    }

}

package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.subject.SubjectList;
import seedu.address.model.subject.TutorSubject;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Email;
import seedu.address.model.tutor.Gender;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Notes;
import seedu.address.model.tutor.Phone;
import seedu.address.model.tutor.Tutor;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class TutorBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_GENDER = "Female";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_NOTE = "";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Gender gender;
    private Phone phone;
    private Email email;
    private Address address;
    private Notes notes;
    private SubjectList subjectList;
    private Set<Tag> tags;

    /**
     * Creates a {@code TutorBuilder} with the default details.
     */
    public TutorBuilder() {
        name = new Name(DEFAULT_NAME);
        gender = new Gender(DEFAULT_GENDER);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        notes = new Notes(DEFAULT_NOTE);
        subjectList = new SubjectList();
        tags = new HashSet<>();
    }

    /**
     * Initializes the TutorBuilder with the data of {@code tutorToCopy}.
     */
    public TutorBuilder(Tutor tutorToCopy) {
        name = tutorToCopy.getName();
        gender = tutorToCopy.getGender();
        phone = tutorToCopy.getPhone();
        email = tutorToCopy.getEmail();
        address = tutorToCopy.getAddress();
        notes = tutorToCopy.getNotes();
        subjectList = tutorToCopy.getSubjectList();
        tags = new HashSet<>(tutorToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Tutor} that we are building.
     */
    public TutorBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Notes} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withNotes(String notes) {
        this.notes = new Notes(notes);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Parses the {@code subject} and add it to the {@code Tutor} that we are building.
     */
    public TutorBuilder withSubject(
            String subjectName,
            String subjectLevel,
            String subjectRate,
            String subjectExperience,
            String subjectQualification) {
        TutorSubject subject = SampleDataUtil.getSampleTutorSubject(
                subjectName,
                subjectLevel,
                subjectRate,
                subjectExperience,
                subjectQualification);
        this.subjectList.add(subject);
        return this;
    }

    public Tutor build() {
        return new Tutor(name, gender, phone, email, address, notes, subjectList, tags);
    }
}



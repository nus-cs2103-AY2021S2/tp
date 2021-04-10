package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Height;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_DATEOFBIRTH = "12121982";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BLOODTYPE = "AB-";
    public static final String DEFAULT_HEIGHT = "169cm";
    public static final String DEFAULT_WEIGHT = "69kg";

    private Name name;
    private DateOfBirth dateOfBirth;
    private Gender gender;
    private Phone phone;
    private Email email;
    private Address address;
    private BloodType bloodType;
    private Height height;
    private Weight weight;
    private Set<Tag> tags;
    private boolean isArchived;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        dateOfBirth = new DateOfBirth(DEFAULT_DATEOFBIRTH);
        gender = new Gender(DEFAULT_GENDER);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        bloodType = new BloodType(DEFAULT_BLOODTYPE);
        height = new Height(DEFAULT_HEIGHT);
        weight = new Weight(DEFAULT_WEIGHT);
        tags = new HashSet<>();
        isArchived = false;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code patientToCopy}.
     */
    public PersonBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        dateOfBirth = patientToCopy.getDateOfBirth();
        gender = patientToCopy.getGender();
        phone = patientToCopy.getPhone();
        email = patientToCopy.getEmail();
        address = patientToCopy.getAddress();
        bloodType = patientToCopy.getBloodType();
        height = patientToCopy.getHeight();
        weight = patientToCopy.getWeight();
        tags = new HashSet<>(patientToCopy.getTags());
        isArchived = patientToCopy.isArchived();
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code Patient} that we are building.
     */
    public PersonBuilder withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = new DateOfBirth(dateOfBirth);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Patient} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Patient} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Patient} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Patient} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Patient} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code BloodType} of the {@code Patient} that we are building.
     */
    public PersonBuilder withBloodType(String bloodType) {
        this.bloodType = new BloodType(bloodType);
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code Patient} that we are building.
     */
    public PersonBuilder withHeight(String height) {
        this.height = new Height(height);
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code Patient} that we are building.
     */
    public PersonBuilder withWeight(String weight) {
        this.weight = new Weight(weight);
        return this;
    }

    /**
     * Builds the {@code Patient} using the information given.
     */
    public Patient build() {
        Patient builtPatient = new Patient(name, dateOfBirth, gender, phone, email, address,
                bloodType, height, weight, tags);
        builtPatient.setArchived(isArchived);
        return builtPatient;
    }

}

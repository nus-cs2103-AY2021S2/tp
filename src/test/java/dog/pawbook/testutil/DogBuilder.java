package dog.pawbook.testutil;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.dog.Sex;
import dog.pawbook.model.managedentity.tag.Tag;
import dog.pawbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building Dog objects.
 */
public class DogBuilder {

    public static final String DEFAULT_NAME = "Goldie";
    public static final String DEFAULT_BREED = "Golden Retriever";
    public static final String DEFAULT_DATEOFBIRTH = "10-1-2020";
    public static final String DEFAULT_SEX = "male";
    public static final int DEFAULT_OWNERID = 1;

    private Name name;
    private Breed breed;
    private DateOfBirth dob;
    private Sex sex;
    private int ownerID;
    private Set<Tag> tags;

    /**
     * Creates a {@code DogBuilder} with the default details.
     */
    public DogBuilder() {
        name = new Name(DEFAULT_NAME);
        breed = new Breed(DEFAULT_BREED);
        dob = new DateOfBirth(DEFAULT_DATEOFBIRTH);
        sex = new Sex(DEFAULT_SEX);
        ownerID = DEFAULT_OWNERID;
        tags = new HashSet<>();
    }

    /**
     * Initializes the DogBuilder with the data of {@code dogToCopy}.
     */
    public DogBuilder(Dog dogToCopy) {
        name = dogToCopy.getName();
        breed = dogToCopy.getBreed();
        dob = dogToCopy.getDob();
        sex = dogToCopy.getSex();
        ownerID = dogToCopy.getOwnerId();
        tags = new HashSet<>(dogToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Dog} that we are building.
     */
    public DogBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Dog} that we are building.
     */
    public DogBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Breed} of the {@code Dog} that we are building.
     */
    public DogBuilder withBreed(String breed) {
        this.breed = new Breed(breed);
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code Dog} that we are building.
     */
    public DogBuilder withDateOfBirth(String dob) {
        this.dob = new DateOfBirth(dob);
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code Dog} that we are building.
     */
    public DogBuilder withSex(String sex) {
        this.sex = new Sex(sex);
        return this;
    }

    /**
     * Sets the {@code OwnerID} of the {@code Dog} that we are building.
     */
    public DogBuilder withOwnerID(int ownerID) {
        this.ownerID = ownerID;
        return this;
    }

    public Dog build() {
        return new Dog(name, breed, dob, sex, ownerID, tags);
    }

}

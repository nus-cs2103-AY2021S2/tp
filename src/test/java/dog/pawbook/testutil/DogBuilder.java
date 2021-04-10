package dog.pawbook.testutil;

import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.dog.Sex;

/**
 * A utility class to help with building Dog objects.
 */
public class DogBuilder extends EntityBuilder<DogBuilder, Dog> {

    public static final String DEFAULT_NAME = "Goldie";
    public static final String DEFAULT_BREED = "Golden Retriever";
    public static final String DEFAULT_DATEOFBIRTH = "10-01-2020";
    public static final String DEFAULT_SEX = "male";

    protected Breed breed;
    protected DateOfBirth dob;
    protected Sex sex;
    protected int ownerId;

    /**
     * Creates a {@code DogBuilder} with the default details.
     */
    public DogBuilder() {
        super(DEFAULT_NAME);
        breed = new Breed(DEFAULT_BREED);
        dob = new DateOfBirth(DEFAULT_DATEOFBIRTH);
        sex = new Sex(DEFAULT_SEX);
    }

    /**
     * Initializes the DogBuilder with the data of {@code dogToCopy}.
     */
    public DogBuilder(Dog dogToCopy) {
        super(dogToCopy);
        breed = dogToCopy.getBreed();
        dob = dogToCopy.getDob();
        sex = dogToCopy.getSex();
        ownerId = dogToCopy.getOwnerId();
    }

    /**
     * Sets the {@code Breed} of the {@code Dog} that we are building.
     */
    public final DogBuilder withBreed(String breed) {
        this.breed = new Breed(breed);
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code Dog} that we are building.
     */
    public final DogBuilder withDateOfBirth(String dob) {
        this.dob = new DateOfBirth(dob);
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code Dog} that we are building.
     */
    public final DogBuilder withSex(String sex) {
        this.sex = new Sex(sex);
        return this;
    }

    /**
     * Sets the {@code ownerId} of the {@code Dog} that we are building.
     */
    public final DogBuilder withOwnerID(int ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Dog build() {
        return new Dog(name, breed, dob, sex, ownerId, tags);
    }

    @Override
    protected DogBuilder self() {
        return this;
    }
}

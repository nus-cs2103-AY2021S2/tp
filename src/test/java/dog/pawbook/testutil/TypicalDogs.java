package dog.pawbook.testutil;

import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_OWNERID_10;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_OWNERID_9;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SEX_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SEX_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_FRIENDLY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_QUIET;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dog.pawbook.model.AddressBook;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;

/**
 * A utility class containing a list of {@code Dog} objects to be used in tests.
 */
public class TypicalDogs {
    public static final int DOG_OWNER_ID = 1;

    public static final Owner DOG_OWNER = new OwnerBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();

    public static final Dog APPLE = new DogBuilder().withName("Apple")
            .withBreed("Golden Retriever").withDateOfBirth("11-2-2020")
            .withSex("female").withOwnerID(1).withTags("friendly").build();
    public static final Dog BUBBLES = new DogBuilder().withName("Bubbles")
            .withBreed("Bulldog").withDateOfBirth("1-1-2021").withSex("female").withOwnerID(DOG_OWNER_ID)
            .withTags("cheerful").build();
    public static final Dog CARSON = new DogBuilder().withName("Carson").withSex("male")
            .withDateOfBirth("2-2-2019").withBreed("male").withOwnerID(DOG_OWNER_ID).build();
    public static final Dog DUKE = new DogBuilder().withName("Duke").withSex("male").withDateOfBirth("4-5-2020")
            .withBreed("German Shepherd").withOwnerID(DOG_OWNER_ID).withTags("quiet").build();
    public static final Dog ELSA = new DogBuilder().withName("Elsa").withSex("female")
            .withDateOfBirth("2-2-2020").withBreed("Poodle").withOwnerID(DOG_OWNER_ID).build();
    public static final Dog FLORA = new DogBuilder().withName("Flora").withSex("female")
            .withDateOfBirth("21-8-2018").withBreed("Australian Shepherd").withOwnerID(DOG_OWNER_ID).build();
    public static final Dog GENIE = new DogBuilder().withName("Genie").withSex("male")
            .withDateOfBirth("29-5-2020").withBreed("Husky").withOwnerID(DOG_OWNER_ID).build();

    // Manually added
    public static final Dog HOOK = new DogBuilder().withName("Hook").withSex("male")
            .withDateOfBirth("13-7-2019").withBreed("Chihuahua").withOwnerID(DOG_OWNER_ID).build();
    public static final Dog INK = new DogBuilder().withName("Ink").withSex("male")
            .withDateOfBirth("9-9-2020").withBreed("Rottweiler").withOwnerID(DOG_OWNER_ID).build();

    // Manually added - Dog's details found in {@code CommandTestUtil}
    public static final Dog ASHER = new DogBuilder().withName(VALID_NAME_ASHER).withSex(VALID_SEX_ASHER)
            .withDateOfBirth(VALID_DATEOFBIRTH_ASHER).withBreed(VALID_BREED_ASHER).withOwnerID(VALID_OWNERID_9)
            .withTags(VALID_TAG_FRIENDLY).build();
    public static final Dog BELL = new DogBuilder().withName(VALID_NAME_BELL).withSex(VALID_SEX_BELL)
            .withDateOfBirth(VALID_DATEOFBIRTH_BELL).withBreed(VALID_BREED_BELL).withOwnerID(VALID_OWNERID_10)
            .withTags(VALID_TAG_QUIET, VALID_TAG_FRIENDLY).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDogs() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical dogs.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addEntityWithId(DOG_OWNER, 1);
        Set<Integer> ids = new HashSet<>(DOG_OWNER.getDogIdSet());
        for (Dog dog : getTypicalDogs()) {
            int id = ab.addEntity(dog);
            ids.add(id);
        }
        ab.setEntity(1, new Owner(DOG_OWNER.getName(), DOG_OWNER.getPhone(), DOG_OWNER.getEmail(),
                DOG_OWNER.getAddress(), DOG_OWNER.getTags(), ids));
        return ab;
    }

    public static List<Dog> getTypicalDogs() {
        return new ArrayList<>(Arrays.asList(APPLE, BUBBLES, CARSON, DUKE, ELSA, FLORA, GENIE));
    }
}

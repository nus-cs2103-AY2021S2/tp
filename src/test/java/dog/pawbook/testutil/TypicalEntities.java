package dog.pawbook.testutil;

import static dog.pawbook.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_OWNERID_15;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_OWNERID_17;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SEX_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SEX_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_ALL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_FRIENDLY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_PUPPIES;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_QUIET;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dog.pawbook.model.Database;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;

/**
 * A utility class containing a list of {@code Owner} objects to be used in tests.
 */
public class TypicalEntities {

    // Owners
    public static final Owner ALICE = new OwnerBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withDogs(2).withTags("friends").build();
    public static final Owner BENSON = new OwnerBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com").withPhone("98765432").withDogs(4)
            .withTags("owesMoney", "friends").build();
    public static final Owner CARL = new OwnerBuilder().withName("Carl Kurz").withPhone("95352563").withDogs(6)
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Owner DANIEL = new OwnerBuilder().withName("Daniel Meier").withPhone("87652533").withDogs(8)
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Owner ELLE = new OwnerBuilder().withName("Elle Meyer").withPhone("9482224").withDogs(10)
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Owner FIONA = new OwnerBuilder().withName("Fiona Kunz").withPhone("9482427").withDogs(12)
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Owner GEORGE = new OwnerBuilder().withName("George Best").withPhone("9482442").withDogs(14)
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Dogs
    public static final Dog APPLE = new DogBuilder().withName("Apple")
            .withBreed("Golden Retriever").withDateOfBirth("11-02-2020")
            .withSex("female").withOwnerID(1).withTags("friendly").build();
    public static final Dog BUBBLES = new DogBuilder().withName("Bubbles")
            .withBreed("Bulldog").withDateOfBirth("01-01-2021").withSex("female").withOwnerID(3)
            .withTags("cheerful").build();
    public static final Dog CARSON = new DogBuilder().withName("Carson").withSex("male")
            .withDateOfBirth("02-02-2019").withBreed("Chipin").withOwnerID(5).build();
    public static final Dog DUKE = new DogBuilder().withName("Duke").withSex("male").withDateOfBirth("04-05-2020")
            .withBreed("German Shepherd").withOwnerID(7).withTags("quiet").build();
    public static final Dog ELSA = new DogBuilder().withName("Elsa").withSex("female")
            .withDateOfBirth("02-02-2020").withBreed("Poodle").withOwnerID(9).build();
    public static final Dog FLORA = new DogBuilder().withName("Flora").withSex("female")
            .withDateOfBirth("21-08-2018").withBreed("Australian Shepherd").withOwnerID(11).build();
    public static final Dog GENIE = new DogBuilder().withName("Genie").withSex("male")
            .withDateOfBirth("29-05-2020").withBreed("Husky").withOwnerID(13).build();

    // Programs
    // With no dogs enrolled
    // 15
    public static final Program ACTIVE_LISTENING = new ProgramBuilder().withName("Active Listening")
            .withSessions("12-12-2021 18:00").withTags("Puppies").build();
    // 16
    public static final Program BEHAVING = new ProgramBuilder().withName("Behaving")
            .withSessions("11-11-2021 20:00").withTags("Puppies").build();
    // 17
    public static final Program COOLDOWN_SESSION = new ProgramBuilder().withName("Cooldown Session")
            .withSessions("10-10-2021 10:00").withTags("All").build();

    // With 1 dog enrolled
    // 18
    public static final Program DANCING = new ProgramBuilder().withName("Dancing")
            .withSessions("09-12-2021 19:00").withDogs(2).withTags("Puppies").build();
    // 19
    public static final Program ELEGANCE_TRAINING = new ProgramBuilder().withName("Elegance Training")
            .withSessions("08-11-2021 10:00").withDogs(4).withTags("All").build();

    // With multiple dogs enrolled
    // 20
    public static final Program FOOD_TASTING = new ProgramBuilder().withName("Food Tasting")
            .withSessions("09-08-2021 09:00").withDogs(4, 6, 8).withTags("Puppies").build();
    // 21
    public static final Program GENERAL_KNOWLEDGE = new ProgramBuilder().withName("General Knowledge")
            .withSessions("27-08-2021 09:00").withDogs(8, 10, 12, 14).withTags("Puppies").build();

    // Manually added owners
    public static final Owner HOON = new OwnerBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Owner IDA = new OwnerBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added dogs
    public static final Dog HOOK = new DogBuilder().withName("Hook").withSex("male")
            .withDateOfBirth("13-07-2019").withBreed("Chihuahua").withOwnerID(13).build();
    public static final Dog INK = new DogBuilder().withName("Ink").withSex("male")
            .withDateOfBirth("09-09-2020").withBreed("Rottweiler").withOwnerID(14).build();

    // Manually added programs
    public static final Program HAPPY_PUPPY = new ProgramBuilder().withName("Happy Puppy")
            .withSessions("05-11-2022 10:30").withTags("Puppies").build();
    public static final Program INDEPENDENCE_TRAINING = new ProgramBuilder().withName("Independence Training")
            .withSessions("03-03-2022 18:30").withTags("All").build();


    // Manually added - Owner's details found in {@code CommandTestUtil}
    public static final Owner AMY = new OwnerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Owner BOB = new OwnerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    // Manually added - Dog's details found in {@code CommandTestUtil}
    public static final Dog ASHER = new DogBuilder().withName(VALID_NAME_ASHER).withSex(VALID_SEX_ASHER)
            .withDateOfBirth(VALID_DATEOFBIRTH_ASHER).withBreed(VALID_BREED_ASHER).withOwnerID(VALID_OWNERID_15)
            .withTags(VALID_TAG_FRIENDLY).build();
    public static final Dog BELL = new DogBuilder().withName(VALID_NAME_BELL).withSex(VALID_SEX_BELL)
            .withDateOfBirth(VALID_DATEOFBIRTH_BELL).withBreed(VALID_BREED_BELL).withOwnerID(VALID_OWNERID_17)
            .withTags(VALID_TAG_QUIET, VALID_TAG_FRIENDLY).build();

    // Manually added - Programs's details found in {@code CommandTestUtil}
    public static final Program OBEDIENCE_TRAINING = new ProgramBuilder().withName(VALID_NAME_OBEDIENCE_TRAINING)
            .withSessions(VALID_SESSION_OBEDIENCE_TRAINING).withTags(VALID_TAG_ALL).build();
    public static final Program POTTY_TRAINING = new ProgramBuilder().withName(VALID_NAME_POTTY_TRAINING)
            .withSessions(VALID_SESSION_POTTY_TRAINING).withTags(VALID_TAG_PUPPIES).build();


    private TypicalEntities() {} // prevents instantiation

    /**
     * Returns an {@code Database} with all the typical owners and dogs.
     */
    public static Database getTypicalDatabase() {
        Database db = new Database();
        for (Entity entity : getTypicalOwnersWithDog()) {
            db.addEntity(entity);
        }

        for (Program program : getTypicalPrograms()) {
            db.addEntity(program);
        }
        return db;
    }

    public static List<Entity> getTypicalOwnersWithDog() {
        return new ArrayList<>(Arrays.asList(ALICE, APPLE,
                BENSON, BUBBLES,
                CARL, CARSON,
                DANIEL, DUKE,
                ELLE, ELSA,
                FIONA, FLORA,
                GEORGE, GENIE));
    }

    public static List<Program> getTypicalPrograms() {
        return new ArrayList<>(Arrays.asList(ACTIVE_LISTENING, BEHAVING, COOLDOWN_SESSION,
                DANCING, ELEGANCE_TRAINING, FOOD_TASTING, GENERAL_KNOWLEDGE));
    }
}

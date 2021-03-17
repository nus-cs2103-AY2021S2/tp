package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REVIEW_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REVIEW_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FASTFOOD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WESTERN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.entry.Entry;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Entry ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withReview("alice@example.com")
            .withRating("3")
            .withTags("WESTERN").build();
    public static final Entry BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withReview("johnd@example.com").withRating("2")
            .withTags("VEGAN", "DESSERT").build();
    public static final Entry CARL = new PersonBuilder().withName("Carl Kurz").withRating("1")
            .withReview("heinz@example.com").withAddress("wall street").build();
    public static final Entry DANIEL = new PersonBuilder().withName("Daniel Meier").withRating("5")
            .withReview("cornelia@example.com").withAddress("10th street").withTags("FASTFOOD").build();
    public static final Entry ELLE = new PersonBuilder().withName("Elle Meyer").withRating("2")
            .withReview("werner@example.com").withAddress("michegan ave").build();
    public static final Entry FIONA = new PersonBuilder().withName("Fiona Kunz").withRating("1")
            .withReview("lydia@example.com").withAddress("little tokyo").build();
    public static final Entry GEORGE = new PersonBuilder().withName("George Best").withRating("0")
            .withReview("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Entry HOON = new PersonBuilder().withName("Hoon Meier").withRating("1")
            .withReview("stefan@example.com").withAddress("little india").build();
    public static final Entry IDA = new PersonBuilder().withName("Ida Mueller").withRating("1")
            .withReview("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Entry AMY = new PersonBuilder().withName(VALID_NAME_AMY).withRating(VALID_RATING_AMY)
            .withReview(VALID_REVIEW_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FASTFOOD).build();
    public static final Entry BOB = new PersonBuilder().withName(VALID_NAME_BOB).withRating(VALID_RATING_BOB)
            .withReview(VALID_REVIEW_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_WESTERN, VALID_TAG_FASTFOOD)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Entry entry : getTypicalPersons()) {
            ab.addPerson(entry);
        }
        return ab;
    }

    public static List<Entry> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

package fooddiary.testutil;

import fooddiary.logic.commands.CommandTestUtil;
import fooddiary.model.FoodDiary;
import fooddiary.model.entry.Entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalEntries {

    public static final Entry ALICE = new EntryBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withReview("alice@example.com")
            .withRating("3")
            .withTags("WESTERN").build();
    public static final Entry BENSON = new EntryBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withReview("johnd@example.com").withRating("2")
            .withTags("VEGAN", "DESSERT").build();
    public static final Entry CARL = new EntryBuilder().withName("Carl Kurz").withRating("1")
            .withReview("heinz@example.com").withAddress("wall street").build();
    public static final Entry DANIEL = new EntryBuilder().withName("Daniel Meier").withRating("5")
            .withReview("cornelia@example.com").withAddress("10th street").withTags("FASTFOOD").build();
    public static final Entry ELLE = new EntryBuilder().withName("Elle Meyer").withRating("2")
            .withReview("werner@example.com").withAddress("michegan ave").build();
    public static final Entry FIONA = new EntryBuilder().withName("Fiona Kunz").withRating("1")
            .withReview("lydia@example.com").withAddress("little tokyo").build();
    public static final Entry GEORGE = new EntryBuilder().withName("George Best").withRating("0")
            .withReview("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Entry HOON = new EntryBuilder().withName("Hoon Meier").withRating("1")
            .withReview("stefan@example.com").withAddress("little india").build();
    public static final Entry IDA = new EntryBuilder().withName("Ida Mueller").withRating("1")
            .withReview("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Entry AMY = new EntryBuilder().withName(CommandTestUtil.VALID_NAME_AMY).withRating(CommandTestUtil.VALID_RATING_AMY)
            .withReview(CommandTestUtil.VALID_REVIEW_AMY).withAddress(CommandTestUtil.VALID_ADDRESS_AMY).withTags(CommandTestUtil.VALID_TAG_FASTFOOD).build();
    public static final Entry BOB = new EntryBuilder().withName(CommandTestUtil.VALID_NAME_BOB).withRating(CommandTestUtil.VALID_RATING_BOB)
            .withReview(CommandTestUtil.VALID_REVIEW_BOB).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).withTags(CommandTestUtil.VALID_TAG_WESTERN, CommandTestUtil.VALID_TAG_FASTFOOD)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEntries() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static FoodDiary getTypicalFoodDiary() {
        FoodDiary ab = new FoodDiary();
        for (Entry entry : getTypicalPersons()) {
            ab.addEntry(entry);
        }
        return ab;
    }

    public static List<Entry> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

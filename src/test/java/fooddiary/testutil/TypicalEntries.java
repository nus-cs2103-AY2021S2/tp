package fooddiary.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fooddiary.logic.commands.CommandTestUtil;
import fooddiary.model.FoodDiary;
import fooddiary.model.entry.Entry;

/**
 * A utility class containing a list of {@code Entry} objects to be used in tests.
 */
public class TypicalEntries {

    public static final Entry ENTRY_A = new EntryBuilder().withName("Restaurant A")
            .withAddress("123, Jurong West Ave 6, #08-111").withReview("This is a review for Restaurant A.")
            .withRating("3").withPrice("11")
            .withTags("WESTERN").build();
    public static final Entry ENTRY_B = new EntryBuilder().withName("Restaurant B")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withReview("This is a review for Restaurant B.").withRating("2").withPrice("12")
            .withTags("VEGAN", "DESSERT").build();
    public static final Entry ENTRY_C = new EntryBuilder().withName("Restaurant C").withRating("1").withPrice("13")
            .withReview("This is a review for Restaurant C.").withAddress("wall street").build();
    public static final Entry ENTRY_D = new EntryBuilder().withName("Restaurant D").withRating("5").withPrice("14")
            .withReview("This is a review for Restaurant D.").withAddress("10th street").withTags("FASTFOOD").build();
    public static final Entry ENTRY_E = new EntryBuilder().withName("Restaurant E").withRating("2").withPrice("15")
            .withReview("This is a review for Restaurant E.").withAddress("michegan ave").build();
    public static final Entry ENTRY_F = new EntryBuilder().withName("Restaurant F").withRating("1").withPrice("16")
            .withReview("This is a review for Restaurant F.").withAddress("little tokyo").build();
    public static final Entry ENTRY_G = new EntryBuilder().withName("Restaurant G").withRating("0").withPrice("17")
            .withReview("This is a review for Restaurant G.").withAddress("4th street").build();

    // Manually added
    public static final Entry ENTRY_H = new EntryBuilder().withName("Restaurant H").withRating("1").withPrice("18")
            .withReview("This is a review for Restaurant H.").withAddress("little india").build();
    public static final Entry ENTRY_I = new EntryBuilder().withName("Restaurant I").withRating("1").withPrice("19")
            .withReview("This is a review for Restaurant I.").withAddress("chicago ave").build();

    // Manually added - Entry's details found in {@code CommandTestUtil}
    public static final Entry VALID_ENTRY_A = new EntryBuilder().withName(CommandTestUtil.VALID_NAME_A)
            .withRating(CommandTestUtil.VALID_RATING_A).withPrice(CommandTestUtil.VALID_PRICE_A)
            .withReview(CommandTestUtil.VALID_REVIEW_A).withAddress(CommandTestUtil.VALID_ADDRESS_A)
            .withTags(CommandTestUtil.VALID_TAG_FASTFOOD).build();
    public static final Entry VALID_ENTRY_B = new EntryBuilder().withName(CommandTestUtil.VALID_NAME_B)
            .withRating(CommandTestUtil.VALID_RATING_B).withPrice(CommandTestUtil.VALID_PRICE_B)
            .withReview(CommandTestUtil.VALID_REVIEW_B).withAddress(CommandTestUtil.VALID_ADDRESS_B)
            .withTags(CommandTestUtil.VALID_TAG_WESTERN, CommandTestUtil.VALID_TAG_FASTFOOD)
            .build();

    private TypicalEntries() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical entries.
     */
    public static FoodDiary getTypicalFoodDiary() {
        FoodDiary ab = new FoodDiary();
        for (Entry entry : getTypicalEntries()) {
            ab.addEntry(entry);
        }
        return ab;
    }

    public static List<Entry> getTypicalEntries() {
        return new ArrayList<>(Arrays.asList(ENTRY_A, ENTRY_B, ENTRY_C, ENTRY_D, ENTRY_E, ENTRY_F, ENTRY_G));
    }
}

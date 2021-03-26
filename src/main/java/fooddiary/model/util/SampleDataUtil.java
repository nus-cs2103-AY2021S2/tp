package fooddiary.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fooddiary.model.FoodDiary;
import fooddiary.model.ReadOnlyFoodDiary;
import fooddiary.model.entry.Address;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Price;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Entry[] getSampleEntries() {
        return new Entry[] {
            new Entry(new Name("KFC"), new Rating("3"), new Price("8"), getReviewList("too oily"),
                    new Address("3155 Commonwealth Ave W, #B1-32/33, Singapore 129588"),
                    getTagSet("FastFood")),
            new Entry(new Name("Frontier"), new Rating("4"), new Price("4"), getReviewList("cheap good food!"),
                    new Address("12 Science Drive 2, Singapore 117549"),
                    getTagSet("Others")),
            new Entry(new Name("Mcdonald"), new Rating("1"), new Price("7"),
                    getReviewList("Food sucks, not properly cooked."),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("FastFood")),
            new Entry(new Name("Ameens"), new Rating("5"), new Price("8"), getReviewList("Cheese fries is lit!!!"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("Western")),
            new Entry(new Name("Techo Edge"), new Rating("5"), new Price("5"),
                    getReviewList("Food is cheap and good! Especially the mixed rice stall."),
                    new Address("2 Engineering Drive 4, Singapore 117584"), getTagSet("FastFood")),
            new Entry(new Name("PGP Canteen"), new Rating("4"), new Price("5"),
                    getReviewList("Good food. Place very crowded during lunch"),
                    new Address("27 Prince George's Park, Singapore 118425"), getTagSet("Western", "Indian")),
            new Entry(new Name("Deck"), new Rating("4"), new Price("4"),
                    getReviewList("Mala the best mannn!"),
                    new Address("NUS School of Computing, COM1, 13, Computing Dr, 117417"),
                    getTagSet("Western"))
        };
    }

    public static ReadOnlyFoodDiary getSampleFoodDiary() {
        FoodDiary sampleAb = new FoodDiary();
        for (Entry sampleEntry : getSampleEntries()) {
            sampleAb.addEntry(sampleEntry);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a review list containing the list of strings given.
     */
    public static List<Review> getReviewList(String... strings) {
        return Arrays.stream(strings)
                .map(Review::new)
                .collect(Collectors.toList());
    }

}

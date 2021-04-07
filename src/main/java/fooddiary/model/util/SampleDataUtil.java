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
import fooddiary.model.tag.TagCategory;
import fooddiary.model.tag.TagSchool;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Entry[] getSampleEntries() {
        return new Entry[] {
            new Entry(new Name("The Deck"), new Rating("5"), new Price("8"),
                getReviewList("The mala at the deck was so good. "
                    + "Gravy was super flavourful. Avoid the waffles.  "
                    + "Yakult fruit juice is a must try. "
                    + "Really cheap coffee, that does the job. "
                    + "Yong Tau Fu is highly recommended. "
                    + "Western store does an okay job, the portion is a little small. "),
                new Address("Faculty of Arts & Social Sciences"),
                getTagCategorySet("dessert", "chinese", "western", "malay", "indian"),
                getTagSchoolSet("FASS")),
            new Entry(new Name("Frontier AC"), new Rating("4"), new Price("4"),
                getReviewList("Love the Pasta Express, Taiwan and Ayam Penyet! Get the coffee!"),
                new Address("Faculty of Science, S16"),
                getTagCategorySet("chinese", "western", "malay"),
                getTagSchoolSet("Science")),
            new Entry(new Name("Frontier NonAC"), new Rating("4"), new Price("7"),
                getReviewList("Cheap and affordable food! Western store was amazing, and thai store was not bad."),
                new Address("Faculty of Science, S16"),
                getTagCategorySet("dessert", "chinese", "western", "malay", "indian"),
                getTagSchoolSet("Science")),
            new Entry(new Name("PGP AC"), new Rating("4"), new Price("8"),
                getReviewList("Amazing mala and Ramen store"),
                new Address("Prince George's Park Residences"),
                getTagCategorySet("japanese", "chinese"),
                getTagSchoolSet("PGP")),
            new Entry(new Name("Ameens"), new Rating("5"), new Price("5"),
                    getReviewList("Cheese fries is lit!!!"),
                new Address("12 Clementi Rd, Singapore 129742"),
                getTagCategorySet("Indian"),
                getTagSchoolSet("SOC")),
            new Entry(new Name("Techno Edge"), new Rating("5"), new Price("5"),
                getReviewList("Food is cheap and good! Especially the mixed rice stall."),
                new Address("2 Engineering Drive 4, Singapore 117584"),
                getTagCategorySet("dessert", "chinese", "western", "malay", "indian"),
                getTagSchoolSet("FOE", "SDE", "CLB")),
            new Entry(new Name("The Spread"), new Rating("3"), new Price("4"),
                getReviewList("Highly-priced, the pastas are worth it but the other main courses are questionable."),
                new Address("Business School Mochtar Riady Building"),
                getTagCategorySet("western"),
                getTagSchoolSet("BIZ")),
            new Entry(new Name("Shaw Foundation House"), new Rating("3"), new Price("1"),
                getReviewList("It’s overpriced, only suited for occasions where I’m feeling more grandiose."),
                new Address("Shaw Foundation Alumni House"),
                getTagCategorySet("chinese", "dessert", "indian"),
                getTagSchoolSet("NUSS")),
            new Entry(new Name("Fine Food"), new Rating("2"), new Price("6"),
                getReviewList("The mala was trash. "
                    + "There was so much MSG in the food, it felt like I was eating salt."
                    + "Chinese food gives high carbs. Would go for the fishball noodles."),
                new Address("Town Plaza"),
                getTagCategorySet("chinese", "dessert", "indian"),
                getTagSchoolSet("UTOWN")),
            new Entry(new Name("Flavours"), new Rating("3"), new Price("12"),
                getReviewList("The fishball noodles were average, it did not taste nice nor did it taste good. "
                    + "It was edible. Wide delicious variety of cai fan dishes"),
                new Address("Stephen Riady Centre"),
                getTagCategorySet("chinese", "dessert", "indian"),
                getTagSchoolSet("UTOWN")),
            new Entry(new Name("Hwangs Korean Restaurant"), new Rating("4"), new Price("1"),
                getReviewList("The kimchi was LIT!!! It feels as though i’m eating in korea!"
                        + " Definitely will go back again"),
                new Address("Town Plaza"),
                getTagCategorySet("korean"),
                getTagSchoolSet("UTOWN")),
            new Entry(new Name("Supersnacks"), new Rating("3"), new Price("6"),
                getReviewList("Love the soya bean ice cream!"),
                new Address("Town Plaza"),
                getTagCategorySet("western"),
                getTagSchoolSet("UTOWN")),
            new Entry(new Name("2359 Li Ji Coffeehouse"), new Rating("3"), new Price("3"),
                getReviewList("Limited choices, but really good spicy noodles and toast."
                    + " Good supper snack!"),
                new Address("Stephen Riady Centre"),
                getTagCategorySet("chinese"), getTagSchoolSet("UTOWN"))
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
    public static Set<TagCategory> getTagCategorySet(String... strings) {
        return Arrays.stream(strings)
                .map(TagCategory::new)
                .collect(Collectors.toSet());
    }

    public static Set<TagSchool> getTagSchoolSet(String... strings) {
        return Arrays.stream(strings)
                .map(TagSchool::new)
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

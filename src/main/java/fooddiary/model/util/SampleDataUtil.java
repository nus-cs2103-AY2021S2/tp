package fooddiary.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import fooddiary.model.FoodDiary;
import fooddiary.model.ReadOnlyFoodDiary;
import fooddiary.model.entry.Address;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Entry[] getSampleEEntries() {
        return new Entry[] {
            new Entry(new Name("The Deck"), new Rating("5"),
                new Review("The mala at the deck was so good. "
                        + "Gravy was super flavourful. Avoid the waffles.  "
                        + "Yakult fruit juice is a must try. "
                        + "Really cheap coffee, that does the job. "
                        + "Yong Tau Fu is highly recommended. "
                        + "Western store does an okay job, the portion is a little small. "),
                new Address("Faculty of Arts & Social Sciences"),
                getTagSet("FASS")),
            new Entry(new Name("Frontier AC"), new Rating("4"),
                new Review("Love the Pasta Express, Taiwan and Ayam Penyet! Get the coffee!"),
                new Address("Faculty of Science, S16"),
                getTagSet("Science")),
            new Entry(new Name("Frontier NonAC"), new Rating("4"),
                new Review("Cheap and affordable food! Western store was amazing, and thai store was not bad."),
                new Address("Faculty of Science, S16"),
                getTagSet("Science")),
            new Entry(new Name("PGP AC"), new Rating("4"),
                new Review("Amazing mala and Ramen store"),
                new Address("Prince George's Park Residences"), getTagSet("PGP")),
            new Entry(new Name("Ameens"), new Rating("5"), new Review("Cheese fries is lit!!!"),
                new Address("12 Clementi Rd, Singapore 129742"), getTagSet("Indian")),
            new Entry(new Name("Techo Edge"), new Rating("5"),
                new Review("Food is cheap and good! Especially the mixed rice stall."),
                new Address("2 Engineering Drive 4, Singapore 117584"), getTagSet("FOE", "SDE", "CLB")),
            new Entry(new Name("The Spread"), new Rating("3"),
                new Review("Highly-priced, the pastas are worth it but the other main courses are questionable."),
                new Address("Business School Mochtar Riady Building"), getTagSet("Western", "Biz")),
            new Entry(new Name("Shaw Foundation House"), new Rating("3"),
                new Review("It’s overpriced, only suited for occasions where I’m feeling more grandiose."),
                new Address("Shaw Foundation Alumni House"),
                getTagSet("NUSS")),
            new Entry(new Name("Fine Food"), new Rating("2"),
                new Review("The mala was trash. "
                    + "There was so much MSG in the food, it felt like I was eating salt."
                    + "Chinese food gives high carbs. Would go for the fishball noodles."),
                new Address("Town Plaza"), getTagSet("UTOWN")),
            new Entry(new Name("Flavours"), new Rating("3"),
                new Review("The fishball noodles were average, it did not taste nice nor did it taste good. "
                    + "It was edible. Wide delicious variety of cai fan dishes"),
                new Address("Stephen Riady Centre"), getTagSet("UTown")),
            new Entry(new Name("Hwangs Korean Restaurant"), new Rating("4"),
                    new Review("The kimchi was LIT!!! It feels as though i’m eating in korea!"
                            + " Definitely will go back again"),
                    new Address("Town Plaza"), getTagSet("UTOWN")),
            new Entry(new Name("Supersnacks"), new Rating("3"),
                    new Review("Love the soya bean ice cream!"),
                    new Address("Town Plaza"), getTagSet("UTOWN")),
            new Entry(new Name("2359 Li Ji Coffeehouse"), new Rating("3"),
                    new Review("Limited choices, but really good spicy noodles and toast. Good supper snack!"),
                    new Address("Stephen Riady Centre"), getTagSet("UTOWN")),
        };
    }

    public static ReadOnlyFoodDiary getSampleFoodDiary() {
        FoodDiary sampleAb = new FoodDiary();
        for (Entry sampleEntry : getSampleEEntries()) {
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

}

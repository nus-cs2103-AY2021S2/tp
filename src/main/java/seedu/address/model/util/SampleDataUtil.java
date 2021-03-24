package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.diet.MacroNutrientComposition;
import seedu.address.model.diet.PlanType;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static DietPlanList getSampleDietPlanList() {
        DietPlanList list = new DietPlanList();
        list.getPlanList().addAll(getSampleDietPlans());
        return list;
    }

    public static DietPlan[] getSampleDietPlans() {
        return new DietPlan[] {
            new DietPlan("Standard Ketogenic Diet",
            "The Standard Ketogenic Diet is a high-fat, low-carb weight-loss diet. "
                    + "It is designed in such a way that by reducing the intake of carbohydrates, "
                    + "the body is forced to burn its fat reserves for fuel thereby resulting in weight-loss. \n"
                    + "\n"
                    + "The Standard Ketogenic Diet is suitable for individuals suffering from Type II Diabetes where "
                    + "excess carbohydrates would have been converted into glucose.\n",
                new MacroNutrientComposition(70, 10, 20),
                PlanType.WEIGHTLOSS),
            new DietPlan("High-Protein Ketogenic Diet",
                "The High-Protein Ketogenic Diet is a variation of the Ketogenic Diet (high-fat, low-carb) "
                        + "which increases the protein intake. This variation is designed to help athletes and "
                        + "bodybuilders maintain their muscle mass whilst burning fat.",
                new MacroNutrientComposition(60, 35, 5),
                PlanType.WEIGHTLOSS),
            new DietPlan("Skinny Guy BodyBuilding",
                "The goal for bodybuilders is to increase muscle mass. Consume high-quality, nutrient-dense "
                        + "carbs when the body needs them most, around workouts. The hyperenergetic diet plan recommends "
                        + "consuming starchy food during and after workouts, and less starchy content on off-hours.",
                new MacroNutrientComposition(15, 55, 30),
                PlanType.WEIGHTGAIN),
            new DietPlan("Balanced Plan",
                "The perfect ying-yang. Eat healthy food and complete the calorie goal. Eat lots of fruits "
                        + "and vegetables, and base meals on higher fiber starchy carbohydrates.",
                new MacroNutrientComposition(30, 40, 30),
                PlanType.WEIGHTMAINTAIN)
        };
    }

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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

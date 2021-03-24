package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Pair;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.DishBook;
import seedu.address.model.dish.ReadOnlyDishBook;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonBook;
import seedu.address.model.person.ReadOnlyPersonBook;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person("Alex Yeoh", "87438807","alexyeoh@example.com",
                "Blk 30 Geylang Street 29, #06-40",
                getTagSet("gluten allergy")),
            new Person("Bernice Yu", "99272758", "berniceyu@example.com",
                "Blk 30 Lorong 3 Serangoon Gardens, #07-18",
                getTagSet("regular", "seafood allergy")),
            new Person("Charlotte Oliveiro", "93210283", "charlotte@example.com",
                "Blk 11 Ang Mo Kio Street 74, #11-04",
                getTagSet("vegan")),
            new Person("David Li", "91031282", "lidavid@example.com",
                "Blk 436 Serangoon Gardens Street 26, #16-43",
                getTagSet("employee discount")),
            new Person("Irfan Ibrahim", "92492021", "irfan@example.com",
                "Blk 47 Tampines Street 20, #17-35",
                getTagSet("regular")),
            new Person("Roy Balakrishnan", "92624417", "royb@example.com",
                "Blk 45 Aljunied Street 85, #11-31",
                getTagSet("regular"))
        };
    }

    public static Ingredient[] getSampleInventory() {
        return new Ingredient[] {
                new Ingredient("Chicken wing", 100),
                new Ingredient("Potato", 51),
                new Ingredient("Tomato", 40),
                new Ingredient("Beef patty", 50),
                new Ingredient("Burger bun", 30),
        };
    }

    public static Dish[] getSampleMenu() {
        return new Dish[] {
                new Dish("Pizza", 14.50, new ArrayList<Pair<Ingredient, Integer>>()),
                new Dish("Burger", 10.50, new ArrayList<Pair<Ingredient, Integer>>()),
                new Dish("Chicken wings", 5.00, new ArrayList<Pair<Ingredient, Integer>>()),
                new Dish("French fries", 3.50, new ArrayList<Pair<Ingredient, Integer>>())
        };
    }

    public static Order[] getSampleOrderList() {
        // dummy contacts to populate order list
        Person david = new Person("David Li", "91031282", "lidavid@example.com",
                "Blk 436 Serangoon Gardens Street 26, #16-43",
                getTagSet("employee discount"));
        Person bernice = new Person("Bernice Yu", "99272758", "berniceyu@example.com",
                "Blk 30 Lorong 3 Serangoon Gardens, #07-18",
                getTagSet("regular", "seafood allergy"));

        // dummy dishes to populate order list
        Dish burger = new Dish("Burger", 10.50, new ArrayList<Pair<Ingredient, Integer>>());
        Dish wings = new Dish("Chicken wings", 5.00, new ArrayList<Pair<Ingredient, Integer>>());
        Dish fries = new Dish("French fries", 3.50, new ArrayList<Pair<Ingredient, Integer>>());

        return new Order[] {
                new Order("14-02-2021 18:30", bernice,
                        new ArrayList<>(Arrays.asList(
                                new Pair<>(burger, 2),
                                new Pair<>(wings, 2)
                        ))),
                new Order("14-02-2021 19:15", david,
                        new ArrayList<>(Arrays.asList(
                                new Pair<>(burger, 1),
                                new Pair<>(fries, 1)
                        )))
        };
    }

    public static ReadOnlyPersonBook getSamplePersonBook() {
        PersonBook sampleAb = new PersonBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static Dish[] getSampleDishes() {
        Dish[] sampleDishes = new Dish[1];
        List<Pair<Ingredient, Integer>> list = new ArrayList<>();
        list.add(new Pair<>(new Ingredient("Fish", 5), 1));
        sampleDishes[0] = new Dish("Fried fish", 20.5, list);
        return sampleDishes;
    }

    public static ReadOnlyDishBook getSampleDishBook() {
        DishBook sampleDb = new DishBook();
        for (Dish sampleDish : getSampleDishes()) {
            sampleDb.addDish(sampleDish);
        }
        return sampleDb;
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

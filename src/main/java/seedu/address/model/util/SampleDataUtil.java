package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Pair;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.DishBook;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientBook;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonBook;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static Ingredient potato = new Ingredient("Potato", 50);
    private static Ingredient chicken = new Ingredient("Chicken", 50);
    private static Ingredient lettuce = new Ingredient("Lettuce", 50);
    private static Ingredient beefPatty = new Ingredient("Beef patty", 50);
    private static Ingredient burgerBun = new Ingredient("Burger bun", 30);

    private static Dish potatoSalad = new Dish("Potato salad", 6.50,
            new ArrayList<Pair<Ingredient, Integer>>(Arrays.asList(
                    new Pair<>(potato, 1),
                    new Pair<>(lettuce, 1)
            )));
    private static Dish burger = new Dish("Burger", 11.50,
            new ArrayList<Pair<Ingredient, Integer>>(Arrays.asList(
                    new Pair<>(burgerBun, 1),
                    new Pair<>(beefPatty, 1),
                    new Pair<>(lettuce, 1)
            )));
    private static Dish wings = new Dish("Chicken wings", 6.00,
            new ArrayList<Pair<Ingredient, Integer>>(Arrays.asList(
                    new Pair<>(chicken, 1)
            )));
    private static Dish fries = new Dish("French fries", 5.50,
            new ArrayList<Pair<Ingredient, Integer>>(Arrays.asList(
                    new Pair<>(potato, 1)
            )));

    private static Person alex = new Person("Alex Yeoh", "87438807", "alexyeoh@example.com",
            "Blk 30 Geylang Street 29, #06-40",
            getTagList("gluten allergy"));
    private static Person bernice = new Person("Bernice Yu", "99272758", "berniceyu@example.com",
            "Blk 30 Lorong 3 Serangoon Gardens, #07-18",
            getTagList("regular", "seafood allergy"));
    private static Person charlotte = new Person("Charlotte Oliveiro", "93210283", "charlotte@example.com",
            "Blk 11 Ang Mo Kio Street 74, #11-04",
            getTagList("vegan"));
    private static Person david = new Person("David Li", "91031282", "lidavid@example.com",
            "Blk 436 Serangoon Gardens Street 26, #16-43",
            getTagList("employee discount"));
    private static Person ibrahim = new Person("Irfan Ibrahim", "92492021", "irfan@example.com",
            "Blk 47 Tampines Street 20, #17-35",
            getTagList("regular"));
    private static Person roy = new Person("Roy Balakrishnan", "92624417", "royb@example.com",
            "Blk 45 Aljunied Street 85, #11-31",
            getTagList("regular"));

    public static Person[] getSamplePersons() {
        return new Person[] { alex, bernice, charlotte, david, ibrahim, roy };
    }

    public static Dish[] getSampleDishes() {
        // dummy dishes to populate order list
        return new Dish[] { potatoSalad, burger, wings, fries };
    }


    public static Ingredient[] getSampleIngredients() {
        return new Ingredient[] { potato, chicken, lettuce, beefPatty, burgerBun };
    }

    public static Order[] getSampleOrders() {
        return new Order[] {
            new Order(LocalDateTime.parse("14-02-2021 18:30", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                    bernice,
                    new ArrayList<>(Arrays.asList(
                            new Pair<>(burger, 2),
                            new Pair<>(wings, 2)
                    ))),
            new Order(LocalDateTime.parse("14-02-2021 19:15", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                    david,
                    new ArrayList<>(Arrays.asList(
                            new Pair<>(burger, 1),
                            new Pair<>(fries, 1)
                    )))
        };
    }

    public static ReadOnlyBook<Person> getSamplePersonBook() {
        PersonBook sampleAb = new PersonBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyBook<Dish> getSampleDishBook() {
        DishBook sampleDb = new DishBook();
        for (Dish sampleDish : getSampleDishes()) {
            sampleDb.addDish(sampleDish);
        }
        return sampleDb;
    }

    public static ReadOnlyBook<Order> getSampleOrderBook() {
        OrderBook sampleOb = new OrderBook();
        for (Order sampleOrder : getSampleOrders()) {
            sampleOb.addOrder(sampleOrder);
        }
        return sampleOb;
    }

    public static ReadOnlyBook<Ingredient> getSampleIngredientBook() {
        IngredientBook sampleIb = new IngredientBook();
        for (Ingredient sampleIngredient : getSampleIngredients()) {
            sampleIb.addIngredient(sampleIngredient);
        }
        return sampleIb;
    }


    /**
     * Returns a tag set containing the list of strings given.
     */
    public static List<String> getTagList(String... strings) {
        return new ArrayList<>(Arrays.asList(strings));
    }

}

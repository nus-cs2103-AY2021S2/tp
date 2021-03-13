package seedu.address.model.user;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import seedu.address.model.food.Food;
import seedu.address.model.person.Name;

/**
 * Handles the representation of the User class in DieTrack.
 */
public class User {
    // Identity fields
    private final Name name;
    private final Age age;
    private final Gender gender;
    private final IdealWeight idealWeight;
    private final LocalDate lastUpdated;

    // Data fields
    private final Bmi bmi;
    private final List<Food> foodList;

    /**
     * Creates a representation of the user with the given parameters.
     * All fields must not be empty.
     * @param name Name of the user
     * @param bmi Bmi object of the user
     * @param foodList Food list of the user
     */
    public User(Name name, Bmi bmi, List<Food> foodList, Age age, Gender gender, IdealWeight idealWeight) {
        requireAllNonNull(name, bmi, foodList);
        this.name = name;
        this.bmi = bmi;
        this.foodList = foodList;
        this.age = age;
        this.gender = gender;
        this.idealWeight = idealWeight;
        this.lastUpdated = LocalDate.now();
    }

    /**
     * Creates a representation of the user with the given parameters.
     * All fields must not be empty.
     * @param bmi Bmi object of the user
     * @param foodList Food list of the user
     */
    public User(Bmi bmi, List<Food> foodList, Age age, Gender gender, IdealWeight idealWeight) {
        requireAllNonNull(bmi, foodList);
        this.name = null;
        this.bmi = bmi;
        this.foodList = foodList;
        this.age = age;
        this.gender = gender;
        this.idealWeight = idealWeight;
        this.lastUpdated = LocalDate.now();
    }

    public Name getName() {
        return this.name;
    }

    public Bmi getBmi() {
        return this.bmi;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public Age getAge() {
        return this.age;
    }

    public Gender getGender() {
        return this.gender;
    }

    public IdealWeight getIdealWeight() {
        return this.idealWeight;
    }

    public String getLastUpdated() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(this.lastUpdated);
    }

    /**
     * Returns true if both users have the same identity and data fields.
     * This defines a stronger notion of equality between two users.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof User)) {
            return false;
        }

        User otherUser = (User) other;
        return otherUser.getName().equals(getName())
                && otherUser.getBmi().equals(getBmi())
                && otherUser.getFoodList().equals(getFoodList());
    }
}

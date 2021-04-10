package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.diet.PlanType;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntake;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.person.Person;
import seedu.address.model.user.User;
import seedu.address.testutil.FoodBuilder;

public class AddFoodItemCommandTest {

    @Test
    public void constructor_nullFoodItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddFoodItemCommand(null));
    }

    @Test
    public void execute_foodAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFoodAdded modelStub = new ModelStubAcceptingFoodAdded();
        Food validFood = new FoodBuilder().build();

        CommandResult commandResult = new AddFoodItemCommand(validFood).execute(modelStub);

        assertEquals(AddFoodItemCommand.MESSAGE_SUCCESS + validFood + ") into food list.",
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFood), modelStub.foodsAdded);
    }

    @Test
    public void execute_duplicateFood_throwsCommandException() {
        Food validFood = new FoodBuilder().build();
        AddFoodItemCommand addFoodItemCommand = new AddFoodItemCommand(validFood);
        ModelStub modelStub = new ModelStubWithFood(validFood);

        assertThrows(CommandException.class, AddFoodItemCommand.MESSAGE_DUPLICATE_FOOD, () ->
                addFoodItemCommand.execute(modelStub));
    }

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFoodItem(Food food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String listFoodItem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFoodItem(Food food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFoodItem(Food food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFoodItem(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public UniqueFoodList getUniqueFoodList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public DietPlanList getDietPlanList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setActiveDiet(DietPlan dietPlan) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public DietPlan getActiveDiet() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public HashMap<Integer, DietPlan> recommendDiets(PlanType planType) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Food addFoodIntake(LocalDate date, Food food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFoodIntake(int index, FoodIntake foodIntake) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FoodIntakeList getFoodIntakeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addUser(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasUser() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editUser(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String listUser() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PlanType getUserGoal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public double getUserBmi() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public User getUser() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetToBlank() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetToTemplate() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithFood extends ModelStub {
        private final Food food;

        ModelStubWithFood(Food food) {
            requireNonNull(food);
            this.food = food;
        }

        @Override
        public boolean hasFoodItem(Food food) {
            requireNonNull(food);
            return this.food.getName().equals(food.getName());
        }
    }

    private class ModelStubAcceptingFoodAdded extends ModelStub {
        final ArrayList<Food> foodsAdded = new ArrayList<>();

        @Override
        public boolean hasFoodItem(Food food) {
            requireNonNull(food);
            for (Food foodItem : foodsAdded) {
                if (foodItem.getName().equals(food)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public void addFoodItem(Food food) {
            requireNonNull(food);
            foodsAdded.add(food);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}

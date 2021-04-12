package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalDietLah.getTypicalFoodIntakeList;
import static seedu.address.testutil.TypicalDietLah.getTypicalUser;
import static seedu.address.testutil.TypicalDietLah.getTypicalUniqueFoodList;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.DietLah;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyDietLah;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.diet.PlanType;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntake;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.user.Age;
import seedu.address.model.user.Bmi;
import seedu.address.model.user.Gender;
import seedu.address.model.user.IdealWeight;
import seedu.address.model.user.User;

public class EditUserCommandTest {
    @Test
    public void constructor_editTypicalUser_editSuccessful() throws CommandException {
        User typicalUser = getTypicalUser();
        ModelStubWithUser model = new ModelStubWithUser();
        model.addUser(typicalUser);

        Bmi newBmi = new Bmi(75.4, 178.5);
        UniqueFoodList typicalUniqueFoodList = getTypicalUniqueFoodList();
        FoodIntakeList typicalFoodIntakeList = getTypicalFoodIntakeList();
        Age newAge = new Age(24);
        Gender newGender = new Gender("M");
        IdealWeight newIdealWeight = new IdealWeight(80);
        User editUser = new User(newBmi, typicalUniqueFoodList.getFoodList(), typicalFoodIntakeList, newAge,
                newGender, newIdealWeight);

        EditUserCommand newEditUserCommand = new EditUserCommand(editUser);
        CommandResult commandResult = newEditUserCommand.execute(model);

        assertEquals(EditUserCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
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
        public Path getDietLahFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDietLahFilePath(Path dietLahFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDietLah(ReadOnlyDietLah newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyDietLah getDietLah() {
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
        public void resetToBlank() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean equals(Object object) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetToTemplate() {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubWithUser extends EditUserCommandTest.ModelStub {
        private DietLah dietLah;

        ModelStubWithUser() {
            dietLah = new DietLah();
            dietLah.resetToBlank(dietLah.getFoodList(), dietLah.getFoodIntakeList());
        }

        @Override
        public void addUser(User user) {
            dietLah.addUser(user);
        }

        @Override
        public boolean hasUser() {
            return dietLah.hasUser();
        }

        @Override
        public User getUser() {
            return dietLah.getUser();
        }

        @Override
        public void editUser(User updateUser) {
            User oldUser = dietLah.getUser();
            User newUser = new User(updateUser.getBmi(), oldUser.getFoodList(),
                    oldUser.getFoodIntakeList(), updateUser.getAge(),
                    updateUser.getGender(), updateUser.getIdealWeight());
            dietLah.addUser(newUser);
        }
    }
}

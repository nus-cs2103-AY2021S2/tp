package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalDietLah.getTypicalUser;

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
import seedu.address.model.user.User;

public class AddUserCommandTest {
    @Test
    public void constructor_addTypicalUser_addSuccessful() throws CommandException {
        User typicalUser = getTypicalUser();
        AddUserCommand addUserCommand = new AddUserCommand(typicalUser);

        ModelStubWithUser model = new ModelStubWithUser();
        CommandResult commandResult = addUserCommand.execute(model);

        assertEquals(AddUserCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_existingUser_addUnsuccessful() throws CommandException {
        User typicalUser = getTypicalUser();
        AddUserCommand addUserCommand = new AddUserCommand(typicalUser);

        ModelStubWithUser model = new ModelStubWithUser();
        addUserCommand.execute(model);

        User newUser = new User();
        AddUserCommand newAddUserCommand = new AddUserCommand(newUser);
        CommandResult commandResult = newAddUserCommand.execute(model);

        assertEquals(AddUserCommand.MESSAGE_UPDATE, commandResult.getFeedbackToUser());
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

    private class ModelStubWithUser extends AddUserCommandTest.ModelStub {
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
    }
}

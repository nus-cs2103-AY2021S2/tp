package seedu.address.ui.budgetpanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.budget.Budget;
import seedu.address.ui.UiPart;

public class BudgetCard extends UiPart<Region> {

    private static final String FXML = "budgetpanel/BudgetCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Budget budget;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label description;

    /**
     * Creates a {@code BudgetCard} with the given {@code Budget} and index to
     * display.
     */
    public BudgetCard(Budget budget) {
        super(FXML);
        this.budget = budget;

        description.setText(String.format("Budget: $%d\nTotal Cost of Appointments: $%d",
            this.budget.getValue(), this.budget.getTotalCost()));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BudgetCard)) {
            return false;
        }

        // state check
        BudgetCard card = (BudgetCard) other;
        return id.getText().equals(card.id.getText())
                && budget.equals(card.budget);
    }



}

package seedu.iscam.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.iscam.model.client.InsurancePlan;

/**
 * An UI component that displays information of a {@code insurancePlan}
 */
public class PlanCard extends UiPart<Region> {
    private static final String FXML = "PlanCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ClientBook level 4</a>
     */

    public final InsurancePlan plan;

    @FXML
    private Label planName;

    /**
     * Creates a {@code planCode} with the given {@code plan} and index to display.
     */
    public PlanCard(InsurancePlan plan) {
        super(FXML);
        this.plan = plan;
        planName.setText(plan.planName);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PlanCard)) {
            return false;
        }

        // state check
        PlanCard card = (PlanCard) other;
        return plan.equals(card.plan);
    }
}

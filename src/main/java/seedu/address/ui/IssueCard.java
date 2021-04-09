package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.issue.Issue;

/**
 * An UI component that displays information of a {@code Issue}.
 */
public class IssueCard extends UiPart<Region> {

    private static final String FXML = "IssueListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Issue issue;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label roomNumber;
    @FXML
    private Label description;
    @FXML
    private Label timestamp;
    @FXML
    private Label status;
    @FXML
    private Label category;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code IssueCode} with the given {@code Issue} and index to display.
     */
    public IssueCard(Issue issue, int displayedIndex) {
        super(FXML);
        this.issue = issue;
        id.setText(displayedIndex + ". ");
        roomNumber.setText(issue.getRoomNumber().value);
        description.setText(issue.getDescription().value);
        timestamp.setText(issue.getTimestamp().toString());
        status.setText(issue.getStatus().value.toString());
        category.setText(issue.getCategory().value);
        issue.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IssueCard)) {
            return false;
        }

        // state check
        IssueCard card = (IssueCard) other;
        return id.getText().equals(card.id.getText())
                && issue.equals(card.issue);
    }
}

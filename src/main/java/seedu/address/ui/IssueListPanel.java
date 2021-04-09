package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.issue.Issue;

/**
 * Panel containing the list of issues.
 */
public class IssueListPanel extends UiPart<Region> {
    private static final String FXML = "IssueListPanel.fxml";

    @FXML
    private ListView<Issue> issueListView;

    /**
     * Creates a {@code IssueListPanel} with the given {@code ObservableList}.
     */
    public IssueListPanel(ObservableList<Issue> issueList) {
        super(FXML);
        issueListView.setItems(issueList);
        issueListView.setCellFactory(listView -> new IssueListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Issue} using a {@code IssueCard}.
     */
    class IssueListViewCell extends ListCell<Issue> {
        @Override
        protected void updateItem(Issue issue, boolean empty) {
            super.updateItem(issue, empty);

            if (empty || issue == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new IssueCard(issue, getIndex() + 1).getRoot());
            }
        }
    }

}

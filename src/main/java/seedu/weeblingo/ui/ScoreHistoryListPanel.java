package seedu.weeblingo.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.weeblingo.commons.core.LogsCenter;
import seedu.weeblingo.model.score.Score;

public class ScoreHistoryListPanel extends UiPart<Region> implements ListPanel {
    private static final String FXML = "ScoreHistoryListPanel.fxml"; // placeholder, todo: beautify UI design
    private final Logger logger = LogsCenter.getLogger(FlashcardListPanel.class);

    @javafx.fxml.FXML
    private ListView<Score> scoreHistoryListView;

    /**
     * Creates a {@code FlashcardListPanel} with the given {@code ObservableList}.
     */
    public ScoreHistoryListPanel(ObservableList<Score> scores) {
        super(FXML);
        scoreHistoryListView.setItems(scores);
        scoreHistoryListView.setCellFactory(listView -> new ScoreHistoryListPanel.ScoreListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Flashcard} using a {@code Score}.
     */
    class ScoreListViewCell extends ListCell<Score> {
        @Override
        protected void updateItem(Score score, boolean empty) {
            super.updateItem(score, empty);

            if (empty || score == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ScoreCard(score, getIndex() + 1).getRoot());
            }
        }
    }
}

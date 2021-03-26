package seedu.weeblingo.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.weeblingo.commons.core.LogsCenter;
import seedu.weeblingo.model.flashcard.Flashcard;

/**
 * Panel containing the list of flashcards.
 */
public class FlashcardListPanel extends UiPart<Region> implements ListPanel {
    private static final int placeHolder = -1;
    // supposed to indicate not during a quiz session but i cannot think of names alr haha lol woof bark
    private static final String FXML = "FlashcardListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FlashcardListPanel.class);

    @FXML
    private ListView<Flashcard> flashcardListView;

    /**
     * Creates a {@code FlashcardListPanel} with the given {@code ObservableList}.
     */
    public FlashcardListPanel(ObservableList<Flashcard> flashcardList) {
        super(FXML);
        flashcardListView.setItems(flashcardList);
        flashcardListView.setCellFactory(listView -> new FlashcardListViewCell(placeHolder, true));

    }

    public void updateCard(int currIndex, boolean showAnswer) {
        flashcardListView.setCellFactory(listView -> new FlashcardListViewCell(currIndex, showAnswer));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Flashcard} using a {@code FlashcardCard}.
     */
    class FlashcardListViewCell extends ListCell<Flashcard> {
        private int currIndex;
        private boolean showAnswer;

        public FlashcardListViewCell(int currIndex, boolean showAnswer) {
            this.currIndex = currIndex;
            this.showAnswer = showAnswer;
        }

        @Override
        protected void updateItem(Flashcard flashcard, boolean empty) {
            super.updateItem(flashcard, empty);

            if (empty || flashcard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FlashcardCard(flashcard, currIndex == placeHolder ? getIndex() + 1 : currIndex,
                        showAnswer).getRoot());
            }
        }

    }

}

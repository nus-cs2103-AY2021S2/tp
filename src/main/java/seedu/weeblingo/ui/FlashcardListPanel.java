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
public class FlashcardListPanel extends UiPart<Region> {
    // Indicates that flashcards are displayed as a list and not individual questions
    private static final int LIST_INDEX = -1;

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
        flashcardListView.setCellFactory(listView -> new FlashcardListViewCell(LIST_INDEX, true));

    }

    public void updateCard(int currIndex, boolean isAnswerVisible) {
        flashcardListView.setCellFactory(listView -> new FlashcardListViewCell(currIndex, isAnswerVisible));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Flashcard} using a {@code FlashcardCard}.
     */
    class FlashcardListViewCell extends ListCell<Flashcard> {
        private int currIndex;
        private boolean isAnswerVisible;

        /**
         * @param currIndex Index of displayed flashcard. If equal to LIST_INDEX, displays flashcards with increasing
         *                  index starting from 1.
         * @param isAnswerVisible Shows the answer of the flashcards if true.
         */
        public FlashcardListViewCell(int currIndex, boolean isAnswerVisible) {
            this.currIndex = currIndex;
            this.isAnswerVisible = isAnswerVisible;
        }

        @Override
        protected void updateItem(Flashcard flashcard, boolean empty) {
            super.updateItem(flashcard, empty);

            if (empty || flashcard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FlashcardCard(flashcard, currIndex == LIST_INDEX ? getIndex() + 1 : currIndex,
                        isAnswerVisible).getRoot());
            }
        }

    }

}

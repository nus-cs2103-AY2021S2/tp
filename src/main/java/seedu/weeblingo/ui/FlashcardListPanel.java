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
 * Panel containing the list of persons.
 */
public class FlashcardListPanel extends UiPart<Region> {
    public static final int QUIZ_LIST = -1;
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
        flashcardListView.setCellFactory(listView -> new FlashcardListViewCell());
    }

    /**
     * Creates a {@code FlashcardListPanel} with the given {@code ObservableList}.
     */
    public FlashcardListPanel(ObservableList<Flashcard> flashcardList, boolean isQuestion, int currIndex) {
        super(FXML);
        flashcardListView.setItems(flashcardList);
        flashcardListView.setCellFactory(listView -> new FlashcardListViewCellQuiz(currIndex, isQuestion));

    }




    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Flashcard} using a {@code FlashcardCard}.
     */
    class FlashcardListViewCell extends ListCell<Flashcard> {
        @Override
        protected void updateItem(Flashcard flashcard, boolean empty) {
            super.updateItem(flashcard, empty);

            if (empty || flashcard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FlashcardCard(flashcard, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Flashcard} using a {@code FlashcardCard}.
     */
    class FlashcardListViewCellQuiz extends ListCell<Flashcard> {
        private int currIndex;
        private boolean isQuestion;

        public FlashcardListViewCellQuiz(int currIndex, boolean isQuestion) {
            this.currIndex = currIndex;
            this.isQuestion = isQuestion;
        }

        @Override
        protected void updateItem(Flashcard flashcard, boolean empty) {
            super.updateItem(flashcard, empty);

            if (empty || flashcard == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (currIndex == QUIZ_LIST) {
                    setGraphic(new FlashcardCard(flashcard, getIndex() + 1, isQuestion).getRoot());
                } else {
                    setGraphic(new FlashcardCard(flashcard, currIndex, isQuestion).getRoot());

                }
            }
        }
    }
}

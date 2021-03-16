package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Flashcard;

/**
 * Panel containing the list of persons.
 */
public class FlashcardListPanel extends UiPart<Region> {
    private static final String FXML = "FlashcardListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FlashcardListPanel.class);
    public static final int QUIZ_LIST = -1;

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
    public FlashcardListPanel(ObservableList<Flashcard> flashcardList, boolean isNotAns, int currIndex) {
        super(FXML);
        flashcardListView.setItems(flashcardList);
        flashcardListView.setCellFactory(listView -> new FlashcardListViewCellQuiz(currIndex, isNotAns));

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
        private boolean isNotAns;

        public FlashcardListViewCellQuiz(int currIndex, boolean isNotAns) {
            this.currIndex = currIndex;
            this.isNotAns = isNotAns;
        }

        @Override
        protected void updateItem(Flashcard flashcard, boolean empty) {
            super.updateItem(flashcard, empty);

            if (empty || flashcard == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (currIndex == QUIZ_LIST) {
                    setGraphic(new FlashcardCard(flashcard, getIndex() + 1, isNotAns).getRoot());
                } else {
                    setGraphic(new FlashcardCard(flashcard, currIndex, isNotAns).getRoot());

                }
            }
        }
    }
}

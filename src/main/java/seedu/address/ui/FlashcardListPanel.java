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
    public FlashcardListPanel(ObservableList<Flashcard> flashcardList, boolean isQuiz) {
        super(FXML);
        flashcardListView.setItems(flashcardList);
        flashcardListView.setCellFactory(listView -> new FlashcardListViewCellQuiz());
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
        @Override
        protected void updateItem(Flashcard flashcard, boolean empty) {
            super.updateItem(flashcard, empty);

            if (empty || flashcard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FlashcardCard(flashcard, getIndex() + 1, true).getRoot());
            }
        }
    }
}

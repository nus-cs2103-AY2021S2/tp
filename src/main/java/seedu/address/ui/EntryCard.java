package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.entry.Entry;

/**
 * An UI component that displays information of a {@code Entry}.
 */
public class EntryCard extends UiPart<Region> {
    private static final String FXML = "EntryListCard.fxml";

    public final Entry entry;

    @FXML
    private HBox cardPane;
    @FXML
    private Label entryName;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code EntryCard} to display.
     */
    public EntryCard(Entry entry) {
        super(FXML);
        this.entry = entry;
        entryName.setText(entry.getEntryName().name);
        startDate.setText("From: "
                + entry.getStartDate().format(DateTimeFormatter.ofPattern("E, dd MMM yyyy h:mm a")));
        endDate.setText("To: "
                + entry.getEndDate().format(DateTimeFormatter.ofPattern("E, dd MMM yyyy h:mm a")));
        entry.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}

package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.medical.SaveMedicalRecordCommand;
import seedu.address.model.medical.MedicalRecord;
import seedu.address.model.medical.Section;
import seedu.address.model.person.Patient;

/**
 * Controller for a help page
 */
public class EditorWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://github.com/AY2021S2-CS2103T-W12-1/"
            + "tp/blob/master/docs/UserGuide.md";
    public static final String HELP_MESSAGE = "Refer to the full user guide here: " + USERGUIDE_URL + "\n";
    public static final String TITLE_EMPTY_MESSAGE = "Section title cannot be empty!";
    public static final String BODY_EMPTY_MESSAGE = "Section body cannot be empty!";

    private static final Logger logger = LogsCenter.getLogger(EditorWindow.class);
    private static final String FXML = "EditorWindow.fxml";
    private final CommandExecutor commandExecutor;
    private final Patient patient;
    private final MedicalRecord mrec;
    private final ObservableList<Section> sections;

    @FXML
    private Button returnButton;

    @FXML
    private Button newSectionButton;

    @FXML
    private ListView<Section> sectionListView;

    /**
     * Creates a new EditorWindow.
     * Associated MedicalRecord is newly created if there is no valid one passed in
     * @param root Stage to use as the root of the HelpWindow.
     */
    private EditorWindow(Stage root, CommandExecutor commandExecutor, Patient patient, MedicalRecord medicalRecord) {
        super(FXML, root);
        this.commandExecutor = commandExecutor;
        this.patient = patient;
        if (medicalRecord != null) {
            this.mrec = medicalRecord;
            root.setTitle(patient.getName().toString() + " - " + medicalRecord.getDateDisplay());
        } else {
            this.mrec = new MedicalRecord(LocalDateTime.now(), List.of(new Section("", "")));
            root.setTitle("New Medical Record - " + patient.getName().toString());
        }
        this.sections = FXCollections.observableArrayList(this.mrec.getSections());
        this.sectionListView.setItems(this.sections);
        this.sectionListView.setCellFactory(listView -> new SectionListViewCell());

        if (this.mrec.getDate().isBefore(LocalDateTime.now().minusDays(1))) {
            newSectionButton.setDisable(true);
            returnButton.setDisable(true);
            this.sectionListView.setCellFactory(listView -> new SectionListDisabledViewCell());
        }
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class SectionListViewCell extends ListCell<Section> {
        @Override
        protected void updateItem(Section section, boolean empty) {
            super.updateItem(section, empty);
            if (empty || section == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SectionCard(section, true).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class SectionListDisabledViewCell extends ListCell<Section> {
        @Override
        protected void updateItem(Section section, boolean empty) {
            super.updateItem(section, empty);
            if (empty || section == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SectionCard(section, false).getRoot());
            }
        }
    }

    /**
     * Creates a new EditorWindow.
     */
    public EditorWindow(CommandExecutor commandExecutor, Patient patient, MedicalRecord medicalRecord) {
        this(new Stage(), commandExecutor, patient, medicalRecord);
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Opens a new section
     */
    @FXML
    private void handleNewSection() throws CommandException {
        Section s = new Section("", "");
        this.sections.add(s);
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        this.hide();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleReturn() throws CommandException {
        // if both title and body fields are empty, we just ignore that section and
        // don't include it in the MedicalRecord
        List<Section> filteredSections = new ArrayList<>();
        for (int i = 0; i < this.sections.size(); i++) {
            Section section = this.sections.get(i);
            if (section.getBody().length() != 0 || section.getTitle().length() != 0) {
                filteredSections.add(section);
            }
        }

        MedicalRecord newMrec = new MedicalRecord(this.mrec.getDate(), filteredSections);
        SaveMedicalRecordCommand saveCommand = new SaveMedicalRecordCommand(this.patient, newMrec);
        this.commandExecutor.execute(saveCommand);
        this.hide();
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(Command command) throws CommandException;
    }
}

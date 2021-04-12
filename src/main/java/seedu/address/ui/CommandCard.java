package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of an {@code Important Date}.
 */
public class CommandCard extends UiPart<Region> {

    private static final String FXML = "CommandCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final String command;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label commandName;
    @FXML
    private Label commandFormat;

    /**
     * Creates a {@code CommandCard} with the given {@code String}.
     */
    public CommandCard(String command) {
        super(FXML);
        this.command = command;
        commandName.setText(command.split(": ")[0]);
        commandFormat.setText(command.split(": ")[1]);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandCard)) {
            return false;
        }

        // state check
        CommandCard card = (CommandCard) other;
        return commandName.getText().equals(card.commandName.getText())
                && commandFormat.getText().equals(card.commandFormat.getText());
    }

}

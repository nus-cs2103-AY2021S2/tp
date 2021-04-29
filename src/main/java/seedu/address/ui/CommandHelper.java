package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;

/**
 * Wraps Commands for Help Window display.
 */
public class CommandHelper {

    private SimpleStringProperty commandTitle;
    private SimpleStringProperty commandUsage;

    /**
     * Constructs a {@code CommandHelper} to display command information in {@Code TableView}
     * @param commandTitle description title of command
     * @param commandUsage actual command to input
     */
    CommandHelper(String commandTitle, String commandUsage) {
        this.commandTitle = new SimpleStringProperty(commandTitle);
        this.commandUsage = new SimpleStringProperty(commandUsage);
    }

    public String getCommandTitle() {
        return commandTitle.get();
    }

    public String getCommandUsage() {
        return commandUsage.get();
    }
}

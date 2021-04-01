package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;

public class CommandHelper {

    SimpleStringProperty commandTitle;
    SimpleStringProperty commandUsage;

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
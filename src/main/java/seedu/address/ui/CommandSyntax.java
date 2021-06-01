package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class that encapsulates information about a command and it's syntax to display to the user.
 */
public class CommandSyntax {
    private SimpleStringProperty command;
    private SimpleStringProperty usage;

    /**
     * Creates a {@code CommandSyntax} object with the given {@code command} and {@code usage}
     * @param command Details about the command.
     * @param usage Details about the usage of a command.
     */
    public CommandSyntax(String command, String usage) {
        this.command = new SimpleStringProperty(command);
        this.usage = new SimpleStringProperty(usage);
    }

    public String getCommand() {
        return command.get();
    }

    public String getUsage() {
        return usage.get();
    }
}

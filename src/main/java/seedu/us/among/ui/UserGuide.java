package seedu.us.among.ui;

/**
 * The Data model for the user guide shown in the help window
 */
public class UserGuide {
    private String command = "";
    private String example = "";

    public UserGuide() {
        this("", "");
    }

    /**
     * @param command description of the command
     * @param example description of the example
     */
    public UserGuide(String command, String example) {
        setCommand(command);
        setExample(example);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}

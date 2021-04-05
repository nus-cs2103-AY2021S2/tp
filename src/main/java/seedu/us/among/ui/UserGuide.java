package seedu.us.among.ui;

/**
 * The Data model for the user guide shown in the help window
 */
public class UserGuide {
    private String action = "";
    private String example = "";

    public UserGuide() {
        this("", "");
    }

    /**
     * @param action description of the action
     * @param example description of the example
     */
    public UserGuide(String action, String example) {
        setAction(action);
        setExample(example);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}

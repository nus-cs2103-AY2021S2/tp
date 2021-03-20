package seedu.us.among.ui;

/**
 * The Data model for the user guide shown in the help window
 */
public class UserGuide {
    private String action = "";
    private String format = "";
    private String example = "";

    public UserGuide() {
        this("", "", "");
    }


    /**
     * @param action description of the action
     * @param format description of the format
     * @param example description of the examples
     */
    public UserGuide(String action, String format, String example) {
        setAction(action);
        setFormat(format);
        setExample(example);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}

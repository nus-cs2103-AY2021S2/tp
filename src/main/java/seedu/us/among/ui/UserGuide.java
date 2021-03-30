package seedu.us.among.ui;

/**
 * The Data model for the user guide shown in the help window
 */
public class UserGuide {
    private String action = "";
    private String format = "";

    public UserGuide() {
        this("", "");
    }

    /**
     * @param action description of the action
     * @param format description of the format
     */
    public UserGuide(String action, String format) {
        setAction(action);
        setFormat(format);
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
}

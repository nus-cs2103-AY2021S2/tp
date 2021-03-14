package seedu.address.model.medical;

public class Section {
    String title;
    String body;
    public static final int MAX_TITLE_LENGTH = 30;
    public static final String MESSAGE_CONSTRAINTS = "Title must be less than " + MAX_TITLE_LENGTH + " characters long";

    public Section(String title) {
        new Section(title, "");
    }

    public Section(String title, String body) {
        this.title = title;
        this.body = "";
    }

    public static boolean isValidTitleBody(String title, String body) {
        return title.length() <= MAX_TITLE_LENGTH;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}

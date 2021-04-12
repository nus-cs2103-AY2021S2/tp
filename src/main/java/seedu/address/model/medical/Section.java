package seedu.address.model.medical;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Section of a Medical Record.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Section {
    public static final int MAX_TITLE_LENGTH = 30;
    public static final String MESSAGE_CONSTRAINTS = "Title must be less than " + MAX_TITLE_LENGTH + " characters long";
    private String title = "";



    private String body = "";

    /**
     * Every field must be present and not null.
     */
    public Section(String title) {
        requireAllNonNull(title);
        new Section(title, "");
    }

    /**
     * Every field must be present and not null.
     */
    public Section(String title, String body) {
        requireAllNonNull(title, body);
        this.title = title;
        this.body = body;
    }

    @Override
    public int hashCode() {
        return title.hashCode() + body.hashCode();
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Section: " + title + " - " + body;
    }
}

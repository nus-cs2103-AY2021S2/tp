package seedu.smartlib.ui;

import javafx.beans.DefaultProperty;

/**
 * A test object which can be constructed via an FXML file.
 * Unlike other JavaFX classes, this class can be constructed without the JavaFX toolkit being initialized.
 */
@DefaultProperty("text")
public class TestFxmlObject {

    private String text;

    /**
     * Constructor for the TestFxmlObject.
     */
    public TestFxmlObject() {}

    /**
     * Constructor for the TestFxmlObject that initializes the object with a specified text.
     *
     * @param text text that the object is to be associated with.
     */
    public TestFxmlObject(String text) {
        setText(text);
    }

    /**
     * Returns the text associated with this object.
     *
     * @return the text associated with this object.
     */
    public String getText() {
        return text;
    }

    /**
     * Updates the text associated with this object.
     *
     * @param text the text to be updated with.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Checks if this TestFxmlObject is equal to another TestFxmlObject.
     *
     * @param other the other TestFxmlObject to be compared.
     * @return true if this TestFxmlObject is equal to the other TestFxmlObject, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TestFxmlObject // instanceof handles nulls
                        && text.equals(((TestFxmlObject) other).getText()));
    }

}

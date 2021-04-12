package seedu.dictionote.ui;


/**
 * DictionaryContentConfig is used as a access point for others component to request for its UI changes.
 */
interface DictionaryListPanelConfig {
    /**
     * Checks if the content panel is visible
     * @return true if content panel is visible
     */
    boolean isContentVisible();

    /**
     * Opens the Content Panel and close the definition panel.
     */
    void openContentDisplay();

    /**
     * Opens the Definition Panels and close the content panel.
     */
    void openDefinitionDisplay();
}

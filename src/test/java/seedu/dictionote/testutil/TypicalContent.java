package seedu.dictionote.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.dictionote.model.Dictionary;
import seedu.dictionote.model.dictionary.Content;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalContent {

    public static final Content WEEK_1 = new Content("OOP programming");
    public static final Content WEEK_14 = new Content("Reading Week!");
    public static final Content WEEK_16 = new Content("Congrats you're done with the module!");

    private TypicalContent() {} // prevents instantiation

    /**
     * Returns an {@code NoteBook} with all the typical notes.
     */
    public static Dictionary getTypicalDictionary() {
        Dictionary dc = new Dictionary();
        for (Content content : getTypicalContent()) {
            dc.addContent(content);
        }
        return dc;
    }

    public static List<Content> getTypicalContent() {
        return new ArrayList<>(Arrays.asList(WEEK_1, WEEK_14, WEEK_16));
    }
}

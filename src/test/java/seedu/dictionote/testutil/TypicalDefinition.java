package seedu.dictionote.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.dictionote.model.DefinitionBook;
import seedu.dictionote.model.Dictionary;
import seedu.dictionote.model.dictionary.Content;
import seedu.dictionote.model.dictionary.Definition;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalDefinition {

    public static final Definition OOP = new Definition("OOP\n", "Object Orientated Programming\n");
    public static final Definition Integer = new Definition("Integer\n", "A data type, involving numbers.\n");
    public static final Definition Java = new Definition("Java\n", "A programming language.\n");

    private TypicalDefinition() {} // prevents instantiation

    /**
     * Returns an {@code NoteBook} with all the typical notes.
     */
    public static DefinitionBook getTypicalDefinitionBook() {
        DefinitionBook dfb = new DefinitionBook();
        for (Definition definition : getTypicalDefinition()) {
            dfb.addDefinition(definition);
        }
        return dfb;
    }

    public static List<Definition> getTypicalDefinition() {
        return new ArrayList<>(Arrays.asList(OOP, Integer, Java));
    }
}

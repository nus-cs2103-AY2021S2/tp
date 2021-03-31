package dog.pawbook.ui;

import java.util.Arrays;
import java.util.List;

/**
 * A class containing command keywords to be matched with for autocompletion.
 */
public class CommandSuggestions {
    private static List<String> suggestions = Arrays.asList(
        "add dog", "add owner", "add program",
        "delete dog", "delete owner", "delete program",
        "edit dog", "edit owner", "edit program",
        "list", "exit", "help"
    );

    public static List<String> getSuggestions() {
        return suggestions;
    }

    public static void addSuggestion(String suggestion) {
        suggestions.add(suggestion);
    }

}

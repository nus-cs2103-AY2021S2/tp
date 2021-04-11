package seedu.address.logic.commands.meetings;

import java.util.List;

public class CommandDisplayUtil {

    /**
     * Customises the format of the string to display in the command message, instead of relying on the List toString
     * method.
     * @return
     */

    public static String formatElementsIntoRows(List<?> listItems) {
        String formatString = "";
        for (Object o : listItems) {
            formatString += listItems.toString();
            formatString += "\n";
        }
        return formatString;
    }
}

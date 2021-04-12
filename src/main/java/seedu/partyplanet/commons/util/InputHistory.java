package seedu.partyplanet.commons.util;

import java.util.ArrayList;
import java.util.List;

/**
 * InputHistory records and retrieves user's GUI input history.
 */
public class InputHistory {
    private static final int MAXSIZE = 20;

    // TODO Can optimise internal list to a linkedlist structure for efficiency
    private List<String> lst;
    private int current;

    /**
     * Initialise InputHistory
     */
    public InputHistory() {
        lst = new ArrayList<>(MAXSIZE);
        current = 0;
    }

    /**
     * Adds new input to history, given it is different from the previous input.
     * If lst have reached maxsize, remove earliest entry.
     *
     * @param s User input.
     */
    public void add(String s) {
        if (lst.isEmpty() || isDifferentFromLatest(s)) {
            if (lst.size() == MAXSIZE) {
                lst.remove(0);
            }
            lst.add(s);
        }

        current = lst.size();
    }

    private boolean isDifferentFromLatest(String s) {
        String latest = lst.get(lst.size() - 1);
        return !s.equals(latest);
    }

    /**
     * Retrieves the previous input in history.
     *
     * @return Input from history.
     */
    public String getPrevious() {
        if (lst.isEmpty()) {
            return "";
        } else if (current == 0) {
            return lst.get(current);
        } else {
            return lst.get(--current);
        }
    }

    /**
     * Retrieves the next input in history.
     * The latest entry will always be an empty string.
     *
     * @return Input from history.
     */
    public String getNext() {
        if (lst.isEmpty()) {
            return "";
        } else if (current == lst.size()) {
            return "";
        } else if (current == lst.size() - 1) {
            current++;
            return "";
        } else {
            return lst.get(++current);
        }
    }

    /**
     * Returns currrent number of input in InputHistory
     */
    public int size() {
        return lst.size();
    }
}

package seedu.address.model.flashcard;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public enum SortOptions implements Comparator<Flashcard> {
    QUESTION_ASCENDING("question a") {
        @Override
        public int compare(Flashcard o1, Flashcard o2) {
            return o1.getQuestion().fullQuestion.compareTo(o2.getQuestion().fullQuestion);
        }
    },
    QUESTION_DESCENDING("question d") {
        @Override
        public int compare(Flashcard o1, Flashcard o2) {
            return o2.getQuestion().fullQuestion.compareTo(o1.getQuestion().fullQuestion);
        }
    },
    PRIORITY_ASCENDING("priority a") {
        @Override
        public int compare(Flashcard o1, Flashcard o2) {
            String p1 = o1.getPriority().value;
            String p2 = o2.getPriority().value;
            if (p1.equals("High")) {
                if (p2.equals("High")) {
                    return 0;
                } else {
                    return 1;
                }
            } else if (p1.equals("Mid")) {
                if (p2.equals("High")) {
                    return -1;
                } else if (p2.equals("Mid")) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                if (p1.equals(p2)) {
                    return 0;
                } else {
                    return -1;
                }
            }

        }
    },
    PRIORITY_DESCENDING("priority d") {
        @Override
        public int compare(Flashcard o1, Flashcard o2) {
            String p1 = o1.getPriority().value;
            String p2 = o2.getPriority().value;
            if (p1.equals("High")) {
                if (p2.equals("High")) {
                    return 0;
                } else {
                    return -1;
                }
            } else if (p1.equals("Mid")) {
                if (p2.equals("High")) {
                    return 1;
                } else if (p2.equals("Mid")) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                if (p1.equals(p2)) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
    };

    public static final String MESSAGE_INVALID_SORT_OPTIONS = "Option is invalid.";
    private static final Map<String, SortOptions> SORT_OPTIONS_MAP = new TreeMap<>();

    public final String option;
    private SortOptions(String option) {
        this.option = option;
    }

    static {
        for (SortOptions sortOptions: values()) {
            SORT_OPTIONS_MAP.put(sortOptions.option, sortOptions);
        }
    }

    public static boolean isValidOption(String option) {
        return SORT_OPTIONS_MAP.containsKey(option);
    }

    public static SortOptions getOption(String option) {
        return SORT_OPTIONS_MAP.get(option);
    }
}

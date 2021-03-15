package seedu.address.model.util;

import java.util.Collections;
import java.util.List;

public abstract class ModelSinglePredicate<U> extends ModelPredicate<U> {

    private final List<String> keywords;

    public ModelSinglePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return Collections.unmodifiableList(this.keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ModelSinglePredicate // instanceof handles nulls
            && keywords.equals(((ModelSinglePredicate) other).keywords)); // state check
    }

}

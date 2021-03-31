package seedu.address.logic.filters;

import seedu.address.model.customer.Customer;
import seedu.address.model.tag.Tag;

public class TagFilter extends AbstractFilter {
    public TagFilter(String filterString) {
        super(filterString.trim());
    }

    @Override
    public boolean test(Customer customer) {
        Object[] tags = customer.getTags().toArray();
        boolean containsTag = false;
        for (Object tag : tags) {
            if (((Tag) tag).tagName.trim().startsWith(filterString)) {
                containsTag = true;
                break;
            }
        }
        return containsTag;
    }
}

package seedu.address.logic.filters;

import seedu.address.model.customer.Customer;
import seedu.address.model.tag.Tag;

import java.util.List;
import java.util.stream.Collectors;


public class TagFilter extends AbstractFilter {
    public TagFilter(String filterString) {
        super(filterString.trim());
    }

    @Override
    public boolean test(Customer customer) {
        Object[] tags = customer.getTags().toArray();
        boolean containsTag = false;
        for (int i = 0; i < tags.length; i++) {
            if (((Tag) tags[i]).tagName.trim().startsWith(filterString)) {
                containsTag = true;
                break;
            }
        }
        return containsTag;
    }
}

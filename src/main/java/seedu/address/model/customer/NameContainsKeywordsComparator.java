package seedu.address.model.customer;

import java.util.Comparator;
import java.util.List;

import seedu.address.commons.util.StringUtil;

public class NameContainsKeywordsComparator implements Comparator<Customer> {

    private final List<String> keywords;

    public NameContainsKeywordsComparator(List<String> keywords) {
        this.keywords = keywords;
    }

    private static double getSimilarityScore(Customer customer, List<String> keywords) {
        return keywords.stream()
                    .mapToDouble(keyword -> {
                        String name = customer.getName().fullName;

                        if (StringUtil.containsWordIgnoreCase(name, keyword)) {
                            return keyword.length() * keyword.length();
                        } else if (StringUtil.containsPrefixWordIgnoreCase(name, keyword)) {
                            return keyword.length();
                        } else {
                            return 0;
                        }
                    }).sum();
    }

    @Override
    public int compare(Customer customer, Customer otherCustomer) {
        double score1 = getSimilarityScore(customer, keywords);
        double score2 = getSimilarityScore(otherCustomer, keywords);

        if (score1 < score2) {
            return 1;
        } else if (score1 > score2) {
            return -1;
        }
        return customer.getName().fullName.toLowerCase().compareTo(otherCustomer.getName().fullName.toLowerCase());
    }

}

package seedu.address.model.person.comparators;

import java.util.Comparator;

import seedu.address.model.date.ImportantDate;



public class ImportantDateDetailsComparator implements Comparator<ImportantDate> {
    public ImportantDateDetailsComparator(){
    }

    @Override
    public int compare(ImportantDate i1, ImportantDate i2) {
        return i1.compareTo(i2);
    }

}

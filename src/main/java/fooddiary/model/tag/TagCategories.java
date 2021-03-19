package fooddiary.model.tag;

import java.util.ArrayList;

public enum TagCategories {
    FASTFOOD,
    WESTERN,
    INDIAN,
    CHINESE,
    FUSION,
    JAPANESE,
    MALAY,
    HALAL,
    VEGETARIAN,
    VEGAN,
    FRUITS,
    DESSERT,
    OTHERS,
    INVALID;

    /**
     * Checks if a String given fits any of the Categories in TagCategories.
     *
     * @param test
     * @return boolean
     */
    public static boolean matches(String test) {
        ArrayList<String> categories = new ArrayList<>();
        for (TagCategories tagCategory : TagCategories.values()) {
            categories.add(tagCategory.name().toLowerCase());
        }
        return categories.contains(test.toLowerCase());
    }

    /**
     * Finds a TagCateogry for user to save as,
     * if category is not found, classified as others.
     *
     * @param category
     * @return a TagCategory based on what user has input
     */
    public static TagCategories find(String category) {
        for (TagCategories tagCategory : TagCategories.values()) {
            if (tagCategory.name().toLowerCase().contains(category.toLowerCase())) {
                return tagCategory;
            }
        }
        return TagCategories.INVALID;
    }

    /**
     * Displays TagCategories in title case.
     *
     * @return string of tag category in title case
     */
    public String titleCase() {
        String categoryInUpperCase = this.name();
        String firstLetter = categoryInUpperCase.substring(0, 1);
        String remainingLetters = categoryInUpperCase.substring(1);
        remainingLetters = remainingLetters.toLowerCase();
        String categoryInTitleCase = firstLetter + remainingLetters;
        return categoryInTitleCase;
    }
}

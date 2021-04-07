package fooddiary.model.tag;

import java.util.ArrayList;

public enum Categories {
    CHINESE,
    DESSERT,
    FASTFOOD,
    FRUITS,
    FUSION,
    HALAL,
    INDIAN,
    JAPANESE,
    KOREAN,
    MALAY,
    VEGAN,
    VEGETARIAN,
    WESTERN,
    OTHERS,
    INVALID;

    /**
     * Checks if a String given fits any of the Categories in Categories.
     *
     * @param test
     * @return boolean
     */
    public static boolean matches(String test) {
        ArrayList<String> categories = new ArrayList<>();
        for (Categories category : Categories.values()) {
            categories.add(category.name().toLowerCase());
        }
        return categories.contains(test.toLowerCase());
    }

    /**
     * Finds a Category for user to save as,
     * if category is not found, classified as others.
     *
     * @param categoryInput
     * @return a Category based on what user has input
     */
    public static Categories find(String categoryInput) {
        for (Categories category : Categories.values()) {
            if (category.name().toLowerCase().contains(categoryInput.toLowerCase())) {
                return category;
            }
        }
        return Categories.INVALID;
    }

    /**
     * Displays Categories in title case.
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

    /**
     * Lists all Categories
     * @return list of Categories
     */
    public static String listAll() {
        StringBuilder list = new StringBuilder("");
        for (Categories category : Categories.values()) {
            if (!category.name().equals("INVALID")) {
                list.append(category.titleCase());
                list.append("\n");
            }
        }
        return list.toString();
    }
}

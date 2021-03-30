---
layout: page
title: User Guide
---

###  Done by (CS2103-T14-2):
Donavan Lim, Marcus Lee Eugene, Chong Sidney, Dinesh S/O Magesvaran, Prabhakaran Gokul

---

* Table of Contents
{:toc}

    * Description
    * Features
        * add
        * delete
        * list
        * find
        * findall
        * view
        * revise
    * Command Summary
    * UI mock-up

--------------------------------------------------------------------------------------------------------------------
## Description

The Food Diary is a desktop app for managing food diary entries, optimized with a Command Line Interface (CLI) and packaged with a Graphical User Interface (GUI).

The Food Diary caters to food-passionate NUS students who would ideally benefit from keeping records of food options tasted in the vicinity of NUS.

The Food Diary will allow students to save time and effort when finding places to
eat around the NUS vicinity. The Food Diary especially caters to students chiefly on 3 aspects:
1. The ability for users to log personal food reviews tagged under different categories for future reference.
2. The ability to effortlessly reference food options based on relevant filters in a user-friendly GUI
3. The ability to import and export their personal food diary to share with friends.

--------------------------------------------------------------------------------------------------------------------

## Features

### View all the food reviews : `list`

Lists all the restaurants with food reviews.

Format: `list`

Parameter: none

Example:
(Refer to mockup)

### Add an entry: `add`

Adds an entry to the Food Diary.

Format: `add n/<RESTAURANT NAME> ra/RATING re/REVIEW a/ADDRESS c/CATEGORIES`

Parameters:

   1. `Name (of restaurant)`
   2. `Rating`
   3. `Address`
   4. `Review`
   5. `Categories (tag)`

Example:

    add  n/Al Amaan Restaurant ra/5 re/best for Butter Chicken a/12 Clementi Rd, Singapore 129742 c/Indian Muslim

### Addon a review or a price to an entry: `addon`
Adds-on a review and/or a price to an entry of the Food Diary.

Format: `addon INDEX [re/REVIEW] [p/PRICE]` or `addon INDEX [p/PRICE] [re/REVIEW]` 

- Adds on a review and/or a price to an entry at the specified `INDEX`. The index
refers to the index number shown in the displayed entry list. The index must be a
  positive integer (e.g. 1,2,3,...)
- At least one of the optional fields must be provided
- Existing reviews in the entry (at the specified `INDEX`) will be added on to the input reviews
- Existing price/price range in the entry (at the specified `INDEX`) will be updated according the 
input price

Examples:

    addon 1 re/I like the way the rice is cooked p/6
    addon 2 re/I like the way the rice is cooked
    addon 3 p/6
    addon 3 p/6 re/I like the way the rice is cooked

### Delete an entry: `delete`

Deletes an entry from the Food Diary.

Format: `delete n/NAME` or `delete i/INDEX`

Parameter: `Restaurant name` or `Index`

Example:

    delete n/McDonald’s Clementi Mall

### Find for any entries

Finds for entries whose names, ratings, address and categories match any of the provided keywords.

Format: `find KEYWORDS`

Parameter: `Restaurant name` or `Rating` or `Address` or `Categories`

Example:

    find kfc

    find fastfood indian

    find clementi 5/5

### Find for specific entries

Finds for entries whose names, ratings, address and categories match all of the provided keywords.

Format: `findall KEYWORDS`

Parameter: `Restaurant name` or `Rating` or `Address` or `Categories`

Example:

    find clementi fastfood 5/5

### View a specific entry

Opens up a window, showing the details of a specified entry in a full expanded view. Allows the user to read through 
reviews that are too lengthy to be shown in the main UI window.

Format: `view <INDEX>`

Parameter: `Index of entry`

Example:

    view 1

### Revise a specific entry

Opens up a window, showing the existing details of an entry and allowing for quick corrections and updates without 
requiring the use of prefixes and command syntax in the UI.

Format: `revise <INDEX>`

Parameter: `Index of entry`

Example:

    revise 1

-------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/<RESTAURANT NAME> ra/5 re/REVIEW a/ADDRESS c/CATEGORIES` <br> e.g., `add  n/Al Amaan Restaurant ra/4 re/best for Butter Chicken a/12 Clementi Rd, Singapore 129742 c/Indian Muslim`
**AddOn** |`addon INDEX [re/REVIEW] [p/PRICE]` or `addon INDEX [p/PRICE] [re/REVIEW]` <br>e.g,`addon 1 re/I like this food a lot p/5`
**Delete** | `delete n/NAME or delete i/INDEX` <br> e.g., `delete n/Al Amaan Restaurant or delete i/1`
**List** | `list`
**Find** | `find kfc`
**FindAll** |`findall clementi fastfood 5/5`
**View** |`view 1`
**Revise** |`revise 1`

## <center> Appendix </center>

### UI Mock-up

![Ui Mock-up](images/Ui.png)

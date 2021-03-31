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
        * addon
        * delete
        * list
        * find
        * findall
        * help
        * view
        * revise
    * Command Summary
    * UI mock-up

--------------------------------------------------------------------------------------------------------------------

## Description

The Food Diary is a desktop app for managing food diary entries, optimized with a Command Line Interface (CLI)
and packaged with a Graphical User Interface (GUI).

The Food Diary caters to food-passionate NUS students who would ideally benefit from keeping records of
food options tasted in the vicinity of NUS.

The Food Diary will allow students to save time and effort when finding places to
eat around the NUS vicinity. The Food Diary especially caters to students chiefly on 3 aspects:
1. The ability for users to save food diary entries for future reference.
2. The ability for users to find entries where food are within a certain price range.
3. The ability to effortlessly reference food options based on relevant filters in a user-friendly GUI

--------------------------------------------------------------------------------------------------------------------

## Features

### View all the food reviews : `list`

Lists all the restaurants with food reviews.

Format: `list`

Parameter: none

    list

Example:
(Refer to Main Window Ui in Appendix)

### Add an entry: `add`

Adds an entry to the Food Diary.

Format: `add n/<RESTAURANT NAME> ra/<RATING> p/<PRICE> re/<REVIEW> a/<ADDRESS> c/<CATEGORIES>`

Parameters:

1. `Restaurant Name`
2. `Rating`
3. `Price`
3. `Address`
4. `Review`
5. `Categories (tag)`


Example:

    add  n/Al Amaan Restaurant ra/5 p/8 re/best for Butter Chicken a/12 Clementi Rd, Singapore 129742 c/Indian Muslim

### Addon a review or a price to an entry: `addon`
Adds-on a review and/or a price to an entry of the Food Diary.

Format: `addon <INDEX> re/<REVIEW> p/<PRICE>` or `addon <INDEX> p/<PRICE> re/<REVIEW>`

Parameters: 

1. `Index`
2. `Review` or `Price` or both.

- Adds on a review and/or a price to an entry at the specified `INDEX`. The index
refers to the index number shown in the displayed entry list. The index must be a
  positive integer (e.g. 1, 2, 3, ...).
- At least one of the optional fields must be provided.
- Existing reviews in the entry (at the specified `INDEX`) will be added on to the input reviews.
- Existing price/price range in the entry (at the specified `INDEX`) will be updated with consideration
to the input price on top of the existing price.

Examples:

    addon 1 re/I like the way the rice is cooked p/6
    addon 2 re/I like the way the rice is cooked
    addon 3 p/6
    addon 3 p/6 re/I like the way the rice is cooked

### Delete an entry: `delete`

Deletes an entry from the Food Diary.

Format: `delete n/<NAME>` or `delete i/<INDEX>`

Parameters:

1. `Restaurant name` or `Index`.

Example:

    delete n/McDonald’s Clementi Mall

### Find entries generally: `find`

Finds entries whose names, ratings, price, address, categories and schools match any of the provided keywords.

- More than one keyword per field can be accepted as parameters.
- Different fields can also be simultaneously accepted as parameters.
- For the price field, a price range can also be accepted as a parameter, and any entry that contains at least one of the prices within the specified range will be returned as a search result.

Format: `find <KEYWORD> ...`

Parameters:

Keyword(s) of any number and sequence:
1. `Restaurant Name`
2. `Rating/5`
3. `$Price`
4. `$Price-Price`
5. `Address`
6. `Categories`
7. `Schools`


Example:
  
    find techno
    find science fass
    find fastfood indian $6
    find clementi 5/5 $8-15 western

### Find specific entries: `findall`

Finds for entries whose names, ratings, price, address, categories and schools match all of the provided keywords.

- More than one keyword per field can be accepted as parameters.
- Different fields can also be simultaneously accepted as parameters.
- For the price field, a price range can also be accepted as a parameter, and any entry that contains at least one of the prices within the specified range will be returned as a search result.
- **Unlike the find feature, the findall feature only returns search results of entries that contain all of
the provided keywords.**

Format: `findall <KEYWORD> ...`

Parameters:

Keyword(s) of any number and sequence:
1. `Restaurant Name`
2. `Rating/5`
3. `$Price`
4. `$Price-Price`
5. `Address`
6. `Categories`
7. `Schools`

Example:

    findall clementi fastfood 5/5 $9

### View a specific entry

Opens up a window, showing the details of a specified entry in a full expanded view. Allows the user to read through
 reviews that are too lengthy to be shown in the main UI window.

Format: `view <INDEX>`

Parameter:

1. `Index of entry`

Example: (Refer to View Window Ui in Appendix)

    view 1

### View all the food reviews : `list`

Opens up a window to show a condensed form of all the different commands,
 and parameters of the commands,
 that can be typed by the user in The Food Diary.

Format: `help`

Parameter: none

Example: (Refer to Help Window in Appendix)

    help

### Revise a specific entry

Opens up a window, showing the existing details of an entry and allowing for quick corrections and updates without
 requiring the use of prefixes and command syntax in the UI.

Format: `revise <INDEX>`

Parameter:

1. `Index of entry`

Example:

    revise 1

-------------------------------------------------------------------------------------

## FAQ

Q: How do I transfer my data to another Computer?

A: Install the app in the other computer and overwrite the empty data file it creates with the file that
contains the data of your previous AddressBook home folder.


-------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/<RESTAURANT NAME> ra/<RATING FROM 0-5> p/<PRICE> re/<REVIEW> a/<ADDRESS> c/<CATEGORIES>` <br> e.g., `add  n/Al Amaan Restaurant ra/4 p/6 re/best for Butter Chicken a/12 Clementi Rd, Singapore 129742 c/Indian Muslim`
**AddOn** |`addon <INDEX> re/<REVIEW> p/<PRICE>` or `addon <INDEX> p/<PRICE> re/<REVIEW>` <br>e.g,`addon 1 re/I like this food a lot p/5`
**Delete** | `delete <INDEX>` <br> e.g., `delete 1`
**List** | `list`
**Find** | `find <KEYWORD> ...` <br> e.g., `find kfc`
**FindAll** |`findall <KEYWORD> ...` <br> e.g., `findall clementi fastfood 5/5 $5-10`
**Help** | `help`
**View** |`view <INDEX>` <br> e.g `view 1`
**Revise** |`revise <INDEX>` <br> e.g `revise 1`

## Keyboard Shortcuts Summary

Action | Keyboard Shortcut| Windows where Keyboard shortcut is available
:-------:|:------------------:|:---------------------------------------------:
**Exit/Close Window** | `ESC` | Main Window, View Window, Revise Window, Help Window
**Open Help Window** | `F1` | Main Window 
**Skip through text fields in Revise Window** | `TAB` | Revise Window 
**Save Changes in Revise Window** | `Ctrl + S (Windows OS)` or `Command + S (macOS)` | Revise Window

## Glossary 

- **Main Window:** The Window that appears when the application starts up
- **View Window:** The Window that appears when viewing a FoodDiary entry through the `view` command
- **Revise Window** The Window that appears when revising a FoodDairy entry through the `edit` command


## <center> Appendix </center>

### Main Window UI

![Main Window UI](images/Ui.png)

### Help Window UI

![Help Window UI](images/HelpWindowUi.png)

### View Window UI

![View Window UI](images/ViewWindowUi.png)

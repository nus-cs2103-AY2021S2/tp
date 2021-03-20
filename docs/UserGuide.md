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

### Add a food review: `add`

Adds a food review to the Food Diary.

Format: `add n/<RESTAURANT NAME> ra/RATING re/REVIEW a/ADDRESS c/CATEGORIES`

Parameters:

   1. `Name (of restaurant)`
   2. `Rating`
   3. `Address`
   4. `Review`
   5. `Categories (tag)`

Example:

    add  n/Al Amaan Restaurant ra/5 re/best for Butter Chicken a/12 Clementi Rd, Singapore 129742 c/Indian Muslim


### Delete a food review: `delete`

Deletes a food review from the Food Diary.

Format: `delete n/NAME` or `delete i/INDEX`

Parameter: `Restaurant name` or `Index`

Example:

    delete n/McDonald’s Clementi Mall

### Find for any food reviews

Finds for food reviews whose names, ratings, address and categories match any of the provided keywords.

Format: `find KEYWORDS`

Parameter: `Restaurant name` or `Rating` or `Address` or `Categories`

Example:

    find kfc

    find fastfood indian

    find clementi 5/5

### Find for specific food reviews

Finds for food reviews whose names, ratings, address and categories match all of the provided keywords.

Format: `findall KEYWORDS`

Parameter: `Restaurant name` or `Rating` or `Address` or `Categories`

Example:

    find clementi fastfood 5/5

### View specific food reviews

Opens up a window, showing the details of a specified food review in a full expanded view.

Format: `view <INDEX>`

Parameter: `Index of food review`

Example:

    view 1

-------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/<RESTAURANT NAME> ra/5 re/REVIEW a/ADDRESS c/CATEGORIES` <br> e.g., `add  n/Al Amaan Restaurant ra/4 re/best for Butter Chicken a/12 Clementi Rd, Singapore 129742 c/Indian Muslim`
**Delete** | `delete n/NAME or delete i/INDEX` <br> e.g., `delete n/Al Amaan Restaurant or delete i/1`
**List** | `list`
**Find** | `find kfc`
**FindAll** |`findall clementi fastfood 5/5`
**View** |`view 1`

## <center> Appendix </center>

### UI Mock-up

![Ui Mock-up](images/Ui.png)

---
layout: page
title: User Guide
---
* Table of Contents
  {:toc}
--------------------------------------------------------------------------------------------------------------------
## 1. Introduction
### 1.1. About StoreMando
Greetings, and welcome to StoreMando!

Looking for an all-in-one solution for your inventory management needs? Look no further!


StoreMando is an integrated platform fully customised for residents in households, residential colleges and halls, with
the aim of helping you manage your items effectively and efficiently. StoreMando allows you to keep track of your items'
whereabouts, quantities and expiry dates with a few simple commands. Furthermore, StoreMando also comes with an inbuilt
reminder feature to help you remember items that are expiring. It looks like you will never have to worry about consuming
expired items or waste time searching for misplaced items anymore!

All your items' information is encapsulated clearly on our simple and clean Graphical User Interface (GUI). Our 
application is optimised for use via the Command Line Interface (CLI) and if you have quick fingers, StoreMando can help 
you manage your items in the blink of an eye.

Explore this User Guide to find out more!

### 1.2. Navigating the User Guide

This User Guide aims to provide you with all the information you need to make the most of StoreMando.
Having understood the pain of using a Command Line Interface (CLI) program, we have tried our best to make 
this a very readable User Guide so that you can use our problems without difficulties.

If you need help setting up StoreMando, you can refer to the [“Quick Start"](#quickstart) section.

To learn more about StoreMando's features and commands, head over to the [“Features”](#features) section.

If you need an overview of StoreMando’s commands, check out the [“Command Summary”](#commandsummary) section.

--------------------------------------------------------------------------------------------------------------------
## 2. Quick start <a name = "quickstart"></a>

### 2.1. Installation

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `storemando.jar` from [here](https://github.com/AY2021S2-CS2103T-W10-2/tp) **(link not yet
   available)**.

1. Copy the file to the folder you want to use as the _home folder_ for your StoreMando.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it.<br>

1. Refer to the [Features](#features) below for details of each command.

### 2.2. StoreMando's layout

--------------------------------------------------------------------------------------------------------------------

## 3. Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/ITEM_NAME`, `ITEM_NAME` is a parameter which can be used as `add n/Chocolate Milk`.

* Items in square brackets are optional.<br>
  e.g. `n/ITEM_NAME [e/EXPIRY_DATE]` can be used as `n/Olive Oil e/2020-10-10` or as `n/Olive Oil`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/favourite`, `t/favourite t/drink` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/ITEM_NAME l/LOCATION`, `l/LOCATION n/ITEM_NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `e/2020-10-10 e/2020-08-08`, only `e/2020-08-08` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### 3.1. Viewing help : `help`  <a name = "help"></a>

Opens user guide of StoreMando on a browser or displays the URL of user guide on help window.

Format: `help`

* Opens user guide on a browser only if there is a supporting browser **and** internet connection.
* Otherwise, displays a help window containing the URL of StoreMando's user guide.


### 3.2. Adding an item : `add`  <a name = "add"></a>

Adds an item to the inventory.

Format: `add n/ITEM_NAME l/LOCATION q/QUANTITY [e/EXPIRY_DATE] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An item can have any number of tags (including 0)

</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Expiry date of an item is optional.

</div>

Examples:

* `add n/Chocolate Milk l/Kitchen Refrigerator q/2`
* `add n/Sunshine Bread l/Bedroom q/10 e/2020-01-01 t/favourite`

### 3.3. Editing an item : `edit`  <a name = "edit"></a>

Editing an existing item in the inventory.

Format: `edit INDEX [n/ITEM_NAME] [l/LOCATION] [q/QUANTITY] [e/EXPIRY_DATE] [t/TAG]…​`

* Edits the item at the specified `INDEX`. The index refers to the index number shown in the displayed item list. The
  index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values **only if input values differ from the existing values**.
* When editing tags, the existing tags of the item will be removed i.e. adding of tags is not cumulative.
* You can remove all the items’ tags by typing `t/` without specifying any tags after it.

Examples:

* `edit 1 q/10 e/2020-10-11` Edits the quantity of the 1st item to be `10` and expiry date of the 1st item to
  be `2020-10-11`
  if the existing quantity and expiry date of the 1st item are both not `10` and `2020-10-11` respectively.
* `edit 2 n/Chocolate Bread t/` Edits the name of the 2nd item to be `Chocolate Bread` and clears all existing tags if
  there are existing tags and/or existing name of the 2nd item is not `Chocolate Bread`.

### 3.4. Deleting an item : `delete`  <a name = "delete"></a>

Deletes the specified item from the inventory.

Format: `delete INDEX`

* Deletes the item at the specified `INDEX`.
* The index refers to the index number shown in the displayed item list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the second item in the entire inventory.
* `find Chocolate` followed by `delete 1` deletes the first item in the result of the `find` command.
* `list Room 2` followed by `delete 3` deletes the third item in the recorded list of items in Room 2.


### 3.5. Listing items : `list`
#### 3.5.1. Listing all items at a specific location
#### 3.5.2. Listing all items with a specific tag
#### 3.5.3. Listing all items in StoreMando

Shows a list of all items in the inventory.

Format: `list` `list [l/LOCATION]` `list [t/TAG]`

* You can view all items in the inventory by typing 'list' without specifying location/tag.
* The search is case-insensitive. e.g 'room' will match 'Room'.
* The order of keywords for location search does not matter. e.g. 'Room Living' will match 'Living Room'.
* Tag keyword only can contain one single word.
* Only full words will be matched e.g. 'Room' will not match 'Bedroom'.
* Only location/tag can be searched.
* Location matching uses each word in the String to do 'AND' search e.g. 'Room' will match 'Living room' but 'Living
  room 1' will not match 'Living room'

### 3.6. Finding items by name : `find`  <a name = "find"></a>

If you can't find an item you are looking for, don't worry. Find command will find and display all items 
whose names contain any of the given keywords, either partially or in full.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the find command:**<br>

* The search is case-insensitive. e.g. `milk` will match `Milk`
* The order of the keywords does not matter. e.g. `Bread Chocolate` will match `Chocolate Bread`
* Only the item name is searched.
* Items matching at least one keyword will be returned. For example, `find Chocolate Milk` will
  return `Cadbury Chocolate` and `Almond Milk`
  
</div>

* #### 3.6.1. Find items with complete name match
  Format: `find KEYWORD [MORE_KEYWORDS]`
  <div markdown="block" class="alert alert-info">

  **:information_source: Notes about the command:**<br>
  
  * Only full words will be matched e.g. `Chocolate` will not match `Chocolates`<br></br>
  </div>
  
  Examples:
  * `find Chocolate` returns `chocolate` and `Chocolate Milk`
  * `find potato chip` returns `Potato Biscuit` and `chocolate chip`
  
* #### 3.6.2 Find items with partial name match
  Format: `find */KEYWORD [MORE_KEYWORDS]`
  <div markdown="block" class="alert alert-info">

  **:information_source: Notes about the command:**<br>
  
  * Partial words will be matched e.g. `Choco` will match `Chocolates`<br></br>
  
  </div>
  
  Examples:
  * `find */Burger` returns `CheeseBurger` and `fishburger`
  * `find */cheese egg` returns `MacAndCheese` and `eggs`


### 3.7. Viewing expiring items : `reminder`  <a name = "reminder"></a>

Want to know which items of yours are expiring soon? Then, this is the right command for you.<br>

Format: `reminder NUMBER TIME_UNIT`
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the reminder command:**<br>

* `TIME_UNIT` must be either `days` or `weeks`. `day` and `week` **will not work**.
*  Items without expiry date will not be shown.
*  `NUMBER` **must be a positive integer** 1, 2 , 3, …​

</div>

Examples:
* `reminder 7 days` returns a list containing all items that are expiring within the next 7 days.
* `reminder 2 weeks` returns a list containing all items that are expiring within the next 2 weeks.
* Given today is 10 March 2021, and the inventory has 2 items: chocolate with expiry date of 13 March 2021 and 
  banana with an expiry date of 20 March 2021.
  * `reminder 5 days` returns the chocolate only.
  * `reminder 2 weeks` returns both the chocolate and the banana.

### 3.8. Sorting items : `sort` <a name = "sort"></a>

Want to see which items you are running out of or which items you have to consume soon? Use this command to find out!

You can use this command in 2 different ways.

* #### 3.8.1. Sorting items by quantity
  Format 1: `sort quantity`
  <div markdown="block" class="alert alert-info">

  **:information_source: Note about the sort by expiry date command:**<br>

  * `quantity` is case-insensitive. Keying in `sort QUANTITY` is also a valid command.
    <br></br>

  </div>
  
  Example:
  * `sort quantity` sorts the items in the displayed list in ascending order of quantity.

* #### 3.8.2. Sorting items by expiry date
  Format 2: `sort expirydate`
  <div markdown="block" class="alert alert-info">

  **:information_source: Note about the sort by expiry date command:**<br>
  
  * Items without expiry date will be pushed to the back of the sorted list.
  * `expirydate` is case-insensitive. Keying in `sort EXPIRYDATE` is also a valid command.
  <br></br>
  
  </div>
  
  Example: 
  * `sort expiryDate` sorts the items in the displayed list from the earliest expiry date to the latest.

### 3.9. Clearing StoreMando : `clear`  <a name = "clear"></a>

Want to clear your entire inventory? Key in this command to clear all items stored in your StoreMando.

Format: `clear`

### 3.10. Exiting StoreMando : `exit`  <a name = "exit"></a>

Done with managing your inventory? Simply key in this command to exit and close the application.

Format: `exit`

### 3.11. Saving the data

Don't worry about losing your data if StoreMando crashes on you unexpectedly. StoreMando saves your data automatically 
for you after every input. Your data will be saved to the data file whenever there is a change made. There is no need 
for you to save your data manually.

### 3.12. Editing the data file

StoreMando saves your data into a JSON file at `[JAR file location]/data/storemando.json`. For our adventurous and 
advanced users, feel free to update the data by directly editing that file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Please be reminded that if your changes to the data file makes its format invalid, StoreMando will discard all data 
and restart with an empty data file at the next run. Proceed with caution!!
</div>


--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous StoreMando home folder.

**Q**: What if I want to have more features implemented in the product?
**A**: Feel free to contact us at e0406389@u.nus.edu!

--------------------------------------------------------------------------------------------------------------------

## 5. Command summary <a name = "commandsummary"></a>

Action | Format, Examples
--------|------------------
**[Add](#add)** | `add n/ITEM_NAME l/LOCATION q/QUANTITY [e/EXPIRY_DATE] [t/TAG]…​` <br> e.g. `add n/koko krunch l/fridge q/1 e/2021-05-27 t/favourite`
**[Delete](#delete)** | `delete INDEX`<br> e.g. `delete 2`
**[Edit](#edit)** | `edit INDEX [n/ITEM_NAME] [e/EXPIRY_DATE] [l/LOCATION] [q/QUANTITY] [t/TAG]…​`<br> e.g.`update 1 l/freezer q/2 `
**[List](#list)** | `list` `list [l/LOCATION]` `list [t/TAG]`<br> e.g. `list` `list l/fridge` `list t/favourite`
**[Find](#find)** | `find [*/]KEYWORD [MORE KEYWORDS]`<br> e.g. `find koko krunch` `find */choco`
**[Reminder](#reminder)** | `reminder number TIME_UNIT`<br> e.g. `reminder 7 days` `reminder 2 weeks`
**[Sort](#sort)** | `sort quantity` `sort expiryDate`
**[Help](#help)** |  `help`
**[Clear](#clear)** | `clear`
**[Exit](#exit)** | `exit`

--------------------------------------------------------------------------------------------------------------------

## 6. Glossary

--------------------------------------------------------------------------------------------------------------------

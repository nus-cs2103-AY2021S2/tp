---
layout: page
title: User Guide
---
* Table of Contents 
{:toc}
--------------------------------------------------------------------------------------------------------------------
## 1. Introduction
### 1.1. About StoreMando
Greetings, and welcome to StoreMando.

Are you looking for an all-in-one solution for your inventory management needs? You are at the right place.

StoreMando is an integrated platform fully customised for anyone who needs to manage their items effectively and 
efficiently. StoreMando allows you to keep track of your items' whereabouts, quantities and expiry dates with a few 
simple commands. Furthermore, StoreMando also comes with an inbuilt reminder feature to help you keep track of items 
that are expiring. It looks like you will not have to worry about consuming expired items or waste time searching for 
misplaced items anymore.

All of your items' information is encapsulated clearly on our simple and clean Graphical User Interface (GUI). Our 
application is optimised for use via the Command Line Interface (CLI) and if you have quick fingers, our short and 
simple commands can help you manage your items in the blink of an eye.

Explore this User Guide to find out more!

### 1.2. Navigating the User Guide

This User Guide aims to provide you with all the information you need to make the most of StoreMando.
We have tried our best to make this a very readable User Guide so that you can use our application without difficulties.

If you need help setting up StoreMando, you can refer to the [“Quick Start"](#2-quick-start)  section.

To learn more about StoreMando's features and commands, head over to the [“Features"](#3-features) section.

If you need an overview of StoreMando’s commands, check out the [“Command Summary"](#5-command-summary) section.

Please note the following symbols used in the User Guide which may serve as points of interests:

`command` : The grey highlight indicates commands that can be executed by StoreMando.

:information_source: : This symbol indicates information that you may wish to take note.

:bulb: : This symbol indicates tips provided by us.

--------------------------------------------------------------------------------------------------------------------
## 2. Quick start


### 2.1. Installation

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `storemando.jar` from [here](https://github.com/AY2021S2-CS2103T-W10-2/tp/releases/tag/v1.3.trail)

1. Copy the file to the folder you want to use as the _home folder_ for your StoreMando.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data for you to see how the app looks.<br>
   ![Ui](images/Ui.png)
   
1. Before adding your own items, you may want to [clear](#39-clearing-storemando--clear) the sample data to start afresh.

1. Type the command in the [command box](#command-box-) and press Enter on your keyboard to execute it.<br>

1. Refer to the [“Features"](#3-features) below for details of each command.

### 2.2. StoreMando's layout

#### Main Panel <br>
The main panel is the display window of items stored in StoreMando. Depending on which command you key in, 
   the main panel will display the corresponding items.
   
#### Command Box <br>
The command box is where you will be entering commands to be executed by StoreMando.

#### Result Display Box <br>
The result display box is where StoreMando’s server replies to every command that you key in. Any success, error or 
   warning messages will be displayed in this box.
   
--------------------------------------------------------------------------------------------------------------------

## 3. Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>


* Every item is made up of item name, location and quantity. Expiry date and tags are optional.<br>

* Words in `UPPER_CASE` are the inputs to be supplied by the user.<br>
  e.g. in `add n/ITEM_NAME`, `ITEM_NAME` is an input which can be used as `add n/Chocolate Milk`.
  
* Inputs in square brackets are optional.<br>
  e.g. `n/ITEM_NAME [e/EXPIRY_DATE]` can be used as `n/Olive Oil e/2020-10-10` or as `n/Olive Oil`.

* Inputs with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/favourite`, `t/favourite t/drink` etc.

* Inputs can be specified in any order.<br>
  e.g. `n/ITEM_NAME l/LOCATION` and `l/LOCATION n/ITEM_NAME` are both acceptable.

* With the exception of tags, if you specified an input field multiple times, only the last occurrence of
  the input will be taken.<br>
  e.g. if you specify `e/2020-10-10 e/2020-08-08`, only `e/2020-08-08` will be taken.

* Additional inputs specified for commands that do not take in any input (such as `help` and `exit`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### 3.1. Viewing help : `help`

If you are unsure about the commands and want to seek help, this command opens up this User Guide on your browser 
**if you are connected to the internet**, where you can get detailed information on how to use each feature in 
StoreMando. If you are not connected to the internet or have no browser installed on the device you are using to access 
StoreMando, this command would then open up a pop-up help window which would provide you with the link to this 
User Guide.

Format: `help`

### 3.2. Adding an item : `add`

Do you have an item to add to the inventory? This command is the right one for you.

Format: `add n/ITEM_NAME l/LOCATION q/QUANTITY [e/EXPIRY_DATE] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An item can have any number of tags (including 0)
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Expiry date of an item is optional.
</div>

Examples:
* `add n/Chocolate Milk l/kitchen refrigerator q/2` adds 2 Chocolate Milk to the kitchen refrigerator. 
* `add n/Sunshine Bread l/kitchen cupboard q/10 e/2021-05-11 t/favourite` adds 10 Sunshine Bread with expiry date, 
  2021-05-11, and "favourite" tag to the kitchen cupboard.


### 3.3. Editing an item : `edit`

If you want to edit any details of an existing item in the inventory, you can do so by using this command. 

Format: `edit INDEX [n/ITEM_NAME] [l/LOCATION] [q/QUANTITY] [e/EXPIRY_DATE] [t/TAG]…​`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the edit command:**<br>

* Edits the item at the specified `INDEX`. The index refers to the index number shown in the displayed item list. The
  index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values **only if input values differ from the existing values**.
* When editing tags, the existing tags of the item will be removed i.e. adding of tags is not cumulative.
* You can remove all the items’ tags by typing `t/` without specifying any tags after it.
</div>

Examples:
* `edit 1 q/10 e/2022-10-11` edits the quantity of the 1st item to be `10` and expiry date of the 1st item to
  be `2022-10-11` if the existing quantity and expiry date of the 1st item are both not `10` and `2022-10-11` respectively.
* `edit 2 n/Chocolate Bread t/` edits the name of the 2nd item to be `Chocolate Bread` and clears all existing tags if
  there are existing tags and/or existing name of the 2nd item is not `Chocolate Bread`.
  

### 3.4. Listing items : `list`

Do you want to view all your items? What about items at a specific location or with a specific tag? This command helps 
you to do just that.

You can use this command in 3 different ways.

* #### 3.4.1. Listing all items in StoreMando 
  This command allows you to view all the items in the inventory.
  <br>
  Format: `list`
  <br>
  Example:
  * `list` displays all the items in the inventory.

* #### 3.4.2. Listing all items at a specific location 
  This command allows you to view all items at a specific location.
  <br>
  Format: `list l/LOCATION`
  <div markdown="block" class="alert alert-info">

  **:information_source: Notes about the command:**<br>
  
  * The search is case-insensitive. e.g 'room' will match 'Room'.
  * Only full words will be matched e.g. 'Room' will not match 'Bedroom'.
  * The order of keywords for location search does not matter. e.g. 'Room Living' will match 'Living Room'.
  * Location matching uses each word in the String to do 'AND' search e.g. 'Room' will match 'Living room' but 'Living room 1' will not match 'Living room'<br>
    
  </div>
  
  Example:
  * `list l/kitchen` displays all the items in the kitchen.
  
* #### 3.4.3. Listing all items with a specific tag 
  This command allows you to view all items containing a specific tag.
  <br>
  Format: `list t/TAG`
  <div markdown="block" class="alert alert-info">

  **:information_source: Notes about the command:**<br>
  * Tag keyword must be a single word. <br>
  </div>
  
  Example:
  * `list t/favourite` displays all the items with the "favourite" tag.
  
  
  
### 3.5. Finding items by name : `find`

If you can't find an item you are looking for, don't worry. Find command will find and display all items 
whose names contain any of the given keywords, either partially or in full.

You can use this command in 2 different ways.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the find command:**<br>

* The search is case-insensitive. e.g. `milk` will match `Milk`
* The order of the keywords does not matter. e.g. `Bread Chocolate` will match `Chocolate Bread`
* Only the item name is searched.
* Items matching at least one keyword will be returned. For example, `find Chocolate Milk` will
  return `Cadbury Chocolate` and `Almond Milk`.
* Items displayed are not in order of relevance.


</div>

* #### 3.5.1. Find items with complete name match
  Format: `find KEYWORD [MORE_KEYWORDS]`
  <div markdown="block" class="alert alert-info">

  **:information_source: Notes about the command:**<br>

  * Only full words will be matched e.g. `Chocolate` will not match `Chocolates`<br>
  </div>

  Examples:
  * `find Chocolate` returns `chocolate` and `Chocolate Milk`
  * `find potato chip` returns `Potato Biscuit` and `chocolate chip`

* #### 3.5.2 Find items with partial name match
  Format: `find */KEYWORD [MORE_KEYWORDS]`
  <div markdown="block" class="alert alert-info">

  **:information_source: Notes about the command:**<br>

  * Partial words will be matched e.g. `Choco` will match `Chocolates`<br>

  </div>

  Examples:
  * `find */Burger` returns `CheeseBurger` and `fishburger`
  * `find */cheese egg` returns `MacAndCheese` and `eggs`
  
### 3.6. Deleting an item : `delete`

Are you looking to get rid of an existing item from the inventory? Use this command.

Format: `delete INDEX`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the delete command:**<br>

* Deletes the item at the specified `INDEX`.
* The index refers to the index number shown in the currently displayed item list.
* The index **must be a positive integer** 1, 2, 3, …​

</div>

Examples:
* `list` followed by `delete 2` deletes the second item in the entire inventory.
* `find Chocolate` followed by `delete 1` deletes the first item in the result of the `find` command.
* `list Room 2` followed by `delete 3` deletes the third item in the recorded list of items in Room 2.

### 3.7. Viewing expiring items : `reminder`

Want to know which items of yours are expiring soon? Then, this is the right command for you.<br>

Format: `reminder NUMBER TIME_UNIT`
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the reminder command:**<br>

* `NUMBER` refers to the number of days/weeks and **can be any integer** …​, -7, -3, 0, 3, 7, …​
* `TIME_UNIT` is either `days` or `weeks`.
* `day` or `week` will only be accepted when `NUMBER` is either **-1, 0 or 1**
*  Items without expiry date will not be shown.

</div>

Examples:
* `reminder 7 days` returns a list containing all items that are expiring within the next 7 days.
* `reminder 2 weeks` returns a list containing all items that are expiring within the next 2 weeks.
* Given today is 10 March 2021, and the inventory has 2 items: chocolate with expiry date of 13 March 2021 and 
  banana with an expiry date of 20 March 2021.
  * `reminder 5 days` returns the chocolate only.
  * `reminder 2 weeks` returns both the chocolate and the banana.
  <br>
* `reminder -7 days` returns a list of items that have been expired for at least 7 days.
* `reminder -2 weeks` returns a list of items that have been expired for at least 2 weeks.
* Given today is 30 March 2021, and the inventory has 2 items: apple with expiry date of 27 March 2021 and
  banana with an expiry date of 20 March 2021.
  * `reminder -5 days` returns both the apple and the banana.
  * `reminder -1 weeks` returns the banana only.



### 3.8. Sorting items : `sort`

Want to see which items you are running out of or which items you have to consume soon? Use this command to find out!

You can use this command in 3 different ways.

* #### 3.8.1. Sorting items by ascending quantity
  Format: `sort quantity asc`
  <div markdown="block" class="alert alert-info">

  **:information_source: Note about the sort by ascending quantity command:**<br>

  * `quantity` is case-insensitive. Keying in `sort QUANTITY asc` is also a valid command.
    <br>

  </div>
  
  Example:
  * `sort quantity asc` sorts the items in the displayed list in ascending order of quantity.

* #### 3.8.2. Sorting items by descending quantity
  Format: `sort quantity desc`
  <div markdown="block" class="alert alert-info">

  **:information_source: Note about the sort by descending quantity command:**<br>

  * `quantity` is case-insensitive. Keying in `sort QUANTITY desc` is also a valid command.
    <br>

  </div>

  Example:
  * `sort quantity desc` sorts the items in the displayed list in descending order of quantity.
  
* #### 3.8.3. Sorting items by expiry date
  Format: `sort expirydate`
  <div markdown="block" class="alert alert-info">

  **:information_source: Note about the sort by expiry date command:**<br>
  
  * Items without expiry date will be pushed to the back of the sorted list.
  * `expirydate` is case-insensitive. Keying in `sort EXPIRYDATE` is also a valid command.
  <br>
  
  </div>
  
  Example: 
  * `sort expiryDate` sorts the items in the displayed list from the earliest expiry date to the latest.

### 3.9. Clearing StoreMando : `clear`

Do you want to clear your entire inventory? Key in this command to clear all items stored in your inventory.

You can use this command in 2 different ways.

* #### 3.9.1. Clear all items in entire inventory
  Format 1: `clear`

  Example:

  * `clear` clears all the items in the inventory.
  
* #### 3.9.2. Clear all items in a specific location
  Format 2: `clear l/LOCATION`
  <div markdown="block" class="alert alert-info">

  **:information_source: Notes about the clear command:**<br>
  
  * The search is case-sensitive. e.g 'room' will not match 'Room'.

  * The location input will be matched exactly e.g. 'Room' will not match 'Bedroom'. 'Bed' will not match 'Bed room'. 
    'Living room' will not match 'Room living'.<br>

  </div>

  Example:

  * `clear l/kitchen` clears all the items with location `kitchen`.
  
### 3.10. Exiting StoreMando : `exit`

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

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous StoreMando home folder.

**Q**: Can I have two or more inventories in one computer?<br>
**A**: Download and install StoreMando in two different folders on your computer so that you will have two different 
data files in separate locations. You can have as many inventories as you want using this method.

**Q**: What if I want to have more features implemented in the product?<br>
**A**: Feel free to contact us at e0406389@u.nus.edu.

**Q**: Sometimes I forget the various commands to use, where can I find the list of commands?<br>
**A**: You could view enter help tab by clicking F1, or by keying in `help` in the command box, which will then lead 
you to this user guide to provide you with the help you need.

--------------------------------------------------------------------------------------------------------------------
## 5. Command summary

Action | Format, Examples
--------|------------------
**[“Help"](#31-viewing-help--help)** |  `help`
**[“Add"](#32-adding-an-item--add)** | `add n/ITEM_NAME l/LOCATION q/QUANTITY [e/EXPIRY_DATE] [t/TAG]…​` <br> e.g. `add n/koko krunch l/fridge q/1 e/2021-05-27 t/favourite`
**[“Edit"](#33-editing-an-item--edit)** | `edit INDEX [n/ITEM_NAME] [e/EXPIRY_DATE] [l/LOCATION] [q/QUANTITY] [t/TAG]…​`<br> e.g.`update 1 l/freezer q/2 `
**[“List"](#34-listing-items--list)** | `list` `list [l/LOCATION]` `list [t/TAG]`<br> e.g. `list` `list l/fridge` `list t/favourite`
**[“Find"](#35-finding-items-by-name--find)** | `find [*/]KEYWORD [MORE KEYWORDS]`<br> e.g. `find koko krunch` `find */choco`
**[“Delete"](#36-deleting-an-item--delete)** | `delete INDEX`<br> e.g. `delete 2`
**[“Reminder"](#37-viewing-expiring-items--reminder)** | `reminder NUMBER TIME_UNIT_KEYWORD`<br> e.g. `reminder -7 days` `reminder 2 weeks`
**[“Sort"](#38-sorting-items--sort)** | `sort quantity asc` `sort quantity desc` `sort expiryDate`
**[“Clear"](#39-clearing-storemando--clear)** | `clear` `clear l/LOCATION` <br> e.g. `clear l/bedroom`
**[“Exit"](#310-exiting-storemando--exit)** | `exit`


--------------------------------------------------------------------------------------------------------------------

## 6. Glossary
* **StoreMando**: Name of the application
* **CLI**: Command Line Interface
* **GUI**: Graphical User Interface
* **Inventory**: List of all items stored in StoreMando

--------------------------------------------------------------------------------------------------------------------

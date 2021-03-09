---
layout: page
title: User Guide
---

StoreMando is a **desktop app for managing inventory, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, StoreMando can get your inventory management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `storemando.jar` from [here](https://github.com/AY2021S2-CS2103T-W10-2/tp) **(link not yet available)**.

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it.<br>

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/ITEM_NAME`, `ITEM_NAME` is a parameter which can be used as `add n/Chocolate Milk`.

* Items in square brackets are optional.<br>
  e.g `n/ITEM_NAME [e/EXPIRY_DATE]` can be used as `n/Olive Oil e/2020-10-10` or as `n/Olive Oil`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/favourite`, `t/favourite t/drink` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/ITEM_NAME l/LOCATION`, `l/LOCATION n/ITEM_NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `e/2020-10-10 e/2020-08-08`, only `e/2020-08-08` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding an item: `add`

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

### Listing all items : `list`

Shows a list of all items in the inventory.

Format: `list [l/LOCATION]`

* You can view all items in the inventory by typing 'list' without specifying location.

### Editing an item's details : `edit`

Editing an existing item in the inventory.

Format: `edit INDEX [n/ITEM_NAME] [l/LOCATION] [q/QUANTITY] [e/EXPIRY_DATE] [t/TAG]…​`

* Edits the item at the specified `INDEX`. The index refers to the index number shown in the displayed item list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values **only if input values differ from the existing values**.
* When editing tags, the existing tags of the item will be removed i.e adding of tags is not cumulative.
* You can remove all the items’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 q/10 e/2020-10-11` Edits the quantity of the 1st item to be `10` and expiry date of the 1st item to be `2020-10-11` 
   if the existing quantity and expiry date of the 1st item are both not `10` and `2020-10-11` respectively.
*  `edit 2 n/Chocolate Bread t/` Edits the name of the 2nd item to be `Chocolate Bread` and clears all existing tags if 
   there are existing tags and/or existing name of the 2nd item is not `Chocolate Bread`.

### Locating items by name: `find`

Finds items whose names contain any of the given keywords, either in full or partially.

Format 1: `find KEYWORD [MORE_KEYWORDS]`

* Only full words will be matched e.g. `Chocolate` will not match `Chocolates`
* The search is case-insensitive. e.g. `milk` will match `Milk`
* The order of the keywords does not matter. e.g. `Bread Chocolate` will match `Chocolate Bread`
* Only the item name is searched.
* Items matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Chocolate Milk` will return `Cadbury Chocolate`, `Almond Milk`

Format 2: `find *KEYWORD [MORE_KEYWORDS]`

* Partial words will be matched e.g. `Choco` will match `Chocolates`
* The search is case-insensitive. e.g. `milk` will match `Milk`
* The order of the keywords does not matter. e.g. `Bread Chocolate` will match `Chocolate Bread`
* Only the item name is searched.
* Items matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Chocolate Milk` will return `Cadbury Chocolate`, `Almond Milk`

Examples:
* `find Chocolate` returns `chocolate` and `Chocolate Milk`
* `find potato chip` returns `Potato Biscuit` and `chocolate chip`
* `find *Burger` returns `CheeseBurger` and `fishburger`
* `find *cheese egg` returns `MacAndCheese` and `eggs`


### Deleting an item : `delete`

Deletes the specified item from the inventory.

Format: `delete INDEX`

* Deletes the item at the specified `INDEX`.
* The index refers to the index number shown in the displayed item list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd item in the location book.
* `find Chocolate` followed by `delete 1` deletes the 1st item in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the location book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

StoreMando data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

StoreMando data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, StoreMando will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

### Sorting items `[coming in v2.0]`

_Details coming soon ..._

### Filtering by item category`[coming in v2.0]`

_Details coming soon ..._

### Viewing reminders `[coming in v2.0]`

_Details coming soon ..._

### Viewing help`[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

**Q**: What if I want to have more features implemented in the product?
**A**: Feel free to contact us at e0406389@u.nus.edu!

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/ITEM_NAME l/LOCATION q/QUANTITY [e/EXPIRY_DATE] [t/TAG]…​` <br> e.g., `add n/koko krunch l/fridge q/1 e/2021-05-27 t/favourite`
**Delete** | `delete INDEX`<br> e.g., `delete 2`
**Edit** | `edit INDEX [n/ITEM_NAME] [e/EXPIRY_DATE] [l/LOCATION] [q/QUANTITY] [t/TAG]…​`<br> e.g.,`update 1 l/freezer q/2 `
**List** | `list [l/LOCATION]`<br> e.g., `list l/fridge`
**Find** | `find KEYWORD [MORE KEYWORDS]`<br> e.g, `find koko krunch`

---
layout: page
title: User Guide
---

CakeCollate is a **desktop app for managing cake orders, optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CakeCollate can get your order management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `cakecollate.jar` from [here](https://github.com/AY2021S2-CS2103T-T11-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your CakeCollate.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will display the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all orders in the CakeCollate database.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/13-05-2021 /o strawberry cake 3` : Adds an order with a contact named `John Doe` to CakeCollate.

   * **`delete`**`3` : Deletes the 3rd order shown in the current list.
   
   * **`remind`**`2` : Lists all orders that are 2 days within the current local date.

   * **`clear`** : Deletes all order in the CakeCollate database.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* `d/DELIVERY_DATE` should specify a date at least 3 days after the date the command is executed.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Gives instructions on how to enter orders into the CLI.
Displays a message with a list of all available commands and their format.

Format: `help`

### Adding an order: `add`

Adds an order to the CakeCollate database.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] d/DELIVERY_DATE o/ORDER_DESCRIPTION`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A order can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/13-05-2021 /o strawberry cake 3`
* `add n/Betsy Crowe t/daughter e/betsycrowe@example.com a/Newgate Prison p/1234567 t/friend d/13-05-2100 /o chocolate cake 5 durian cake 10 mochi cake 100`

### List all existing orders : `list`

Shows a list of all orders in the CakeCollate database.

Format: `list`

### Editing an order : `edit`

Edits an existing order in the CakeCollate database.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG] [d/DELIVERY_DATE] [o/ORDER_DESCRIPTION]​`

* Edits the order at the specified `INDEX`. The index refers to the index number shown in the displayed order list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the order will be removed i.e adding of tags is not cumulative.
* You can remove all the order’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st order to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd order to be `Betsy Crower` and clears all existing tags.

### Locating orders by name: `find`

Finds orders whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Orders matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting an order : `delete`

Deletes the specified order from the CakeCollate database.

Format: `delete ID...`

* Deletes the orders with the specified ids.
* The `ID` refers to the order `ID` number shown in the list of orders.
* One or more ids can be entered.
* The `ID` **must be valid**.

Examples:
* `list` followed by `delete 2` deletes the order with `ID` 2 from the CakeCollate database.
* `delete 2 3` deletes the orders with ids 2 and 3 from the CakeCollate database.

### Receiving reminders for orders : `remind`

Obtains a list of reminder for orders that are X days within the current date.

Format: `remind DAYS...`

* Lists all orders within the specified day, starting from the current date.
* The `DAYS` refers to the number of days from the current date.
* The `DAYS` **must be a positive integer starting from 0**.

Examples:
* `remind 0` lists all orders that have a delivery date for today.
* `remind 3` lists all orders that have a delivery date within 3 days from today.

### Setting the delivery status of an order as undelivered : `undelivered`

Sets the delivery status of the specified order/orders from the CakeCollate database as `undelivered`.

Format: `undelivered ID...`

* Sets the delivery status of the orders with the specified ids as `undelivered`.
* The `ID` refers to the order `ID` number shown in the list of orders.
* One or more ids can be entered.
* The `ID` **must be valid**.

Examples:
* `list` followed by `undelivered 2` sets the delivery status of the orders with `ID` 2 from the 
  CakeCollate database as `undelivered`.
* `undelivered 2 3` sets the delivery status of the orders with ids 2 and 3 from the 
  CakeCollate database as `undelivered`.

### Setting the delivery status of an order as delivered : `delivered`

Sets the delivery status of the specified order/orders from the CakeCollate database as `delivered`.

Format: `delivered ID...`

* Sets the delivery status of the orders with the specified ids as `delivered`.
* The `ID` refers to the order `ID` number shown in the list of orders.
* One or more ids can be entered.
* The `ID` **must be valid**.

Examples:
* `list` followed by `delivered 2` sets the delivery status of the orders with `ID` 2 from the
  CakeCollate database as `delivered`.
* `delivered 2 3` sets the delivery status of the orders with ids 2 and 3 from the CakeCollate database as `delivered`.

### Setting the delivery status of an order as cancelled : `cancelled`

Sets the delivery status of the specified order/orders from the CakeCollate database as `cancelled`.

Format: `cancelled ID...`

* Sets the delivery status of the orders with the specified ids as cancelled.
* The `ID` refers to the order `ID` number shown in the list of orders.
* One or more ids can be entered.
* The `ID` **must be valid**.

Examples:
* `list` followed by `cancelled 2` sets the delivery status of the orders with `ID` 2 from the
  CakeCollate database as `cancelled`.
* `cancelled 2 3` sets the delivery status of the orders with ids 2 and 3 from the CakeCollate database as `cancelled`.

### Clearing all existing orders: `clear`

Deletes all existing orders in the CakeCollate database.

Warning: this is an undo-able operation.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

CakeCollate data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CakeCollate data is saved as a JSON file `[JAR file location]/data/cakecollate.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CakeCollate will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CakeCollate home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] d/DELIVERY_DATE o/ORDER_TYPE` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/13-05-2021 /o strawberry cake 3`
**Clear** | `clear`
**Delete** | `delete INDEXES`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**Remind** | `remind DAYS`<br> e.g., `remind 3`
**Undelivered** | `undelivered INDEXES`<br> e.g., `undelivered 3 4`
**Delivered** | `delivered INDEXES`<br> e.g., `delivered 3 4`
**Cancelled** | `cancelled INDEXES`<br> e.g., `cancelled 3 4`

--------------------------------------------------------------------------------------------------------------------

## Acknowledgements

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

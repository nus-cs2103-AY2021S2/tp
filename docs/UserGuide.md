---
layout: page
title: User Guide
---

Car@leads is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) <br>
while still having the benefits of a Graphical User Interface (GUI). 
<br> If you can type fast, Car@leads can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named 
     `John Doe`

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`list`** : Lists all contacts.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.
   
   * **`help`** : Get a legend of commands.    

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


### Adding a customer: `add`

Adds a customer to the contact list.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/PREFERRED_CAR [x/COE_EXPIRY_DATE] [t/TAG]…​`

Note that the `COE_EXPIRY_DATE` is in Year Month Day order with spaces and two digits for the month and day.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A customer can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/Honda City`
* `add n/Betsy Crowe c/Honda City t/friend e/betsycrowe@example.com x/2011 03 27 a/Newgate Prison p/1234567 t/criminal`

### Deleting a customer : `delete`

Deletes the specified customer from the contact list.

Format: `delete INDEX`

* Deletes the customer at the specified `INDEX`.
* The index refers to the index number shown in the displayed customer list.
* The index **must be a positive integer** 1, 2, 3, …​

### Listing all customers : `list`

Shows a list of all customers in the system

Format: `list`

### Clear all customers : `clear`
* **`clear`** : Deletes all customers in the system.


### Exiting app: `exit`
* **`exit`** : Exits the app.

### Quick help: `help`
* **`help`** : Overview of commands and input syntax.
--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/PREFERRED_CAR [x/COE_EXPIRY_DATE] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 c/Honda Civic /x 2007 03 21 t/friend t/colleague`
**delete** | `delete INDEX`<br> e.g., `delete 3`
**list** | `list`
**clear** | `clear`
**exit** | `exit`
**help** | `help`


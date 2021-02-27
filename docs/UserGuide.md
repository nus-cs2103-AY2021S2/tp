---
layout: page
title: User Guide
---

PocketEstate enables easy organization of mass clientele property information through sorting of information by price, location and housing type, that may otherwise be difficult to manage.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add property n/NAME`, `NAME` is a parameter which can be used as `add property n/Mayfair`.

* Items in square brackets are optional.<br>
  e.g `add appointment n/NAME r/REMARKS d/DATE [t/TIME]` can be used as `add appointment n/Meet John r/At M hotel d/17-2-2021` or as `add appointment n/Meet John r/At M hotel d/17-2-2021 t/2040`.
  
* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME r/REMARKS`, `r/REMARKS n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/John n/Alice`, only `n/Alice` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a property: `add property`

Adds a property to the app.

Format: `add property n/NAME t/PROPERTY_TYPE a/ADDRESS p/POSTAL_CODE d/DEADLINE [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE]​`

Examples:
* `add property n/Mayfair t/Condo a/1 Jurong East Street 32 p/ 609477 d/31-12-2021`
* `add property n/Mayfair t/Condo a/1 Jurong East Street 32 p/609477 d/31-12-2021 r/Urgent to sell cn/Alice cc/91234567 ce/alice@gmail.com ca/$800,000`

### Adding an appointment: `add appointment`

Adds an appointment to the app.

Format: `add appointment n/NAME r/REMARKS d/DATE [t/TIME]​`

Examples:
* `add appointment n/Meet Alex r/at M hotel d/17-2-2021`
* `add appointment n/Meet Alex r/at M hotel d/17-2-2021 t/1500`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Sorting : `sort`

Sorts and shows a list of properties or appointments that is sorted according to the comparator provided.

Formats:
* `sort appointment <asc or desc> [deadline or task type]`
* `sort property <asc or desc> [price or location or housing type]`

Description:
* Sorts appointment or property by the specified sorting key in ascending or descending order.
* The default order is `asc` if the order field is not specified.
* The sorting key field must be specified.

Examples:
*  `sort appointment asc deadline` Sorts `appointment` by `deadline` in ascending order.
*  `sort property desc price` Sorts `property` by `price` in descending order.

### Editing an entry : `edit`

Overwrites the information of the property/appointment according to the flags provided.

Formats: 
* `edit INDEX [n/NAME] [t/PROPERTY_TYPE] [a/ADDRESS] [p/POSTAL_CODE] [d/DEADLINE] [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE]`
* `edit INDEX [n/NAME] [r/REMARKS] [d/DATE] [t/TIME]`

Description:
* Edits the entry at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 r/Urgent to sell cn/Alice` Edits the remark and client name of the 1st property to be `Urgent to sell` and `Alice` respectively.
*  `edit 3 d/2021-03-28 r/at M hotel` Edits the date and remark of the 3rd appointment to be `2021-03-28` and `at M hotel` respectively.

### Updating the status of a property : `update`

Updates the status of a property from Option to Purchase, to Sales and Purchase Agreement to Completion

Formats:
* `update INDEX new AMOUNT`
* `update INDEX [proceed][cancel]`

Description:
* Edits the property at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index **must be a positive integer** 1, 2, 3, …​
* The `new` keyword can only be used on a property without an existing status
* `proceed` or `cancel` can only be used on a property with an existing status
* `proceed` would move the status on to the next one. e.g. Option to Sales Agreement or Sales Agreement to Completion
* `cancel` would remove the status of the property
* At least one of the optional fields must be provided.

Examples:
*  `update 1 new 600000` Creates a new status with amount 600000 for the 1st property.
*  `update 3 proceed` Moves the status of the 3rd property to next one.

### Removing an entry : `delete`

Deletes the specified property or appointment from the app.

Formats:
* `delete appointment INDEX`
* `delete property INDEX`

Description:
* Deletes the appointment or property at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index **must be a positive integer** 1, 2, 3, …​
* The field INDEX must be provided.

Examples:
*  `delete appointment 7` Deletes the `appointment` at index `7`.
*  `delete property 7` Deletes the `property` at index `7`.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add property** | `add property n/NAME t/PROPERTY_TYPE a/ADDRESS p/POSTAL_CODE d/DEADLINE [r/REMARKS] [cn/CLIENT_NAME] [cc/CLIENT_CONTACT_NUMBER] [ce/CLIENT_EMAIL] [ca/CLIENT_ASKING_PRICE]` <br> e.g., `add property n/Mayfair t/Condo a/1 Jurong East Street 32 p/609477 d/31-12-2021 r/Urgent to sell cn/Alice cc/91234567 ce/alice@gmail.com ca/$800,000`
**Add appointment** | `add appointment n/NAME r/REMARKS d/DATE [t/TIME]` <br> e.g., `add appointment n/Meet Alex r/at M hotel d/17-2-2021 t/1500`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Sort** | `sort`<br> e.g., `sort appointment asc deadline`
**Remove an entry** | `delete <appointment or property> INDEX`<br> e.g., `delete appointment 7`
**Help** | `help`

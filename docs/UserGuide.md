---
layout: page
title: User Guide
---

SpamEZ is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, SpamEZ can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `spamez.jar` from [here](https://github.com/AY2021S2-CS2103-T16-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for SpamEZ.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 m/email` : Adds a contact named `John Doe` to the contacts list.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.
   
   * **`blist`** `2` : Blacklists the 2nd contact shown in the current list.

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, `dark`, `light` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Commands will only handle `INDEX` values from 1 to 2147483647 (inclusive).
  Values out of this valid integer range will be treated as invalid inputs.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the contacts list.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS m/MODE_OF_CONTACT [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 m/email`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 m/phone t/criminal`

### Listing all persons : `list`

Shows a list of all contacts.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the contacts list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [m/MODE_OF_CONTACT] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name and/or tag: `find`

Finds persons whose attributes (except remark) contain any of the given keywords.

Format: `find [n/NAME_KEYWORDS] [t/TAG_KEYWORDS] [a/ADDRESS_KEYWORDS] [e/EMAIL_KEYWORDS] [p/PHONE_NUMBERS] [b/IS_BLACKLISTED] [m/MODE_OF_CONTACT]`

* At least one of the parameters must be included as the parameters.
* Parameters, if provided, may not be empty. In other words, commands such as `find n/` or `find n/abc t/` are invalid.
* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* For name, tag and address parameters, only full words will be matched e.g. `Han` will not match `Hans`.
* For email and phone parameters, partial matches are allowed, e.g. for phone number, `8123` will match `81234567`.  
* Persons matching at least one keyword of each provided attribute will be returned.
  e.g. `n/Hans Bo` will return `Hans Gruber`, `Bo Yang`, `Bo Hans`, while `n/Hans Bo t/friends` will only return `Hans Gruber` and `Bo Yang` if only `Hans Gruber` and `Bo Yang` are tagged with `friends`.
* Blacklist parameter (`b/`) only accepts `true` or `false`.
* Mode of contact parameter (`m/`) only accepts `phone`, `email` or `address`.
* Blacklist and mode of contact parameters only take in the first keyword. For example, if `b/true blah blah` is inputted, it will be interpreted as `b/true`.

Examples:
* `find n/John` returns `john` and `John Doe`
* `find n/alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavid.png)
* `find n/alex david t/family` returns `David Li`

### Deleting a person : `delete`

Deletes the specified person from the contacts list.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a valid positive integer** 1, 2, …​, 2147483647

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the contacts list.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

###Deleting multiple contacts : `massdelete`

Deletes all contacts within the specified index range.

Format: `massdelete START-END`
* The index refers to the index number shown in the displayed person list.
* Both the Start Index and End Index **must be a valid positive integer** 1, 2, …​, 2147483647
* Start Index < End Index and End Index cannot be larger than the number of contacts in the list.

Example:
`massdelete 2-41`

### Blacklist a contact : `blist`

Blocks specific contacts, to specify that they do not want to be contacted.
If the contact is already blacklisted, they will be un-blacklisted. 

Format: blist INDEX

* Changes the blacklist status of the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a valid positive integer** 1, 2, …​, 2147483647

### Blacklist or unblacklist multiple contacts : `massblist`

Blacklists or unblacklists all contacts within the specified index range (inclusive).

Format: `massblist START-END b/BLACKLIST_OR_UNBLACKLIST`

* The index refers to the index number shown in the displayed person list.
* Both the start index and end index **must be valid positive integers** positive integers 1, 2, …​, 2147483647
* Start index must be strictly smaller than the end index. 
* End index cannot be larger than the number of contacts in the list.

Example:
`massblist 15-42 b/blacklist`

### Collect details from contacts : `collect`

Collects the specified details of all contacts in the displayed person list.
The type of detail is specified by the prefix provided. Details will be separated
by the given separator, or by a semicolon if unspecified.

Format: collect [n/] or [p/] or [e/] or [a/] [s/SEPARATOR]

* Exactly one of the type of detail prefix must be provided.
  The corresponding fields are as follows.
  * `n/`: Name
  * `p/`: Phone
  * `e/`: Email
  * `a/`: Address
* The separator will ignore leading and trailing spaces. 
  As such, it is not possible to start or end the separator with a space.   
* Words following any prefix other than `s/` will be ignored.
* Unrelated prefixes will be ignored.

### Clearing all entries : `clear`

Clears all entries from the contacts list.

Format: `clear`

### Adding a new remark or replacing an existing remark : `remark`

Adds a new remark to the specified person in the contacts list. 
If the person already has a remark, the new remark will replace the
existing remark.

Format: `remark INDEX r/REMARK`

* Adds/Replaces the remark of the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a valid positive integer** 1, 2, …​, 2147483647

Example:

`remark 3 r/Currently on Stay Home Notice`

### Sort entries by name : `sort`

Sort the contacts in the address book by name in alphabetical order. The list can be sorted in
either ascending or descending order.

Format: `sort ASCENDING_OR_DESCENDING`

Examples:

* `sort ascending`
* `sort descending`

### Changing view type to light mode : `light`

Changes the color theme to a light theme.

Format: `light`

### Changing view type to dark mode : `dark`

Changes the color theme to a dark theme.

Format: `dark`

### Review previous commands
Users can view the commands they have inserted previously using up and down arrow keys.
To re-execute the command, users simply need to press enter.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SpamEZ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SpamEZ data are saved as a JSON file `[JAR file location]/data/addressbook.json`.
Do not update data directly by editing that data file as it may result in unintended behaviors.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If you edit the data file and make its format invalid, SpamEZ will discard all data and start with an empty data file at the next run.
</div>

### Undo previous operations: `undo`
Undo the changes done to the list of contacts.

Format: `undo`
* This command only applies to the commands that make changes to the list of contacts, e.g. `add`, `edit`, `delete` etc.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SpamEZ home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Blacklist** | `blist INDEX`<br> e.g., `blist 2`
**Mass blacklist** | `massblist START-END`<br> e.g., `massblist 13-67 b/blacklist`
**Clear** | `clear`
**Collect** | `collect [n/] or [p/] or [e/] or [a/] [s/SEPARATOR]`<br> e.g., `collect e/ s/,`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Mass Delete** | `massdelete START-END` <br> e.g., `massdelete 4-12`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find [n/NAME_KEYWORDS] [t/TAG_KEYWORDS] [a/ADDRESS_KEYWORDS]`<br> e.g., `find n/James Jake t/classmates a/Singapore`
**Help** | `help`
**List** | `list`
**Remark** | `remark INDEX r/REMARK`<br> e.g., `remark 5 r/Currently on Leave of Absence`
**Tag** | `tag n/NAME t/TAG`<br> e.g., `tag n/Jane Bo t/Student`
**Sort** | `sort ASCENDING_OR_DESCENDING`<br> e.g., `sort ascending`
**Light** | `light`
**Dark** | `dark`
**Sort** | `sort ASCENDING_OR_DESCENDING`<br> e.g., `sort ascending`
**Undo** | `undo`

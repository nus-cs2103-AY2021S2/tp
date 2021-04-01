---
layout: page
title: User Guide
---

HeliBook is a **desktop app for managing you children's contacts and their related appointments, 
optimized for use via a Command Line Interface** (CLI) while still having the benefits of a 
Graphical User Interface (GUI). 
If you can type fast, HeliBook can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `helibook.jar` from [here](https://github.com/AY2021S2-CS2103T-W13-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your HeliBook.

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

This section is separated into the following sub-sections:
* [General Commands](#general-commands): Commands related to navigating HeliBook
* [Address Book Commands](#address-book-commands): Commands related to managing contacts
* [Appointment Book Commands](#appointment-book-commands): Commands related to managing appointments

The command summary table can also be accessed [here](#command-summary).

### General Commands

#### Viewing help : `help`

Shows information about available commands and how they can be used.

Format: `help [COMMAND]`

* If command is not specified, a summary of all available commands will be displayed, along with a link to access the full user guide.
* If command is specified, detailed information about the command will be displayed. 

Examples:
* `help` Displays summary of all available commands.
* `help find` Displays detailed information about the find command.

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Address Book Commands

#### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [tc/CHILDTAG]…​ [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can add a person with only some of the information, you can fill in the rest later on.
</div>

Examples:
* `add n/John Doe p/98765432`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

#### Adding/replacing tags to a contact: `tag` 

Adds or replaces tags to the specified person by index.

Format: `tag INDEX [o/OPTION] [tc/CHILDTAG]…​ [t/TAG]…​`

* Tags the person at the specified INDEX. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​

Currently available options for the `[OPTION]` field include:
* `rt` Replaces the currently existing tags with the given new set of tags 
  
Examples:
*  `tag 4 t/School t/English` Adds the tags School and English to the 4th person.
*  `tag 2 o/rt tc/Alexa t/English` Replaces all existing tags of the 2nd person with the child tag Alexa and the tag English.


#### Listing all persons : `list`

Shows a list of persons in the address book.

Format: `list [o/OPTION]`

Currently available options for the `[OPTION]` field include:
* `fav` Shows list of favourited persons in the address book

Examples:
* `list` List all persons in the address book
* `list o/fav` Lists all favourited persons in the address book

#### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [tc/CHILDTAG]…​ [t/TAG]…​`

* Edits the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it. Note: all ChildTags will also be removed.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

#### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Incomplete words will also be matched e.g. `Han` will match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
* If *n* persons can be found, message “*n* persons listed!” will be displayed
  e.g. when 0 results, "0 persons listed!" is displayed
  
Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li` when no exact matches are found

  ![result for 'find alex david'](images/findAlexDavidResult.png)

#### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

#### Sorting all persons : `sort`

Sorts the address book in the order based on the given option.

Format: `sort o/OPTION` 

Currently available options for the `[OPTION]` field include:
* `name` Sorts by name (alphabetical order)
* `date` Sorts by date added (chronological order)
  
Examples:
* `sort o/name` returns the contact list sorted in alphabetical order.
* `sort o/date` returns the contact list sorted in chronological order.

#### Favourite a person : `fav`

Format: `fav INDEX [o/OPTION]`

* Favourite the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Currently available options for the `[OPTION]` field include:
* `remove` Unfavourites the specified person

Examples:
* `list` followed by `fav 2` favourites the 2nd person in the address book.
* `find Betsy` followed by `delete 1` favourites the 1st person in the results of the `find` command.
* `fav 3 o/remove` unfavourites the 3rd person in the address book.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
When a person is favourited, the star next to their name will become filled/white.
When a person is unfavourited, the star will turn empty.
</div>

#### Clearing all entries : `clear`

Clears all entries from the address book or clears all contacts with the specified tags.

<div markdown="span" class="alert alert-primary">:warning: **Warning:**
The tags here do not differentiate between ChildTags and regular Tags. 
This command will delete all entries that match **ANY** of the given tags.
</div>

Format: `clear [t/TAG]…​`


Examples:
* `clear` deletes all entries in the address book.
* `clear t/teacher` deletes all contacts with the tag `teacher`

### Appointment Book Commands

#### Adding an appointment : `addAppt`

Adds an appointment to the appointment book.

Format: `addAppt n/NAME a/ADDRESS d/DATE [c/CONTACT_INDEX]…​ [tc/CHILDTAG]…​`

* Contact in the address book at the specified `CONTACT_INDEX` is added to the appointment.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* `DATE` has to be in the format "`dd`/`MM`/`yyyy` `HH`:`mm`".

Examples:
* `addAppt n/PTM a/ABC Primary School d/21/03/2021 10:00 c/2 ct/amy`

#### Deleting an appointment : `deleteAppt`

Deletes the specified appointment from the appointment book.

Format: `deleteAppt INDEX`

* Deletes the appointment at the specified `INDEX`.
* The index refers to the index number shown in the displayed appointment list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `deleteAppt 2` deletes the 2nd appointment in the appointment book.
* `findAppt ptm` followed by `deleteAppt 1` deletes the 1st appointment in the results of the `findAppt` command.

#### Finding appointments by name: `findAppt`

Finds appointments whose names contain any of the given keywords.

Format: `findAppt KEYWORD [MORE_KEYWORDS]…​`

* The search is case-insensitive. e.g `ptm` will match `PTM`
* The order of the keywords does not matter. e.g. `Teacher meeting` will match `Meeting teacher`
* Only the name is searched.
* Incomplete words will also be matched e.g. `PT` will match `PTM`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Teacher meeting` will return `Speak to ballet teacher`, `PSG meeting`
* If *n* appointments can be found, message “*n* appointments listed!” will be displayed
  e.g. when 0 results, "0 appointments listed!" is displayed

Examples:
* `findAppt ptm` returns `PTM`

--------------------------------------------------------------------------------------------------------------------

## Managing HeliBook Data

### Saving your data

HeliBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HeliBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HeliBook will discard all data and start with an empty data file at the next run.
</div>

### Exporting your data

1. After running HeliBook at least once, locate the `data` folder on your device which can be found in the same directory as your JAR file. 
2. Send this `data` folder to your other device.  

### Importing your data

1. Install HeliBook on your new device and run it once, exit the program before proceeding.
2. Locate the `data` folder on your old device which can be found in the same directory as your JAR file.
3. Copy the `data` folder from your old device to the new device to the same directory as where you installed the JAR file.
4. Replace the files on your new device when prompted.
5. Congratulations! You have successfully exported and imported your data to a new device.

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: What's the difference between a Child Tag and a Tag? <br>
**A**: Child Tags are meant to represent your children, useful especially 
if you have multiple children. Child Tags will always appear at the front of the list of Tags
in the Address Book and are displayed in a different color to differentiate them. Any command
that works with regular tags such as 'Find' or 'Sort' will also work with Child Tags.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
​ | **General Commands**
**Help** | `help [COMMAND]` <br> e.g, `help find`
**Exit** | `exit`
​ | **Address Book Commands**
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [tc/CHILDTAG]…​ [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear [t/TAG]…​`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [tc/CHILDTAG]…​ [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Fav** | `fav INDEX [o/OPTION]` <br> e.g., `fav 3 o/remove`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Tag** | `tag INDEX [o/OPTION] [tc/CHILDTAG]…​ [t/TAG]…​`<br> e.g., `tag 4 t/School t/English`
**Sort** | `sort o/OPTION` <br> e.g., `sort o/name`
​ | **Appointment Book Commands**
**Add** | `addAppt n/NAME a/ADDRESS d/DATE [c/CONTACT_INDEX]…​ [tc/CHILDTAG]…​` <br> e.g., `addAppt n/PTM a/ABC Primary School d/21/03/2021 10:00 c/2`
**Delete** | `deleteAppt INDEX` <br> e.g., `delete 2`
**Find** | `find KEYWORD [MORE_KEYWORDS]…​` <br> e.g., `find PTM`

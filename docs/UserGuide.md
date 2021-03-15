---
layout: page
title: User Guide
---

**Dictionote** is a desktop application that helps CS2103T students in finding information about the module's materials and writing notes about them. It is optimised for Command Line Interface (CLI) users so that searching and writing operations can be done quickly by typing in commands.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `dictionote.jar` from [here](https://github.com/AY2021S2-CS2103T-W13-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Dictionote application.

1. Double-click the file to start the app. The GUI should appear in a few seconds. <br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.

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

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Viewing Command List : `listcommand`

Shows a list of available command

Format: `listcommand`

### Opening UI Panel: `open`

Open selected UI Panel.

Format: `open Option`

* The following `Option` are supported
    * `-a`: All panel
    * `-c`: Contact panel
    * `-d`: Dictionary panel
    * `-dc`: Dictionary content panel
    * `-dl`: Dictionary list panel
    * `-n`: Note panel
    * `-nc`: Note content panel
    * `-nl`: Note list panel
    * `-l`: Both dictionary list and note list panel

Examples:
* `open -c`
    * show contact panel
* `open -a`
    * show all panel

### Closing UI Panel: `close`

Close selected UI Panel.

Format: `close Option`

* The following `Option` are supported
    * `-a`: All panel
    * `-c`: Contact panel
    * `-d`: Dictionary panel
    * `-dc`: Dictionary content panel
    * `-dl`: Dictionary list panel
    * `-n`: Note panel
    * `-nc`: Note content panel
    * `-nl`: Note list panel
    * `-l`: Both dictionary list and note list panel

Examples:
* `close -c`
    * close contact panel
* `close -a`
    * close all panel

### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

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


### Finding content in the Dictionary using keywords: `findcontent`

Finds relevant content in the dictionary that matches the keywords.

Format: `findcontent KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `programming` will match `PROGRAMMING` or `Programming`
* The order of the keywords does not matter. e.g. `content some` will match `some content`
* Only the content is searched, headers and week numbers are not included.
* Only full words will be matched e.g. `prog` will not match `programming`
* Contents matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `some different` will return `some content`, `different thing`

Examples:
* `findcontent programming` returns `Programming` and `PROGRAMMING LANGUAGE`
* `findcontent some different` returns `some content`, `different thing`<br>


### Finding definition in the Dictionary using keywords: `finddef`

Finds relevant content in the dictionary that matches the keywords.

Format: `finddef KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `programming` will match `PROGRAMMING` or `Programming`
* The order of the keywords does not matter. e.g. `content some` will match `some content`
* Only the content is searched, headers and week numbers are not included.
* Only full words will be matched e.g. `prog` will not match `programming`
* Contents matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `some different` will return `some content`, `different thing`

Examples:
* `finddef programming` returns `Programming Language` and `Object-Oriented Programming` <br>


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
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Close** | `close Option` <br> e.g., `close -c`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**ListCommand** | `listcommand`
**Help** | `help`
**Open** | `open Option` <br> e.g., `open -c`

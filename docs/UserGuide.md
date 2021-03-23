---
layout: page
title: User Guide
---

SmartLib is a desktop app for managing private book loaning services owning less than 10,000 books, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SmartLib can systematically manage your books and borrowers’ information in a more efficient manner than traditional GUI apps.

# Table of Contents
* [Quick start](#quick-start)
* [Features](#features)
* [FAQ](#faq)
* [Command summary](#command-summary)


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `smartlib.jar` from [here](https://github.com/AY2021S2-CS2103T-W13-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your SmartLib.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`listbook`** and pressing Enter will list all the current books in store.<br>
   Some example commands you can try:

   * **`addreader`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a reader named `John Doe` to SmartLib.

   * **`deletereader`**`3` : Deletes the 3rd contact shown in the current list.

   * **`listbook`** : Lists all contacts.

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

### Adding a book : `addbook`

Adds a book to the book list.

Format: `addbook b/NAME a/AUTHOR p/PUBLISHER i/ISBN`

Examples:
* addbook b/Harry Porter a/JK Rowling p/NUS i/978-3-16-148410-0
* addbook b/Hunger Games a/unknown p/publisher 1 i/978-3-16-148410-0

### Deleting a book: `deletebook`

Deletes a specific book from the book list.

Format: `deletebook INDEX`

* Deletes the book at the specified `INDEX`.
* The index refers to the index number shown in the displayed reader list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
* listbook followed by `deletebook 2` deletes the 2nd book in the book list.


### Listing all books: `listbook`

Lists all the current in-store books.

Format: `listbook`


### Locating books by name: `findbook`

Finds books whose names contain any of the given keywords.

Format: `findbook KEYWORD [MORE_KEYWORDS]`

Examples: 
*`findbook fiction` returns books related to fiction
*`findbook history [American History]` returns books related to history, especially American history.

### Listing all readers : `listreader`

Shows a list of all readers in SmartLib.

Format: `list`

### Locating readers by name: `findreader`

Finds readers whose names contain any of the given keywords.

Format: `findreader KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Readers matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `findreader John` returns `john` and `John Doe`
* `findreader alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'findreader alex david'](images/findAlexDavidResult.png)

## The contents from here onwards are to be removed by the last editor

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a reader: `addreader`

Adds a reader to SmartLib's registered reader base .

Format: `addreader n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe p/88888888 e/betsycrowe@example.com a/Newgate Prison`

### Editing a reader : `edit`

Edits an existing reader in SmartLib.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the reader at the specified `INDEX`. The index refers to the index number shown in the displayed reader list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the reader will be removed i.e adding of tags is not cumulative.
* You can remove all the reader’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st reader to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd reader to be `Betsy Crower` and clears all existing tags.

### Deleting a reader : `deletereader`

Deletes the specified reader from SmartLib's registered reader base.

Format: `deletereader INDEX`

* Deletes the reader at the specified `INDEX`.
* The index refers to the index number shown in the displayed reader list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* deletereader 1
* deletereader 2

Tip:
* `listreader` followed by `deletereader 2` deletes the 2nd reader in the displayed reader list.


### Borrowing a book : `borrow`

Records a borrowing activity.

Format: `borrow b/BOOKNAME r/READERNAME`

* Let reader at the specified name READERNAME borrow the book specified by name BOOKNAME

Examples:
* `borrow b/The Old Man and the Sea r/Alex Yeoh` records a rental entry that reader whose name is Alex Yeoh borrowed
a book which name is The Old Man and the Sea

### Returning a book : `return`

Records a returning activity.

Format: `return b/BOOKNAME r/READERNAME`

* Let reader at the specified name READERNAME return the book specified by name BOOKNAME

Examples:
* `return b/The Old Man and the Sea r/Alex Yeoh` records a rental entry that reader whose name is Alex Yeoh returned
a book which name is The Old Man and the Sea

### Clearing all entries : `clear`

Clears all entries from SmartLib.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SmartLib data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SmartLib data are saved as a JSON file `[JAR file location]/data/smartlib.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SmartLib will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SmartLib home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add reader** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665`
**Clear** | `clear`
**Delete reader** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`

---
layout: page
title: User Guide
---

# Table of Contents
* [Introduction](#introduction)
    * [Purpose](#purpose)
    * [Audience](#audience)
    * [Overview](#overview)
* [Quick start](#quick-start)
* [Features](#features)
    * [Adding a reader](#adding-a-reader--addreader)
    * [Deleting a reader](#deleting-a-reader--deletereader)
    * [Listing all readers](#listing-all-readers--listreader)
    * [Finding a reader](#locating-readers-by-name--findreader)
    * [Adding a book](#adding-a-book--addbook)
    * [Deleting a book](#deleting-a-book--deletebook)
    * [Listing all books](#listing-all-books--listbook)
    * [Finding a book](#locating-books-by-name--findbook)
    * [Borrowing a book](#borrowing-a-book--borrow)
    * [Returning a book](#returning-a-book--return)
    * [Clearing entries](#clearing-all-entries--clear)
    * [Accessing the help page](#viewing-help--help)
    * [Exiting the application](#exiting-the-program--exit)
* [FAQ](#faq)
* [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## **Introduction**

### Purpose

The purpose of this document is to provide a brief guide to the various features and functions of SmartLib,
so that our target readers would be able to gain a better understanding of how our application works,
and may navigate the application with greater ease.

### Audience

This User Guide (UG) is meant for any user who is interested in understanding the various features and
functions of our application, SmartLib. Some of our intended audience include:

* SmartLib's end-users: any user who may want to use SmartLib features to support their businesses and/or for other
purposes.

### Overview

SmartLib is a desktop app for managing private book loaning services owning less than 10,000 books,
optimized for use via a Command Line Interface (CLI),
while still having the benefits of a Graphical User Interface (GUI).

If you can type fast, SmartLib would be a brilliant and efficient assistant in the systematic management of your books
and borrowers' information, as compared to the traditional GUI apps currently available in the market.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `smartlib.jar` from [here](https://github.com/AY2021S2-CS2103T-W13-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your SmartLib.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
contains some sample data.<br>
![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`listbook`** and pressing Enter will
list all the current books in store.<br>
   Some example commands you can try:

   * **`addreader`**`r/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a reader named
   `John Doe` to SmartLib.

   * **`deletereader`**`3` : Deletes the 3rd contact shown in the current list.

   * **`listbook`** : Lists all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add r/NAME`, `NAME` is a parameter which can be used as `add r/John Doe`.

* Items in square brackets are optional.<br>
  e.g `r/NAME [t/TAG]` can be used as `r/John Doe t/friend` or as `r/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/VIP`, `t/VIP t/MostFrequentCustomer` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `r/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER r/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding a reader : `addreader`

Adds a reader to SmartLib's registered reader base .

Format: `addreader r/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`

Examples:
* `addreader r/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addreader r/Betsy Crowe p/88888888 e/betsycrowe@example.com a/Newgate Prison`

### Deleting a reader : `deletereader`

Deletes the specified reader from SmartLib's registered reader base.

Format: `deletereader INDEX`

* Deletes the reader at the specified `INDEX`.
* The index refers to the index number shown in the displayed reader list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `deletereader 1`
* `deletereader 2`

Tip:
* `listreader` followed by `deletereader 2` deletes the 2nd reader in the displayed reader list.

### Listing all readers : `listreader`

Shows a list of all readers in SmartLib.

Format: `list`

### Locating readers by name : `findreader`

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

### Adding a book : `addbook`

Adds a book to the book list.

Format: `addbook b/NAME a/AUTHOR p/PUBLISHER i/ISBN g/Genre`

Examples:
* `addbook b/Harry Porter a/JK Rowling p/Bloomsbury i/9783161484100 g/Fantasy`
* `addbook b/Hunger Games a/Suzanne Collins p/Scholastic i/9783161484100 g/Young Adult`

### Deleting a book : `deletebook`

Deletes a specific book from the book list.

Format: `deletebook INDEX`

* Deletes the book at the specified `INDEX`.
* The index refers to the index number shown in the displayed reader list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
* `listbook` followed by `deletebook 2` deletes the 2nd book in the book list.

### Listing all books : `listbook`

Lists all the current in-store books.

Format: `listbook`

### Locating books by name : `findbook`

Finds books whose names contain any of the given keywords.

Format: `findbook KEYWORD [MORE_KEYWORDS]`

Examples:
*`findbook fiction` returns books related to fiction
*`findbook history [American History]` returns books related to history, especially American history.

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

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SmartLib data are saved in the hard disk automatically after any command that changes the data. There is no need to save
manually.

### Editing the data file

SmartLib data are saved as a JSON file `[JAR file location]/data/smartlib.json`. Advanced users are welcome to update
data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SmartLib will discard all data and start with an empty data
file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous SmartLib home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add reader** | `addreader r/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g., `addreader r/James Ho p/22224444e/jamesho@example.com a/123, Clementi Rd, 1234665`
**Delete reader** | `deletereader INDEX`<br> e.g., `deletereader 3`
**Find reader** | `findreader KEYWORD [MORE_KEYWORDS]`<br> e.g., `findreader James Jake`
**List readers** | `listreader`
**Add book** | `addbook b/NAME a/AUTHOR p/PUBLISHER i/ISBN g/Genre` <br> e.g., `addbook b/Harry Porter a/JK Rowling p/Bloomsbury i/9783161484100 g/Fantasy`
**Borrow book** | `borrow b/BOOKNAME r/READERNAME`<br> e.g., `borrow b/The Old Man and the Sea r/Alex Yeoh`
**Delete book** | `deletebook INDEX`<br> e.g., `deletebook 3`
**Find book** | `findbook KEYWORD [MORE_KEYWORDS]`<br> e.g., `findbook Thomas the Tank Engine`
**List books** | `listbook`
**Return book** | `return b/BOOKNAME r/READERNAME`<br> e.g., `return b/The Old Man and the Sea r/Alex Yeoh`
**Clear** | `clear`
**Help** | `help`
**Exit** | `exit`

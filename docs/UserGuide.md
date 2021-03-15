---
layout: page
title: User Guide
---

ClientBook is a desktop app for managing client contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ClientBook can get your client management tasks done faster than traditional GUI apps.


## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `clientbook.jar`. (A release will be coming out soon)

1. Copy the file to the folder you want to use as the _home folder_ for your ClientBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 i/Policy_1022`** : Adds a client named `John Doe` to the ClientBook.

   * **`delete 3`** : Deletes the 3rd contact shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for the full list of commands and their descriptions.

--------------------------------------------------------------------------------------------------------------------
## Features
- Viewing help : `help`
- Adding a client: `add`
- List all clients: `list`
- Locating clients by name: `find`
- Deleting a client : `delete`
- Exiting the program : `exit`
- Locking ClientBook: `lock`
- Unlocking ClientBook: `unlock`
- Saving the data

--------------------------------------------------------------------------------------------------------------------

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in UPPER_CASE are the parameters to be supplied by the user.
 e.g. in `add n/NAME`, NAME is a parameter which can be used as `add n/John Doe`.


* Items in square brackets are optional.
 e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.


* Items with …​ after them can be used multiple times including zero times.
 e.g. `[t/TAG]…​` can be used as   (i.e. 0 times), `t/basic`, `t/basic t/lifeinsurance` etc.


* Parameters can be in any order.
 e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.


* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.
 e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.


* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.
 e.g. if the command specifies `help 123`, it will be interpreted as `help`.


</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a client to ClientBook.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [i/POLICY_ID] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* (example with policy)`add n/John Doe p/98765432 e/johnd@example.com a/Ochard i/Policy_1273 t/basic`
* (example with no policy)`add n/Betsy Crowe e/betsycrowe@example.com a/Kent Ridge t/nopolicy`

### Listing all persons : `list`

Shows a list of all clients in ClientBook.

Format: `list`

### Editing a person : `edit`

Edits an existing client in the ClientBook.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/POLICY_NUMBER] [t/TAG]…​`

* Edits the client at the specified INDEX. The index refers to the index number shown in the displayed client list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.


Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower` Edits the name of the 2nd person to be `Betsy Crower`.

### Locating persons by name: `find`

Finds clients whose names contain any of the given keywords.

Format: `find KEYWORD [& MORE_KEYWORDS]`

* The search is case-insensitive. e.g hans will match Hans.
* The order of the keywords does not matter.
* Only the name field is searched.
* The delimiter `&` between keywords is used to indicate mutiple keywords.
* Clients matching at least one keyword will be returned (i.e. OR search). e.g. Hans & Bo will return Hans Gruber, Bo Yang.


Examples:
* `find John` returns `john` and `John Doe`
* `find alex & david` returns `Alex Yeoh`, `David Li`<br>

### Deleting a person : `delete`

Deletes the specified client from ClientBook after listing or finding contacts.

Format: `delete INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Locking ClientBook : `lock`

Locks ClientBook.

Format: `lock [CURRENT_PASSWORD] [NEW_PASSWORD]`

* Verifies the current password before locking ClientBook with the new password.
* The `CURRENT_PASSWORD` field can be omitted if ClientBook is not yet locked.
* When `CURRENT_PASSWORD` and `NEW_PASSWORD` fields are both omitted, ClientBook will attempt to lock itself using the last used password that is safely stored on your device.

### Unlocking ClientBook : `unlock`

Unlocks ClientBook.

Format: `unlock CURRENT_PASSWORD`

* Verifies the current password before unlocking ClientBook.


### Saving the data

ClientBook data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ClientBook data are saved as a JSON file `[JAR file location]/data/clientbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ClientBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ClientBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [i/POLICY_ID] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 i/Policy_1023 t/premium t/lifeinsurance`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [i/POLICY_NUMBER] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James & Jake`
**List** | `list`
**Help** | `help`
**Exit** | `exit`
**Lock** | `lock [CURRENT_PASSWORD] NEW_PASSWORD`
**Unlock** | `unlock [CURRENT_PASSWORD]`

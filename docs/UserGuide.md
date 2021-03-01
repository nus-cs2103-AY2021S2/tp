
# User Guide

GreenMileageEfforts (GME) is a platform that helps drivers and passengers of any IT company quickly arrange carpooling in order to lower their carbon footprint. The platform follows that of a command-line interface (CLI) such that power users that are familiar can efficiently navigate the program.

- Quick start
- Features
    - Create passengers: `create`
    - Read passengers: `list`
    - Search for passengers: `search`
    - Delete passengers: `delete`
    - Select passengers to be driven: `drive`
- FAQ
- Command summary


--------------------------------------------------------------------------------------------------------------------

# Quick start

1. Ensure you have java 11 or above installed in your computer
2. Download the latest `GreenMileageEfforts.jar` from [here](https://github.com/AY2021S2-CS2103T-W10-1/tp/releases)
3. Copy the file to the folder you want to use as the *home* folder for your **GreenMileageEfforts**.
4. Double click the file to start the app.
5. Type the command in the command box and press `Enter` to execute it
6. Refer to the [Features]() below for the details on each command.

--------------------------------------------------------------------------------------------------------------------

## Features

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

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`


>**Examples**:
>* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
>* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Search for passengers: `search`

Search passengers according to the constraints such as looking for female driver only or if they are available

Format: `search CONSTRAINT`

- Only one criteria can be search at a time
- List of constraints that can be searched includes:
  *availability*, *gender*

> **Examples**:`search female` or `search available`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

>**Examples**:
>*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
>*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

>**Examples**:
>* `find John` returns `john` and `John Doe`
>* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

>**Examples**:
>* `list` followed by `delete 2` deletes the 2nd person in the address book.
>* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

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


### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

# FAQ

**Q:** Where can I find the data stored by the address book?

**A:** The json file containing the data stored is named `addressbook.json` and can be found in the `../data` folder, where `..` is the path to your `GreenMileageEfforts.jar` file.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**create** | `create n/NAME a/ADDRESS t/TIME` <br> e.g., `create n/Mike Hunt a/Geylang t/18:00`
**list** | `list`
**search** | `search CONSTRAINT`<br> e.g., `search female or search available`
**delete** | `delete INDEX`<br> e.g.,`delete 3`
**drive** | `drive INDEX INDEX INDEX...`<br> e.g., `drive 2 3 4`

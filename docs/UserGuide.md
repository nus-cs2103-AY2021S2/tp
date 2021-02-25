---
layout: page
title: User Guide
---

imPoster is a **desktop app for running and testing APIs, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, imPoster can enable you to test your APIs more conveniently than traditional GUI apps.

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

   * **`list`** : Lists all APIs in the API list.

   * **`add`**`-x GET -u https://www.google.com` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd API shown in the current list.

   * **`run`**`-x GET -u https://www.google.com` : Run a Get request to `https://www.google.com` on the fly.
   
   * **`send`**`3` : Run the 3rd API shown in the current list.

   * **`clear`** : Deletes all APIs in the current list.

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

### Viewing help : `help`

Gets the link to the user guide to the application in the form of a pop up window.

![help message](images/helpMessage.png)

Format: `help`

### Adding an API route: `add`

Adds an API route to API list.

Format: `add -x METHOD -u URL`

Examples:
* `add -x GET -u https://www.google.com`
* `add -x GET -u https://www.yahoo.com`

### Running an API call directly: `run`

Runs an API on the fly (without saving).

Format: `run -x METHOD -u URL`

Examples:
* `run -x GET -u https://www.google.com`
* `run -x GET -u https://www.yahoo.com`

### Sending an API call from API list: `send`

Calls an API from from the API list.

Format: `send INDEX​`

Examples:
* `send 1` calls the 1st API from the API list.
* `send 2` calls the 2nd API from the API list.

### Listing all saved API requests: `list`

Shows a list of all API routes in the API list.

Format: `list`

### Editing an API: `edit`

Edits an existing saved API.

Format: `edit INDEX [-x METHOD] [-u URL]`
* Edits the API route at the specified INDEX. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `edit 1 -u https://facebook.com. Edits the URL of the 1st API route to be https://facebook.com.`
* `edit 2 -x POST. Edits the METHOD of the 2nd API route to be POST.`

### Locating API by name: `find`

Finds API routes containing the search word in any of its fields.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `Google` will match `google`
* The order of the keywords does not matter. e.g. `maps google` will match `google maps`
* Only the name is searched.
* Only full words will be matched e.g. `googl` will not match `google`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `foo bar` will return `foo cup`, `bar water`

Examples:
* `find google` returns `google.com` and `https://www.google.com/maps`
* `find facebook maps` returns `https://www.google.com/maps`, `https://facebook.com`

### Remove a saved API request: `remove`

Removes the specified API from the saved list of APIs.

Format: `remove INDEX`

* Removes the saved request at the specified INDEX.
* The index refers to the index number shown in the displayed request list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `remove 1` removes the 1st API route from the API list.
* `remove 2` removes the 2nd API route from the API list.

### Clearing all entries : `clear`

Clears all entries from the API list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Imposter data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

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
**Add** | `add -x METHOD -u URL` <br> e.g., `add -x GET -u https://www.google.com`
**Run** | `run -x METHOD -u URL` <br> e.g., `run -x GET -u https://www.yahoo.com`
**Send** | `send INDEX` <br> e.g., `send 1`
**Clear** | `clear`
**Remove** | `remove INDEX`<br> e.g., `remove 3`
**Edit** | `edit INDEX [-x METHOD] [-u URL]`<br> e.g.,`edit 1 -u https://facebook.com`
**Find** | ` find KEYWORD [MORE_KEYWORDS]…​`<br> e.g., `find maps`
**List** | `list`
**Help** | `help`

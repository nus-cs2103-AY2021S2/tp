---
layout: page
title: User Guide
---

imPoster is a **desktop application for running and testing APIs, optimized for
use via a Command Line Interface** (CLI) while still having the benefits of a
Graphical User Interface (GUI). If you can type fast, imPoster can enable you to
test your APIs more conveniently than traditional GUI applications.

- Table of Contents {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `imposter.jar` from
   [here](https://github.com/AY2021S2-CS2103T-T12-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for
   imPoster.

1. Double-click the file to start the application. A GUI similar to the one
   shown below should appear in a few seconds. Note how the application contains
   some sample data.<br> ![Ui](images/Ui.png)

1. Type the command in the command box and press <kbd>Enter</kbd> to execute it.
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help
   window.<br> Some example commands you can try:

   - **`list`** : Lists all API endpoints in the API endpoint list.

   - **`add`**`-x GET -u https://www.google.com` : Adds an API endpoint to the
     API endpoint list.

   * **`add`**`-x GET -u https://www.google.com t/important` : Adds an API endpoint to the API endpoint list, tagging it as important.

   * **`remove`**`3` : Removes the 3rd API endpoint using the index shown in the API endpoint list.

   - **`send`**`3` : Runs the 3rd API endpoint using the index shown in the API
     endpoint list.

   * **`clear`** : Removes all API endpoints in the API endpoint list.

   - **`exit`** : Exits the application.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br> e.g.
  in `add -x METHOD`, `METHOD` is a parameter which can be used as `add -x GET`.

- Items in square brackets are optional.<br> e.g.
  `edit INDEX [-x METHOD] [-u URL]` can be used as `edit 1 -x GET` or
  `edit 1 -u https://www.google.com`.

- Items with `…`​ after them can be used multiple times including zero
  times.<br> e.g. `find [KEYWORD 1] [KEYWORD 2]…​` can be used as
  `find KEYWORD1` or `find KEYWORD1 KEYWORD2 KEYWORD3`.

- Parameters can be in any order.<br> e.g. if the command specifies
  `-x METHOD -u URL`, `-u URL -x METHOD` is also acceptable.

- If a parameter is expected only once in the command but you specified it
  multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `-x GET -x POST`, only `-x POST` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as
  `help`, `list`, `exit` and `clear`) will be ignored.<br> e.g. if the command
  specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Gets the link to the user guide to the application in the form of a pop up
window.

![help message](images/helpMessage.png)

Format: `help`

### Adding an API route: `add`

Adds an API route to API list.

Format: `add -x METHOD -u URL [t/TAG]…`

**Tip:** A person can have any number of tags (including 0)

Examples:
* `add -x GET -u https://www.google.com`
* `add -x GET -u https://www.yahoo.com t/important`

### Running an API call directly: `run`

Runs an API on the fly (without saving).

Format: `run -x METHOD -u URL`

Examples:

- `run -x GET -u https://www.google.com`
- `run -x GET -u https://www.yahoo.com`

### Sending an API call from API list: `send`

Calls an API from from the API list.

Format: `send INDEX`

Examples:

- `send 1` calls the 1st API from the API list.
- `send 2` calls the 2nd API from the API list.

### Listing all saved API requests: `list`

Shows a list of all API routes in the API list.

Format: `list`

### Editing an API: `edit`

Edits an existing saved API.

Format: `edit INDEX [-x METHOD] [-u URL] [t/TAG]…`
* Edits the API endpoint at the specified INDEX. The index refers to the index number shown in the displayed API endpoint list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values. 
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing t/ without specifying any tags after it.

Examples:
* `edit 1 -u https://facebook.com` Edits the URL of the 1st API route to be `https://facebook.com.`
* `edit 3 -x POST` Edits the METHOD of the 3rd API route to be POST.
* `edit 2 -u https://www.taylorswift.com t/` Edits the URL of the 2nd endpoint to be `https://www.taylorswift.com` and clears all existing tags.

### Locating API by name: `find`

Finds API routes containing the search word in any of its fields.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g `Google` will match `google`
- The order of the keywords does not matter. e.g. `maps google` will match
  `google maps`
- Only the name is searched.
- Only full words will be matched e.g. `googl` will not match `google`
- API endpoints matching at least one keyword will be returned (i.e. `OR`
  search). e.g. `foo bar` will return `foo cup`, `bar water`

Examples:

- `find google` returns `google.com` and `https://www.google.com/maps`
- `find facebook maps` returns `https://www.google.com/maps`,
  `https://facebook.com`

### Remove a saved API request: `remove`

Removes the specified API from the saved list of APIs.

Format: `remove INDEX`

- Removes the saved request at the specified INDEX.
- The index refers to the index number shown in the displayed request list.
- The index must be a positive integer 1, 2, 3, …​

Examples:

- `remove 1` removes the 1st API route from the API list.
- `remove 2` removes the 2nd API route from the API list.

### Clearing all entries : `clear`

Clears all entries from the API list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

imposter data are saved in the hard disk automatically after any command that
changes the data. There is no need to save manually.

### Editing the data file

imPoster data are saved as a JSON file `[JAR file location]/data/imposter.json`.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, imPoster will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br> **A**: Install the
application in the other computer and overwrite the empty data file it creates
with the file that contains the data of your previous imPoster home folder.

---

## Command summary

| Action     | Format                                | Example                                |
| ---------- | ------------------------------------- | -------------------------------------- |
| **Add**    | `add -x METHOD -u URL [t/TAG]…` <br>  | `add -x GET -u https://www.google.com` |
| **Run**    | `run -x METHOD -u URL` <br>           | `run -x GET -u https://www.yahoo.com`  |
| **Send**   | `send INDEX` <br>                     | `send 1`                               |
| **Clear**  | `clear`                               | `clear`                                |
| **Remove** | `remove INDEX`<br>                    | `remove 3`                             |
| **Edit**   | `edit INDEX [-x METHOD] [-u URL] [t/TAG]`<br> | `edit 1 -u https://facebook.com` |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]…`<br>  | `find maps`                            |
| **List**   | `list`                                | `list`                                 |
| **Help**   | `help`                                | `help`                                 |


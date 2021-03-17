---
layout: page
title: User Guide
---

imPoster is a **desktop application for running and testing APIs, optimized for
use via a Command Line Interface** (CLI) while still having the benefits of a
Graphical User Interface (GUI). If you can type fast, imPoster can enable you to
test your APIs more conveniently than traditional GUI applications.

* Table of Contents 
{:toc}

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

1. Type the command in the command box and press <kbd>Enter</kbd> to execute it. Some example commands you can try:

   - **`help`** : Opens a help window that shows a link for the application's user guide.

   - **`add -x GET -u https://api.data.gov.sg/v1/environment/pm25`** : Adds an API endpoint to the API endpoint list.

   - **`edit 1 -u https://api.data.gov.sg/v1/environment/uv-index`** : Edits the API endpoint with index `1` shown in the API endpoint list.

   - **`show 3`** : Shows the details of the API endpoint with index `3` shown in the API endpoint list.

   - **`remove 3`** : Removes the API endpoint with index `3` shown in the API endpoint list.

   - **`find pm25`** : Finds all the API endpoints with fields containing `pm25`.

   - **`send 2`** : Calls the API endpoint with index `2` shown in the API endpoint list.

   - **`run -x GET -u https://api.data.gov.sg/v1/environment/pm25`** : Calls the API endpoint with information given in the command box.

   - **`list`** : Lists all API endpoints in the API endpoint list.

   - **`clear`** : Clears all API endpoints in the API endpoint list.

   - **`toggle light`** : Toggles the theme of the application to one of the presets.

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
  `edit 1 -u https://api.data.gov.sg/v1/environment/pm25`.

- Items with `…`​ after them can be used multiple times including zero times.<br> e.g. `find [KEYWORD 1] [KEYWORD 2]…​` can be used as `find github` or `find github cats`.

- Parameters can be in any order.<br> e.g. both `-x METHOD -u URL` and `-u URL -x METHOD` are acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br> e.g. if you specify `-x GET -x POST`, only `-x POST` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br> e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### <ins>General Commands</ins>

#### View help: `help`

Description: Get the link to the user guide to the application in the form of a pop up window.

Format: `help`

![help message](images/helpMessage.png)

#### Toggle theme: `toggle`

Description: Toggle the theme for the application.

Format: `toggle THEME`

Example:
- `toggle light`

#### Exit program: `exit`

Description: Exit the application.

Format: `exit`

### <ins>Endpoint Commands</ins>

#### Add an API endpoint: `add`

Description: Add an API endpoint to the API endpoint list.

Format: `add -x METHOD -u URL [-d DATA] [-h HEADER]… [-t TAG]…`

`DATA` must be in **JSON** format and `HEADER` must be enclosed in `" "` else an error message will be shown.

Examples:
- `add -x GET -u http://localhost:3000/ -d {"some": "data"} -h "key1: value1" -h "key2: value2" -t local -t data`
- `add -x GET -u https://api.data.gov.sg/v1/environment/pm25`

**Tip:** An endpoint can have any number of **unique** tags and headers (including 0).

**Tip 2:** An endpoint can only have 0 or 1 data.

#### Edit an API endpoint: `edit`

Description: Edit the API endpoint at the specified index shown in the API endpoint list (at least one optional argument must be provided).

Format: `edit INDEX [-x METHOD] [-u URL] [-d DATA] [-h HEADER]… [-t TAG]…`

Examples:
- `edit 1 -x GET -u http://localhost:3000/ -d {"some": "data"} -h "key: value" -h "key: value" -t local -t data`
- `edit 2 -x POST`
- `edit 3 -x GET -t fresh -t food`

**Tip:** Multiple tags must be unique and duplicates will be ignored.

#### Show an API endpoint: `show`

Description: Show the details of the API endpoint at the specified index shown in the API endpoint list (index must 
be a positive integer).

Format: `show INDEX`

Examples:
- `show 1`
- `show 3`

#### Remove an API endpoint: `remove`

Description: Remove the API endpoint at the specified index showin in the API endpoint list.

Format: `remove INDEX`

Examples:
- `remove 1`

#### Find a saved API endpoint: `find`

Description: Find API routes containing the search word in any of its fields. (First Format)

Format: `find KEYWORD [MORE_KEYWORDS]…`

Examples:

- `find pm25`
- `find singapore pm25`


Description: Find API routes containing the search word in a specific field. (Second Format)

Format: `find -x KEYWORD [MORE_KEYWORDS]…`, `find -t KEYWORD [MORE_KEYWORDS]…` (able to use any prefix)

Examples:

- `find -x get`
- `find -t singapore pm25`
#### List all saved API endpoints: `list`

Description: Show a list of all API endpoints in the API endpoint list.

Format: `list`

#### Clear all saved API endpoints: `clear`

Description: Clear all API endpoints in the API endpoint list.

Format: `clear`

#### Call a saved API endpoint: `send`

Description: Call an API endpoint from the API endpoint list.

Format: `send INDEX`

Examples:

- `send 1`

**Tip:** You may cancel an API call with <kbd>ctrl</kbd> + <kbd>d</kbd>.

#### Call an API endpoint directly without saving: `run`

Description: Call an API endpoint on the fly (without saving). Two command formats are available. The first format performs a standard call to an API endpoint. (First Format)

Format: `run -x METHOD -u URL [-d DATA] [-h HEADER]…`

Examples:

- `run -x GET -u https://api.data.gov.sg/v1/environment/pm25`
- `run -x GET -u https://api.data.gov.sg/v1/environment/uv-index -d {"some": "data"} -h "key: value"`

Description: Performs a GET request to a valid API URL address, without specifying any prefixes or any other parts of an API endpoint. (Second Format)

Format: `run URL`

Examples:

- `run https://api.data.gov.sg/v1/environment/pm25`
- `run https://api.data.gov.sg/v1/environment/uv-index"`

**Tip:** You may cancel an API call with <kbd>ctrl</kbd> + <kbd>d</kbd>.

### Miscellaneous Information

#### Autosave

imposter data are saved in the hard disk automatically after any command that
changes the data. There is no need to save manually.

#### Data file

imPoster data are saved as a JSON file `[JAR file location]/data/imposter.json`.
Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, imPoster will discard all data and start with an empty data file at the next run.
</div>

#### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br> **A**: Install the
application in the other computer and overwrite the empty data file it creates
with the file that contains the data of your previous imPoster home folder.

---

## Command summary

### <ins>General Commands</ins>

| Action     | Format                                | Example                                |
| ---------- | ------------------------------------- | -------------------------------------- |
| **Help**   | `help`                                | `help`                                 |
| **Toggle** | `toggle`                              | `toggle light`                         |
| **Exit**   | `exit`                                | `exit`                                 |

### <ins>Endpoint Commands</ins>

| Action     | Format                                | Example                                |
| ---------- | ------------------------------------- | -------------------------------------- |
| **Add**    | `add -x METHOD -u URL [-d DATA] [-h HEADER]… [-t TAG]…` <br>  | `add -x GET -u http://localhost:3000/ -d {"some": "data"} -h "key: value1" -h "key: value2" -t local -t data` |
| **Edit**   | `edit INDEX [-x METHOD] [-u URL] [-d DATA] [-h HEADER]… [-t TAG]…`<br> | `edit 1 -x GET -u http://localhost:3000/ -d {"some": "data"} -h "key: value" -h "key: value" -t local -t data` |
| **Show**   | `show INDEX`<br>                      | `show 1`                             |
| **Remove** | `remove INDEX`<br>                    | `remove 3`                             |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]…`<br> OR <br>`find -x KEYWORD [MORE_KEYWORDS]…`<br> | `find maps`<br> `find -x get`                            |
| **List**   | `list`                                | `list`                                 |
| **Clear**  | `clear`                               | `clear`                                |
| **Send**   | `send INDEX` <br>                     | `send 1`                               |
| **Run**    | `run -x METHOD -u URL [-d DATA] [-h HEADER]…` <br> OR <br>`run URL`  | `run -x GET -u https://api.data.gov.sg/v1/environment/uv-index -d {"some": "data"} -h "key: value"` <br> `run https://api.data.gov.sg/v1/environment/uv-index` |

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts and events. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Add command

1. What: Add in new contact into the application. All fields are optional except name.<br>
Command: `add -n bobby -r Past EXCO member`<br>
Expected message: `New person added: bobby; Remark: Past EXCO member`

1. What: If name is not specified, an error will be thrown.<br>
Command: `add -r nameless`<br>
Expected message: `Invalid command format! ...`

## Delete command

1. What: delete everyone in filtered list<br>
Command: `delete`<br>
Expected Message: `Deleted the following person(s): ...`

1. What: delete first and second person<br>
Command: `delete 1 2`<br>
Expected Message: `Deleted the following person(s): ...`

1. What: Wrong format (not a valid index)<br>
Command: `delete -1`<br>
Expected Message: `Invalid command format! ...`

1. What: Wrong format (index does not exist in the list)<br>
Command: `delete 10000`<br>
Expected Message: `None of the indexes provided are valid`

1. What: Delete all contacts that has `choir` AND `year2` tag<br>
Command: `delete -t choir -t year2`<br>
Expected Message: `Deleted the following person(s): ...`

1. What: Delete all contacts that has `choir` OR `year2` tag<br>
Command: `delete --any -t choir -t year2`<br>
Expected Message: `Deleted the following person(s): ...`

1. What: Invalid tag<br>
Command: `delete -t @`<br>
Expected Message: `Tags names should be alphanumeric and should not be longer than 40 characters`

### Edit command

1. What: Edit the address of a contact in the application.<br>
Command: `edit 1 -a NUS Temasek Hall Block E`<br>
Expected: `Edited Person: Alex Yeoh; Phone: 87438807; Email: ...`

1. What: If index is out of bounds.<br>
Command: `edit -a NUS Temasek Hall Block E`<br>
Expected Message: `The person index provided is invalid`

1. What: Remove a tag from all contacts in the application.<br>
Command: `edit --remove -t year4`<br>
Expected Message: `Removed tag from:...`

### List command

Lists contacts in the application. Used for 2 functions: Searching and Sorting.

1. What: List all contacts<br>
Command: `list`<br>
Expected message: `Listed all persons!...`

1. What: Search on partial criteria (case-insensitive), match all. (name contains `l` and tag contains `choir`)<br>
Command: `list -n l -t choir`<br>
Expected message: `3 person(s) listed! Each person meets all requirements stated. ...`

1. What: Search on exact criteria (case-insensitive), match any. (name is `Alex Yeoh` or contains tag `year2`)<br>
Command: `list --exact --any -n Alex Yeoh -t year2`<br>
Expected message: `2 person(s) listed! Each person meets at least 1 requirement stated. ...`

1. What: Sort list by upcoming birthday. (Ignores year)<br>
Command: `list -s u`<br>
Expected message: `Listed all persons! Sorted by upcoming birthdays.  ...`

1. What: Sort list by descending name. (Case insensitive)<br>
Command: `list -s n -o d`<br>
Expected message: `Listed all persons! Sorted names in descending order.  ...`<br>
Note: Default sort is `name` and default order is `ascending`

### EAdd command

1. What: Add in new event into the application. All fields are optional except name.<br>
Command: `eadd -n party -r invite people`<br>
Expected message: `New event added: party; Remark: invite people`

1. What: If name is not specified, an error will be thrown.<br>
Command: `eadd -r nameless`<br>
Expected message: `Invalid command format! ...`

### EDelete command

1. What: delete all event in filtered list<br>
Command: `edelete`<br>
Expected Message: `Deleted the following event(s): ...`

1. What: delete first and second event<br>
Command: `edelete 1 2`<br>
Expected Message: `Deleted the following event(s): ...`

1. What: Wrong format (not a valid index)<br>
Command: `edelete -1`<br>
Expected Message: `Invalid command format! ...`

1. What: Wrong format (index does not exist in the list)<br>
Command: `edelete 10000`<br>
Expected Message: `None of the indexes provided are valid`

### EEdit command

Eedit
1. What: Edit the date of an event in the application.<br>
Command: `eedit 1 -d 25 Dec 2021`<br>
Expected: `Edited event: Jan celebration; Date: 25 Dec 2021...`

1. What: If date does not have a year.<br>
Command: `eedit 1 -d 25 Dec`<br>
Expected Message: `Event date must contain a year`

### EDone command

1. What: mark first and second event as done<br>
Command: `edone 1 2`<br>
Expected Message: `Deleted the following event(s): ...`

1. What: mark first and second event as done (second event already completed)<br>
Command: `edone 1 2`<br>
Expected Message: `Event(s) marked as completed:... Invalid/Already completed event index(es):...`

1. What: Wrong format (not a valid index)<br>
Command: `edone -1`<br>
Expected Message: `Invalid command format! ...`

1. What: Wrong format (index does not exist in the list)<br>
Command: `edone 10000`<br>
Expected Message: `None of the indexes provided are valid`

### EList command

Lists events in the application. Used for 2 functions: Searching and Sorting.

1. What: List all events<br>
Command: `elist`<br>
Expected message: `Listed all events!`

1. What: Search on partial criteria (case-insensitive), match all. (name contains `l` and remark contains `people`)<br>
Command: `elist -n l -r people`<br>
Expected message: `3 event(s) listed! Each event meets all requirements stated. ...`

1. What: Search on exact criteria (case-insensitive), match any. (name is `Christmas celebration` or remark is `10 people`)<br>
Command: `elist --exact --any -n Christmas celebration -r 10 people`<br>
Expected message: `2 event(s) listed! Each event meets at least 1 requirement stated.  ...`

1. What: Sort list by upcoming event date. (Full date including year)<br>
Command: `elist -s u`<br>
Expected message: `Listed all events! Sorted by upcoming event dates.`

1. What: Sort list by descending event name. (Case insensitive)<br>
Command: `elist -s n -o d`<br>
Expected message: `Listed all events! Sorted event names in descending order. `
Note: Default sort is `name` and default order is `ascending`

### Undo command

1. What: To undo the last action<br>
Command: `undo`<br>
Expected Message: `Completed undo for the change:...`

1. What: If there is no more actions to undo<br>
Command: `undo`<br>
Expected Message: `There's nothing left to undo!`

### Redo command

1. What: To redo the last action undone<br>
Command: `redo`<br>
Expected Message: `Completed redo for the change:...`

1. What: If there is no more actions to redo<br>
Command: `redo`<br>
Expected Message: `There's nothing left to redo!`

### ToggleTheme command

1. What: Toggle between dark and pastel theme<br>
Command: `theme`<br>
Expected Message: `Changed to ... theme!`

### Exit command

1. What: exits the app<br>
Command: `exit`<br>
Expected Message: NA

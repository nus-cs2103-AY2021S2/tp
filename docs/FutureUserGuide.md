| Command                       | Description                                         | Format, Examples                                        |
| ----------------------------- | --------------------------------------------------- | ------------------------------------------------------- |
| [**Done**](#235-done-command) | Changes the status of an existing event to **Done** | `done IDENTIFIER` <br> e.g., `done 2`                   |
| [**Find**](#236-find-command) | **Finds** an existing event by specific keywords    | `find KEYWORD [KEYWORD]...`<br> e.g., `find James Jake` |

### 2.3.5 `done` Command

Changes the specified event status to DONE in Focuris.

Format: `done IDENTIFIER`

- Changes the event status at the specified `IDENTIFIER` to DONE.
- The identifier refers to the index number shown in the displayed event list.
- The identifier **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `done 2` changes the status of the 2nd event to DONE in Focuris.
- `find CS2100` followed by `done 1` changes the status of the 1st event to DONE in the results of the `find` command.

[Return to Table of Contents](#table-of-contents)

### 2.3.6 `find` Command

Finds events whose names contain any of the given keywords.

Format: `find KEYWORD [KEYWORD]...`

- The search is case-insensitive. e.g `cs2040` will match `CS2040`
- The order of the keywords does not matter. e.g. `Household Chores` will match `Chores Household`
- Only the name is searched.
- Only full words will be matched e.g. `Chore` will not match `Chore`
- Events matching at least one keyword will be returned (i.e. OR search). e.g. `Household` will return `Household Tidy`, `Household Clean`

Examples:

- `find CS2103` returns `CS2103` and `CS2103T`
- `find assignment` returns `CS2101 assignment`, `CS2103 assignment`

[Return to Table of Contents](#table-of-contents)

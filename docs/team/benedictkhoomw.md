---
layout: page
title: Benedict's Project Portfolio Page
---

## Project: SunRez

**SunRez** is a desktop app designed for college residential staff to efficiently manage student housing services. It
features a Graphical User Interface (GUI) but is optimized for use via a Command Line Interface (CLI).

Given below are my contributions to the project.

* **New Feature**: Added command history feature ([\#63](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/63) and 
  [\#107](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/107))
  * **What it does**: The `history [COUNT]` command displays (from most to least recent) successful commands previously
    executed. If the `COUNT` parameter is not specified, the entire history is displayed. Otherwise, the `COUNT` most
    recent entries are displayed. This history is saved to a file, and loaded back upon app startup. Lastly, the `UP`
    and `DOWN` arrow keys can be used to navigate command history (auto-filling the command box with previous successful
    commands).
  * **Justification**: Managing student housing records can involve a lot of repeated commands, or commands which differ
    only slightly from one another; command history speeds up this repetitive aspect of housing management. History is 
    saved to a file so that users do not lose it when they close SunRez for the day.
  * **Highlights**: This feature entailed in-depth knowledge of every component in SunRez: command text had to be
    intercepted in the Logic component, stored in the Model component and saved using the Storage component. The UI
    component had to be modified to support navigating command history, the implementation of which was a [design
    consideration](https://ay2021s2-cs2103-t14-1.github.io/tp/DeveloperGuide.html#aspect-should-command-history-selection-logic-be-in-commandbox)
    that ultimately led to a [refactoring](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/104) to better respect the
    Single Responsibility Principle. A [further design consideration](https://ay2021s2-cs2103-t14-1.github.io/tp/DeveloperGuide.html#aspect-should-history-include-invalid-commands)
    of whether to record invalid commands in history was [discussed](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/104#issuecomment-804772596)
    and resolved with the decision to only record successful commands.
  * **Credits**: Inspiration for the user-facing behavior of the command history feature was taken from
    [Address Book (Level 4)](https://github.com/se-edu/addressbook-level4) but its implementation was not referenced
    during development.

* **New Feature**: Added undo/redo commands ([\#128](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/128)) with keyboard
  and menu shortcuts.
  * **What it does**: The `undo` command reverses the effects of a previous state-changing command, while the `redo`
    command reverses an `undo` command's effects.
  * **Justification**: Sometimes users execute the wrong command. The undo/redo feature allows them to easily correct those
    mistakes. Cross-platform keyboard shortcuts quicken these operations for fast typists, while GUI menu shortcuts
    provide a simple interface for beginners.
  * **Highlights**: Much of the design was understood and adapted from another project (See Credits below). However,
    the adding of shortcuts involved understanding the UI to add accelerators and organize keyboard shortcuts in
    `KeyboardShortcuts`.
  * **Credits**: Code for undo/redo commands was adapted from [Address Book (Level 4)](https://github.com/se-edu/addressbook-level4).

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=benedictkhoomw&tabRepo=AY2021S2-CS2103-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * Updated the GUI to better display three types of data and long user feedback messages: 
    [\#152](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/152),
    [\#299](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/299) and
    [\#300](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/300)

* **Contributions to user guide (UG)**:
  * Standardized UG formatting in initial draft: [\#19](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/19).
  * Added the section describing the SunRez UI: [\#325](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/325).
  * Added the initial command parameters section (later revised): [\#279](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/279).
  * Added feature sections on `history`, `undo`, `redo`, `clear` and data transfer:
    [\#63](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/63),
    [\#128](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/128),
    [\#163](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/163) and
    [\#160](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/160).
  * Various other fixes and formatting changes. The comprehensive list is
    [here](https://github.com/AY2021S2-CS2103-T14-1/tp/pulls?q=is%3Apr+author%3Abenedictkhoomw+ug).

* **Contributions to developer guide (DG)**:
  * Wrote use cases UC-017, UC-018 and UC-021.
  * Added manual testing instructions for command history and undo/redo:
    [\#333](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/333).
  * Updated [logic component](https://ay2021s2-cs2103-t14-1.github.io/tp/DeveloperGuide.html#logic-component)
    descriptions and diagrams: [\#386](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/386) and
    [\#429](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/429).
  * Added implementation details and diagrams for command history:
    [\#109](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/109),
    [\#317](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/317) and
    [\#417](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/417).
  * Added introductory sections explaining audience and diagrams:
    [\#345](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/345) and
    [\#408](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/408).

* **Community**:
  * PRs reviewed (with non-trivial review comments):
    [\#65](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/65),
    [\#103](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/103),
    [\#115](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/115),
    [\#116](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/116),
    [\#121](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/121),
    [\#122](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/122),
    [\#133](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/133),
    [\#137](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/137),
    [\#156](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/156),
    [\#289](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/289);
    and a comprehensive list of all PRs reviewed
    [here](https://github.com/AY2021S2-CS2103-T14-1/tp/pulls?q=is%3Apr+reviewed-by%3Abenedictkhoomw+-author%3Abenedictkhoomw+).
  * Contributed to forum discussions: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/243#issuecomment-817067120),
    [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/301#issuecomment-817068673),
    [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/293#issuecomment-817065231).
  * Bug reports for my team:
    [\#60](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/60),
    [\#67](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/67),
    [\#105](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/105),
    [\#124](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/124),
    [\#125](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/125),
    [\#127](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/127),
    [\#154](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/154),
    [\#387](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/387),
    [\#391](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/391);
    and a comprehensive list
    [here](https://github.com/AY2021S2-CS2103-T14-1/tp/issues?q=is%3Aissue+author%3Abenedictkhoomw+-assignee%3Abenedictkhoomw+bug+).
  * Bug reports made for other teams (9/9 accepted): [PE-D](https://github.com/benedictkhoomw/ped/issues).
  
* **Team Tasks**:
  * Updated the README and project website:
    [\#13](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/13) and
    [\#153](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/153).
  * Updated various user-visible references of AddressBook to SunRez:
    [\#83](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/83),
    [\#151](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/151) and
    [\#281](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/281).
  * Enabled assertions: [\#101](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/101).
  * Created SunRez logo: [\#19](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/19).
  * Created [PE-D bug triage spreadsheet](https://docs.google.com/spreadsheets/d/1mXHfqkTdQwbmS0mEdQXIWZdkCLMxEFyW35C4lfsnoDY/edit?usp=sharing) and collated 33 PE-D issues.
  * Updated sample SunRez data: [\#172](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/172).
  * Updated product image: [\#153](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/153),
    [\#309](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/309),
    [\#400](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/400).

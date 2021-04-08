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
    only slightly from one another. By having command history in general, college residential staff can more efficiently
    perform their daily tasks as fast typists. Nevertheless, when command history gets long, navigation via arrow keys 
    can be slow and tedious. Sometimes it is more efficient to simply view a list of previous commands, scroll down, 
    then copy and paste a prior command. Hence, the view `history` command has its place. The optional `COUNT` parameter
    is there in case the user wishes to avoid the clutter of viewing the entire history (which can make the scroll bar 
    tiny if history is long). Finally, history is saved to a file, so users do not lose it when they close SunRez for the 
    day.
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
  * **Credits**: Code for undo/redo commands is adapted from [Address Book (Level 4)](https://github.com/se-edu/addressbook-level4).

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=benedictkhoomw&tabRepo=AY2021S2-CS2103-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
  
* **Enhancements to existing features**:
  * Updated the GUI to better display three types of data and long user feedback messages: 
    [\#152](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/152), 
    [\#299](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/299) and
    [\#300](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/300)

* **Documentation**:

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
    and a comprehesive list of all PRs reviewed
    [here](https://github.com/AY2021S2-CS2103-T14-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Abenedictkhoomw+).
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/54), 
    [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/207), 
    [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/250)).
  * Bug reports for my team:
    [\#60](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/60),
    [\#67](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/67),
    [\#68](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/68),
    [\#70](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/70),
    [\#105](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/105),
    [\#124](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/124),
    [\#125](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/125),
    [\#126](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/126),
    [\#127](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/127) and
    [\#154](https://github.com/AY2021S2-CS2103-T14-1/tp/issues/154).
  * Bug reports made for other teams (9/9 accepted): [PE-D](https://github.com/benedictkhoomw/ped/issues).

* **Team Tasks**:
  * Updated README and project website: 
    [\#13](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/13) and 
    [\#153](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/153).
  * Updated various user-visible references of AddressBook to SunRez: 
    [\#83](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/83) and
    [\#151](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/151).
  * Enabled assertions: [\#101](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/101).
  * Created SunRez logo: [\#19](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/19).
  * Created [PE-D bug triage spreadsheet](https://docs.google.com/spreadsheets/d/1mXHfqkTdQwbmS0mEdQXIWZdkCLMxEFyW35C4lfsnoDY/edit?usp=sharing) and collated 33 PE-D issues.
  * Updated sample SunRez data: [\#172](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/172).
  
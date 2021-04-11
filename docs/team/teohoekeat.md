---
layout: page
title: Teo Hoe Keat Project Portfolio Page
---

## Project: Dictionote


**Dictionote** is a desktop application that helps CS2103T students in finding information about the module's materials and writing notes about them. It is optimised for Command Line Interface (CLI) users so that searching and writing operations can be done quickly by typing in commands.

Given below are my contributions to the project.

* **New Feature**: Added the ability to manipulate UI panel
  * What it does: allows the user to change the UI panel visibility, divider position and orientation via command
  * Justification: In a note-taking application, It important to allow the user to customize the interface to fit the user need.
  * Highlights: Change the user interface as the user desire.

* **New Feature**: Added a show command for dictionary content and note content that allows the user to show the content in a panel.

  * What it does: allows the user to show the dictionary content or note on their respective panel
  * Justification: In note-taking application, some note and dictionary content can be really long.
    It can make the list view really long to scroll. Showing it on a separate panel allows the list to be more compact.

* **New Feature**: Added edit mode command for the shown note that allows the user to edit note.

  * What it does: allows the user to edit the note in a text-editor like an environment
  * Justification: Note can be relatively long. It isn't ideal to let the user edit their note with CLI.
    The most ideal way to let the user edit their note in a way where the user is allowed to modify their existing note easily

* **New Feature**: Added ListCommand and CommandDetail command that shows the user command summary.
  * What it does: show the user list of command available and command details
  * Justification: With so many commands available, it is hard for the user to remember all available feature. User can use `listcommand` to quickly recap the available command.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=w13&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=TeoHoeKeat&tabRepo=AY2021S2-CS2103T-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2`, `v1.3` (2 releases) on GitHub
  * Organising of team project document and weekly meeting minutes on team project document [link](https://docs.google.com/document/d/1sCGYPCkC0vt98Ym-6Cq818hXfvr8K53MCU5_UjriTyI/edit)

* **Enhancement Features**:
  * Update existing GUI to Dictionote GUI
    (Pull requests [\#42](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/45))
  * Add `UI` Feature : `open` and ``close` command
    (Pull requests [\#42](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/45))
  * Save User Interface Configuration upon exit
    (Pull requests [\#78](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/78))
  * Add `Help` Feature : `listcommand` command
    (Pull requests [\#88](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/88))
  * Update `Note` Feature : update Note Card UI
    (Pull requests [\#84](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/84))
  * Update `Dictionary` Feature : update Dictionary Content Card UI
    (Pull requests [\#101](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/101))
  * Add `Dictionary` Feature : `showdc` command
    (Pull requests [\#102](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/102))
  * Update `Note` Feature : update Show Note Content UI
    (Pull requests [\#104](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/104))
  * Add `Note` Feature : Add Edit Mode features (Pull requests [\#119](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/119))
    * Command include: `editmode`, `save`, and `quit`
  * Add `UI` Feature : Toggle UI Orientation (Pull requests [\#140](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/140))
    * Command include: `toggledividerd` and `toggledividerc`
  * Add `UI` Feature : Set Divider Position (Pull requests [\#153](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/153))
    * Command include: `setdividerm`, `setedividerd`, `setedividern` and `setedividerc`
  * Add `Help` Feature : List command details
    (Pull requests [\#160](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/160)
    ,[\#236](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/236))
    * Command include: `commanddetaild`, `commanddetailn`, `commanddetailc` and `commanddetailu`
  * Updated the UI Theme
    (Pull requests [\#162](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/162))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `open` and `close`
      (Pull requests [\#38](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/38)
      ,[\#175](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/175))
    * Added documentation for the features `listcommand`
      (Pull requests [\#89](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/89))
    * Added documentation for the features `editmode` and all `setdivider` and `toggledivider` command
      (Pull requests [\#114](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/114)
      ,[\#148](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/148)
      , [\#257](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/257))

  * Developer Guide:
    * Added Non-functional Requirement
      (Pull requests [\#29](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/29))
    * Added user stories and use case for UI related feature
      (Pull requests [\#37](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/37))
    * Added implementation details for UI related feature
      (Pull requests [\#149](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/149)
      , [\#260](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/260))
    * Updated UI class diagram and UI component details
      (Pull requests [\#141](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/141)
      , [\#260](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/260))

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
    [\#53](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/53),
    [\#105](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/105#discussion_r595257515),
    [\#139](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/139#discussion_r602392869), 
    [\#158](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/158#discussion_r602886244)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/195))
  * Reported bugs and suggestions for other teams in the class (examples:
    [1](https://github.com/AY2021S2-CS2103-W16-1/tp/issues/263),
    [2](https://github.com/AY2021S2-CS2103-W16-1/tp/issues/267),
    [3](https://github.com/AY2021S2-CS2103-W16-1/tp/issues/264))

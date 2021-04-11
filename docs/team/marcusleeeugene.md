---
layout: page
title: Marcus Lee Eugene's Project Portfolio Page
---

## Project: The Food Diary

The Food Diary - The Food Diary is a desktop app for managing food diary entries, optimized with a Command Line Interface (CLI) and packaged with a Graphical User Interface (GUI) using JavaFX. It is written in Java, and has about 8.5k LoC.

Given below are my contributions to the project.

* **Revise Feature**: Added the ability to allow users to quickly make corrections to existing data in the UI with shortcut keys.
    * What it does: Opens up a window, showing the existing details of a FoodDiary entry and allows for quick corrections and updates without requiring the use of prefixes and command syntax in the UI.
    * Justification: This feature improves the product significantly because a user can make corrections to existing data quickly, without losing previous entry details.
    * Implementation: The revise feature is built upon the existing Edit command. When the user enters the revise command, 
      The Food Diary will get the specified entry from the model's `getFilteredEntryList()`. Then it will pass the entry to command result 
      in the `MainWindow.java`. From there, `MainWindow#handleRevise` will execute and `ReviseWindow.java` will project the 
      details of the entry into a pop up window for revision. Upon revise completion, edit command will be executed and changes will 
      be updated. There are some codes in place which allows for the TAB, ESC and Ctrl-S / Command-S key to be used to quickly iterate 
      through text fields, close the window and save revisions respectively.
    * Pull requests: [#96](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/96), [#100](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/100), [#103](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/103), [#184](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/184)


* **View Feature**: Added the ability to allow users to open an expanded view for entries that are very lengthy.
    * What it does: Opens up a window, showing the details of a specified FoodDiary entry in a full expanded view. Allows the user to read through reviews that are too lengthy to be shown in the main UI window.
    * Justification: This feature allows users to view entry reviews that are too lengthy to be shown in the entry card. All the data are projected onto the view window UI and also separates reviews within the entry for easier view.
    * Implementation: When the user enters the view command, The Food Diary will get the specified entry from the model's `getFilteredEntryList()`. Then it will pass the entry to command result
      in the `MainWindow.java`. From there, `MainWindow#handleView` will execute and `ReviseWindow.java` will project the
      details of the entry into a pop up window for view.
    * Pull requests: [#68](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/68), [#76](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/76), [#87](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/87), [#93](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/93)
    

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=marcusleeeugene&tabRepo=AY2021S2-CS2103-T14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Project management**:
    * Managed releases `v1.2.1`, `v1.3.0` and `v1.4.0` (3 releases) on GitHub
    * Tracked team's tasks using project board and setting deadlines

<div style="page-break-after: always;"></div>

* **Documentation**:
    * User Guide:
        * Added documentation for `revise`, `view` and `edit`
        * Product description
    * Developer Guide:
        * Implementation and design considerations for `view`
        * Design considerations for `revise`
        * Added component details for the Model architecture
        * Added command details and user stories for the features `view`, `clear`, `revise`
        * Instructions for manual testing: View an entry, Clear all entries


* **Contributions to team-based tasks**:
    * Set up Github team repo
    * Set up Github actions
    * Maintain issue tracker
    * Release management
    * Merged pull requests
    * Refactored model, logic and test codes from AB3 to fit FoodDiary
    * Worked on Revise and View features and UI enhancements


* **Review/mentoring contributions**:
    * Helped the person in charge of merging, with resolving conflicts
    * PRs reviewed:
      
      [#189](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/189), 
      [#185](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/185),
      [#114](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/114),
      [#102](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/102),
      [#98](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/98),
      [#97](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/97),
      [#86](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/86),
      [#84](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/84),
      [#83](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/83),
      [#82](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/82),
      [#78](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/78),
      [#75](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/75),
      [#67](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/67),
      [#66](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/66),
      [#65](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/65), 
      [#55](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/55),
      [#44](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/44), 
      [#26](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/26),
      [#21](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/21), 
      [#17](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/17), 
      [#1](https://github.com/AY2021S2-CS2103-T14-2/tp/pull/1)
      

* **Contributions beyond the project team**:
    * Shared approach to using enums for individual project [#14](https://github.com/nus-cs2103-AY2021S2/forum/issues/14)
    * Helped user who faced difficulties in opening jar file due to java version [#158](https://github.com/nus-cs2103-AY2021S2/forum/issues/158)


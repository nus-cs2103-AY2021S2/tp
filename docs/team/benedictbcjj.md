---
layout: page
title: BenedictBCJJ's Project Portfolio Page
---

## Project: NUS Module Planner

NUS Module planner is a brownfield project based on AddressBook - Level 3, a desktop NUS module planner application used 
for managing and planning modules for their tenure at NUS. The user interacts with it using a CLI, and it has a GUI 
created with JavaFX. It is written in Java, and has about 12 kLoC.

Given below are my contributions to the project.


* **New Feature:** Added info command and tests (PR: [\#53](https://github.com/AY2021S2-CS2103-W17-1/tp/pull/53))
    * **What it does:** The `info m/[MODULECODE]` command displays module information that has been stored in the 
      `.\data\module.json` file. If the `MODULECODE` is not specified, all the module information will be display in the 
      order it is in `.\data\module.json` file. Otherwise, if the `MODULECODE` is specified and found in the 
      `.\data\module.json` file, it would display its information. If a corresponding `MODULECODE` is not found, a module
      not found message will be shown.
    * **Justification:** Before user can decide which module to take, they would need information on their availability,
    prerequisites and preclusion's. The info command would give the user the information needed before deciding to take
      the module.
    * **Highlights:** This feature requires in-depth knowledge of the Module Planner Model, Parser and Command 
      structure. Text had to be parsed and read to obtain the `MODULE CODE`, module information had to be retrieved from
      the Model component and command was executed to achieve the results. There was discussion to allow users to add
      module information to the Module Planner, however considering that it requires verifying valid module information
      and modifying the Module Planner structure to enable writing to a json file, it was shelved.
* **New Feature:** Added parsing of json file for Module Information, reads the json file stored in the `.\data`
    directory and stores it as a `JsonModule` object so that `info` could display the information stored.
* **New Feature:** Changing of UI to accommodate different information displayed (PR:[\#69](https://github.com/AY2021S2-CS2103-W17-1/tp/pull/69))
    * **What it does:** As different types of information would be displayed based on the type of command inputted into 
      Module Planner, the `UI` component has to detect the execution and changes based on the requirements of each command.
    * **Justification:** Users need to be able to read information that are relevant to their commands, this requires
      the Module Planner to be able to switch between the information being displayed to be the ones desired by the user.
    * **Highlights:** This feature requires knowledge of the UI, Command and Model component, and the Observer pattern. 
      The UI component had to observe the `StringProperty` an observable in the Model component that would be changed 
      by Command execution, based on the new value of the observable, there might be a change in the information displayed.
* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=BenedictBCJJ&tabRepo=AY2021S2-CS2103-W17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **Enhancements to existing features:**  
    * Updated the Main Window so other planes can be mounted on to show information in any format
* **Documentation:**
    * User Guide:
        * Added complete documentation for `info` command, (PR:[\#130](https://github.com/AY2021S2-CS2103-W17-1/tp/pull/130))
        * Added base documentation for `master` and `current` semester command
    * Developer Guide:
        * Added implementation details of `info` command (PR: [\#118](https://github.com/AY2021S2-CS2103-W17-1/tp/pull/118))
        * Added Non-functional requirements
    * List of UG and DG PRs [here](https://github.com/AY2021S2-CS2103-W17-1/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3ABenedictBCJJ+)
* **Project Management:**
    * Created and tagged various issues
    * Closed issues that required completion from members
    * Assisted in providing information for features implement by me that were used for other features
* **Community:**
    * Reviewed 2 peer prs
    * Submitted 13 bugs during PED


---
layout: page
title: Sean Iau Yang's Project Portfolio Page
---

## Project: NUS Module Planner

NUS ModulePlanner is a brownfield project based on AddressBook - Level 3, a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- **New Feature:** Added the ability to add and delete a Semester to a Plan.
    - What it does: This feature allows the user to add or delete a Semester to or from a Plan.
    - Justification: This feature is needed as the user will need to plan what modules to potentially take in each semester. These modules are also only available in certain semesters and as such having semester planning is necessary.
    - Highlights: Implementation of this feature required the addition of new `Model`, `Command`, `Parser` and `JsonSerializable` implementation for `Semester`.

- **Enhancement to existing features:** Morphed existing Person implementation into Plan.
  - What it does: This feature allows the user to add or delete a Plan within the application.
  - Justification: This feature is needed as this will enable to user to be able to manage and organise their different study plans accordingly.
  - Highlights: Implementation of this feature required the modification of all functionalities related to the AB3 `Person` model such as its variables and its JSON storage implementation.

- **Code contributed:** [RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=seaniy&tabRepo=AY2021S2-CS2103-W17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

- **Documentation:**
  - User Guide:
    - Added documentation for the feature `addp`/`deletep`.
    - Added documentation for the feature `adds`/`deletes`.
  - Developer Guide:
    - Added documentations, `Plan` object diagram and several sequence sequence diagrams for the `addp` function.
      - Architecture sequence diagram.
      - Constructor sequence diagram.
      - `toString()` method sequence diagram.
    - Added several use cases:
      - Add a plan.
      - Delete a plan.
      - Add a module to semester.

- **Project Management:**
  - Encouraged features to be done by the stipulated deadline.
  - Provided support and help to teammates who require assistace.
  - Managed issues on Github issue tracker to ensure that issues are being closed appropriately and in a timely manner.

- **Community:**
  - 2 peer PRs reviewed.

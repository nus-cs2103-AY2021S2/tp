---
layout: page
title: Nicole's Project Portfolio Page
---

## Project: ParentPal

ParentPal is a desktop app for managing your children’s contacts and their related appointments. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=nicoleang09&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=nicoleang09&tabRepo=AY2021S2-CS2103T-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Added storage for appointment list.
  * What it does: Allows the user to save appointments related to their children in the app across usages. Data is saved in a .json file.
  * Justification: It is essential for users to be able to save their appointments and retrieve them when re-opening the app. This allows users to view and keep track of their upcoming appointments.

* **New Feature**: Added logic for handling appointments.
  * What is does: Allows ParentPal to handle appointment-related commands.
  * Highlights: The logic was implemented such that the appointment list remains sorted by the date of the appointment at all times. Various sample data and sample data generators were also implemented in order to set up unit tests for the appointment logic (pull requests [\#130](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/130), [\#148](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/148)).
  
* **New Feature**: Added the ability to save and apply preferred sorting order of contact list across use sessions.
  * What it does: Allows the user to set their preferred sorting order of the contact list. Preferred sorting order is saved as a user preference. Ordering of contact list is maintained according to preferred sorting order regardless of the commands executed.
  * Justification: This feature makes organising the contact list much easier. Furthermore, users no longer have to enter the sort command after adding or editing contacts to view the newly sorted list. The list will automatically be sorted after a change that might affect the sorting is made.
  * Highlights: The implementation of this enhancement required an analysis of design alternatives. As the contact list was stored as a filtered list that could not be easily sorted, there was a need to consider the tradeoff between the user's convenience and the computation that had to be done by the app. To maintain a sorted contact list that would be easier for the user to view, more computation had to be done every time the contact list changes. An analysis of which commands would likely affect the order of the contact list had to be done to decide when to internally sort the list. 
  
* **Enhancements to existing features**: Extend usage of `help` command.
  * What it does: Previously, this command only displayed a link to the online user guide. Now, the command displays a summary of all available commands. Furthermore, a summary of information about specific commands can also be retrieved.
  * Justification: This feature allows the user to get useful information about the commands available without having to leave the application. Internet access is also no longer necessary to get help.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation considerations are documented [here](https://ay2021s2-cs2103t-w13-3.github.io/tp/DeveloperGuide.html#help-feature). Since the eventual implementation of the command reads and parses information from the user guide, a simple Markdown to plain text converter had to be written. Furthermore, CSS styling was added for the help window to match the main window of ParentPal.

* **Documentation**:
  * User Guide:
    * Added documentation for the `help` feature [\#59](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/59)
    * Added initial documentation for appointment commands (`addAppt`, `deleteAppt`, `findAppt`) [\#116](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/116)
    * Updated documentation for the `sort` feature [\#254](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/254)
    * Reorganised the command summary table [\#139](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/139)
    * Organised features into general, address book and appointment book commands [\#139](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/139)
  * Developer Guide:
    * Added implementation details of the `help` feature [\#98](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/98)
    * Updated implementation details of the `appointment` storage and logic components [\#269](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/269)
    * Identified non-functional requirements of ParentPal [\#20](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/20)
    * Updated glossary [\#23](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/23)

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#149](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/149), [\#113](https://github.com/AY2021S2-CS2103T-W13-3/tp/pull/113)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S2-CS2103T-W10-4/tp/issues/216), [2](https://github.com/AY2021S2-CS2103T-W10-4/tp/issues/212), [3](https://github.com/AY2021S2-CS2103T-W10-4/tp/issues/213), [4](https://github.com/AY2021S2-CS2103T-W10-4/tp/issues/214), [5](https://github.com/AY2021S2-CS2103T-W10-4/tp/issues/215), [6](https://github.com/AY2021S2-CS2103T-W10-4/tp/issues/211))

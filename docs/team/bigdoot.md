---
layout: page
title: Glendon Chua's Project Portfolio Page
---

## Project: DocBob

DocBob is a patient management application for Doctors to manage patient information, upcoming appointments and medical records. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.


Given below are my contributions to the project.

* **New Feature**: Added the ability to display an overview of all a patient's information.
  * What it does: allows the user to view all information regarding a patient, including personal details, appointments and medical records, in a panel.
  * Justification: This feature helps the user get a quick overview of everything about a patient, which allows them to then follow up by editing the patient's information or viewing a medical record.
  * Highlights: This enhancement affects existing patient data fields and patient information to be added in future. This enhancement required careful analysis on how to set up the data to show up on the GUI. The implementation was not as simple as it required manipulation of data to the Javafx panels which I am not as familiar with.
  * Credits: Teammate Nicholas created and refined the GUI with Javafx.

* **New Feature**: Added the listappt command that allows the user to list out all upcoming appointments.
  * What it does: retrieve all upcoming appointments and sort by date and time. It also tells the user how many appointments they have.
  * Justification: This feature helps the user easily find out what upcoming appointments they have scheduled.
  * Highlights: This enhancement required thought on how to retrieve the data as well as how to link it to the GUI. Making it appear on the same panel as the patient overview was not straightforward as the functions displayed the data in different formats and required a different amount of space.

* **Testing and error handling**: Added JUnit test cases to test code and bring up code coverage. Updated the app to handle invalid inputs with error messages. Updated the app GUI display certain text according to various inputs from the user. E.g. After the user deletes the last patient in the app, the GUI displays a message to let the user know that they have no more patients left in DocBob, and that they should start adding more patients.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=BigDoot&tabRepo=AY2021S2-CS2103T-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed release `v1.3` on GitHub
  * Maintained issue tracker
  * Maintained milestones
  * Set up google docs for project planning

* **Enhancements to existing features**:
  * Updated the GUI theme to DocBob and changed icon (Pull request [\#23](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/23))
  * Modified existing features to work synonymously with `view` function and other new functions. For example, `find` now displays the overview of the first matching patient found(if any). (Pull request [\#92](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/92), [\#44](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/44))
  * Updated `help` function window to display command information

* **Documentation**:
  * User Guide:
    * Added documentation for the features `view`, `listappt`, `mrec`, `vrec`
    * Modified some existing documentation to fit theme
    * Kept command summary up to date
    * Proofreaded the guide to ensure proper grammar
  * Developer Guide:
    * Added implementation details of the `view` feature
    * Added UML diagrams for the `view` feature
    * Updated and refined user stories

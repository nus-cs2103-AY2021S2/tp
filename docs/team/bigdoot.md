---
layout: page
title: Glendon Chua's Project Portfolio Page
---

## Project: DocBob

DocBob is a patient management application for Doctors to manage patient information, upcoming appointments and medical records. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.


Given below are my contributions to the project.

* **New Feature**: Added the ability to display an overview of all a patient's information.
  * What it does: allows the user to view all information regarding a patient, including personal details, appointments and medical records.
  * Justification: This feature helps the user get a quick overview of everything about a patient, which allows them to then follow up by editing the patient's information or view a medical record.
  * Highlights: This enhancement affects existing patient data fields and patient information to be added in future. This enhancement required careful analysis on how to set up the data to show up on the GUI. The implementation was not as simple as it required manipulation of data to the Javafx panels which I am not as familiar with.
  * Credits: Teammate Nicholas created and refined the GUI with Javafx.

* **New Feature**: Added the listappt command that allows the user to list out all upcoming appointments.
  * What it does: retrieve all upcoming appointments and sort by date and time. It also tells the user how many appointments they have.
  * Justification: This feature helps the user to easily find out have appointments they have scheduled.
  * Highlights: This enhancement required thought on how to retrieve the data as well as how to link it to the GUI. Making it appear on the same panel as the patient overview was not straightforward as the functions displayed in different formats and required a different amount of space.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=BigDoot&tabRepo=AY2021S2-CS2103T-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.3` on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_

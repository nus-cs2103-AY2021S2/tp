---
layout: page
title: Vivegan Kanakaraja's Project Portfolio Page
---

## Project: DocBob

DocBob is a patient management application for Doctors to manage patient information, upcoming appointments and medical records. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is 
written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to archive/unarchive a patient's information.
* **New Feature**: Added the ability to view all archived patients.
  * What it does: Allows the user to archive a patient's information. Archived patients can be unarchived using the unarchive command. All archived patients could be seen using the archivelist command.
  * Justification for archive/unarchive commands: This feature improves the product significantly because a user (the doctor) can have many patients cluttering the main list, most of whom do not visit the clinic after being cured of illness. Therefore, this provides a way
  * for the user to reduce clutter on the main list and only keep the active patients on the main list. Inactive patients' information will exist in the archives till a time when it is needed again (as in when the patients return when they get ill again).
  * Justification for archivelist command: This feature complements the first feature as now, there is a command to view all the archived patients. This could be seen as a change of mode from the main mode to the archive mode. List command switches DocBob to the
  * main mode and archivelist command changes it to the archive mode.
  * Highlights: These enhancements affects existing commands as only the find and delete commands were customized to work in archive mode and the other commands were blocked from working in archive mode. Analysis of design alternatives was needed as there was 
  * another possible implementation considered as mentioned in the Developer's Guide. The current implementation was challenging too as it required changes to existing commands to support or not support archived patients and also required changes to the UniquePersonList
  * containing patients and the information contained by a patient. The feature was fully tested before being deployed with many test cases to prevent integration bugs.

* **New Feature**: Added the fields of DateOfBirth, Gender, Height and Weight.
* * What it does: Allows the user to keep track of very important additional fields of a patient on top of the existing fields.
* * Justification: The user (a doctor) usually keeps track of all these medical fields of a patient as well on top of their contact information.
* * Highlights: Implementing these fields was challenging as it required me to learn basic RegEx which I was not familiar with at all. This also required big changes in all parts of the program where the attributes of a patient were involved. Thorough test code was added for all fields
* to prevent invalid arguments and test cases involving patients' details were updated to include the new fields.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=vivegank&tabRepo=AY2021S2-CS2103T-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Created team Github repository and added all teammates.
  * Maintained issue tracker
  * Maintained milestones
  * 
  * 

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

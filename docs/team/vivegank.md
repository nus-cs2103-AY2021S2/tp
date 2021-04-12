---
layout: page
title: Vivegan Kanakaraja's Project Portfolio Page
---

## Project: DocBob

DocBob is a patient management application for Doctors to manage patient information, upcoming appointments and medical records. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to archive/unarchive a patient's information.
* **New Feature**: Added the ability to view all archived patients.
  * What it does: Allows the user to archive a patient's information. Archived patients can be unarchived using the unarchive command. All archived patients could be seen using the archivelist command.
  * Justification for archive/unarchive commands: This feature improves the product significantly because a user (the doctor) can have many patients cluttering the main list, most of whom do not visit the clinic after being cured of illness. Therefore, this provides a way for the user to reduce clutter on the main list and only keep the active patients on the main list. Inactive patients' information will exist in the archives till a time when it is needed again (as in when the patients return when they get ill again).
  * Justification for archivelist command: This feature complements the first feature as now, there is a command to view all the archived patients. This could be seen as a change of mode from the main mode to the archive mode. List command switches DocBob to the main mode and archivelist command changes it to the archive mode.
  * Highlights: These enhancements affect existing commands as only the find and delete commands were customized to work in archive mode and the other commands were blocked from working in archive mode. Analysis of design alternatives was needed as there was another possible implementation considered as mentioned in the Developer's Guide. The current implementation was challenging too as it required changes to existing commands to support or not support archived patients and also required changes to the UniquePersonList containing patients and the information contained by a patient to show whether or not they are archived.

* **New Feature**: Added the fields of DateOfBirth, Gender, Height and Weight.
  * What it does: Allows the user to keep track of very important additional fields of a patient on top of the existing fields.
  * Justification: The user (a doctor) usually keeps track of all these medical fields of a patient as well on top of their contact information.
  * Highlights: Implementing these fields was challenging as it required me to learn basic RegEx which I was not familiar with at all. This also required big changes in all parts of the program where the attributes of a patient were involved.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=vivegank&tabRepo=AY2021S2-CS2103T-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Created team Github repository and added all teammates.
  * Maintained issue tracker
  * Maintained milestones

* **Testing**:
  * Wrote JUnit tests related to archive, unarchive and archivelist features to increase code coverage. (Pull request [\#41](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/41))
  * Wrote new JUnit tests for Date of Birth, Gender, Height, Weight, and updated all existing JUnit tests related to patient attributes and their usage in other parts of the codebase to increase code coverage.(Pull request [\#96](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/96)).

* **Enhancements to existing features**:
  * Modified existing commands to make some of them work in archive mode and block some of them from working in archive mode. (Pull request [\#41](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/41/files)).
  * Updated the information contained by sample patients to be shown on first startup of product. (Pull request [\#97](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/97)).

* **Documentation**:
  * Created Mockup UI for product.
  * Updated UI.png in every iteration of the product.
  * Maintained README.md that appears whenever project repo is opened.
  * User Guide:
    * Added documentation for the features `archive`, `unarchive` and `archivelist` (Pull request [\#43](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/43)).
    * Added Layout section (Image credited to teammate Nicholas)
    * Tweaked existing documentation of feature `add` to include new fields and show the expected format of all fields (Pull request [\#98](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/98)).
  * Developer Guide:
    * Added implementation details of the `archive`, `unarchive` and `archivelist` features. (Pull request [\#100](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/100)).
    * Added UML diagrams for `archive` feature.
    * Updated UML diagram for Model component to include the new fields added to Patient.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#91](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/91).
  * Helped teammate write JUnit tests for AddAppointmentCommand and related classes (Pull request [\#26](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/26)).
  * Helped teammate write JUnit tests for BloodType field (Pull request [\#96](https://github.com/AY2021S2-CS2103T-W12-1/tp/pull/96)).

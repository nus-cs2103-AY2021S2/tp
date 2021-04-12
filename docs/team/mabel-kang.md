---
layout: page
title: Kang Min Hui, Mabel's Project Portfolio Page
---

## Project: TutorsPet

TutorsPet is a **desktop app designed for private tutors in Singapore to manage students’ information, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). TutorsPet helps improve the efficiency and effectiveness of student management by categorizing relevant contact information and keeping track of both lesson schedules and important dates.

Given below are my contributions to the project.

* **New Feature**: Added the ability to **add**, **delete** and **list** important dates. (Pull request [\#97](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/97))
    * What it does: 
      * **add**: allows the user to add an important date consisting of description and details into TutorsPet.
      * **delete**: allows the user to delete an important date.
      * **list**: allows the user to view a list of important dates, sorted according to the details of the dates.
    * Justification: This feature improves the usefulness of the product significantly because a user can have dates they need to take note of for each student, hence the app should provide a way to store and manage such dates.
    * Highlights: The implementation of this feature resulted in the need to examine the design of original AddressBook structure as it required the set up of a separate storage from the original storage meant for the student contacts.


* **New Feature**: Added a lesson field as part of a student's contact. (Pull request [\#59](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/59))
    * What it does: allows the user to store a student's lesson day and time as part of the contact.
    * Justification: This feature is important because a user can have many students and hence lesson timings to take note of. The app should provide a way to record down such details for each student and allow the user to easily identify the lesson details of a student.
    * Highlights: The addition of the lesson field has brought about the possibility of other significant features such as having an overview of all lessons for the week.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/#breakdown=true&search=mabel-kang)


* **Project management**:
    * Helped with modifying existing AddressBook comments to fit TutorsPet's context. (Pull request [\#46](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/46))
    * Managed and added milestones, labels and assignees to github issues and pull requests for team, where necessary. 
    * Updated index.md so that the website displays information relevant to TutorsPet instead of AddressBook. (Pull request [\#238](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/238))


* **Enhancements to existing features**:
    * Updated the list displaying all the contacts so that it updates in real time when there are changes in data caused by commands such as `add`, `edit`, `delete` and `clear`. (Pull request [\#108](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/108))
    * Updated the error messages thrown by commands taking in an index as a parameter, for better clarity on the type of error present. (Pull requests [\#207](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/207), [\#215](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/215))


* **Enhancements to other new features**:
    * Updated the search command with more detailed error messages which will be thrown accordingly based on the specified parameter used by the user in the search command. (Pull request [\#225](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/225))
    

* **Documentation**:
    * User Guide:
        * Added documentation for the features `add-date`, `delete-date`, `list-date` and `delete` (Pull request [\#97](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/97))
        * Edited documentation for the features `add` and `edit`, to include details regarding the lesson field. ((Pull request [\#59](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/59)))
        
    * Developer Guide:
        * Added implementation details and UML diagrams for the `add-date`, `delete-date`, `list-date` and `delete` feature. (Pull requests [\#69](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/69), [\#236](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/236))
        * Added use cases, test cases and non-functional requirements. (Pull requests [\#21](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/21), [\#323](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/323))


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#93](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/93)
    * Reported bugs and suggestions for other teams (example: [bug reports](https://github.com/mabel-kang/ped/issues))

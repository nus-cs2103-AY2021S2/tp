---
layout: page
title: Png Zheng Jie, Sebastian's Project Portfolio Page
---

## Project: App-Ointment

App-Ointment is a patient appointment management software adapted from AddressBook - Level 3, which is a desktop 
address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and 
it has a GUI created with JavaFX. It is written in Java, and has about 19 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete appointments by their indices.
    * What it does: Allows the user to delete appointments from the appointment schedule by their indices.
    * Justification: 
        * Fulfills the fundamental purpose of App-Ointment, which is to manage appointments of patients.
        * With the use of indices, users can easily delete appointments in the appointment schedule.


* **New Feature**: Added the ability to force delete appointments through `delete-patient` and `delete-doctor` commands.
    * What it does: When a patient/ doctor is force deleted, all existing appointments of a patient/ doctor in the 
        appointment schedule will be deleted too.
    * Justification: 
        * Allows the user to conveniently mass delete all appointments associated with a patient/ doctor with a single 
            command, rather than having to delete each appointment manually before deleting the patient/ doctor.
        * Prevents any possible bugs/ errors in the appointments when a referenced patient/ doctor is deleted. E.g. 
            presence of `null` or code in the user interface.


* **Code contributed**: [RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=pngsebastian&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&since=2021-02-19&checkedFileTypes=docs~functional-code~test-code~other)


* **Project management**:
    * Regularly update PRs with the relevant tags and milestones for the project.


* **Documentation**:
     * User Guide:
        * Added the documentation for the delete commands (`delete-appt`, `delete-doctor`, `delete-patient`)
        * Updated example cases for all commands
        * Resolved multiple bugs raised during the pe dry run [#146](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/146)
     * Developer Guide:
        * Added use cases for delete commands 
            [#70](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/70) 
        * Updated uml diagrams:
            * [Architecture Sequence Diagram](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/docs/diagrams/ArchitectureSequenceDiagram.puml) 
            * [Delete Sequence Diagram](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/docs/diagrams/DeleteSequenceDiagram.puml)
            * [Logic Class Diagram](https://github.com/AY2021S2-CS2103-W17-2/tp/blob/master/docs/diagrams/LogicClassDiagram.puml)
        * Updated logic section and `delete-patient` test case in the appendix section 
            [#155](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/155)


* **Community**:
    * PRs reviewed (with non-trivial review comments):
        [#49](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/49)
        [#153](https://github.com/AY2021S2-CS2103-W17-2/tp/pull/153)
    * Reported [bugs and suggestions](https://github.com/pngsebastian/ped/issues) for other teams in the class 

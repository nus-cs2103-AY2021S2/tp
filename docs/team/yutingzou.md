---
layout: page
title: Zou Yuting's Project Portfolio Page
---

Hi I'm Yuting, a year 4 Business Analytics student. I like to cook and explore nice food in every city I visit :)

## Project: Tutor Tracker

Given below are my contributions to the project.

* **New Feature**: Added the ability to view an individual tutor.
  * What it does: allows the user to view an individual tutor with a specified index in the Tutor Tracker.
  * Justification: This feature improves the product because a user can select to view a particular
  tutor of interest, including details such as a tutor's profile and cost of different subjects.
  * Highlights: This feature is an extension from the existing feature - list tutors. It is affected by other commands
  that change the student panel in GUI because this feature depends on the index of a tutor in the list at the time 
    when the command is entered.
  * Links: [#86](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/86) </br>

* **New Feature**: Added the ability to list all tutors.
  * What it does: allows the user to view a full list of all existing tutors.
  * Justification: This feature improves the product because a user can select to view a particular
    tutor of interest, including details such as a tutor's profile and cost of different subjects.
  * Highlights: This feature is an extension from the existing feature - list tutors. It is affected by other commands
    that change the student panel in GUI because this feature depends on the index of a tutor in the list at the time
    when the command is entered.
  * Links: [#83](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/83) </br>

* **New Feature**: Implemented Grade and GradeList class.
  * What it does: allows the user to create new Grade objects and store them in the GradeList in Tutor Tracker.
  * Justification: this feature improves the product significantly because it extends the usability of the product by allowing user to store grades in Tutor Tracker.
  * Highlights: This enhancement affects all grade-related commands to be added in the future.
  * Links: [#126](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/126)
  * Credits: Codes are adapted from `Person` in AddressBook 3.
    
* **New Feature**: Added the ability to add a grade record.
  * What it does: allows the user to add a new grade record, including the subject, graded item and grade to the GradeBook.
  * Justification: this feature improves the product significantly because it extends the usability of the product. 
    It provides the user a convenient way to add their grade records to the digital GradeBook for reference when choosing tutors.
  * Highlights: This enhancement affects all grade-related commands to be added in the future.
    The implementation too was challenging as it required changes to the existing model, logic, storage and ui.
  * Links: [#144](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/144) </br>
  
* **New Feature**: Added the ability to delete an existing grade record.
  * What it does: allows the user to delete a grade record that is outdated.
  * Justification: This feature improves the product significantly because the grade records in the GradeBook can be outdated when the user is promoted.
    the app should provide a convenient way to keep the digital GradeBook updated.
  * Highlights: This enhancement affects all grade-related commands to be added in the future.
      The implementation too was challenging as it required changes to the existing model, logic, storage and ui. </br>
  * Links: [#144](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/144) </br>
  
* **New Feature**: Added the ability to edit a grade record.
  * What it does: allows the user to edit an existing grade record with a specified index.
  * Justification: This feature improves the product significantly because the user can make mistakes when adding a new grade
    and the app should provide a convenient way to rectify them.
  * Highlights: The implementation was challenging as it required changes to the existing model, logic, storage and ui.
  * Links: [#144](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/144)  </br>

* **New Feature**: Added the ability to list all existing grade(s).
  * What it does: allows the user to view a full list of all existing grade records.
  * Justification: This feature allows user to quickly access the list of all existing grade(s).
  * Highlights: It mainly supports the GUI in displaying the list of grades of the user.
  * Credits: Codes are adapted from `ListCommand` in AddressBook 3.
  * Links: [#144](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/144)  </br>
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=yutingzou&tabRepo=AY2021S2-CS2103-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false) </br>

* **Project management**:
  * Contributed in refactoring the existing code base
  * Maintained abstraction of the code: created TagList ([Pull request #65](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/65)), SubjectList ([#80](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/80)) 
    and GradeList ([#126](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/126))
  * Documented the target user profile [#7](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/7)
  * Maintain the defensiveness of the code: enable assertions in the project's gradle file. </br>
  
* **Enhancements to existing features**:
  * Refactor ListCommand to add `list_tutor` feature [#83](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/83)
  * Update the GUI to display subject list in each `TutorCard` [#91](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/91)
  * Update the GUI to display the newly added `GradeBook` [#144](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/144) </br>
  
* **Documentation**:
    * User Guide:
      * Added documentation for the feature `add_appointment` [#7](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/7)
      * Added documentation for the features `add_grade`, 
        `delete_grade`, `list_grade`, `edit_grade` [#188](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/188) </br>
    * Developer Guide:
      * Added glossary [#8](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/8)
      * Added use case of the `add_appointment` feature [#8](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/8)
      * Added use cases of the `view_tutor` feature [#84](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/84/)
      * Added use cases of the `add_grade`, `delete_grade`, `list_grade`, `edit_grade` features [#307](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/307)
      * Added implementation details of the Grade Book feature [#329](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/329)
      * Added class diagram of Grade [#333](https://github.com/AY2021S2-CS2103-T14-3/tp/pull/333) </br>

* **Community**:
  * Constantly approved PRs of all group members.
  * Reported bugs and suggestions for other teams in the class.
  
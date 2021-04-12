---
layout: page
title: Maurice's Project Portfolio Page
---

## Project: MeetBuddy Application

MeetBuddy is your handy desktop meeting and contacts management app,
 optimized for NUS students to manage their social connections and daily meetings who prefer to work with a 
 Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI)! 

I was in charge of Overhauling the GUI and creating functionality for users to view
their meeting schedules in the form of a timetable. I also made GUI enhancements and managed the 
storage component.
 The GUI of MeetBuddy was created with JavaFX and written in Java,
 and I have contributed  about 4+ kLoC of code across a span of 6 weeks.

Given below are my contributions to the project.

* **Minor Feature**: Added the ability to sync contacts profile picture from Gravatar account. ([\#53()], Implement Caching improvements [\#184()])
  * What it does: If a contact has an email aassociated with a Gravatar account, 
  the application gets and displays the contact's Gravatar profile picture automatically as a profile picture in MeetBuddy upon adding said contact. 
  * Justification: This feature improves the product by making it easier to remember contacts as long as they have Gravatar accounts.
  
* **Major Feature**: Add a timetable component [\#102()] [\#99()] [\#90()]
     *What it does : with a setTimetable Command allowing users to switch between different 
starting dates of the timetable via CLI. Meetings are synced to the timetable when added/ edited or deleted.
    * Justification: This feature improves the product by making it easier to peruse their weekly schedules, 
or find free times to schedule events.


* **Code contributed! Do check out my code!**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=Maurice2n97&tabRepo=AY2021S2-CS2103-T16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed release `v1.3` on GitHub
  * Managed the storage component to ensure neatness and code quality [\#57()]
  * Managed the GUI component to ensure neatness and code quality [\#106()]
  * Managed integration of Team member Yuheng's features with storage. [\#187()]

* **Enhancements to existing features**:
  * Overhauled the existing GUI (Pull requests ) [\#45()][\#97()]
  * Wrote additional tests for new features to maintain above 90% coverage for storage component.
  * Implemented deletem command to delete meetings [\#54()]
  * Implemented APIs within meeting to handle clashing meeting checks, enhanced add meeting command.[\#75()]
  

* **Documentation**:
  * User Guide:
    * Added documentation for the features `Profile Picture` and `setTimetable` [\#113()]
  * Developer Guide:
    * Added implementation details of the `setTimetable` feature. [\#203()]
    * Updated Ui class Diagram [\#203()]
    * Updated Storage class Diagram.[\#203()]
    * Added user stories to DG [\#204]()



* _{you can add/remove categories in the list above}_

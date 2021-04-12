---
layout: page
title: Tan Wan Ling, Andrea's Project Portfolio Page
---

## Project: TutorPets

TutorsPet is a desktop app designed for private tutors in Singapore to manage students’ information, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). TutorsPet helps improve the efficiency and effectiveness of student management by categorizing relevant contact information and keeping track of both lesson schedules and important dates.

Given below are my contributions to the project.

* **New Feature**: Added the ability to Sort the list panel by name, school, subject and lesson. Pull Requests: [\#81](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/81), [\#102](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/102)
  * What it does: Allows the user to sort their contact list in the list panel according to their preferred criteria
  * Justification: This feature greatly improves readability of the product by making it easier for users to manage their list of students by organising it, so students of the same or similar details will be grouped together in the list panel.
  * Highlights: The implementation of this feature required a deep understanding of observable lists and their implementations in the Model Manager, because it requires both filtering and sorting of the persons list. 
 
* **New Feature**: Added the ability to increase and decrease the levels of all the students at once. Pull Requests: [\#124](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/124), [\#131](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/131)
  * What it does: allows the user to increase or decrease all their students levels by one, unless the students are excluded
  * Justification: This feature allows the mass updating of the students' levels at the start of the school year, and the ability to undo the update if it was accidental.
  * Highlights: This implementation requires the transformation of the observable list in Model Manager using unary functions in order to exclude some students but upgrade the rest. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=andrea-twl&tabRepo=AY2021S2-CS2103T-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.3` (1 release) on GitHub

* **Enhancements to existing features**:
  * Add new Level field for Student. (Pull request: [\#121](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/121)
  * Add sorting functions for schedule window so lessons are displayed in chronological order. (Pull request: [\#120](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/120))
  * Added invalid index error message to specify when the invalid command is due to wrong format in index. (Pull request: [\#217](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/217))
  * Enhanced appearance of details GUI and added the Level field to the details panel. (Pull requests: [\#222](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/222), [\#230](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/230), [\#243](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/243))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `sort`, `levelup` and `leveldown`
    * Added field format summary table. (Pull request: [\#211](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/211))
    * Added annotated diagram of TutorsPet UI for quick start section (Pull request: [\#374](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/374))
* Developer Guide:
    * Added implementation details of `sort`, `levelup` and `leveldown`  features. [/#282](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/282)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#60](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/61#partial-pull-merging)
  * Reported and logged bugs for team ([bug issues](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues?q=is%3Aissue+label%3Abug+author%3Aandrea-twl+is%3Aclosed))

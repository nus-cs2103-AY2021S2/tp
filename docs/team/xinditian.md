---
layout: page
title: XindiTian's Project Portfolio Page
---

## Project: Dictionote

**Dictionote** is a desktop application that helps CS2103T students in finding information about the module's materials and writing notes about them. It is optimised for Command Line Interface (CLI) users so that searching and writing operations can be done quickly by typing in commands.

Given below are my contributions to the project.

* **New Model**: Added a new Model for the Dictionary Contents.   
  (Pull request [\#70](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/70))

* **New Model**: Added a new Model for the Dictionary Definitions.   
  (Pull request [\#95](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/95))

* **New Feature**: Added the ability to find content in the Dictionary.
    * What it does: allows the user to find all the contents in the dictionary section of the Dictionote which will allow the user to use for reference easily.
    * Justification: This feature improves the product significantly because a user can efficiently search for contents they need to save time, which satisfies their concern of wasting time having to find data in the textbook. The contents are taken from the CS2103/T textbook.  
      (Pull request [\#70](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/70)), (Pull request [\#86](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/86))

* **New Feature**: Added the ability to find definitions in the Dictionary.
  * What it does: allows the user to find all the definitions listed in the dictionary section of the Dictionote which will allow the user to refer to when they need to.
  * Justification: This feature improves the product significantly because a user can efficiently search for defintions they need to save time, which satisfies their concern of wasting time having to find data in the textbook. All definitions are extracted from the CS2103/T textbook.  
    (Pull request [\#95](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/95))
  
* **New Feature**: Added ability to show specific content in the Dictionary.
  * What it does: display the contents/definitions at the specified index that is shown on the Dictionary Panel. The feature returns the corresponding content at the index and displays it onto the Dictionary content panel which enables the user to view the full content/definitions.
  * Justifications: This feature improves the product significantly because a user can view the content/definition that they want in detail and easily with a simple command, which satisfies their concern of wasting time digging for contents.
  * Highlights: The index and sequence of contents/definitions depends on the current list shown in the dictionary panel.

* **New Feature**: Added ability to list all the contents in the Dictionary.
  * What it does: displays the extensive list of contents in the Dictionary onto the Dictionary panel.
  * Justifications: This feature improves the product significantly as there are many features in the application (e.g. findcontent, finddef) that will trim the list to fit the conditions given by the user,  or toggling between the contents list, and the definitions list, since they share the same display panel. This means that the list of contents displayed is always changing in accordance to the user demands. So, the users will need a way to view the whole list of contents again, and this feature can be used to achieve that.  
    (Pull request [\#139](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/139))

* **New Feature**: Added ability to list all the definitions in the Dictionary.
  * What it does: displays the extensive list of definitions in the Dictionary onto the Dictionary panel.
  * Justifications: This feature improves the product significantly as there are many features in the application (e.g. findcontent, finddef) that will trim the list to fit the conditions given by the user, or toggling between the contents list, and the definitions list, since they share the same display panel. This means that the list of definitions displayed is always changing in accordance to the user demands. So, the users will need a way to view the whole list of definitions again, and this feature can be used to achieve that.
    (Pull request [\#139](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/139))

* **New Feature**: Added ability to copy the contents from the dictionary to a new note.
  * What it does: copies the content specified at the index provided by the user from the list of contents in the dictionary and makes a new note with the corresponding contents.  
  * Justifications: This feature improves the product significantly as it allows users to copy the wanted content from the dictionary directly into a note with a simple command. This allows them to satisfy their want of being able to take down important content easily from the textbook without having the going through the effort of copy-pasting into an external app.  
    (Pull request [\#165](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/165))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=XindiTian&tabRepo=AY2021S2-CS2103T-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Attended the weekly group meetings.

* **Enhancements to existing features**:
    * Updated the 'List Command' command to include features of the dictionary (Pull requests [\#168](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/168))
    * Updated the dictionary feature by changing the header from 'week' to 'title' to make the dictionary more realistic (Pull requests [\#248](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/248))
    * Wrote additional tests for existing features to increase coverage (Pull requests [\#165](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/165))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `findcontent` and `finddef` [\#99](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/99)
        * Added documentation for the features `listcontent`, `listddef` and `copytonote` [\#248](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/248)
        * Did cosmetic tweaks to existing documentation of features by editing or adding images `findcontent`, `finddef`, `listcontent`, `listdef` and `copytonote`: [\#248](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/248), [\#251](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/251), [\#264](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/264)
    * Developer Guide:
        * Added implementation details of the `finddef` feature.
        * Added 'setting up, getting started' section in the Developer's Guide. [\#30](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/30)
        * Added Introduction to Dictionote in the Developer's Guide. [\#135](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/135)  
        * Edited Table of Contents and User Stories. [\#136](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/136)
        * Added Use Cases for Dictionary Features. [\#](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#2](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/2), [\#3](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/3), [\#5](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/5),
      [\#6](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/6), [\#28](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/28), [\#29](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/29), [\#34](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/34),
      [\#56](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/56), [\#59](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/59), [\#60](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/60), [\#62](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/62),
      [\#101](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/101), [\#102](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/102), [\#133](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/133), [\#134](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/134),
      [\#139](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/139), [\#167](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/167), [\#172](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/173), [\#262](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/262), [\#266](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/266)
      * Reported bugs and suggestions for other teams in the class: [1](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues/178), [2](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues/179), [3](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues/180),
        [4](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues/181), [5](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues/183), [6](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues/182), [7](https://github.com/AY2021S2-CS2103T-T11-3/tp/issues/184)

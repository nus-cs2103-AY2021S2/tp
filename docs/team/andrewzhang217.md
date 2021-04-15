---
layout: page
title: Zesheng's Project Portfolio Page
---

## Project: SmartLib

SmartLib is a desktop app for managing private book loaning services owning less than 10,000 books,
optimized for use via a Command Line Interface (CLI),
while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=andrewzhang&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=Andrewzhang217&tabRepo=AY2021S2-CS2103T-W13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Added a `findbook` command that allows the user to find all the books in SmartLib whose information 
    (Title, Author, Publisher, ISBN, Genre) contains any of the given keywords.
  * What it does: Allows the user to find books via the keywords.
  * Justification: This feature is crucial for the product, as users are very likely to have the need to search
    for books with its information, as manually scrolling through the book list would be very time consuming.
  * Credits: _AB3's prior implementation of `find` guided the implementation of the `findbook` command._

* **New Feature**: Added a `listrecord` command that allows the user to list all the current records related to borrowing and returning books in SmartLib.
  * What it does: Allows the user to list all the records in SmartLib.
  * Justification: This feature is essential for enabling the user to retrieve the original record list,
    after performing the `findrecord` command.
  * Highlights: This enhancement is useful when the record list is updated after `findrecord`, so that the user can retrieve the original
    list of all records in SmartLib.
  * Credits: _AB3's prior implementation of `list` guided the implementation of the `listrecord` command._


* **Project management**:
  * Created our team organisation and set up our team repository on GitHub
  * Added a UI mockup of our intended final product to guide our development. 

* **Enhancements to existing features**:
  * Fix bugs that book with same name and different ISBN can be added(Pull request [\#299](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/299))
  * Wrote additional tests for existing features to increase coverage (Pull requests[\#265](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/265))

* **Contributions to the User Guide**:
  * Added documentation for the features `listbook` and `findbook` (Pull request [\#88](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/88))

* **Contributions to the Developer Guide**:
  * Added use cases (pull request [\#91](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/91))
  * Added implementation details of the 'findbook' feature (pull request [\#153](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/153))

* **Contributions to UI**: Improved the UI
  * Added bookcard and recordcard in GUI (Pull requests
   [\#119](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/119),
   [\#164](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/164))
  * Changed the GUI style and added labels (Pull request [\#189](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/189))
  
* **Community**:
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/1#issuecomment-761852294))
  * Reported bugs and suggestions for other teams in the class (examples: [PE-D](https://github.com/Andrewzhang217/ped/issues))




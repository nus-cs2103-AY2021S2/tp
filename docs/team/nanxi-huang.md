---
layout: page
title: Nanxi's Project Portfolio Page
---

## Project: SmartLib

SmartLib is a desktop app for managing private book loaning services owning less than 10,000 books, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SmartLib can systematically manage your books and borrowers’ information in a more efficient manner than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add a reader into the SmartLib registered reader base.
    * What it does: allows the user to add a reader and stores his/her personal information such as email, phone number, address etc into the SmartLib's registered reader base.
    * Justification: This feature is essential for the product because a user can then provide book rental services to these readers and keep track of borrow and return of the books.
    * Highlights: This enhancement is very useful for the user's customer service and business activities.
      For example, the user can tag a reader as vip and provide privileged services to this reader.
    * Credits: _AB3's prior implementation of `add` guided the implementation of the `addreader` command._
  
* **New Feature**: Added the ability to delete a reader from the SmartLib's registered reader base.
    * What it does: Allows the user to delete a reader and all his/her stored information from the SmartLib's registered reader base, by the reader's index on SmartLib's displayed reader list.
    * Justification: This feature is essential for the product because a user can then delete a reader if he/she no longer wish to have any services from the user.
    In addition, if the user make any mistake during `addreader`, he/she can rectify by deleting and then adding the correct information again.
    * Highlights: This enhancement has also taken care of the situation when a reader is deleted before he/she returns the borrowed books.
    Hence, the user can only delete readers who have no unreturned books.
    * Credits: _AB3's prior implementation of `delete` guided the implementation of the `deletereader` command._


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=nanxi-huang&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=Nanxi-Huang&tabRepo=AY2021S2-CS2103T-W13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Project management**:
    * Helped manage releases `v1.1` - `v1.4` (5 milestones) on GitHub
    * In charge of code quality and documentation comments

* **Enhancements to existing features**:
    * Refactor the borrow command to accept book barcode and reader name as parameters to prevent mixing of books of the same title (Pull request 
      [\#293](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/293)).
    * Refactor the return command to accept book barcode only to increase efficiency and prevent mixing of books of the same title (Pull request 
      [\#177](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/177))  
    * Wrote additional tests for `Book` class to increase coverage (Pull requests 
      [\#141](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/141), 
      [\#146](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/146)).
    * Wrote additional tests for `findrecord` and `findbook` features to increase coverage (Pull request
      [\#326](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/326)).  
    * Improve the `findbook` feature to enable searching books through book title, publisher, author, isbn, or genre (Pull request 
      [\#158](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/158)())
    * Improve the code quality by adopting SLAP principle and Single Responsibility Principle (Pull request
      [\#313](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/313))  

* **Documentation**:
    * User Guide:
        * Added documentation for the features `addreader`, `deletereader` and `findbook` (Pull requests 
          [\#74](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/74), 
          [\#323](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/323)).
    * Developer Guide:
        * Added implementation details of the `return` feature (Pull request 
          [\#198](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/198)).
        * Added implementation details of the `addreader` feature (Pull request 
          [\#154](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/154),
          [\#73](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/73),
          [\#75](https://github.com/AY2021S2-CS2103T-W13-2/tp/pull/75))

* **Community**:
    * Reported bugs and suggestions for other teams in the class (examples: [PE-D](https://github.com/Nanxi-Huang/ped/issues))
  



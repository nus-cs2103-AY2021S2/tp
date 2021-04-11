---
layout: page
title: Alzahrani Riyadh Hamdan S's Project Portfolio Page
---

## Project: Dictionote

**Dictionote** is a desktop application that helps CS2103T students in finding information about the module's materials and writing notes about them. It is optimised for Command Line Interface (CLI) users so that searching and writing operations can be done quickly by typing in commands.

Given below are my contributions to the project.

* **New Feature**: Added the ability to send emails to contacts.
  * What it does: allows the user to send an email to any of their contacts stored in the contacts list. The body of the email can contain the contents of any note present in the notes list.
  * Justification: This feature provides a method to communicate with the stored contacts by utilizing the existing AddressBook feature of storing email addresses. Not only so, but the ability to include stored notes in emails simplifies the process of sharing them with others.
  * Highlights: Due to the use of `mailto` links in the implemtnation of this feature, it would rely on the user's operating system (OS) default mail client to handle the email composition and sending requests, which might need to be installed if not present.
  * Credits: [TorstenH. and alexey_s from CodeProject for the Java URL invocation code](https://www.codeproject.com/questions/398241/how-to-open-url-in-java). Note that the Java method used in Dictionote's source code was not the one specified in the aforementioned posts, but a similar one from the same Java API. 
  
* **New Feature**: Added the ability to sort the contacts list by most frequently-contacted.
  * What it does: sorts the contacts list, in descending order, based on the number of times the user had attempted to send an email to each contact.
  * Justification: This feature provides the user with an easy way to identify their most-contacted contacts, as well as positioning them on top of the contacts list for quicker access.
  * Highlights: In order to implement this feature, a custom helper attribute called `FrequencyCounter` was added for each contact, which starts from 0 and increments by 1 every time an `emailcontact` command targeting them executes. This allows for contacts found in previous versions of Dictionote to be easily imported to the new version, since this feature does not modify their exisiting attributes.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=w13&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=riyadh-h&tabRepo=AY2021S2-CS2103T-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.3b` (1 release) on GitHub
  * Managed weekly Zoom meetings during Weeks 4 to 12.

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

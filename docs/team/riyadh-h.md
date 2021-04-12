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
  * Managed weekly Zoom meetings during Weeks 4 to 13.

* **Enhancements to existing features**:
  * Updated the `find` (now known as `findcontact`) command to allow for users to search their contacts lists by tags or email. (Pull requests [\#65](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/65), [\#137](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/137))
  * Fixed a bug in the original implementation of the contacts list (i.e., AB3) where two contacts could share the same phone number or email, but not the name. (Pull request [\#250](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/250))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `emailcontact` and `mostfreqcontact`: [\#96](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/96), [\#145](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/145).
	* Updated documentation for the exisiting `Contact` (also known as `Person` in AB3) features: [\#96](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/96).
  * Developer Guide:
    * Updated the description and class diagram of `Storage`: [\#265](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/265), [\#282](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/282).
    * Added implementation details of the `emailcontact` and `mostfreqcontact` features: [\#133](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/133), [\#277](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/277).
	* Added use cases for the `deletecontact`, `clearcontact`, and `emailcontact` features: [\#277](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/277)
	* Added a manual testing guide for the `addcontact` and `editcontact` features: [\#282](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/282/files).

* **Community**:
  * Examples of PRs reviewed (with non-trivial review comments): [\#60](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/60),  [\#143](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/143),  [\#268](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/268),  [\#269](https://github.com/AY2021S2-CS2103T-W13-1/tp/pull/269).
  * Reported bugs found in the textbook's content: [\#2](https://github.com/nus-cs2103-AY2021S2/forum/issues/2).
  * Highlighted potential bugs in the developer guide of AddressBook3: [\#309](https://github.com/nus-cs2103-AY2021S2/forum/issues/309).

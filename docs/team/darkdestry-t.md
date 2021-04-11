---
layout: page
title: darkdestry-t's Project Portfolio Page
---

## Project: A-Bash Book

A-Bash Book is a desktop address book application intended for anyone to search and insert into a centralized address book easily. It is targeted at technical professionals and have its CLI commands made to be bash-like to streamline the learning process.

Given below are my contributions to the project.

* **New Feature**: Added fuzzy search to find command with specific field searches of some data fields
  * What it does: allows the user to search without exact match of string to specific fields, mainly the email, remarks, name and tags fields.
  * Justification: This feature improves the usage of the program as people can type in spelling mistakes and still find the relevant info. It also allow for similar names to be returned in the case where the user cannot remember the exact spelling of the contact.
  * Highlights: This enhancement requires balancing the similarity index to the expected output. Certain metrics such as one way partial matching were used to prevent backwards matching from data to query.
  * Credits: FuzzyWuzzy library was used to provide partial matching.

* **New Feature**: Added field specific search and general search to the find command
  * What it does: allows the user to find specific fields as well as to search all the fields simultaneously.
  * Justification: This feature allows the user to narrow down their search results to a specific field or to search generally when they are not sure where the information may be located.
  * Highlights: Parser modification poses certain challenges when having to shuffle between creation of different predicates for each field. General search composites all the predicates together to a super-predicate.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=CS2103T-T12-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=DarkDestry-t&tabRepo=AY2021S2-CS2103T-T12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2.1` - `v1.4.0` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Added jar artifacts to CI for easier releases and PR testing (Pull Requests [\#134](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/134)[\#137](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/134))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `find` [\#76](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/76) [\#130](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/130)
  * Developer Guide:
    * Added implementation details of the `find` feature about fuzzy string matching.[\#118](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/118)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#95](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/95)

* **Tools**:
  * Integrated a third party library (FuzzyWuzzy) to the project ([\#76](https://github.com/AY2021S2-CS2103T-T12-3/tp/pull/76))

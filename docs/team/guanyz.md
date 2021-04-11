---
layout: page
title: Guan Yang Ze's Project Portfolio Page
---

## Project: JJIMY

JJIMY is a restaurant management app for managing food orders, ingredient inventory, etc. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 21.3 kLoC.

I was tasked with overseeing the documentation of the project and was additionally in charge of extending various commands to operate on all the components of the app.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=guanyz&tabRepo=AY2021S2-CS2103T-W15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **New Feature**: Implemented `add` and `edit` commands for all components of the app.
    * What it does: Allows the user to add and edit items within all four lists within the app.
    * Highlights: Implemented validation to ensure that the added or edited items are valid in the context of the rest of the list (for example, are not duplicates; do not reference other items that do not exist) before merging them into the list.


* **Enhancements to existing features**:
	* Modified an existing implementation of dishes to encapsulate an ingredient list. ([#121](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/121))
    * Extended the existing sample data for all the aspects of the app to aid in testing. ([#60](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/60), [#216](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/216))


* **Documentation**:
    * User Guide:
        * Updated guides for the usage of the `add` and `edit` commands. ([#143](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/143), [#149](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/149))
    * Developer Guide:
        * Overhauled implementation documentation ([#90](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/90))
		* Updated user stories ([#21](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/21))
		* Updated NFRs and glossary ([#28](https://github.com/AY2021S2-CS2103T-W15-3/tp/pull/28))


* **Review/mentoring contributions**:
    * Consistently reviewed other team members' PRs for approval.
	* Implemented several bugfixes.

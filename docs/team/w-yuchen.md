---
layout: page
title: Wang Yuchen's Project Portfolio Page
---

## Project: PocketEstate

PocketEstate enables easy organisation of mass clientele property information through sorting of information by price,
location and housing type, that may otherwise be difficult to manage. I have been tasked with greatly expanding the capabilities of 
the search function, as well as some documentation and testing. 

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=w-yuchen&tabRepo=AY2021S2-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
* **Feature**: Implemented `find` commands for `appointment` and `property`. 
    * What it does: Allow the user to search in any attributes that the entities have, using multiple predicates at the same time. 
    * Justification: Users now have access to a much more flexible search that allows for better control of what they want to search for, allowing users to choose the strictness of their searches. 
* **Feature**: Implemented `find client` command.
    * What is does: Allow the user to search through both `appointment` and `property` for entries that contain the searched name. 
    * Justification: Users can easily search for all entries related to a particular client they are interested in. 
* **Documentation**: 
    * User guide: Updated guides for the usage of `find appointment`, `find property`, and `find client` commands. 
    * Developer guide: Added in some user stories and added instructions for manually testing `find` commands.
* **Testing**: 
    * Wrote unit tests for: 
        * `find` commands and parsers
        * `edit appointment` command and parser 
        * `JsonAdaptedPropety`, which is used to store properties as JSON strings
        * `list all` command
    * Contributed to unit tests for: 
        * `PocketEstateParser`, which is the main parser that reads raw user input
* **Team-based tasks**: 
    * Reported bugs as issues for better tracking. 
    * Updated Java CI codecov badges from AB3's to PocketEstate's. 
* **Review/mentoring**: 
    * Reviewed and merged pull requests
    * Helped to resolve merge conflicts in other members' pull requests. 
    * Implemented several bugfixes. 

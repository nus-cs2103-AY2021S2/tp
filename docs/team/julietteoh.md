---
layout: page
title: Juliet Teoh's Project Portfolio Page
---

### Project: imPoster

imPoster is a desktop application for beginners of API development to easily grasp the basics. The application is optimised for fast typists and can be fully operated through a Command Line Interface. My contributions to the project are as listed below:

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=AY2021S2-CS2103T-T12-4%2Ftp&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=JulietTeoh&tabRepo=AY2021S2-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### New Features & Enhancements

- **Morphing of AddressBook**:
  - Refactor Delete Command to Remove Command
  - Removed unneeded attributes, such as `phone` and `email`
  - Added new attributes to Endpoint, such as `data` and `header`


- **Enhancements**: 
  - Designed logo and various mascot images for the project
  - Fixed and wrote additional test cases
 

- **Feature**: Update UI for when List is called when the Endpoint List is empty
  - What it does: When the Endpoint List is empty and the user types the command `list`, an image is displayed which allows the user to clearly know that the Endpoint List is empty
  - Justification: The user may become confused as to why there are no endpoints displayed when they input the command `list`, and so a picture can help them quickly understand the the Endpoint List is empty
  - Highlights: Ensures that the image is only displayed when Endpoint List is empty, and not when the filtered list is empty.
  

- **Project management**:
  - Created a demo API server to facilitate testing, written in python. 
  - Managed releases `v1.3b` on GitHub.
  

- **Documentation**:
  - User Guide:
    - Added documentation for the features `add`, `remove`, `edit`, `find`, `list`, `clear`, `help`
  - Developer Guide:
    - Added User Stories
    - Updated `Model`, `Logic`, `UI` sections of the DG, including fixing the diagrams of `UI` and `Model`
    - Added implementation details for `Endpoint` and its attributes `Method`, `Address`, `Data`, `Headers Set`, `Tags Set` and `Response`


- **Community**:
  - PRs reviewed (with non-trivial review comments): 
  - Helped and provided tips through forum discussions

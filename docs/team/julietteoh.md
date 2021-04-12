---
layout: page
title: Juliet Teoh's Project Portfolio Page
---

<h1 class="post-title">{{ page.title | escape }}</h1>

### Project: imPoster
imPoster is a desktop application for beginners of API development to easily grasp the basics. The application is optimised for fast typists and can be fully operated through a Command Line Interface. My contributions to the project are as listed below:

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=JulietTeoh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=JulietTeoh&tabRepo=AY2021S2-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### New Features & Enhancements
- **Morphing of AddressBook**:
  - Refactor Delete Command to Remove Command [\#90](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/90)
  - Removed unneeded attributes, such as `phone` and `email` [\#93](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/93)
  - Added new attributes to Endpoint, such as `data` and `header` [\#145](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/145) [\#162](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/162) <br>
    
- **Enhancements**: 
  - Designed logo and various mascot images for the project [\#77](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/77) [\#308](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/308)
  - Fixed and wrote additional test cases for multiple attributes and commands [\#127](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/127) [\#254](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/254) [\#272](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/272) [\#277](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/277) [\#428](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/428) [\#624](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/624)
  - Help fix index error messages and the formatting of command help messages [\#566](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/566) <br>
    
- **Feature**: Update UI for when List is called when the Endpoint List is empty [\#384](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/384)
  - What it does: When the Endpoint List is empty and the user types the command `list`, an image is displayed which allows the user to clearly know that the Endpoint List is empty
  - Justification: The user may become confused as to why there are no endpoints displayed when they input the command `list`, and so a picture can help them quickly understand the Endpoint List is empty
  - Highlights: Ensures that the image is only displayed when main Endpoint List is empty, and not when the filtered list is empty. <br>

<div style="page-break-after: always;"></div>

#### Project management
  - Created a demo API server to facilitate testing, written in python.  [repo here](https://github.com/AY2021S2-CS2103T-T12-4/demoAPI)
  - Managed releases `v1.3b` on GitHub. [release v1.3b](https://github.com/AY2021S2-CS2103T-T12-4/tp/releases/tag/v1.3b) <br>

#### Documentation
  - **User Guide**:
    - Added documentation for the features `add`, `remove`, `edit`, `find`, `list`, `clear`, `help` [\#42](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/42) [\#64](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/64)
    - Help correct some highlighting errors and add useful tips through the user guide [\#447](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/447) [\#545](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/545) [\#546](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/546) <br>
      
  - **Developer Guide**:
    - Added User Stories [\#64](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/64)
    - Updated high level architecture of the DG, including fixing the diagrams of `UI` and `Model` [\#325](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/325) [\#375](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/375) [\#380](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/380) [\#586](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/586)
    - Added implementation details for `Endpoint` and its attributes `Method`, `Address`, `Data`, `Headers Set`, `Tags Set` and `Response` [\#356](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/356)
    - Contributed to the formatting of the Developer's Guide, as well as Appendix H, which are the instructions for manual testing [\#615](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/615) <br>

#### Community
  - PRs reviewed (with non-trivial review comments): 
    [\#122](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/122)
    [\#181](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/181)
    [\#464](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/464)
    [\#551](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/551)
    [\#572](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/572) <br>
  - Helped and provided tips through forum discussions:
    [\#78](https://github.com/nus-cs2103-AY2021S2/forum/issues/78#issuecomment-770334258)
    [\#240](https://github.com/nus-cs2103-AY2021S2/forum/issues/240#issuecomment-800513614)
    [\#301](https://github.com/nus-cs2103-AY2021S2/forum/issues/301#issuecomment-815884675) <br>

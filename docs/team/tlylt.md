---
layout: page
title: Yongliang's Project Portfolio Page
---

<h1 class="post-title">{{ page.title | escape }}</h1>

### Project: imPoster

imPoster is a desktop application for beginners of API development to easily grasp the basics. The application is optimised for fast typists and can be fully operated through a Command Line Interface. My contributions to the project are as listed below:

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=tlylt&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=zoom&zA=tlylt&zR=AY2021S2-CS2103T-T12-4%2Ftp%5Bmaster%5D&zACS=121.05555555555556&zS=2021-02-19&zFS=tlylt&zU=2021-03-29&zMG=undefined&zFTF=commit&zFGS=groupByRepos&zFR=false)

#### New Features & Enhancements

- **New Feature**: Added the ability to send a request without saving the endpoint.

  - What it does: allows the user to run API requests on the fly. 
  - Justification: This feature improves the product significantly because a
    user can quickly type in an API URL with specifying details, and the app 
    will conveniently trigger a request for the user to view the response.
  - Highlights: This enhancement affects existing commands that triggers an outbound
    request. It required an in-depth analysis of design alternatives.
    The implementation too was challenging as it required changes to existing
    commands and how we verify the validity of URLs.

- **New Feature**: Added a keyboard shortcut that allows the user to retrieve 
  the previous valid command using <kbd>ctrl</kbd> + <kbd>up-arrow</kbd> (Windows) / <kbd>cmd</kbd> + 
  <kbd>up-arrow</kbd> (Mac).

- **New Feature**: Added a help command that allows the user to open a pop up window displaying
   relevant links such as the product's user guide and also a table of command summary. 

- **New Feature**: Added a show command that allows the user to view the details of a saved endpoint.

- **Project management**:

  - Contributed to gathering of discussion topics and updating of meeting minutes in weekly team meetings.

- **Enhancements to existing features**:

  - Updated various custom feedback messages in the result display for commands.
  - Wrote additional tests for existing features to increase coverage.

- **Documentation**:

  - User Guide:
    - Added the documentation for the features `send`, `run` and `help`.

  - Developer Guide:
    - Added the high level overview of product design 
      (Architecture, UI component and Logic component).
    - Added implementation details of the `send` & `run` feature.

- **Community**:

  - PRs reviewed (with non-trivial review comments): 
    [\#79](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/79), 
    [\#146](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/146),
    [\#189](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/189), 
    [\#345](https://github.com/AY2021S2-CS2103T-T12-4/tp/pull/345).
  - Contributed to forum discussions (
    [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/220#issuecomment-797323149), 
    [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/241#issuecomment-802904375), 
    [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/236#issuecomment-799401973), 
    [4](https://github.com/nus-cs2103-AY2021S2/forum/issues/235#issuecomment-799058631)).
  - Provided helpful tips and suggestions for other teams in the class (
    [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/179#issuecomment-785740902),
    [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/175#issuecomment-784177269), 
    [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/243#issuecomment-803282899)).
  - Raised pertinent issues on the topic of design principles and code refactoring 
    ([1](https://github.com/nus-cs2103-AY2021S2/forum/issues/240), 
    [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/230)).
    

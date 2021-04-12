---
layout: page
title: Yue Yang's Project Portfolio Page
---

## Yue Yang's Portfolio

## Project: GreenMileageEfforts

### Overview

GreenMileageEfforts (GME) is a platform that helps the HR executive of any company quickly arrange carpooling among its employees in order to lower the carbon footprint of the company.

Given below are my contributions to the project.

* **New Feature**: Added the ability to find by more parameters. (PR #110, #122, #145)
  * What it does: allows the user to find passengers by other parameters not limited to name.
  * Justification: This is allows users to find passengers more quickly.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=lyueyang&tabRepo=AY2021S2-CS2103T-W10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * Allow for multiple deletion of passengers (PR #133)
  * Renamed addressbook.json to GMEdata.json (PR #133)
  
* **Contributions to UG**:
  * Add explanations for the behaviour of Find command.
  * Add explanations for the multi-delete enhancement of Delete command.
  * Added more examples for the command summary for Find and Delete.
  * Correcting other minor mistakes made such as the lack of full stops.
  * Suggested the grouping of features according to Passenger-oriented and Pool-oriented commands later implemented by Zech.

* **Contributions to DG**:
  * Add rationale, sequence diagram and explanation for Find command.
  * Created sequence diagram for older iteration of Delete command.
  * Added manual tests for Find and Delete.
  * Correct other minor mistakes made such as the inconsistent lack of full stops.
  
* **Contributions to the team**:
  * Reviewed issues to close duplicates such as [#159](https://github.com/AY2021S2-CS2103T-W10-1/tp/issues/159) [#166](https://github.com/AY2021S2-CS2103T-W10-1/tp/issues/166) [#205](https://github.com/AY2021S2-CS2103T-W10-1/tp/issues/205)
  * Reviewed PRs such as [#226](https://github.com/AY2021S2-CS2103T-W10-1/tp/pull/226#pullrequestreview-632542191)

### UG Contributions

* Explanation for Find Command:

  Finds passengers whose names contain any of the given keywords.
  
  **Format:** `find PREFIX/KEYWORD` where PREFIX is one of the following: `n`, `a`, `p`, `tag`, `pr`, `all`, `d`, `t`
  
  <div markdown="block" class="alert alert-info">
  
  **:information_source: Notes about the find command:**<br>
  
  * Only full words will be matched e.g. `Han` will not match `Hans`.
  * Only **one type of prefix** can be specified.
  * More than 1 keyword can be specified for a single prefix.
  * Keywords separated by space will require both keywords to be matched in whole and cannot be broken apart.
    e.g. `Hans Yang` will only return `Gruber Hans Yang` instead of `Bo Yang` and `Hans Gruber Yang`.
  * Prefixes for searching name `n/`, address `a/`, tag `tag/`, phone number `p/`, price `pr/`, day `d/` and time `t/`.
  * Searching with prefixes such as `n/` and `a/` which are anticipated to have multiple words separated by spaces will have multiple spaces shortened to one. Refer to 4th example for clarification.
  * `all` prefix for searching across names, addresses, tags and phone numbers quickly.
  * However, if any or all of the words are invalid arguments, searching with `all` will not tell you it is invalid. It will only show that no passengers with those arguments are found.
  </div>
  
  **Examples:**
  * `find a/serangoon` returns `Bernice Yu`, `David Li`.<br>
    ![result for 'find serangoon'](images/findAddress.png)
  * `find d/Monday d/Tuesday` returns `Alex Yeoh`, `Irfan Ibrahim` and `Roy Balakrishnan`.
    ![result for 'find d/Monday d/Tuesday'](images/findCommandExampleDay.png)
  * `find n/Alex Yeoh` with multiple spaces between Alex and Yeoh
    ![result for 'find n/alex    yeoh'](images/findAlexMultipleSpace.png) 



* Explanation for Delete Command:

  * Multiple passengers can be deleted by including additional indexes after the first.
  * The index refers to the index number shown in the displayed passenger list.
  * The index **must be a positive integer** 1, 2, 3, …​
  * `search female` followed by `delete 3` deletes the *1st* passenger in the results of `search female` command.
  * Passengers with a carpool arrangement cannot be deleted, `unpool` must first be done on the pools they are in before deletion.
  </div>
  
  **Examples:**
  * `list` followed by `delete 3` deletes the *3rd* person in the passenger list.
  * `delete 1 2 5` deletes the 1st, 2nd and 5th person in the passenger list. 
    
<br> 

* Additional examples for command summary
  * **find** | `find a/ADDRESS [a/ADDRESS a/ADDRESS ...]` or `find n/NAME [n/NAME n/NAME ...]` or `find p/PHONE NUMBER [p/PHONE NUMBER p/PHONE NUMBER ...]` or `find tag/TAG [tag/TAG tag/TAG ...]` or `find all/KEYWORD [all/KEYWORD all/KEYWORD ...]` or `find d/DAY [d/DAY d/DAY ...]` or `find t/TIME [t/TIME t/TIME ...]` <br> e.g., `find tag/female`
  * **delete** | `delete INDEX [INDEX INDEX...]`<br> e.g.`delete 1 3`
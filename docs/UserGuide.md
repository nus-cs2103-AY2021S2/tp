---
layout: page
title: User Guide
---

**Tutor Tracker** is a desktop app designed to **help students search for tutors and manage tuition appointments**, optimised for use via a Command Line Interface (CLI) for a fast and streamlined experience.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Add new tutor details: `add_tutor`

Add a new tutor and enter their basic details.

Details:
* Name
* Phone Number
* Email
* Address
* Subjects (multiple allowed)
* Hourly Rate
* Years of Experience

Format:
`add_tutor n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS s/SUBJECT... r/RATE y/YEARS`

Example Input:
`add_tutor n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/English s/Mathematics (Sec 3, 4, JC) r/60 y/6`

### List all tutors: `list_tutors`

View a list of all tutors known.

Example Output:
```
1) John Doe
2) Jane Doe
3) Peter Ng
```

## Command summary

Action | Format, Examples
--------|------------------
**Add Tutor** | `add_tutor n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS s/SUBJECT... r/RATE y/YEARS​` <br> e.g., `add_tutor n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/English s/Mathematics (Sec 3, 4, JC) r/60 y/6`
**List Tutors** | `list_tutors`

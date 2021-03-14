---
layout: page
title: Dictionary
---

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

# Week 1

## OOP: Class and Objects
### W1.1a Paradigms → OOP → Introduction → What

_(definition):_

**Object-Oriented Programming (OOP)** is a programming paradigm.

_(definition):_
A **programming paradigm** guides programmers to analyze programming problems, and structure programming solutions, in a specific way.

> Programming languages have traditionally divided the world into two parts—data and operations on data. Data is static and immutable, except as the operations may change it. The procedures and functions that operate on data have no lasting state of their own; they’re useful only in their ability to affect data.
>
> This division is, of course, grounded in the way computers work, so it’s not one that you can easily ignore or push aside. Like the equally pervasive distinctions between matter and energy and between nouns and verbs, it forms the background against which you work. At some point, all programmers—even object-oriented programmers—must lay out the data structures that their programs will use and define the functions that will act on the data.
>
> With a procedural programming language like C, that’s about all there is to it. The language may offer various kinds of support for organizing data and functions, but it won’t divide the world any differently. Functions and data structures are the basic elements of design.
>
> Object-oriented programming doesn’t so much dispute this view of the world as restructure it at a higher level. It groups operations and data into modular units called objects and lets you combine objects into structured networks to form a complete program. In an object-oriented programming language, objects and object interactions are the basic elements of design.
>
> <small>-- Object-Oriented Programming with Objective-C, Apple</small>
Some other examples of programming paradigms are:

**Paradigm** | **Programming Languages**
---------|----------------------------------------------
_Procedural Programming paradigm_ | C
_Functional Programming paradigm_ | F#, Haskell, Scala
_Logic Programming paradigm_ | Prolog

**Some programming languages support multiple paradigms.**

Java is primarily an OOP language but it supports limited forms of functional programming and it can be used to (although not recommended) write procedural code. e.g. se-edu/addressbook-level1

JavaScript and Python support functional, procedural, and OOP programming.



### W1.1a Paradigms → OOP → Objects → What

_(definition):_

**Object Oriented Programming (OOP)** views the world as a network of interacting objects.

> Every object has both state (data) and behavior (operations on data). In that, they’re not much different from ordinary physical objects. It’s easy to see how a mechanical device, such as a pocket watch or a piano, embodies both state and behavior. But almost anything that’s designed to do a job does, too. Even simple things with no moving parts such as an ordinary bottle combine state (how full the bottle is, whether or not it’s open, how warm its contents are) with behavior (the ability to dispense its contents at various flow rates, to be opened or closed, to withstand high or low temperatures).
>
> It’s this resemblance to real things that gives objects much of their power and appeal. They can not only model components of real systems, but equally as well fulfill assigned roles as components in software systems.
>
> <small>-- Object-Oriented Programming with Objective-C, Apple</small>

:checkered_flag: A real world scenario viewed as a network of interacting objects:

You are asked to find out the average age of a group of people Adam, Beth, Charlie, and Daisy.
You take a piece of paper and pen, go to each person, ask for their age, and note it down.
After collecting the age of all four, you enter it into a calculator to find the total.
And then, use the same calculator to divide the total by four, to get the average age.
This can be viewed as the objects ```You```, ```Pen```, ```Paper```, ```Calculator```,```Adam```,```Beth```,
```Charlie```, and ```Daisy``` interacting to accomplish the end result of calculating the average age of the four persons.
These objects can be considered as connected in a certain network of certain structure.

**OOP solutions try to create a similar object network inside the computer’s memory** –
a sort of virtual simulation of the corresponding real world scenario – **so that a similar result can be achieved programmatically**.

**OOP does not demand that the virtual world object network follow the real world exactly.**

--------------------------------------------------------------------------------------------------------------------

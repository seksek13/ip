# Sumikko User Guide

## Introduction

Summikko is a app that aids in managing daily tasks. It is designed for users who enjoys working with Command Line Interface. 

## Quick Start

1. Ensure that Java 11 is installed
2. Download the sumikko.jar file [here] 
3. Move the file to your desired home directory
4. Double click to open the app
5. Key in the commands below to enjoy the app features!


## Features 

### 1. Listing all tasks : `list`

Show a list of all tasks added to the list

Command Format: `list`

Example of usage: 

`list`

Expected outcome:

<img width="385" alt="Screenshot 2022-02-17 at 6 25 50 PM" src="https://user-images.githubusercontent.com/69745285/154624367-70a622a2-b0a3-4241-ad2f-046d6288acfb.png">



### 2. Adding Todo Task : `todo`

Adds basic task that does not contain any date/time

Command Format: `todo TASK_DESCRIPTION`

Example of usage: 

`todo math assignment`

Expected outcome:

<img width="383" alt="Screenshot 2022-02-17 at 6 26 13 PM" src="https://user-images.githubusercontent.com/69745285/154624419-c0ac8094-39b3-48b3-b8cf-e22389497479.png">


### 3. Adding Deadline Task : `deadline`

Adds task with deadline date/time

Commmand Format: `deadline TASK_DESCRIPTION /by dd/MM/yyyy HH:mm`

Example of usage: 

`deadline submit assignments /by 10/12/2022 17:00`

Expected outcome:

<img width="369" alt="Screenshot 2022-02-17 at 6 27 07 PM" src="https://user-images.githubusercontent.com/69745285/154624437-1a541aab-5846-4aff-a15c-43cad9fe5796.png">


### 4. Adding Event Task : `event`

Adds an event task 

Command Format: `event TASK_DESCRIPTION /at dd/MM/yyyy HH:mm`

Example of usage: 

`event group meeting /at 02/08/2022 14:30`

Expected outcome:

<img width="373" alt="Screenshot 2022-02-17 at 6 28 02 PM" src="https://user-images.githubusercontent.com/69745285/154624470-030fa2a4-e9b7-4b96-835e-ca63fb219f27.png">



## Marking task as done : `mark`

Marks a task as done [x]

Command Format: `mark TASK_NUMBER`

Example of usage: 

`mark 1`

Expected outcome:

<img width="373" alt="Screenshot 2022-02-17 at 6 28 26 PM" src="https://user-images.githubusercontent.com/69745285/154624505-a0b9415d-e543-4375-b025-3706520e4b40.png">



## Unmarking task as done : `unmark`

Unmark a task as done [x]

Command Format: `unmark TASK_NUMBER`

Example of usage: 

`unmark 1`

Expected outcome:

<img width="368" alt="Screenshot 2022-02-17 at 6 28 36 PM" src="https://user-images.githubusercontent.com/69745285/154624534-53badf05-7409-48a3-babb-d7d225a510a0.png">


## Deleting task : `delete`

Removing a task from list

Command Format: `delete TASK_NUMBER`

Example of usage: 

`delete 3`

Expected outcome:

<img width="374" alt="Screenshot 2022-02-17 at 6 28 49 PM" src="https://user-images.githubusercontent.com/69745285/154624549-83ae59ef-fa73-4ff4-bffb-994d0a39e94b.png">


## Finding a task : `find`

Search for a particular task by entering keywords and return all tasks that contains the keyword in its task description

Commnad Format: `find KEYWORD`

Example of usage: 

`find book`

Expected outcome:

<img width="370" alt="Screenshot 2022-02-17 at 6 29 02 PM" src="https://user-images.githubusercontent.com/69745285/154624574-71f7309e-47ae-4963-8301-e597fe202321.png">


## Reminding tasks : `reminder`

Reminders about task that will be due on the date entered by user

Command Format: `reminder dd/MM/yyyy HH:mm`

Example of usage: 

`reminder 13/12/2022 00:00`

Expected outcome:

<img width="369" alt="Screenshot 2022-02-17 at 6 29 26 PM" src="https://user-images.githubusercontent.com/69745285/154624596-5e9f2f22-7940-4b7c-8418-ab983b8f905d.png">


## Exiting the app : `bye`

Exit and close the app

Command Format: `bye`

Example of usage: 

`bye`




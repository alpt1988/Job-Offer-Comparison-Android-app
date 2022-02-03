# Team Design Description 

## Table of Context
1. [Requirement 1 Design Description](#requirement-1-design-description)
2. [Requirement 2 Design Description](#requirement-2-design-description)
3. [Requirement 3 Design Description](#requirement-3-design-description)
4. [Requirement 4 Design Description](#requirement-4-design-description)
5. [Requirement 5 Design Description](#requirement-5-design-description)
6. [Requirement 6 Design Description](#requirement-6-design-description)
7. [Requirement 7 Design Description](#requirement-7-design-description)
8. [Requirement 8 Design Description](#requirement-8-design-description)
9. [Requirement 9 Design Description](#requirement-9-design-description)



##### Requirement 1
When the app is started, the user is presented with the main menu, which allows the user to (1) enter current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  

#### Requirement 1 Design Description
To realize this requirement, We included a `Main Menu` class, which is the entrypoint into the application. This class gives the user the option to enter any of the four views described in the requirement above.

##### Requirement 2
When choosing to enter current job details, a user will:
1.	Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
    1.	Title
    2.	Company
    3.	Location (entered as city and state)
    4.	Overall cost of living in the location (expressed as an index)
    5.	Commute time (round-trip and measured in hours or fraction thereof)
    6.	Yearly salary
    7.	Yearly bonus
    8.	Retirement benefits (as percentage matched)
    9.	Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
2.	Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

#### Requirement 2 Design Description
To realize this requirement, We created a `Current Job` class, which is a child of the `Job` class. The `Job` class has all the attributes described above, and the `Current Job` class inherits all these attributes and specifically identifies that this class refers to the user's current job. The `Current Job` class has a save and edit function and the `Main Menu` class has a saveCurrentJob function that will allow the user to save/edit the currently entered job details for their current job. The `Main Menu` class has an object which stores the Current Job.

##### Requirement 3
When choosing to enter job offers, a user will:	
1.	Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
2.	Be able to either save the job offer details or cancel.
3.	Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).


#### Requirement 3 Design Description
To realize this requirement, We created a `Job Offer` class, which is a child of the `Job` class. This `Job Offer` class also inherits all the `Job` class atributes, and has a many to 1 relationship with the user since a user may have multiple job offers, and may need to add another job offer after entering a job offer. The `Main Menu` class has an object which stores an array of Job offers.

##### Requirement 4
When adjusting the comparison settings, the user can assign integer weights to:
1.	Commute time
2.	Yearly salary
3.	Yearly bonus
4.	Retirement benefits
5.	Leave time

If no weights are assigned, all factors are considered equal.


#### Requirement 4 Design Description
To realize this requirement, We created a `Comparison Settings` class which has attributes for each of the weights described in the requirement above. We also assigned a default value of 1 to all of the weights so that if none of them are entered, they will all be considered equal at a weight of 1. The `Main Menu` class has an object which stores the Comparison Settings.

##### Requirement 5
When choosing to compare job offers, a user will:
1.	Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
2.	Select two jobs to compare and trigger the comparison.
3.	Be shown a table comparing the two jobs, displaying, for each job:
    1.	Title
    2.	Company
    3.	Location
    4.	Commute time
    5.	Yearly salary adjusted for cost of living
    6.	Yearly bonus adjusted for cost of living
    7.	Retirement benefits (as percentage matched)
    8.	Leave time
4.	Be offered to perform another comparison or go back to the main menu.


#### Requirement 5 Design Description
To realize this requirement, We created a rankJobs() method for which we will pass in the current job and all the job offers, along with the comparison settings. This method will return an array of jobs ranked by the comparison settings passed in. 
We then included a CompareJobOffersView(), which will take in two jobs, and the UI will handle displaying the fields for those two jobs to the user, along with offering another comparison or going back to the main menu.

##### Requirement 6
When ranking jobs, a job’s score is computed as the weighted sum of:AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)where:AYS = yearly salary adjusted for cost of livingAYB = yearly bonus adjusted for cost of livingRBP = retirement benefits percentageLT = leave timeCT = commute timeThe rationale for the CT subformula is:
1.	value of an employee hour = (AYS / 260) / 8
2.	commute hours per year = CT * 260
3.	therefore commute-time cost = CT * 260 * (AYS / 260) / 8 = CT * AYS / 8

    For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as:2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 (CT * AYS / 8)


#### Requirement 6 Design Description
We did not realize this requirement, since it will be handled in the implementation of the rankJobs() method, which takes in a list of jobs and the comparison settings, and will perform the calculation described above to rank the jobs. 

##### Requirement 7
The user interface must be intuitive and responsive.

#### Requirement 7 Design Description
We did not realize this requirement since it is a nonfunctional requirement, and will be handled in the implemenation of the Job Comparison Application.


##### Requirement 8
The performance of the app should be such that users do not experience any considerable lag between their actions and the response of the app.


#### Requirement 8 Design Description
To satisfy this requirement, all data will be stored locally so there is no lookup lag. Additionally, all operations will run in constant or linear time for quick performance. As a note, the GUI implementation may affect app performance as well, but that is not represented in this document.



##### Requirement 9
For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).



#### Requirement 9 Design Description
To realize this requirement, We only considered a system with one user by only including one instance of the Main Menu for the one user, and we did not include any saving between two different devices.

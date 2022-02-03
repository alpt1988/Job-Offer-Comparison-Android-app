
# Design Document

**Author**: Team 072
Revision| Detail
------------ | -------------
00| New issue  
01| Update GUI design
02| Update Component Diagram
03| Update Assumptions


## 1 Design Considerations

### 1.1 Assumptions

- The system will operate with one user and will not share data with other systems, and that user's data and state will be persisted between uses.
- The user will be running Android 9.0 or later.
- The user will be entering job information in English
- The monetary system used will be US dollars
- The user will have a maximum of one current job, with no restrictions on the amount of job offers entered
- The Comparison Settings weight must be greater than 0 for all weights entered since a zero or negative weight does not make sense
- All numberic values for the Job Class must be integer values with a value greater than or equal to 0 and will be stored as integers
- The Commute Time value on the Job class cannot be greater than 25, since having a commute greater than 24 hours is not possible
- The Retirement benefits value must be less than 100, since it is not possible to match more than 100 percent

### 1.2 Constraints


- The application must be developed as an Android application and written in Java
- The application must be responsive and easy to interact with
- The application must hold the user state, and therefore will have to use a database to keep track of state


### 1.3 System Environment

- The system will be an android application written in Java that will support Andriod 9.0 or later.
- The system will save data in a SQLite database
- The full application will be pushed to github will all artifacts, and run after a fresh clone of the repository



## 2 Architectural Design

### 2.1 Component Diagram

![Component Diagram](./images/ComponentDiagram.png "Component Diagram")

Figure 1: Component Diagram


This diagram shows the required components and interactions between these components. As displayed in the diagram above, there are five different componenets. The Main Menu component interacts with the Comparison Settings, Current Job and Job Offer components in order to create or update objects. The Current Job, Job Offer and Comparison Settings components interact with the database to save their current states. 

### 2.2 Deployment Diagram

![Deployment Diagram](./images/DeploymentDiagram.png "Deployment Diagram")

Figure 2: Deployment Diagram

This diagram describes the deployment of the application on target devices. The Job Comparison application will be deployed on an android device, and will be connected to a SQLite Database on the device. 

## 3 Low-Level Design

### 3.1 Class Diagram

![Class Diagram](./images/ClassDiagram.png "Class Diagram")

Figure 3: Class Diagram


The Class Diagram describes the structure of the classes used and the relationship between classes in the application. For our class diagram, the Main Menu is the entrypoint into the application. The user can then adjust comparison settings, enter current job or enter a new job offer. The Main menu provides functions to rank jobs, compare jobs and edit comparison settings.

### 3.2 Other Diagrams

TBD

## 4 User Interface Design

![Main](./images/Main.png "Main")

Main Activity

![Current](./images/CurrentJob.png "Current")

Current Job Activity

![Job](./images/JobOffers.png "Job")

Job Offers Activity

![Comparison](./images/Comparison.png "Comparison")

Comparison Settings Activity

![List](./images/List.png "List")

Display list of job offers


![Jobcompare](./images/Jobcompare.png "Jobcompare")
Display Compared Job Offers Activity
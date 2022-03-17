# News Portal
## Description
A company news posting rest API
## Technologies used
- Postgresql
- SparkJava
- Gradle
- Junit
- GSON

## Behavior Driven Development

This application implements Create Read Update Delete ( CRUD ) operations in its entirety which rely on the basic database

| Behavior                          | Input                                                    | Expected Output                                               |
|-----------------------------------|----------------------------------------------------------|---------------------------------------------------------------|
| Add member                        | Raw Json that matches the member class's members         | A new member is created and saved                             |
| Add department                    | Raw Json that matches the department class's members     | A new department is created and saved                         |   
| Add company News                  | Raw Json that matches the companyNews class's members    | Company news is created and saved                             |
| Add department news               | Raw Json that matches the departmentNews class's members | Department news is added and saved                            |
| Get all Members                   | members/all endpoint  is called                          | Members are returned in Json format                           |
| Get member                        | member/:memberId endpoint is called                      | The specific member is returned if they exist in the database |
| Get all company news              | companyNews/all endpoint is called                       | All company news is returned in Json format                   |
| Get news in a specific department | department/:departmentId/news endpoint is called         | News for a specific department is returned                    |
| Get all members in a department   | departments/:id/members/all endpoint is called           | Members for a specific department are returned                |
| Get department                    | departments/:departmentId endpoint is called             | Specific department is returned                               |
| Get all department news           | departmentNews/all endpoint is called                    | News with type department is returned                         |
| Get all departments               | depaertments/all endpoint is called                      | All departments are returned                                  |

## Test Driven Development
Each database operation method has been tested using Junit.

## Setup
### Prerequisites
- Postgresql ( 14.2 )
- Java Development Kit ( JDK )
- Postman
- Gradle

### Install

Clone the repository using the following command:
```
git clone https://github.com/Nyanjuimarvin/News-Portal.git
```

### Recreate Database
Navigate to the root directory of the project in your terminal and Run the following command to recreate the database on your local machine
```
psql < create.sql;
```
If the database seems to be populated, reset it using:
```
psql < drop.sql
```
and recreate it again.


### Run
Open the project in Intellij Idea and refresh gradle to download dependencies.  
After downloading dependencies, run
```
gradle build
```  
and thereafter
```
gradle run
```
to ignite a Spark server.The project is now live at
```
localhost:4567
```
### Postman
Use the following templates for the post methods:
- new member
```
{
    "name":" ",
    "position":" ",
    "roles":[],
    "departmentId":integer
}
```
- new department
```
{
    "name":" ",
    "description":" "
}
```
- news
```
{
   "information":"  ",
   "category":" "
}
```

## Live Link
https://pt-news-portal.herokuapp.com/
## Contact
For any issues, additional requests or compliments, reach out to me using:
* E-mail - marnyanjui@gmail.com



## License and Copyright

Copyright 2022 Marvin Nyanjui

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
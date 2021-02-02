# Notifier
> implemented an Employee Management System with a feature of sending messages inside the company.
> 
> Notifier is a spring boot web application, that can run in any environment system!

## Prerequisites
- [x] Java 11
- [x] Maven + JAVA_HOME set to jdk

## Installation
1. Clone the repository:

    ```sh
    $ git clone https://github.com/Itayventura/Notifier.git
    ```

2. Build and run tests:
    - from git bash:
        ```sh
        $ cd Notifier/
        $ mvn clean install
        ```

    - All modules should be built successfully

3. Run app :
    ```sh
    java -jar target/notifier-0.0.1-snapshot.jar
    ```

## REST APIs

### GET Employees
> GET localhost:8080/employees/
> 
#### Response
```
{
    "_embedded": {
        "employeeList": [
            {
                "employeeId": 1,
                "firstName": "Itay",
                "lastName": "Ventura",
                "emailAddress": "a@a.com",
                "roll": "software developer",
                "team": {
                    "teamId": 1,
                    "name": "sw1",
                    "department": "R&D"
                },
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/employees/1"
                    },
                    "employees": {
                        "href": "http://localhost:8080/employees"
                    }
                }
            },
            {
                "employeeId": 2,
                "firstName": "Mani",
                "lastName": "mani",
                "emailAddress": "c@c.com",
                "roll": "Team leader",
                "team": {
                    "teamId": 1,
                    "name": "sw1",
                    "department": "R&D"
                },
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/employees/2"
                    },
                    "employees": {
                        "href": "http://localhost:8080/employees"
                    }
                }
            },
            {
                "employeeId": 3,
                "firstName": "Moshe",
                "lastName": "Moshiko",
                "emailAddress": "b@b.com",
                "roll": "software developer",
                "team": {
                    "teamId": 2,
                    "name": "sw2",
                    "department": "R&D"
                },
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/employees/3"
                    },
                    "employees": {
                        "href": "http://localhost:8080/employees"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees"
        }
    }
}
```

### GET employee by id
> GET localhost:8080/employees/{id}
#### Response
```
{
    "employeeId": 1,
    "firstName": "Itay",
    "lastName": "Ventura",
    "emailAddress": "a@a.com",
    "roll": "software developer",
    "team": {
        "teamId": 1,
        "name": "sw1",
        "department": "R&D"
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/1"
        },
        "employees": {
            "href": "http://localhost:8080/employees"
        }
    }
}
```

### POST new employee
> POST localhost:8080/employees/new

#### Body
```
{
    "firstName": "my",
    "lastName": "Employee",
    "emailAddress": "new@Employee.com",
    "roll": "Software Engineer",
    "team": 
    {
        "teamId": 1

    }
}
```
#### Response
```
{
    "employeeId": 4,
    "firstName": "my",
    "lastName": "Employee",
    "emailAddress": "new@Employee.com",
    "roll": "Software Engineer",
    "team": {
        "teamId": 1,
        "name": null,
        "department": null
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/4"
        },
        "employees": {
            "href": "http://localhost:8080/employees"
        }
    }
}
```

### DELETE employee
> DELETE localhost:8080/employees/delete
#### Body
```
{
    "employeeId": 4,
    "firstName": "new",
    "lastName": "Employee",
    "emailAddress": "new@Employee.com",
    "roll": "Software Engineer",
    "team": 
    {
        "teamId": 2
    }
}
```

### PUT update employee
> PUT localhost:8080/employees/update
#### Body
```
{
    "employeeId": 1,
    "firstName": "new",
    "lastName": "Employee",
    "emailAddress": "new@Employee.com",
    "roll": "Software Engineer",
    "team": 
    {
        "teamId": 2,
        "name": "sw2",
        "department": "R&D"
    }
}
```
#### Response
```
{
    "employeeId": 1,
    "firstName": "new",
    "lastName": "Employee",
    "emailAddress": "new@Employee.com",
    "roll": "Software Engineer",
    "team": {
        "teamId": 2,
        "name": "sw2",
        "department": "R&D"
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/1"
        },
        "employees": {
            "href": "http://localhost:8080/employees"
        }
    }
}
```

### GET team employees
> GET http://localhost:8080/employees/team/1
#### Response
```
{
    "_embedded": {
        "employeeList": [
            {
                "employeeId": 2,
                "firstName": "Mani",
                "lastName": "mani",
                "emailAddress": "c@c.com",
                "roll": "Team leader",
                "team": {
                    "teamId": 1,
                    "name": "sw1",
                    "department": "R&D"
                },
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/employees/2"
                    },
                    "employees": {
                        "href": "http://localhost:8080/employees"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/team/1"
        }
    }
}
```

### GET teams
> GET localhost:8080/teams
#### Response
```
{
    "_embedded": {
        "teamList": [
            {
                "teamId": 1,
                "name": "sw1",
                "department": "R&D",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/teams/1"
                    },
                    "teams": {
                        "href": "http://localhost:8080/teams"
                    }
                }
            },
            {
                "teamId": 2,
                "name": "sw2",
                "department": "R&D",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/teams/2"
                    },
                    "teams": {
                        "href": "http://localhost:8080/teams"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/teams"
        }
    }
}
```

### GET team by id
> GET localhost:8080/teams/{id}
#### Response
```
{
    "teamId": 1,
    "name": "sw1",
    "department": "R&D",
    "_links": {
        "self": {
            "href": "http://localhost:8080/teams/1"
        },
        "teams": {
            "href": "http://localhost:8080/teams"
        }
    }
}
```

### POST new team
> POST localhost:8080/team/new

#### Body
```
{
    "name": "my team",
    "department": "R&D"
}
```
#### Response
```
{
    "teamId": 3,
    "name": "my team",
    "department": "R&D",
    "_links": {
        "self": {
            "href": "http://localhost:8080/teams/3"
        },
        "teams": {
            "href": "http://localhost:8080/teams"
        }
    }
}
```

### GET messages
> GET localhost:8080/messages
#### Response
```
[
    {
        "messageId": 1,
        "content": "This is a first message",
        "sender": {
            "employeeId": 2,
            "firstName": "Mani",
            "lastName": "mani",
            "emailAddress": "c@c.com",
            "roll": "Team leader",
            "team": {
                "teamId": 1,
                "name": "sw1",
                "department": "R&D"
            }
        },
        "localDateTime": "2021-02-02T15:40:19.12022",
        "team": {
            "teamId": 1,
            "name": "sw1",
            "department": "R&D"
        },
        "type": "Team"
    }
]
```

### GET team messages by team
> GET localhost:8080/messages/team/{id}
#### Body
```
{
    "teamId": 1,
    "name": "my team",
    "department": "R&D"
}
```
#### Response
```
[
    {
        "messageId": 1,
        "content": "This is a first message",
        "sender": {
            "employeeId": 2,
            "firstName": "Mani",
            "lastName": "mani",
            "emailAddress": "c@c.com",
            "roll": "Team leader",
            "team": {
                "teamId": 1,
                "name": "sw1",
                "department": "R&D"
            }
        },
        "localDateTime": "2021-02-02T15:40:19.12022",
        "team": {
            "teamId": 1,
            "name": "sw1",
            "department": "R&D"
        },
        "type": "Team"
    }
]
```

### GET All employee messages by employee
> GET localhost:8080/messages/employee/{id}
#### Body
```
 {
    "employeeId": 2,
    "firstName": "Mani",
    "lastName": "mani",
    "emailAddress": "c@c.com",
    "roll": "Team leader",
    "team": {
        "teamId": 1,
        "name": "sw1",
        "department": "R&D"
    }
 }
```
#### Response
```
[
    {
        "messageId": 1,
        "content": "This is a first message",
        "sender": {
            "employeeId": 2,
            "firstName": "Mani",
            "lastName": "mani",
            "emailAddress": "c@c.com",
            "roll": "Team leader",
            "team": {
                "teamId": 1,
                "name": "sw1",
                "department": "R&D"
            }
        },
        "localDateTime": "2021-02-02T15:40:19.12022",
        "team": {
            "teamId": 1,
            "name": "sw1",
            "department": "R&D"
        },
        "type": "Team"
    }
]
```

### POST send a new message to employee
> POST localhost:8080/messages/employee/new

#### Body
```
{
    "content": "this is a new content of employee message",
    "sender": {
        "employeeId": 1
    },
    "employee": {
        "employeeId": 2
    }
}
```
#### Response
```
{
    "messageId": 1,
    "content": "this is a new content of employee message",
    "sender": {
        "employeeId": 1,
        "firstName": null,
        "lastName": null,
        "emailAddress": null,
        "roll": null,
        "team": null
    },
    "localDateTime": "2021-02-02T16:10:08.3215814",
    "employee": {
        "employeeId": 2,
        "firstName": null,
        "lastName": null,
        "emailAddress": null,
        "roll": null,
        "team": null
    },
    "type": "Employee"
}
```

### POST send a new message to team
> POST localhost:8080/messages/team/new

#### Body
```
{
    "content": "this is a new content",
    "sender": {
        "employeeId": 1
    },
    "team": {
        "teamId": 1
    }
}
```
#### Response
```
{
    "messageId": 2,
    "content": "this is a new content",
    "sender": {
        "employeeId": 1,
        "firstName": null,
        "lastName": null,
        "emailAddress": null,
        "roll": null,
        "team": null
    },
    "localDateTime": "2021-02-02T16:12:37.9457285",
    "team": {
        "teamId": 1,
        "name": null,
        "department": null
    },
    "type": "Team"
}
```
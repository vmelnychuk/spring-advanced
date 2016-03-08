# Spring Advanced: Task 3

Notes:
- use the same options for DB as for task #2, but user account does not exists now.
- import data first

# Spring Advanced: Task 2

Notes:
- use MySQL, check db.properties for details
- credentials from users.json file username == password
    - admin/admin
    - manager/manager
    - several/several
- import data for users and other entities:
    - auditoriums.json
    - events.json
    - users.json
    - assigned-events.json

Task description:

+1. Configure Spring Security for ticket booking web application - add DelegatingFilterProxy to web.xml
2. Configure access control via security namespace.
    All application operations should be accessible to users with role RESGISTERED_USER only.
    Getting booked tickets for particular event should be accessible only to users with role BOOKING_MANAGER.
    Add two new fields to User entity - password and roles. Roles field should contain comma-separated list of user roles.
    All users in database should have REGISTERED_USER role by default.
    Create several test users with additional BOOKING_MANAGER role.
3. Implement
    +form-based login via security namespace, 
    +add custom login page,
    +configure DAOAuthenticationProvider
    +and UserDetailsService to load user data from database.
    +Configure logout filter.
4. +Configure Remember-Me authentication.
5. +Implement password encoding during authentication.


# Spring Advanced: Task 1

## Notes:
- use MySQL, check db.properties for details
- files to import (use "Import section from main menu"):
    - auditoriums.json
    - events.json
    - users.json
    - assigned-events.json
    - tickets.json


## Task description:
1. Based on the codebase of previous hometasks, create a web application, configure Spring MVC application context and dispatcher servlet.
2. For all BookingFacde operations implement Spring MVC annotation-based controllers.
3. For operations that return one or several entites as a result (e.g. getUserByEmail, getUsersByName, getBookedTickets) implement simple views rendered via Velocity template engine. Use InternalResourceViewResolver for view resolving procedure.
4. For operations, that return list of booked tickets (by event, or by user) implement alternative controllers, that will return result as PDF document. Map this controller to a specific value of Accept request header  - Accept=application/pdf
5. Implement batch loading of users and events into system. In order to do this, create controller which accepts multipart file upload, parses it and calls BookingFacade methods to add events and users into the system. The format of the file (JSON, XML, ...) is up to you as long as you can implement the correct parsing procedure.
6. Implement generic exception handler which should redirect all controller exceptions to simple Velocity view, that just prints exception message.

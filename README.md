# Spring Advanced: Task 1

## Notes:
- use MySQL, check db.properties for details
- files to import: users.json, events.json, auditoriums.json

### Doing:
- Moving to velocity:
  - http://o7planning.org/web/fe/default/en/document/132382/spring-mvc-and-velocity-tutorial
  - http://stackoverflow.com/questions/9989472/spring-mvc-velocity-template-structure
  - http://jatechs.blogspot.com.by/2013/02/integrating-velocity-with-spring-mvc.html


## Task description:
1. Based on the codebase of previous hometasks, create a web application, configure Spring MVC application context and dispatcher servlet.
2. For all BookingFacde operations implement Spring MVC annotation-based controllers.
3. For operations that return one or several entites as a result (e.g. getUserByEmail, getUsersByName, getBookedTickets) implement simple views rendered via Velocity template engine. Use InternalResourceViewResolver for view resolving procedure.
4. For operations, that return list of booked tickets (by event, or by user) implement alternative controllers, that will return result as PDF document. Map this controller to a specific value of Accept request header  - Accept=application/pdf
5. Implement batch loading of users and events into system. In order to do this, create controller which accepts multipart file upload, parses it and calls BookingFacade methods to add events and users into the system. The format of the file (JSON, XML, ...) is up to you as long as you can implement the correct parsing procedure.
6. Implement generic exception handler which should redirect all controller exceptions to simple Velocity view, that just prints exception message.

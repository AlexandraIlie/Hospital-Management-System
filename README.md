# Hospital-Management-System

Implementation with JDBC of a hospital management system. This should be able to be imported into the DBMS PostgreSQL 10.
When generating the database schema, integrity conditions are taken into account in order to ensure the consistency of the database:
-declaratives that are defined in the ”CREATE TABLE statement”
-procedures that are implemented via TRIGGER
In this task, I had to manage the design of a service interface and determine interfaces for new persistent classes.
The persistence of the corresponding interfaces is expressed by the fact that they derive from the interface PersistentObject
To successfully complete the task, the implementation and the developed database accesses is tested with provided JUnit test cases.
All classes that affect the test program are defined in the package hshn.mi.pdbg.basicservice

The Entity–Relationship model can be seen below:

![image](https://user-images.githubusercontent.com/43239611/94337935-de030900-fff6-11ea-870c-fa7f56aa9c70.png)

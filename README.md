Travel agency project

Technology stack:

Build tool: Maven. Add plugins for static code analysis (findbugs and/or pmd) and checkstyle.
Application container: Spring IoC (Spring Framework version 5.x).
Logging framework: SLF4J.
Unit testing framework: JUnit (version 4.x is preferred, but you can use 5.x).
Mocking framework: Mockito (heavy usage of all annotations: @Mock, @Spy, etc.).
Database: PostgreSQL (version 9.x is preferred, but you can use 10.x).
Database connection pooling: HikariCP.
Use Flyway as version control tool for database. Don't forget to add initial scripts to resources.
Continuous Integration server: Jenkins LTS (see CI section for more info).

Continuous Integration: 

Configure Jenkins security (install Role strategy plugin). Remove anonymous access. Create administrator user (all permissions) and developer user (build job, cancel builds). Add Jenkins credentials to Readme file in your repository.
Configure Jenkins build job to checkout your repository from gitlab (use either webhook or pooling interval).
Make Maven build your project and execute unit tests.
Install SonarQube. Configure Jenkins to use local SonarQube installation. Analyze your source code with SonarQube after Maven builds your project. Use JaCoCo for code coverage.



Tech requirements:

Clear layered structure should be used with responsibilities of each application layer defined.
All Spring-related configuration should be done using Java config.
Spring JDBC Template should be used for data access. 
Repository layer should be tested using integration tests with an in-memory embedded database.

Jenkins user Admin:
log - admin
pass - admin

Jenkins user Developer:
log - developer
pass - developer

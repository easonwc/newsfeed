# This is a News Feed simple web application.

### Build

* The pom.xml file points to a mysql database that will be used for testing.
* Be sure to edit the sql.* properties in the pom.xml file
* Use Maven to build the project
    * mvn clean install site

### Deploy

* Copy the .war file to a servlet container.
* Copy the mysql-connector-java-5.1.38.jar file to the servlet container java
library path - for Apache Tomcat the /lib folder will do
* Make sure the servlet container has a database context referenced by
jdbc/NewsFeedDB
* An example is provided for Apache Tomcat context.xml under
src/main/resources/context.xml

### Run

* Start the servlet container
* Access the NewsFeed context under the web page index.html
* If you did not change the .war file name produced by the build the url will be:
http://{your.domain}/newsfeed-1.0-SNAPSHOT
* If you did change the .war file  name the url will be:
http://{your.domain}/{war_file_name}
# This is a News Feed simple web application.

### Build

* The pom.xml file points to a mysql database that will be used for testing.
* Be sure to edit the sql.* properties in the pom.xml file
* Use Maven to build the project
    * mvn clean install site

### Deploy

* Copy the .war file to a servlet container.
* Make sure the servlet container has a database context referenced by
jdbc/NewsFeedDB
* An example is provided for Apache Tomcat context.xml under
src/main/resources/context.xml

### Run

* Access the NewsFeed context under the web page index.html
* If you did not change the .war file name produced by the build the url will be:
http://{your.domain}/newsfeed-1.0-SNAPSHOT
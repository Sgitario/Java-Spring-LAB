Java-Spring-LAB
===============

This project is a reference Web Application working as a personal laboratory in order to test spring features. 

Spring api for this application:
- spring-context 4.0.1.RELEASE
- spring-webmvc 4.0.1.RELEASE
- spring-web 4.0.1.RELEASE

1.- Priorities of dependencies resolution

When there are both implementations of a concrete service interface, one defined using annotation and another one using spring configuration. Which implementation will Spring use to resolve that interface?

Let's start:

- In Java-Spring-LAB/src/main/com/java/spring/lab/controllers/ServiceController.java, there is a field using an autowired annotation to resolve the Service implementation.

- In Java-Spring-LAB/src/main/com/java/spring/lab/services/Service.java is the interface to resolve.

- In Java-Spring-LAB/src/main/com/java/spring/lab/services/impl/NameService.java is the implementation that is resolved by autoscan components (by including the Service annotation).

- In Java-Spring-LAB/src/main/resources/spring/mvc-config.xml, there is a "service" bean with MyNameService reference implementation which is in Java-Spring-LAB/src/main/com/java/spring/lab/services/impl/MyNameService.java.


In the current status, with the commented bean in mvc-config.xml, the NameService class is properly resolved due to autoscan components tag is set in such spring properties.

When user uncomments the bean of MyNameService (important to specify the id), the MyNameService class is used insteads of NameService class.

Therefore, the XML settings prioritizes its configuration againts annotations.
 
===============
Reactive Design
===============
Same as above but with reactive non-blocking api design.

http://docs.spring.io/spring-framework/docs/5.0.0.M1/spring-framework-reference/html/web-reactive.html

https://spring.io/blog/2016/07/28/reactive-programming-with-spring-5-0-m1

https://github.com/bclozel/spring-boot-web-reactive
# caching-practise
SpringBoot ehcaching

Redis/Cache

video1 :: https://www.youtube.com/watch?v=HBmlNMGh9O0
video2 :: https://www.youtube.com/watch?v=IwYEdZOmY6g

Note :: Don't use caching for all module of your application, use for which frequently used


What is cache :: It is a Temp memory, it is used between Server(app) and database. 

What is Caching :: It is a short term memory that has limited space but is faster and contains the most recently accessed item. 

example:: Suppose you prepare for dinner ever day and you need ingredients for food preparation. Whenever you prepare food you go to your nearest shop to by these ingredients? But to do this everyday take time , instide of this you can buy some food for rest of the week and put it into your refrigerator work like a cache/local store/temporary store. The cooking time get reduced if ingredients already present.

what data should be cached ?
:: the data that do not changed frequentl. 
:: the frequently used read query in which result does not change in each call, at least for a period.

 
Q) Is there is any area when data  does not change frequently.  

--Example Application -- 
Spring Boot Redis as Database

#1. Create one Spring Boot Startre Project 
Name : SpringBootRedisAsDb
Dep: Redis, Lombok
pom.xml

spring-boot-starter-data-redis, lombok

#2. application.properties 
spring.redis.host=localhost 
spring.redis.port=6379

#3. Model class
Student (stdId, stdName, std Fee)
note:- One redis template -> one entity if there multiple entitys are there we have to create redis template for each.

#4. Spring Config File 
a) Redis Connection
b) Redis Template
I

List of annotations of rdis
-> @Cacheable  : get one employee data
-> @Cacheput   : update data
-> @CacheEvict : delete data (value ="employee", key="#empId") key should be same with parameter name and value may be anything but use entity name 
Note :- while deleting some data from db if assosiation are coming in your way you can evict it by using allEntries = true instide of key


=================== Steps to add redis to your application  ===================================
1) add redis dependency
2) add @EnableCaching in sprinbootApplication class
3) add configuration thing in application.properties or yml file
   spring.cache.type=redis
   spring.cache.redis.cache-null-value=true
   spring.cache.redis.time-to-live=6000(1minute)

4) add the following annotation on the method on the basis of method requirement
   
   @Cacheable :- Add this annotation on the method which return the list<objet>, all data
      syntax :: @Cacheable(value = "employees") value could be anything 
      syntax :: @Cacheable(value = "employees", key ="#empId") key should be same with given parameter variable name

   @Cacheput : This will use to update the data in redis cached on the basis of id.
      syntax :: @Cacheput(value = "employees", key = "#empId")

   @CacheEvict : This will use to delete something from cache on the basis of given ID.  
      syntax :: @CacheEvict(value = "employees", key = "#empId")

Syntax:
@Cache_(value="CACHE-REG", key="#Variable")

Note :: Most important the redis server should be open




//Spring Boot In Memory Caching
====================== Steps to implement in memory application caching =================================
1) add the dependency in pom.xml
   <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-cache</artifactId>
      </dependency>
2) add annotation on Application main class.
   @EnableCaching

3) add the annotation on the methods in which we want to perform caching like
  -> getUser() -> use @Cachable       --> @CacheEvict(cacheNames = {"Student"}, key = "#studentId")
  -> getAllUsers() -> use @Cachable   --> @Cacheable(cacheNames = {"Student"})
  -> deleteUser() -> use @CacheEvict  --> @CacheEvict(cacheNames = {"Student"}, key = "#studentId")
  -> updateUser()  -> use @CachePut   --> @CachePut(cacheNames = {"Student"}, key = "#student.studentId")


Note:- Don't use key on the methods where we're getting data in bulk or wherever we're not having parameters/parameter of that method.
      Example :- getAllStudens() here we're not having any parameter
       @Cacheable(cacheNames = {"Student"})

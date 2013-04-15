Struts 1(MVC) + Hibernate + Spring 

Database: MySQL

Goal: basic CRUD with SSH

P.S. This a module from my previous repo (https://github.com/neatpilot/drp).

Version 3:
Deploy it with Spring
1. web.xml -- org.springframework.web.context.ContextLoaderListener
           -- org.springframework.web.filter.CharacterEncodingFilter
           -- org.springframework.orm.hibernate3.support.OpenSessionInViewFilter
2. struts-config.xml	-- org.springframework.web.struts.DelegatingActionProxy
3.-------Entity-------------------------------Injection------------
    SessionFactory                        hibernate.cfg.xml
    DAO                                   sessionFactory
    Service                               dao
    Action                                service
	
    DelegationActionProxy is associated with action's path name
	

Version 2:
Deploy it with Hibernate;
Use open session in view to control hibernate session. 
--> open it in filter and close it in filter too

Version 1:
Deploy previous item maintenance module with Struts1.
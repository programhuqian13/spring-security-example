package org.tony.spring.security.example.helloworld.security.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
/***
 * 定义web的拦截器 实例化
 * @author Tony
 *相当于web.xml
<filter>
    <filter-name>springSecurityFilterChain</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
 
<filter-mapping>
    <filter-name>springSecurityFilterChain</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	
}

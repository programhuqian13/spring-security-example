package org.tony.spring.security.custom.login.form.configure;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/****
 * 指定一个初始化类去注册springSecurityFilter 到 应用中
 * @author Tony
	 *<filter>
	    <filter-name>springSecurityFilterChain</filter-name>
	    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	</filter>
	 
	<filter-mapping>
	    <filter-name>springSecurityFilterChain</filter-name>
	    <url-pattern>/*</url-pattern>
	</filter-mapping>
 */
public class SpringWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	
}

package org.tony.spring.security.example.helloworld.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.configuration.ObjectPostProcessorConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/****
 * 添加spring security的配置类
 * @author Tony
 *
 * The first and foremost step to add spring security in our application
 * is to create Spring Security Java Configuration. This configuration 
 * creates a Servlet Filter known as the springSecurityFilterChain 
 * which is responsible for all the security 
 * (protecting the application URLs, validating submitted username 
 * and passwords, redirecting to the log in form, etc) 
 * within our application.
 */

@Configuration   //spring的配置   相当于加载相应的bean/controller .etc
@EnableWebSecurity   //添加这个注解是定义spring的security的配置    @Import({ WebSecurityConfiguration.class, 
						//ObjectPostProcessorConfiguration.class,SpringWebMvcImportSelector.class })
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	/****
	 * 
	 * 相当于xml中配置的 <http auto-config="true" >
        <intercept-url pattern="/" access="permitAll" />
        <intercept-url pattern="/home" access="permitAll" />
        <intercept-url pattern="/admin**" access="hasRole('ADMIN')" />
        <intercept-url pattern="/dba**" access="hasRole('ADMIN') and hasRole('DBA')" />
        <form-login  authentication-failure-url="/Access_Denied" />
    </http>
  
    <authentication-manager >
        <authentication-provider>
            <user-service>
                <user name="huqian"  password="abc123"  authorities="ROLE_USER" />
                <user name="tony" password="abc123" authorities="ROLE_ADMIN" />
                <user name="johnsmith"   password="123456" authorities="ROLE_ADMIN,ROLE_DBA" />
            </user-service>
        </authentication-provider>
    </authentication-manager>
	 */
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("huqian").password("abc123").roles("USER");
		auth.inMemoryAuthentication().withUser("tony").password("abc123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("johnsmith").password("123456").roles("ADMIN","DBA");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//配置相应链接的权限    ‘/’，‘home’是不需要认证的  所有的人都可以访问，"/admin/**"只能是admin的角色才能访问 "/db/**"只能是admin和dba的角色才能访问
		//formLogin()提供登陆界面进行认证，这里可以自定义登陆界面，and().exceptionHandling().accessDeniedPage("/Access_Denied");定义当报403的时候跳转的界面
		http.authorizeRequests().antMatchers("/","/home").permitAll().antMatchers("/admin/**").access("hasRole('ADMIN')")
		.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')").and().formLogin().and().exceptionHandling()
		.accessDeniedPage("/Access_Denied");
	}
}

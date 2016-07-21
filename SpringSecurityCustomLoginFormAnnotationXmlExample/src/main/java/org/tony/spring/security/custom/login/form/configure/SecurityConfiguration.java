package org.tony.spring.security.custom.login.form.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/***
 * 添加一个spring security config
 * @author Tony
 * 第一步添加spring security的配置去创建Spring Security Java Configuration
 * 这个配置创建一个servlet filter 作为 springSecurityFilterChain，这个filter
 * 是负责所有的安全机制(保护应用的的URL，用户名和密码的认证，重定向到登录，等)
 * 
 * 
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	/****
	 * 在内存中进行用户管理  
	 * AuthenticationManagerBuilder 认证管理容器 ，允许在内存中创建，LDAP 认证，还有就是JDBC认证
	 * @param auth   认证管理容器
	 * @throws Exception   异常
	 */
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("huqian").password("huqian").roles("USER");
		auth.inMemoryAuthentication().withUser("tony").password("Tony").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("dba").password("dba123456").roles("ADMIN","DBA");
	}
	
	/****
	 * 重写父类的方法   configure(HttpSecurity http) 默认的配置是：
	 * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
	 * loginPage指定自定义的登录界面  创建一个登录界面
	 * 接受ssoId作为用户名和密码的请求参数
	 * 调用csrf()是spring security 4的默认操作，自定义模板
	 * 如果你想禁用掉，可以用csrf().disable()方法进行禁用，但是不建议这样做
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/home").permitAll().antMatchers("/admin/**").access("hasRole('ADMIN')")
		.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')").and().formLogin().loginPage("/login")
		.usernameParameter("ssoId").passwordParameter("password").and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denid");
	}
}

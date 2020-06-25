package com.javacloud.assetmanagenmentroot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.javacloud.assetmanagenmentroot.BootAuthEntryPoint;
import com.javacloud.assetmanagenmentroot.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringBootSecurityJWTConfigurer extends WebSecurityConfigurerAdapter {

	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Autowired
	private BootAuthEntryPoint bootAuthEntryPoint;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired 
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
		
	} // End of configureGlobal()
	
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable()
		//Permit access to all
		    .authorizeRequests()
		    .antMatchers("/api/user/login","/api/user/forgetpwd")
		    .permitAll()
		    .and()	 
		    
		//Permit access to only user
		    
//		    .authorizeRequests()
//		    .antMatchers("/api/request/addrequest")
//		    .hasRole("USER")
//		    .and()
		    .authorizeRequests()
		    .antMatchers("/api/request/addrequest/{id}")
		    .hasRole("USER")
		    .and()  
		    .authorizeRequests()
		    .antMatchers("/api/user/getusers/{id}")
		    .hasAnyRole("USER","ÄDMIN")
		    .and()
		    .authorizeRequests()
		    .antMatchers("/api/user/updateuser")
		    .hasAnyRole("USER","ÄDMIN")
		    .and()
		    
		 //Permit access only to admin   
		 //User class API  
		    .authorizeRequests()
		    .antMatchers("/api/user")
		    .hasRole("ADMIN")
		    .and()		    
		    .authorizeRequests()
		    .antMatchers("/api/user/{pageNo}/{usersPerPage}")
		    .hasRole("ADMIN")
		    .and()	    
		    .authorizeRequests()
		    .antMatchers("/api/user/{pageNo}/{usersPerPage}/{userFieldName}")
		    .hasRole("ADMIN")
		    .and()		
		    .authorizeRequests()
		    .antMatchers("/api/user/adduser","/api/user/deleteuser/{id}")
		    .hasRole("ADMIN") 
		    .and()

		  //Assets class API 
		    .authorizeRequests()
		    .antMatchers("/api/assets/getassets/{asset_id}")
		    .hasRole("ADMIN")  
		    .and()     		    	        
		    .authorizeRequests()
		    .antMatchers("/api/assets/{pageNo}/{assetsPerPage}/{assetFieldName}",
		    "/api/assets/{pageNo}/{assetsPerPage}")
		    .hasAnyRole("ADMIN","USER")  
		    .and()				   
		    .authorizeRequests()
		    .antMatchers("/api/assets")
		    .hasAnyRole("ADMIN","USER")
		    .and()	    
		    .authorizeRequests()
		    .antMatchers("/api/assets/addasset","/api/assets/deleteasset/{asset_id}")
		    .hasRole("ADMIN")
		    .and()
		    .authorizeRequests()
		    .antMatchers("/api/assets/updateasset")
		    .hasRole("ADMIN")
		    .and()
		    
		    //Request class API
		    
		    .authorizeRequests()
		    .antMatchers("/api/request/getrequest/{request_id}")
		    .hasRole("ADMIN")  
		    .and()	    		    
		    .authorizeRequests()
		    .antMatchers("/api/request/{pageNo}/{requestPerPage}")
		    .hasRole("ADMIN")  
		    .and()		    
		    .authorizeRequests()
		    .antMatchers("/api/request/{pageNo}/{requestPerPage}/{requestFieldName}")
		    .hasRole("ADMIN")  
		    .and()
		    .authorizeRequests()
		    .antMatchers("/api/request")
		    .hasRole("ADMIN")
		    .and()
		    .authorizeRequests()
		    .antMatchers("/api/request/get/{status}")
		    .hasRole("ADMIN")
		    .and()
		    .authorizeRequests()
		    .antMatchers("/api/request/updaterequest/{id}")
		    .hasRole("ADMIN")  
		   
		    
		    .and()

		    .authorizeRequests()
		    .antMatchers("/api/request/getrequestAllocated/{pageNo}/{itemsPerPage}")
		    .hasRole("ADMIN")
		    .and()
		    
		    .authorizeRequests()
		    .antMatchers("/api/request/getrequestAllocated/{pageNo}/{itemsPerPage}/{fieldName}")
		    .hasRole("ADMIN")
		    .and()
		    
		    .authorizeRequests()
		    .antMatchers("/api/request/getrequestRejected/{pageNo}/{itemsPerPage}")
		    .hasRole("ADMIN")
		    .and()
		    
		    .authorizeRequests()
		    .antMatchers("/api/request/getrequestRejected/{pageNo}/{itemsPerPage}/{fieldName}")
		    .hasRole("ADMIN")
		    
	        .and()
		    
		    .authorizeRequests()
		    .antMatchers("/api/request/getrequestPending/{pageNo}/{itemsPerPage}")
		    .hasRole("ADMIN")
		    .and()
		    
		    .authorizeRequests()
		    .antMatchers("/api/request/getrequestPending/{pageNo}/{itemsPerPage}/{fieldName}")
		    .hasRole("ADMIN")
		    	    
		    
		    .anyRequest()
		    .authenticated()
		    .and()
		    
		    .exceptionHandling()
		    .authenticationEntryPoint(bootAuthEntryPoint)
		    .and()
		    .sessionManagement()
		    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
		    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		
	}// End of configure()
	
	
}

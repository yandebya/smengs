
  package com.gessco;
  
  import javax.sql.DataSource;
  
  import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
  import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder; 
  import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
  import org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
  import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
  
  @Configuration
  @EnableGlobalMethodSecurity(securedEnabled = true)
  @EnableWebSecurity public class ConfigSecurite extends WebSecurityConfigurerAdapter {
  
  @Autowired 
  private CustomLoginSuccesHandler successHandler;
  
  @Autowired 
  public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
	  auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passEn())
	  .usersByUsernameQuery("select username as principal, password as credentials, true from Utilisateur where username=?")
		//.authoritiesByUsernameQuery("select user_id as principals, role_id as role from users_roles where user_id = ?")
		.authoritiesByUsernameQuery("SELECT u.username, r.role FROM Utilisateur u INNER JOIN users_roles ur ON (u.id_utilisateur = ur.user_id) INNER JOIN PROFIL r ON (ur.role_id = r.id_role)  WHERE u.username = ?")
		.rolePrefix("ROLE_");
  
  }
  @Bean
  public PasswordEncoder  passEn() {
	  return new BCryptPasswordEncoder();
  }

  @Override protected void configure(HttpSecurity http) throws Exception {
  
		http.authorizeRequests()
				.antMatchers("/css/**","/js/**","/images/**","/img/**","/video/**","/plugins/**","/scripts/**","/font-awesome/css/**","/font-awesome/fonts/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/index/homeSite").permitAll()
				.antMatchers("/index/listeAppSite").permitAll()
				.antMatchers("/index/listeSerSite").permitAll()
				.antMatchers("/index/promotion").permitAll()
				.antMatchers("/index/contact").permitAll()
				.antMatchers("/index/footerEquipe").permitAll()
				.antMatchers("/index/footerFaq").permitAll()
				.antMatchers("/index/footerService").permitAll()
				.antMatchers("/index/getImage_home").permitAll()
				.antMatchers("/creationAbonne").permitAll()
				.antMatchers("/creationAbonne").permitAll()
				.antMatchers("/index/getPhotiService").permitAll()
				.antMatchers("/index/getImage_app").permitAll()
				.antMatchers("/index/getImage_app2").permitAll()
			    .antMatchers("/index/getImage").permitAll()
		
				
			
				.anyRequest().authenticated()
			.and()
				.csrf().disable() 
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.successHandler(successHandler)
			.and()
				.logout()
				.invalidateHttpSession(true)
				.logoutUrl("/index/logout")
				.permitAll();
	}
}

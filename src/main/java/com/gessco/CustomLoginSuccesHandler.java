package com.gessco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.gessco.dao.UtilisateurRepository;
import com.gessco.entite.Utilisateur;
//import com.gessco.controlleur.UtilisateurControlleur;
@Configuration
public class CustomLoginSuccesHandler extends SimpleUrlAuthenticationSuccessHandler  {

	@Autowired
	private UtilisateurRepository ur;

	//private UtilisateurControlleur uc;
	
public void handle(HttpServletRequest request,HttpServletResponse response, Authentication authentication) 
			throws IOException {
		String targetUrl=determineTargetUrl(authentication);
	
		if(response.isCommitted()) {
			return;
		}
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	public String determineTargetUrl(Authentication authentication) {
		String url="/login?error=true";
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		
		
		//Utilisateur u = uc.getLogedUserUc(request);
		
		//boolean b = u.isActived();
		for(GrantedAuthority a: authorities) {
			roles.add(a.getAuthority());
			
		}
		
		if(roles.contains("ROLE_SUPERADMIN")) {
			url="/index/home";
		}else if(roles.contains("ROLE_ADMIN")) {
			url="/";
		}
		else {
			url="/forbidden";
		}
		
		return url;
		
	}
}

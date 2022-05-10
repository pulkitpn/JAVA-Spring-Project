package com.genpact.shoppingcart.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.genpact.shoppingcart.model.Role;
import com.genpact.shoppingcart.model.User;
import com.genpact.shoppingcart.repository.RoleRepository;
import com.genpact.shoppingcart.repository.UserRepository;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

	
	
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;

	
	public RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		OAuth2AuthenticationToken token=(OAuth2AuthenticationToken) authentication;
		String email=token.getPrincipal().getAttributes().get("email").toString();
		if(userRepository.findUserByEmail(email).isPresent()) {
			
		}else {
			User user=new User();
			user.setFirstname(token.getPrincipal().getAttributes().get("given_name").toString());
			user.setLastname(token.getPrincipal().getAttributes().get("family_name").toString());
			user.setEmail(email);
			List<Role> roles=new ArrayList<>();
			roles.add(roleRepository.findById(2).get());
			user.setRoles(roles);
			userRepository.save(user);
			
		}
		redirectStrategy.sendRedirect(request, response, "/");
		
	}
	
	
	
}
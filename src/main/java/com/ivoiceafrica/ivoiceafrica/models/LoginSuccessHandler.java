package com.ivoiceafrica.ivoiceafrica.models;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetail;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;

 
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
	@Autowired HttpSession session;
	
	@Autowired CustomUserDetailService customUserDetailService ;
	
	/**
	 * This Class extends an implementation of
	 *  AuthenticationSuccessHandler, such as 
	 *  SavedRequestAwareAuthenticationSuccessHander
	 */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
         
        String redirectURL = request.getContextPath();
         
        String role = "";
        String userName = "";
        if (userDetails.hasRole("ROLE_ADMIN")) {
        	role = "ROLE_ADMIN";
            redirectURL = "/admin-dashboard";
            userName = userDetails.getUsername();
        } else if (userDetails.hasRole("ROLE_SUPERVISOR")) {
        	role = "ROLE_SUPERVISOR";
            redirectURL = "/admin-dashboard";
            userName = userDetails.getUsername();
        } 
        else if (userDetails.hasRole("ROLE_CLIENT")) {
        	role = "ROLE_CLIENT";
            redirectURL = "/client-dashboard";
            userName = userDetails.getUsername();
        } 
        else if (userDetails.hasRole("ROLE_FREELANCER")) {
        	role = "ROLE_FREELANCER";
            redirectURL = "/freelancer-dashboard";
            userName = userDetails.getUsername();
        }
        
        Optional<User> user = customUserDetailService.findFirstUserByUsername(userName);
        
        session.setAttribute("userId", userName);
        session.setAttribute("user", user.get());
        session.setAttribute("userRole", role);
        
        System.out.println("===>>> User(session): "+user.get());
        System.out.println("===>>> UserName: "+userName);
        System.out.println("===>>> User has authorities: " + userDetails.getAuthorities());
         
        response.sendRedirect(redirectURL);
         
    }
 
}
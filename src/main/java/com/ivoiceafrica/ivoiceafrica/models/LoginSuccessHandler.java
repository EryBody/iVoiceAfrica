package com.ivoiceafrica.ivoiceafrica.models;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetail;

 
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
	
	/**
	 * This Class that extends an implementation of
	 *  AuthenticationSuccessHandler, such as 
	 *  SavedRequestAwareAuthenticationSuccessHander
	 */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
         
        String redirectURL = request.getContextPath();
         
        if (userDetails.hasRole("ROLE_ADMIN")) {
            redirectURL = "/admin/admin-home";
        } else if (userDetails.hasRole("ROLE_USER")) {
            redirectURL = "/index";
        } 
         
        response.sendRedirect(redirectURL);
         
    }
 
}
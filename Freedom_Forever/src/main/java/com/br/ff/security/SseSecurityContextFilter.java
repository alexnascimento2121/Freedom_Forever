package com.br.ff.security;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SseSecurityContextFilter extends OncePerRequestFilter{
	
	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws ServletException, IOException {
	        if (request.getRequestURI().contains("/answers/chat/stream")) {
	            SecurityContext context = SecurityContextHolder.getContext();
	            request.setAttribute("SPRING_SECURITY_CONTEXT", context);
	        }
	        chain.doFilter(request, response);
	    }
	
}

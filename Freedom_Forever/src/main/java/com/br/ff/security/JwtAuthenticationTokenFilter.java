package com.br.ff.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.ff.services.UsersService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    
    // Remover anotações @Autowired e usar injeção via construtor
    private final UsersService usersService;
    private final JwtUtil jwtTokenUtil;
    
    // Lista de endpoints que aceitam token via query parameter
    private static final List<String> SSE_ENDPOINTS = Arrays.asList(
        "/answers/chat/stream"
    );
    
    // Construtor para injeção de dependências
    public JwtAuthenticationTokenFilter(UsersService usersService, JwtUtil jwtTokenUtil) {
        this.usersService = usersService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        
        logger.debug("Interceptando requisição: {}", request.getRequestURI());
        
        String token = null;
        String username = null;
        
        // Primeiro tenta extrair o token do header Authorization
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        } 
        // Se não encontrou no header e é um endpoint SSE, tenta extrair do query parameter
        else if (isSSEEndpoint(request.getRequestURI())) {
            token = request.getParameter("token");
            if (token == null) {
                logger.debug("Token JWT ausente no header e no query parameter");
            }
        } else {
            logger.debug("Token JWT ausente ou sem prefixo 'Bearer '");
        }
        
        // Se encontrou um token, valida e configura a autenticação
        if (token != null) {
            try {
                username = jwtTokenUtil.getUsernameFromToken(token);
                
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = usersService.findByUsername(username);
                    
                    if (jwtTokenUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = 
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        
                        logger.debug("Usuário extraído do token: {}", username);
                    }
                }
            } catch (Exception e) {
                logger.error("Erro ao processar token JWT: {}", e.getMessage());
            }
        }
        
        chain.doFilter(request, response);
    }
    
    private boolean isSSEEndpoint(String uri) {
        return SSE_ENDPOINTS.stream().anyMatch(uri::equals);
    }
    
    // Métodos setter mantidos para compatibilidade, mas não são necessários com injeção via construtor
    public void setUsersService(UsersService usersService) {
        // Este método não deve ser usado quando a injeção é feita via construtor
        logger.warn("setUsersService chamado, mas a injeção já foi feita via construtor");
    }

    public void setJwtTokenUtil(JwtUtil jwtTokenUtil) {
        // Este método não deve ser usado quando a injeção é feita via construtor
        logger.warn("setJwtTokenUtil chamado, mas a injeção já foi feita via construtor");
    }
}

package com.mphasis.parent.configuration;

import com.mphasis.parent.utility.JwtUtil;
import com.mphasis.parent.entity.UserDetails;
import com.mphasis.parent.service.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
 
import java.io.IOException;
 
public class JwtAuthenticationFilter extends OncePerRequestFilter {
 
    private final JwtUtil jwtUtil;
    private final UserDetailService UserDetailService;
 
    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailService userDetailService) {
        this.jwtUtil = jwtUtil;
        this.UserDetailService = userDetailService;
    }
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
 
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
 
            try {
                if (jwtUtil.isTokenValid(token)) {
                    String username = jwtUtil.extractUsername(token);
                    UserDetails userDetails = new UserDetails(username, null);
 
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, null);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                System.err.println("Invalid JWT Token: " + e.getMessage());
            }
        }
 
        filterChain.doFilter(request, response);
    }
}
package com.ecommerce.auth_service.util;

import java.io.IOException;
// import java.util.HashMap;
// import java.util.Enumeration;
import java.util.List;
// import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

                /*
                    System.out.println("----- HTTP REQUEST & RESPONSE DETAILS -----");
                    System.out.println("URI       : " + request.getRequestURI());
                    System.out.println("Method    : " + request.getMethod());
                    System.out.println("Context   : " + request.getContextPath());
                    System.out.println("Query     : " + request.getQueryString());
                    System.out.println("Remote IP : " + request.getRemoteAddr());
                    Enumeration<String> headers = request.getHeaderNames();
                    while (headers.hasMoreElements()) {
                        String header = headers.nextElement();
                        System.out.println(header + " : " + request.getHeader(header));
                    }
                    System.out.println("Status Code : " + response.getStatus());
                    System.out.println("Content Type: " + response.getContentType());
                    response.getHeaderNames().forEach(name ->
                        System.out.println(name + " : " + response.getHeader(name))
                    );
                    System.out.println("----- END OF DETAILS -----");
                */
                 
        

        /*
            ----- HTTP REQUEST & RESPONSE DETAILS -----
                    URI       : /auth/login
                    Method    : POST
                    Context   :
                    Query     : null
                    Remote IP : 0:0:0:0:0:0:0:1
                    Content-Type : application/json
                    Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb2dlc2giLCJpYXQiOjE3NjczMTczOTcsImV4cCI6MTc2NzMxODI5N30.Zz1tXj9oMxBFomb_LtF9uiagFDX7CJdV2Y-NdiyfYSs
                    User-Agent : PostmanRuntime/7.51.0
                    Accept : *
                    Postman-Token : 7dcb1e19-749b-4c20-96c2-09f392050df4
                    Host : localhost:8080
                    Accept-Encoding : gzip, deflate, br
                    Connection : keep-alive
                    Content-Length : 51
                    Status Code : 200
                    Content Type: null
            ----- END OF DETAILS -----
         */
        


        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            try {
                Claims claims = jwtUtil.extractClaims(token);

                /*  In back How Claim Works
                Map<String, Object> claims = new HashMap<>();
                                            claims.put("role", "ADMIN");
                                            claims.put("userId", 101L);

                                            String token = Jwts.builder()
                                                    .setClaims(claims)
                                                    .setSubject("logesh")
                                                    .setIssuedAt(new Date())
                                                    .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                                                    .signWith(secretKey)
                                                    .compact();
                 */

                /*
                System.out.println("Claims Print");
                claims.forEach((key, value) -> 
                    System.out.println("Key: " + key + ", Value: " + value)
                );
                */

                String username = claims.getSubject();
                String role = claims.get("role", String.class);

                List<GrantedAuthority> authorities =
                        List.of(new SimpleGrantedAuthority(role));

                /*
                System.out.println("authorities Print");
                authorities.forEach(a ->
                        System.out.println(a.getAuthority())
                    );
                */

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username, null, authorities);

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

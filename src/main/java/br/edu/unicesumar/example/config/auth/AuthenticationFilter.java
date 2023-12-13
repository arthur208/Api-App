package br.edu.unicesumar.example.config.auth;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import br.edu.unicesumar.example.config.auth.jwt.Jwt;
import br.edu.unicesumar.example.config.auth.jwt.JwtTool;
import br.edu.unicesumar.example.service.UsersService;

public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtTool tokenTool;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String refreshTokenHeader = request.getHeader("Refresh-Token");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.replace("Bearer ", "");
            if (tokenTool.validateJwtToken(token)) {
                setSecurityContext(token, request);
            } else if (refreshTokenHeader != null && tokenTool.validateRefreshToken(refreshTokenHeader)) {
                // Renovar o token de acesso
                String username = tokenTool.getUsernameFromToken(new Jwt(refreshTokenHeader));
                UserDetails userDetails = usersService.loadUserByUsername(username);
                Jwt newAccessToken = tokenTool.generateToken(userDetails);

                // Configurar o novo token de acesso no contexto de segurança
                setSecurityContext(newAccessToken.getToken(), request);

                // Opcional: retornar o novo token de acesso no cabeçalho da resposta
                response.setHeader("New-Access-Token", newAccessToken.getToken());
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token!");
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setSecurityContext(String token, HttpServletRequest request) {
        String username = tokenTool.getUsernameFromToken(new Jwt(token));
        UserDetails userDetails = usersService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}

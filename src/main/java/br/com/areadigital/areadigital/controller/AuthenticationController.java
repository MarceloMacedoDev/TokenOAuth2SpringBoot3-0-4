package br.com.areadigital.areadigital.controller;

import br.com.areadigital.areadigital.config.security.TokenGenerator;
import br.com.areadigital.areadigital.dto.LoginDTO;
import br.com.areadigital.areadigital.dto.SignupDTO;
import br.com.areadigital.areadigital.dto.TokenDTO;
import br.com.areadigital.areadigital.model.User;
import br.com.areadigital.areadigital.service.UserManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Controlador responsável pelas operações relacionadas à autenticação do usuário.
 *
 * @RestController Indica que a classe é um controlador REST.
 * @RequestMapping Define o caminho raiz da API e o versionamento da mesma.
 * @RequiredArgsConstructor Cria um construtor com argumentos para todos os campos marcados com @NonNull ou final.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    /**
     * Gerador de tokens de autenticação.
     */
    private final TokenGenerator tokenGenerator;
    /**
     * Provedor de autenticação para autenticação baseada em banco de dados.
     */
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    /**
     * Serviço de gerenciamento de usuários.
     */
    private final UserManagerService service;
    /**
     * Provedor de autenticação para autenticação baseada em token JWT de atualização.
     */
    @SuppressWarnings("SpringQualifierCopyableLombok")
    @Qualifier("jwtRefreshTokenAuthProvider")
    private final JwtAuthenticationProvider refreshTokenAuthProvider;

    /**
     * Registra um novo usuário no sistema.
     *
     * @param request DTO contendo as informações do usuário a ser registrado.
     * @return ResponseEntity contendo um token de acesso.
     */
    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody SignupDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

// userDetailsManager.createUser(user);
        user = service.createUser(user);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(
                user,
                user.getPassword(),
                Collections.emptyList());

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    /**
     * Realiza a autenticação de um usuário através de credenciais de login.
     *
     * @param request DTO contendo as credenciais de login do usuário.
     * @return ResponseEntity contendo um token de acesso.
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO request) {
        Authentication authentication = daoAuthenticationProvider.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        request.getUsername(),
                        request.getPassword()));

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    /**
     * Renova o token de acesso de um usuário através de um token JWT de atualização.
     *
     * @param request DTO contendo o token JWT de atualização.
     * @return ResponseEntity contendo um novo token de acesso e o token JWT de atualização.
     */
    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody TokenDTO request) {
        Authentication authentication = refreshTokenAuthProvider.authenticate(
                new BearerTokenAuthenticationToken(request.getRefreshToken()));
        Jwt jwt = (Jwt) authentication.getPrincipal();

        return ResponseEntity.ok(
                Map.of(
                        "accessToken", jwt.getTokenValue(),
                        "refreshToken", request.getRefreshToken(),
                        "userDetails", jwt.getClaims().get("userDetails")));
    }
}





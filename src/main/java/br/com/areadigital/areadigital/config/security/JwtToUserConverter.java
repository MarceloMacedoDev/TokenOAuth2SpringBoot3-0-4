package br.com.areadigital.areadigital.config.security;

import br.com.areadigital.areadigital.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * A classe JwtToUserConverter é responsável por converter um JWT (JSON Web Token) em um objeto UsernamePasswordAuthenticationToken.
 * <p>
 * Esta classe é anotada com @Component para ser automaticamente registrada como um bean do Spring.
 */
@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    /**
     * Converte um JWT em um objeto UsernamePasswordAuthenticationToken.
     *
     * @param jwt O JWT a ser convertido.
     * @return O objeto UsernamePasswordAuthenticationToken representando o usuário autenticado.
     */
    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        User user = new User();
        user.setId(Long.valueOf(jwt.getSubject()));
        return new UsernamePasswordAuthenticationToken(user, jwt, Collections.emptyList());
    }
}



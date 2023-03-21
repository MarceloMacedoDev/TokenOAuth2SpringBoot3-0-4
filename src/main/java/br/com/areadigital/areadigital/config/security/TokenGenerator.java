package br.com.areadigital.areadigital.config.security;

import br.com.areadigital.areadigital.model.User;
import br.com.areadigital.areadigital.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


/**

 The TokenGenerator class is responsible for creating and returning access and refresh tokens based on a user's authentication credentials.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TokenGenerator {

    /**
     * The JwtEncoder used to encode access tokens.
     */
    private final JwtEncoder accessTokenEncoder;
    /**
     * The JwtEncoder used to encode refresh tokens.
     */
    @SuppressWarnings("SpringQualifierCopyableLombok")
    @Qualifier("jwtRefreshTokenEncoder")
    private final JwtEncoder refreshTokenEncoder;

    /**
     * Creates an access token based on the provided authentication.
     *
     * @param authentication The authentication object containing the user's credentials.
     * @return A string representing the access token.
     */
    private String createAccessToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder().issuer("myApp").issuedAt(now).expiresAt(now.plus(5, ChronoUnit.MINUTES)).subject(String.valueOf(user.getId())).build();

        String tokenValue = accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
        return tokenValue;
    }

    /**
     * Creates a refresh token based on the provided authentication.
     *
     * @param authentication The authentication object containing the user's credentials.
     * @return A string representing the refresh token.
     */
    private String createRefreshToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder().issuer("myApp").issuedAt(now).expiresAt(now.plus(30, ChronoUnit.DAYS)).subject(String.valueOf(user.getId())).build();

        return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    /**
     * Creates and returns an access token and refresh token based on the provided authentication.
     *
     * @param authentication The authentication object containing the user's credentials.
     * @return A TokenDTO object containing the user's ID, access token, and refresh token.
     * @throws BadCredentialsException if the authentication principal is not of type User.
     */
    public TokenDTO createToken(Authentication authentication) throws BadCredentialsException {
        if (!(authentication.getPrincipal() instanceof User user)) {
            throw new BadCredentialsException(MessageFormat.format("principal {0} is not of User type", authentication.getPrincipal().getClass()));
        }

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUserId(String.valueOf(user.getId()));
        tokenDTO.setAccessToken(createAccessToken(authentication));

        String refreshToken;
        if (authentication.getCredentials() instanceof Jwt jwt) {
            Instant now = Instant.now();
            Instant expiresAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now, expiresAt);
            long daysUntilExpired = duration.toDays();
            if (daysUntilExpired < 7) {
                refreshToken = createRefreshToken(authentication);
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = createRefreshToken(authentication);
        }
        tokenDTO.setRefreshToken(refreshToken);

        return tokenDTO;
    }
}